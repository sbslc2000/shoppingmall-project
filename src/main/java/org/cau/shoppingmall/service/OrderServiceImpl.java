package org.cau.shoppingmall.service;

import lombok.RequiredArgsConstructor;
import org.cau.shoppingmall.dto.orders.OrderForm;
import org.cau.shoppingmall.dto.orders.OrderDto;
import org.cau.shoppingmall.dto.orders.OrderItem;
import org.cau.shoppingmall.dto.orders.OrderedItemForm;
import org.cau.shoppingmall.entity.item.*;
import org.cau.shoppingmall.entity.order.*;
import org.cau.shoppingmall.entity.user.User;
import org.cau.shoppingmall.repository.*;
import org.cau.shoppingmall.repository.item.ColorRepository;
import org.cau.shoppingmall.repository.item.ItemRepository;
import org.cau.shoppingmall.repository.item.SizeRepository;
import org.cau.shoppingmall.repository.item.StockDetailsRepository;
import org.cau.shoppingmall.repository.order.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final ItemRepository itemRepository;
    private final OrdersRepository orderRepository;
    private final OrderProcessRepository orderProcessRepository;
    private final PaymentMethodRepository paymentMethodRepository;
    private final UserRepository userRepository;
    private final OrderedItemRepository orderedItemRepository;
    private final StockDetailsRepository stockDetailsRepository;
    private final ColorRepository colorRepository;
    private final SizeRepository sizeRepository;
    private final PaymentRepository paymentRepository;

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
    public OrderDto create(OrderForm form, Long userId) {
        User user = userRepository.findById(userId).orElseThrow( () ->
                new NoSuchElementException("사용자를 찾을 수 없습니다."));

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

            sumOfPrice += item.getPrice()*itemForm.getQuantity();
            OrderedItem buildedOrderedItem = new OrderedItem().builder()
                    .color(colorRepository.findById(itemForm.getColorId()).get())
                    .item(item)
                    .size(sizeRepository.findById(itemForm.getSizeId()).get())
                    .exchangeFlag(false)
                    .returnFlag(false)
                    .reviewFlag(false)
                    .quantity(itemForm.getQuantity())
                    .build();

            OrderedItem savedOrderedItem = orderedItemRepository.save(buildedOrderedItem);
            orderedItemList.add(savedOrderedItem);
        }

        CashReceipt cashReceipt = form.isCashReceiptFlag() ? form.createCashReceipt() : null;
        Payment payment = form.createPayment(paymentMethod, sumOfPrice - form.getPointUsed(), cashReceipt);
        Payment savedPayment = paymentRepository.save(payment);
        Orders order = form.toEntity(user, orderProcess, savedPayment);

        Orders savedOrder = orderRepository.save(order);

        //db update
        user.getShoppingData().changePointAmount(-savedOrder.getPayment().getPointUsed());
        user.getShoppingData().raiseSalesCount();
        user.getShoppingData().changePointAmount((int) Math.round(savedOrder.getPayment().getPaymentPrice()*0.05));

        for(OrderedItem oItem : orderedItemList) {
            oItem.setOrder(savedOrder);
            Item item = itemRepository.findById(oItem.getItem().getId()).get();
            item.raiseSales(oItem.getQuantity());
            item.changeQuantity(-oItem.getQuantity());
            StockDetails stockDetails = stockDetailsRepository.findByItem_IdAndSize_IdAndColor_Id(oItem.getItem().getId(), oItem.getSize().getId(), oItem.getColor().getId()).get();
            stockDetails.changeQuantity(-oItem.getQuantity());
        }

        OrderDto orderDto = OrderDto.of(savedOrder);
        return orderDto;
    }

    @Override
    public OrderDto get(Long orderId,Long userId) throws Exception {
        //userRepository 에서 userId를 통해 user을 가져온 후 manager 이면 진행, findOrder 와 userId가 동일하면 진행, 아니라면 exception 발생

        Optional<Orders> findOrder = orderRepository.findById(orderId);

        if(findOrder.isPresent()) {
            Orders orders = findOrder.get();

            //if not userId same: exception
            User user = userRepository.findById(userId).orElseThrow( () ->
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

    @Override
    public void setNameData(OrderItem orderItem) {


        Item item = itemRepository.findById(orderItem.getItemId()).orElseThrow(
                () -> new NoSuchElementException("해당하는 상품이 없습니다.")
        );

        Color color = colorRepository.findById(orderItem.getColorId()).orElseThrow(
                () -> new NoSuchElementException("해당하는 색상이 없습니다..")
        );

        Size size = sizeRepository.findById(orderItem.getSizeId()).orElseThrow(
                () -> new NoSuchElementException("해당하는 사이즈가 없습니다.")
        );

        orderItem.setItemName(item.getName());
        orderItem.setColorName(color.getName());
        orderItem.setSizeName(size.getName());
        orderItem.setPrice(orderItem.getQuantity()*item.getPrice());
    }
}
