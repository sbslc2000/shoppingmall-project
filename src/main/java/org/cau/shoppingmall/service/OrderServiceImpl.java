package org.cau.shoppingmall.service;

import lombok.RequiredArgsConstructor;
import org.cau.shoppingmall.dto.orders.OrderForm;
import org.cau.shoppingmall.dto.orders.OrderDto;
import org.cau.shoppingmall.dto.orders.OrderedItemForm;
import org.cau.shoppingmall.entity.item.Item;
import org.cau.shoppingmall.entity.item.OrderedItem;
import org.cau.shoppingmall.entity.item.StockDetails;
import org.cau.shoppingmall.entity.order.*;
import org.cau.shoppingmall.entity.user.User;
import org.cau.shoppingmall.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{


    private final ItemRepository itemRepository;
    private final OrdersRepository orderRepository;
    private final OrderProcessRepository orderProcessRepository;
    private final PaymentMethodRepository paymentMethodRepository;
    private final UserRepository userRepository;
    private final OrderedItemRepository orderedItemRepository;
    private final StockDetailsRepository stockDetailsRepository;

    /*
        Order createOrder : 사용자에게 입력받은 정보를 토대로 주문을 등록한다.

        프로세스 로직:
         - "입금전" OrderProcess를 entity 가져오기
         - 결제 수단에 따른 PaymentMethod entity 가져오기
         - form 의 정보로 order, payment, cashReceipt Entity 생성, Order <-> User 연관관계 매핑
         - OrderedItem Entity 생성 -> Order 과 연관관계 매핑

         체크사항
         - stockDetails에 재고가 있는지
         - 서버로 들어온 사용 포인트가 user의 포인트보다 작은지

         db 업데이트 사항
         - user->shoppingmallData에서 구매 횟수 1 증가
         - user->shoppingmallData에서 포인트 ++
         - 각각의 item의 누적 판매량 증가
         - 각각의 item의 전체 재고량 감소
         - item -> stockDetails의 재고량 감소
 */
    @Override
    @Transactional
    public Orders create(OrderForm form, Long userId) {
        Optional<User> user = userRepository.findById(userId);

        if(user.isEmpty()) {
            throw new NoSuchElementException("사용자를 찾을 수 없습니다.");
        }


        OrderProcess orderProcess = orderProcessRepository.findById(1L).get();
        PaymentMethod paymentMethod = paymentMethodRepository.findById(form.getPaymentMethod()).get();




        int sumOfPrice = 0;
        //user 의 point를 확인한 후 sum of orderedItem.price - point 한 가격 주입하기
        if(user.getPoint() < form.getPointUsed()) {
            throw new IllegalArgumentException("유저의 포인트가 사용된 포인트보다 낮습니다.");
        }

        List<OrderedItem> orderedItemList = new ArrayList<>();
        for (OrderedItemForm itemForm : form.getOrderedItemList()) {
            //상품 정보를 가져옴
            Item item = itemRepository.findById(itemForm.getItemId()).orElseThrow(() ->
                    new NoSuchElementException("해당 상품을 찾을 수 없습니다."));

            //상품 세부 재고 정보를 가져와 실재 재고가 있는지 확인한다.
            StockDetails findStockDetails = stockDetailsRepository.findByItem_IdAndSize_IdAndColor_Id(itemForm.getItemId(), itemForm.getSizeId(), itemForm.getColorId())
                    .orElseThrow( () ->
                            new NoSuchElementException("해당하는 재고 정보가 존재하지 않습니다."));

            if(findStockDetails.getQuantity() < itemForm.getQuantity()) {
                throw new IllegalArgumentException("재고보다 구매량이 많습니다.");
            }


            sumOfPrice += item.getPrice();
            OrderedItem buildedOrderedItem = new OrderedItem().builder()
                    .colorId(itemForm.getColorId())
                    .itemId(itemForm.getItemId())
                    .sizeId(itemForm.getSizeId())
                    .exchangeFlag(false)
                    .returnFlag(false)
                    .reviewFlag(false)
                    .quantity(itemForm.getQuantity())
                    .build();

            orderedItemRepository.save(buildedOrderedItem);
        }

        CashReceipt cashReceipt = form.isCashReceiptFlag() ? form.createCashReceipt() : null;
        Payment payment = form.createPayment(paymentMethod, sumOfPrice - form.getPointUsed(), cashReceipt);
        Orders order = form.toEntity(user, orderProcess, payment);

        Orders result = orderRepository.save(order);

        //db update
        user.getShoppingmallData().changePointAmount(result.getPayment().getPointUsed());
        user.getShoppingmallData().raiseSalesCount();
        user.getShoppingmallData().changePointAmount(result.getPayment().getPaymentPrice()*0.05);

        for(OrderedItem oItem : orderedItemList) {
            Item item = itemRepository.findById(oItem.getItemId()).get();
            item.raiseSales(oItem.getQuantity());
            item.changeQuantity(-oItem.getQuantity());
            StockDetails stockDetails = stockDetailsRepository.findByItem_IdAndSize_IdAndColor_Id(oItem.getItemId(), oItem.getSizeId(), oItem.getColorId()).get();
            stockDetails.changeQuantity(-oItem.getQuantity());
        }

        return result;
    }

    @Override
    public OrderDto get(Long orderId,Long userId) throws Exception {
        //userRepository 에서 userId를 통해 user을 가져온 후 manager 이면 진행, findOrder 와 userId가 동일하면 진행, 아니라면 exception 발생

        Optional<Orders> findOrder = orderRepository.findById(orderId);

        if(findOrder.isPresent()) {
            Orders orders = findOrder.get();

            //if not userId same: exception
            User user = userRepository.findById(userId).orElse( () ->
                    new NoSuchElementException("해당하는 사용자가 없습니다."));

            if(user.getId().equals(userId) || user.getAuthority().getName().equals("관리자")) {
                OrderDto result = OrderDto.of(orders);
                return result;
            } else {
                throw new Exception("주문 정보를 확인할 권한이 없습니다.");
            }


        } else {
            throw new NoSuchElementException("해당하는 주문정보가 없습니다.");
        }
    }
}
