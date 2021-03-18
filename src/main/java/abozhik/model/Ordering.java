package abozhik.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "ordering")
public class Ordering {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ordering_id_seq")
    @SequenceGenerator(name = "ordering_id_seq", allocationSize = 1, sequenceName = "ordering_id_seq")
    @Column(name = "id")
    private Long id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "done")
    private boolean done;

    @OneToMany(mappedBy = "ordering", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<OrderingItem> orderingItems;

}
