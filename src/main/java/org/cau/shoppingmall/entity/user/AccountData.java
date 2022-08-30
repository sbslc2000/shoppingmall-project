package org.cau.shoppingmall.entity.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AccountData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "late_date")
    private LocalDateTime lateDate;

    @Column(name = "register_date")
    private LocalDateTime registerDate;

    @Column(name = "sms_agreement")
    private boolean smsAgreement;

    public void setLateDateToNow() {
        lateDate = LocalDateTime.now();
    }
}
