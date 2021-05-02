package fonepay.task.ODSBE.model;

import fonepay.task.ODSBE.enums.PaymentType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "checkout_cart")
public class CheckoutCart {

    @Id
    @SequenceGenerator(name = "checkout_sequence", sequenceName = "checkout_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "checkout_sequence")
    private long id;

    long customerId;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "orderId")
    private List<Order> orders;

    private PaymentType paymentType;

    private String deliveryAddress;

    private long totalCost;

    private LocalDate orderDate;

}
