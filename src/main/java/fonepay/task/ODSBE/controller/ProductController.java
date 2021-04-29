package fonepay.task.ODSBE.controller;

import fonepay.task.ODSBE.model.Product;
import fonepay.task.ODSBE.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/products")
public class ProductController implements CrudController<Product> {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllData() {
        List<Product> Products = productService.findAllProducts();
        return new ResponseEntity<>(Products, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getDataById(@PathVariable("id") long id) {
        Product Product = productService.findProductById(id);
        return new ResponseEntity<>(Product, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Product> addData(@RequestBody Product product) {
        Product newProduct = productService.addProduct(product);
        return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Product> updateData(@RequestBody Product product) {
        Product updatedProduct = productService.updateProduct(product);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteDataById(@PathVariable("id") long id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
