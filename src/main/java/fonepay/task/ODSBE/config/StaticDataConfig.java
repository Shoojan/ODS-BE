package fonepay.task.ODSBE.config;

import fonepay.task.ODSBE.enums.OrderStatus;
import fonepay.task.ODSBE.model.Order;
import fonepay.task.ODSBE.model.Product;
import fonepay.task.ODSBE.model.Customer;
import fonepay.task.ODSBE.repository.OrderToCartRepository;
import fonepay.task.ODSBE.repository.ProductRepository;
import fonepay.task.ODSBE.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Configuration
public class StaticDataConfig {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public StaticDataConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    private final List<Product> products = List.of(
            new Product("Keyboard", "Silent and comfortable with USB Input Interface", 399.00, "https://www.tolinktrading.com.np/wp-content/uploads/2020/04/DG-W12-WIRED-KEYBOARD.png"),
            new Product("Msi Curved Gaming Monitor", "Fast response time and 75hz 1080p resolution.", 20000.12, "https://itti.com.np/pub/media/catalog/product/cache/c0bb400db441ec67b37045c5a66e35a8/d/9/d95dcf83c7a092378e6ffa41dd24fc62-hi.jpg"),
            new Product("Logitech Gaming Mouse", "Logitech g302 daedalus prime gaming mouse", 3999.00, "https://www.techlandbd.com/image/cache/catalog/mouse/Logitech/logitech-g302-gaming-mouse-500x500w.jpg"),
            new Product("Fantech Gaming Headphone", """
                    - FANTECH HG20 Gaming Headset 3.5mm
                    - USB Wired Earphones with Microphone and RGB Lights
                    - With the stereo surround effect, the high-grade driver delivers the best voice
                    """, 2690.11, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQiPRN_paZ2GWZTo3OmJRaGnhivDf3HEyI_tg&usqp=CAU")
    );

    @Bean
    CommandLineRunner commandLineRunner(CustomerRepository userRepository, ProductRepository productRepository, OrderToCartRepository cartRepository) {
        return args -> {
            List<Customer> customers = List.of(
                    new Customer("Customer", "A", "a@gmail.com", passwordEncoder.encode("a123"), "98xxxxxxxx", "Chandragiri-10, Kathmandu"),
                    new Customer("Customer", "B", "b@gmail.com", passwordEncoder.encode("b123"), "98yyyyyyyy", "Chandragiri-5, Kathmandu"),
                    new Customer("Customer", "C", "c@gmail.com", passwordEncoder.encode("c123"), "98zzzzzzzz", "Bhaktapur")
            );
            List<Customer> savedCustomers = userRepository.saveAll(customers);

            List<Product> saveProducts = productRepository.saveAll(products);

            savedCustomers.forEach(customer -> cartRepository.saveAll(
                    saveProducts.stream()
                            .map(p -> getOrder(p, customer.getId()))
                            .collect(Collectors.toList()))
            );

        };
    }

    private Order getOrder(Product product, long customerId) {
        Order order = new Order();
        order.setCustomerId(customerId);
        order.setQuantity(getRandomNumber((int) product.getId(), 10));
        order.setProduct(product);
        order.setUnitPrice(product.getPrice());
        order.setTotalPrice(order.getQuantity() * order.getUnitPrice());
        order.setOrderedAt(LocalDate.now());
        order.setOrderStatus(OrderStatus.ADDED_TO_CART);
        return order;
    }

    private int getRandomNumber(int min, int max) {
        if (min > max) min -= max;
        return new Random().nextInt((max - min) + 1) + min;
    }
}
