package org.cau.shoppingmall.entityinterface;

public interface ShoppingmallDataInterface {

    void raiseSalesCount();

    void changePointAmount(int amount);

    /*
    * reviews 를 1 올린다.
    * */
    void addReviews();
}
