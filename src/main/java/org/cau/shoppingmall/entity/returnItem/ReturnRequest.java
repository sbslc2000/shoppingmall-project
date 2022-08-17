package org.cau.shoppingmall.entity.returnItem;

import lombok.Getter;
import org.cau.shoppingmall.entity.exchange.ExchangePickup;
import org.cau.shoppingmall.entity.exchange.ExchangeReason;

import javax.persistence.*;
import java.time.LocalDateTime;
@Entity
@Getter
public class ReturnRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long user_id;

    @ManyToOne
    @JoinColumn(name = "return_reason_id")
    private ExchangeReason returnReason;

    @ManyToOne
    @JoinColumn(name = "return_pickup_id")
    private ExchangePickup returnPickup;

    @Column(name = "refund_flag")
    private boolean refundFlag;

    @Column(name = "register_date")
    private LocalDateTime registerDate;

    @Column(length = 3600)
    private String img;
}
