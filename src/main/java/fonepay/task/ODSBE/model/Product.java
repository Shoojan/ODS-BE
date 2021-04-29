package fonepay.task.ODSBE.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
public class Product {

    @Id
    @SequenceGenerator(name = "product_sequence", sequenceName = "product_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_sequence")
    @Column(nullable = false, updatable = false)
    private long id;

    private String productName;
    private String description;
    private double price;
    private String imagePath;

    private LocalDate createdAt;
    private LocalDate updatedAt;
    private LocalDate deletedAt;

    public Product(String productName, String description, double price, String imagePath) {
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.imagePath = imagePath;
        this.createdAt = LocalDate.now();
    }

}
