package org.cau.shoppingmall.service;

public interface NotificationService {

    /*
     * 1대1 문의에 답변이 달렸을 때 사용자에게 알림을 전송함.
     * */
    void addAnsweredAtInquiryNotification(Long inquiryId);

    /*
     * 배송이 완료되었을 때 사용자에게 알림을 전송함.
     * */
    void addDeliveryCompletedNotification(Long orderId);

    /*
     * 주문이 완료되었음을 사용자에게 알림
     * */
    void addOrderCompletedNotification(Long orderId);

}
