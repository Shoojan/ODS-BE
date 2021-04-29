package fonepay.task.ODSBE.model;

import fonepay.task.ODSBE.enums.OrderStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity(name = "orders")
public class Order {

    @Id
    @SequenceGenerator(name = "order_sequence", sequenceName = "order_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_sequence")
    private long id;

    private long userId;
    private long productId;

    private int quantity;
    private double unitPrice;
    private double totalPrice;

    private LocalDate orderedAt;

    private OrderStatus orderStatus;

}
