package fonepay.task.ODSBE.model;


import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
public class User {

    @Id
    @SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    @Column(nullable = false, updatable = false)
    private long id;

    private String firstName;
    private String lastName;
    private String email;
    private String mobile;
    private String address;

    private LocalDate createdAt;
    private LocalDate updatedAt;
    private LocalDate deletedAt;

    public User(String firstName, String lastName, String email, String mobile, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.mobile = mobile;
        this.address = address;
    }
}
