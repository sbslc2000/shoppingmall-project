package org.cau.shoppingmall.repository;

import org.cau.shoppingmall.entity.inquiry.InquiryType;
import org.cau.shoppingmall.entity.item.Category;
import org.cau.shoppingmall.entity.item.Size;
import org.cau.shoppingmall.entity.order.OrderProcess;
import org.cau.shoppingmall.entity.order.PaymentMethod;
import org.cau.shoppingmall.entity.user.Authority;
import org.cau.shoppingmall.repository.item.CategoryRepository;
import org.cau.shoppingmall.repository.item.SizeRepository;
import org.cau.shoppingmall.repository.order.OrderProcessRepository;
import org.cau.shoppingmall.repository.order.PaymentMethodRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

//@SpringBootTest
public class IntegratedDatabaseSetting {

    @Autowired
    private InquiryTypeRepository inquiryTypeRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SizeRepository sizeRepository;


    @Autowired
    private OrderProcessRepository orderProcessRepository;

    @Autowired
    private PaymentMethodRepository paymentMethodRepository;

    //@Test
    void insertPaymentMethod() {
        PaymentMethod pm1 = new PaymentMethod().builder()
                .name("무통장")
                .build();

        PaymentMethod pm2 = new PaymentMethod().builder()
                .name("신용카드")
                .build();

        PaymentMethod pm3 = new PaymentMethod().builder()
                .name("카카오페이")
                .build();

        paymentMethodRepository.save(pm1);
        paymentMethodRepository.save(pm2);
        paymentMethodRepository.save(pm3);


    }

    //@Test
    void insertSize() {
        Size m = new Size().builder()
                .name("M")
                .build();

        Size l = new Size().builder()
                .name("L")
                .build();

        Size free = new Size().builder()
                .name("Free")
                .build();

        sizeRepository.save(m);
        sizeRepository.save(l);
        sizeRepository.save(free);
    }

    //@Test
    void insertCategory() {
        Category c1 = new Category().builder()
                .name("의류")
                .build();

        Category c2 = new Category().builder()
                .name("뷰티")
                .build();

        Category c3 = new Category().builder()
                .name("가방/지갑")
                .build();

        Category c4 = new Category().builder()
                .name("액세사리")
                .build();

        Category c5 = new Category().builder()
                .name("신발")
                .build();

        Category c6 = new Category().builder()
                .name("레드불")
                .build();

        categoryRepository.save(c1);
        categoryRepository.save(c2);
        categoryRepository.save(c3);
        categoryRepository.save(c4);
        categoryRepository.save(c5);
        categoryRepository.save(c6);

    }
    //@Test
    public void insertAuthority() {
        Authority normalUser = new Authority().builder()
                .name("일반사용자")
                .build();

        Authority manager = new Authority().builder()
                .name("관리자")
                .build();

        Authority topManager = new Authority().builder()
                .name("최고관리자")
                .build();

        authorityRepository.save(normalUser);
        authorityRepository.save(manager);
        authorityRepository.save(topManager);
    }



    //@Test
    void insertInquiryType() {
        InquiryType type1 = new InquiryType().builder()
                .name("배송")
                .build();
        InquiryType type2 = new InquiryType().builder()
                .name("엣지")
                .build();

        InquiryType type3 = new InquiryType().builder()
                .name("주문/결제")
                .build();
        InquiryType type4 = new InquiryType().builder()
                .name("교환/반품")
                .build();
        InquiryType type5 = new InquiryType().builder()
                .name("회원")
                .build();
        InquiryType type6 = new InquiryType().builder()
                .name("기타")
                .build();


        inquiryTypeRepository.save(type1);
        inquiryTypeRepository.save(type2);
        inquiryTypeRepository.save(type3);
        inquiryTypeRepository.save(type4);
        inquiryTypeRepository.save(type5);
        inquiryTypeRepository.save(type6);
    }


    //@Test
    void insert() {
        OrderProcess op1 = new OrderProcess().builder()
                .name("입금 전")
                .build();
        OrderProcess op2 = new OrderProcess().builder()
                .name("배송 준비중")
                .build();
        OrderProcess op3 = new OrderProcess().builder()
                .name("배송 준비중")
                .build();
        OrderProcess op4 = new OrderProcess().builder()
                .name("배송 완료")
                .build();

        orderProcessRepository.save(op1);
        orderProcessRepository.save(op2);
        orderProcessRepository.save(op3);
        orderProcessRepository.save(op4);

    }


}
