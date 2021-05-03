package fonepay.task.ODSBE.config;

import fonepay.task.ODSBE.enums.OrderStatus;
import fonepay.task.ODSBE.model.Order;
import fonepay.task.ODSBE.model.Product;
import fonepay.task.ODSBE.model.Customer;
import fonepay.task.ODSBE.repository.OrderToCartRepository;
import fonepay.task.ODSBE.repository.ProductRepository;
import fonepay.task.ODSBE.repository.CustomerRepository;
import fonepay.task.ODSBE.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.List;
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
            new Product("Xbox 360 Joystick", "Wired Joystick Game pad, Microsoft Xbox 360 Style , High Quality, Durable, Long Lasting.", 650.00, "https://images-na.ssl-images-amazon.com/images/I/61ctfFQjHlL._AC_SX466_.jpg"),
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
                    new Customer("Sujan", "Maharjan", "sujan@gmail.com", passwordEncoder.encode("123"), "98xxxxxxxx", "Chandragiri-10, Kathmandu"),
                    new Customer("Diwas", "Sapkota", "diwas@gmail.com", passwordEncoder.encode("123"), "98yyyyyyyy", "Chandragiri-5, Kathmandu"),
                    new Customer("Ankita", "Shrestha", "ankita@gmail.com", passwordEncoder.encode("123"), "98zzzzzzzz", "Pulchowk, Lalitpur")
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
        order.setQuantity(CommonUtils.getRandomNumber((int) product.getId(), 10));
        order.setProduct(product);
        order.setUnitPrice(product.getPrice());
        order.setTotalPrice(order.getQuantity() * order.getUnitPrice());
        order.setOrderedAt(LocalDate.now());
        order.setOrderStatus(OrderStatus.ADDED_TO_CART);
        order.setCreatedAt(LocalDate.now());
        return order;
    }

}
