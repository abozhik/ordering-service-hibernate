package abozhik.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "ordering_items")
public class OrderingItem {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ordering_items_id_seq")
    @SequenceGenerator(name = "ordering_items_id_seq", allocationSize = 1, sequenceName = "ordering_items_id_seq")
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ordering_id", referencedColumnName = "id")
    private Ordering ordering;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "item_count")
    private Integer itemCount;

    @Column(name = "item_price")
    private BigDecimal itemPrice;

    public OrderingItem(String itemName, Integer itemCount, BigDecimal itemPrice) {
        this.itemName = itemName;
        this.itemCount = itemCount;
        this.itemPrice = itemPrice;
    }
}
