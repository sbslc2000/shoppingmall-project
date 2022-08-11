package org.cau.shoppingmall.entity.inquiry;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;


@Entity
@Getter
public class OneToOneInquiry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*
     * TODO: Type Entity 와 연관관계 매핑
     * */
    private Long typeId;

    /*
     * TODO: User Entity와 연관관계 매핑
     * */
    private Long userId;

    private LocalDateTime registerDate;

    private String content;

    private String img;
}
