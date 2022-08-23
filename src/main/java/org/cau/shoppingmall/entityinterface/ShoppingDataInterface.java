package org.cau.shoppingmall.entityinterface;

public interface ShoppingDataInterface {

    /*
    * User 의 판매량을 증가시킨다.
    * */
    void raiseSalesCount();

    /*
     * 포인트 적립, 포인트 사용시에 사용한다.
     * */
    void changePointAmount(int amount);

    /*
    * reviews 를 1 올린다.
    * */
    void addReviews();
}
