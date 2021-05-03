package fonepay.task.ODSBE.service.order_service;

import fonepay.task.ODSBE.enums.OrderStatus;
import fonepay.task.ODSBE.model.CheckoutCart;
import fonepay.task.ODSBE.model.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderToCartService {
    //Add to Cart
    Order addToCart(long customerId, long productId, int qty);

    List<Order> getCartOrders(long customerId, OrderStatus orderStatus);

    Order updateCartOrder(Order order);

    void removeCartOrder(long orderId);

    //Checkout
    CheckoutCart makePayment(CheckoutCart checkoutCart);

    List<CheckoutCart> getCheckoutCartOrders(long customerId);
}
