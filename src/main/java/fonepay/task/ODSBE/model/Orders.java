package fonepay.task.ODSBE.model;

import fonepay.task.ODSBE.enums.OrderStatus;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
public class Orders {

    @Id
    @SequenceGenerator(name = "order_sequence", sequenceName = "order_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_sequence")
    @Column(nullable = false, updatable = false)
    private long id;

    private long userId;
    private long productId;

    private int quantity;
    private double unitPrice;
    private double totalPrice;

    private LocalDate orderedAt;

    private OrderStatus orderStatus;

}
