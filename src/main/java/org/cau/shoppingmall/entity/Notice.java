package org.cau.shoppingmall.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.cau.shoppingmall.entity.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    @Column(name = "register_date")
    private LocalDateTime registerDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
