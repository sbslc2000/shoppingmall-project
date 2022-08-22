package org.cau.shoppingmall.entity.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.cau.shoppingmall.dto.item.ItemDetails;
import org.cau.shoppingmall.entity.item.Item;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne
    @JoinColumn( name = "user_id")
    private User user;

    private ItemDetails itemDetails;

    @Column(name = "register_date")
    private LocalDateTime registerDate;

    private String title;

    private String content;

    @Column(length = 3600)
    private String img;

    private int stars;
}
