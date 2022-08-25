package org.cau.shoppingmall.entity.inquiry;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.cau.shoppingmall.entity.user.User;

import javax.persistence.*;

import java.time.LocalDateTime;


@Builder
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OneToOneInquiry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*
     * TODO: Type Entity 와 연관관계 매핑
     * */

    @ManyToOne
    @JoinColumn(name = "type_id")
    private InquiryType type;

    /*
     * TODO: User Entity와 연관관계 매핑
     * */

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private LocalDateTime registerDate;

    private String content;

    private String img;
}
