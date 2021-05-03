package fonepay.task.ODSBE.service.order_service;

import fonepay.task.ODSBE.enums.OrderStatus;
import fonepay.task.ODSBE.enums.PaymentType;
import fonepay.task.ODSBE.exception.ApiRequestException;
import fonepay.task.ODSBE.model.CheckoutCart;
import fonepay.task.ODSBE.model.Order;
import fonepay.task.ODSBE.model.Product;
import fonepay.task.ODSBE.repository.CheckoutCartRepository;
import fonepay.task.ODSBE.repository.OrderToCartRepository;
import fonepay.task.ODSBE.service.product_service.ProductService;
import fonepay.task.ODSBE.utils.CommonUtils;
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
        order.setCreatedAt(LocalDate.now());

        return orderToCartRepository.save(order);
    }

    @Override
    public List<Order> getCartOrders(long customerId, boolean isActive) {
        if (isActive)
            return orderToCartRepository.fetchActiveOrders(customerId, OrderStatus.ADDED_TO_CART);
        else
            return orderToCartRepository.fetchDeletedOrders(customerId, OrderStatus.ADDED_TO_CART);
    }

    public Order findOrderById(long id) {
        Order order = orderToCartRepository.findById(id)
                .orElseThrow(() -> new ApiRequestException("Order of id " + id + " was not found!"));
        if (order.getDeletedAt() == null)
            return order;
        else throw new ApiRequestException("Order not valid!");
    }

    @Override
    public Order updateCartOrder(Order order) {
        order.setUpdatedAt(LocalDate.now());
        return orderToCartRepository.save(order);
    }

    @Override
    public void removeCartOrder(long orderId) {
        Order order = findOrderById(orderId);
        order.setDeletedAt(LocalDate.now());
        orderToCartRepository.save(order);
    }

    @Override
    public CheckoutCart makePayment(CheckoutCart checkoutCart) {
        checkoutCart.setOrderDate(LocalDate.now());
        checkoutCart.setExpectedDate(LocalDate.now().plusDays(4));
        checkoutCart.setPaymentType(PaymentType.VISA_CARD_PAYMENT);

        //Change the Status
        checkoutCart.getOrders().forEach(order -> {
            order.setOrderedAt(LocalDate.now());
            switch (CommonUtils.getRandomNumber(1, 5)) {
                case 2 -> order.setOrderStatus(OrderStatus.ORDER_PROCESSED);
                case 3 -> order.setOrderStatus(OrderStatus.QUALITY_CHECKED);
                case 4 -> order.setOrderStatus(OrderStatus.SHIPPED);
                default -> order.setOrderStatus(OrderStatus.ORDER_CONFIRMED);
            }
            orderToCartRepository.save(order);
        });
        return cartRepository.save(checkoutCart);
    }

    @Override
    public List<CheckoutCart> getCheckoutCartOrders(long customerId) {
        return cartRepository.findAllByCustomerId(customerId);
    }

}
