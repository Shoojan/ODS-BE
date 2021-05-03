package fonepay.task.ODSBE.service.order_service;

import fonepay.task.ODSBE.enums.OrderStatus;
import fonepay.task.ODSBE.enums.PaymentType;
import fonepay.task.ODSBE.model.CheckoutCart;
import fonepay.task.ODSBE.model.Order;
import fonepay.task.ODSBE.model.Product;
import fonepay.task.ODSBE.repository.CheckoutCartRepository;
import fonepay.task.ODSBE.repository.OrderToCartRepository;
import fonepay.task.ODSBE.service.product_service.ProductService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public record OrderToCartServiceImpl(OrderToCartRepository orderToCartRepository,
                                     CheckoutCartRepository cartRepository,
                                     ProductService productService) implements OrderToCartService {

    @Override
    public Order addToCart(long customerId, long productId, int qty) {
        Product product = productService.findDataById(productId);

        Order order = new Order();
        order.setCustomerId(customerId);
        order.setQuantity(qty);
        order.setProduct(product);
        order.setUnitPrice(product.getPrice());
        order.setTotalPrice(order.getQuantity() * order.getUnitPrice());
        order.setOrderStatus(OrderStatus.ADDED_TO_CART);

        return orderToCartRepository.save(order);
    }

    @Override
    public List<Order> getCartOrders(long customerId, OrderStatus orderStatus) {
        return orderToCartRepository.findAllByCustomerIdAndOrderStatus(customerId, orderStatus);
    }

    @Override
    public Order updateCartOrder(Order order) {
        return orderToCartRepository.save(order);
    }

    @Override
    public void removeCartOrder(long orderId) {
        orderToCartRepository.deleteById(orderId);
    }

    @Override
    public CheckoutCart makePayment(CheckoutCart checkoutCart) {
        checkoutCart.setOrderDate(LocalDate.now());
        checkoutCart.setPaymentType(PaymentType.VISA_CARD_PAYMENT);

        //Change the Status
        checkoutCart.getOrders().forEach(order -> {
            order.setOrderStatus(OrderStatus.ORDER_PLACED);
            order.setOrderedAt(LocalDate.now());
            orderToCartRepository.save(order);
        });
        return cartRepository.save(checkoutCart);
    }

    @Override
    public List<CheckoutCart> getCheckoutCartOrders(long customerId) {
        return cartRepository.findAllByCustomerId(customerId);
    }

}
