package fonepay.task.ODSBE.controller;

import fonepay.task.ODSBE.enums.OrderStatus;
import fonepay.task.ODSBE.model.CheckoutCart;
import fonepay.task.ODSBE.model.Order;
import fonepay.task.ODSBE.service.order_service.OrderToCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/orders")
public class OrderToCartController {

    private final OrderToCartService cartService;

    @Autowired
    public OrderToCartController(OrderToCartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping
    public ResponseEntity<Order> addToCart(@RequestParam("customerId") long customerId,
                                           @RequestParam("productId") long productId,
                                           @RequestParam("quantity") int quantity) {
        Order newOrder = cartService.addToCart(customerId, productId, quantity);
        return new ResponseEntity<>(newOrder, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Order>> getCartOrdersOfUser(@RequestParam("customerId") long customerId) {
        return new ResponseEntity<>(cartService.getCartOrders(customerId, OrderStatus.ADDED_TO_CART), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Order> updateCartOrder(@RequestBody Order order) {
        return new ResponseEntity<>(cartService.updateCartOrder(order), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDataById(@PathVariable("id") long orderId) {
        cartService.removeCartOrder(orderId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/checkout")
    public ResponseEntity<CheckoutCart> checkout(@RequestBody CheckoutCart checkoutCart) {
        CheckoutCart cart = cartService.makePayment(checkoutCart);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @GetMapping("/checkout")
    public ResponseEntity<List<CheckoutCart>> checkout(@RequestParam("customerId") long customerId) {
        return new ResponseEntity<>(cartService.getCheckoutCartOrders(customerId), HttpStatus.OK);
    }


}
