package org.cau.shoppingmall.entity.exchange;

import lombok.Getter;
import org.cau.shoppingmall.entity.user.AccountData;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
public class ExchangeRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long user_id;

    @ManyToOne
    @JoinColumn(name = "exchange_reason_id")
    private ExchangeReason exchangeReason;

    @ManyToOne
    @JoinColumn(name = "exchange_pickup_id")
    private ExchangePickup exchangePickup;

    @Column(name = "payment_flag")
    private boolean paymentFlag;

    @Column(name = "register_date")
    private LocalDateTime registerDate;

    @Column(length = 3600)
    private String img;

}
