package fonepay.task.ODSBE.service;

import fonepay.task.ODSBE.exception.ApiRequestException;
import fonepay.task.ODSBE.model.Product;
import fonepay.task.ODSBE.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public List<Product> findAllProducts() {
        return productRepository.findAllByDeletedAt(null);
    }

    public Product findProductById(long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ApiRequestException("Product of id " + id + " was not found!"));
        if (product.getDeletedAt() == null)
            return product;
        else throw new ApiRequestException("Product not valid!");
    }

    public Product addProduct(Product product) {
        product.setCreatedAt(LocalDate.now());
        return productRepository.save(product);
    }

    public Product updateProduct(Product product) {
        product.setUpdatedAt(LocalDate.now());
        return productRepository.save(product);
    }

    public void deleteProduct(long id) {
        Product product = findProductById(id);
        product.setDeletedAt(LocalDate.now());
        productRepository.save(product);
    }
}
