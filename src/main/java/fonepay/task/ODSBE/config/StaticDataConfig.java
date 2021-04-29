package fonepay.task.ODSBE.config;

import fonepay.task.ODSBE.model.Product;
import fonepay.task.ODSBE.model.Customer;
import fonepay.task.ODSBE.repository.ProductRepository;
import fonepay.task.ODSBE.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class StaticDataConfig {

    @Bean
    CommandLineRunner commandLineRunner(CustomerRepository userRepository, ProductRepository productRepository) {
        return args -> {
            userRepository.saveAll(List.of(
                    new Customer("Sujan", "Maharjan", "sujan@gmail.com", "9860059666", "Chandragiri-10, Kathmandu"),
                    new Customer("Sonam", "Neupane", "sonam@gmail.com", "9860123456", "Chandragiri-5, Kathmandu"),
                    new Customer("Jenisha", "Adhikari", "jenisha@gmail.com", "9860059111", "Bhaktapur")
            ));

            productRepository.saveAll(List.of(
                    new Product("Keyboard", "Silent and comfortable with USB Input Interface", 399.00, "https://www.tolinktrading.com.np/wp-content/uploads/2020/04/DG-W12-WIRED-KEYBOARD.png"),
                    new Product("Msi Curved Gaming Monitor", "fast response time.75hz 1080p resolution.", 20000.12, "https://itti.com.np/pub/media/catalog/product/cache/c0bb400db441ec67b37045c5a66e35a8/d/9/d95dcf83c7a092378e6ffa41dd24fc62-hi.jpg"),
                    new Product("Logitech Gaming Mouse", "Logitech g302 daedalus prime gaming mouse", 3999.00, "https://www.techlandbd.com/image/cache/catalog/mouse/Logitech/logitech-g302-gaming-mouse-500x500w.jpg"),
                    new Product("Fantech Gaming Headphone", """
                            - FANTECH HG20 Gaming Headset 3.5mm
                            - USB Wired Earphones with Microphone and RGB Lights
                            - With the stereo surround effect, the high-grade driver delivers the best voice
                            """, 2690.11, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQiPRN_paZ2GWZTo3OmJRaGnhivDf3HEyI_tg&usqp=CAU")
            ));

        };
    }
}
