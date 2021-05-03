package fonepay.task.ODSBE.service.product_service;

import fonepay.task.ODSBE.exception.ApiRequestException;
import fonepay.task.ODSBE.model.Product;
import fonepay.task.ODSBE.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public record ProductServiceImpl(ProductRepository productRepository) implements ProductService {

    public List<Product> findAllData() {
        return productRepository.findAllByDeletedAt(null);
    }

    public Product findDataById(long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ApiRequestException("Product of id " + id + " was not found!"));
        if (product.getDeletedAt() == null)
            return product;
        else throw new ApiRequestException("Product not valid!");
    }

    public Product addData(Product product) {
        product.setCreatedAt(LocalDate.now());
        return productRepository.save(product);
    }

    public Product updateData(Product product) {
        product.setUpdatedAt(LocalDate.now());
        return productRepository.save(product);
    }

    public void deleteData(long id) {
        Product product = findDataById(id);
        product.setDeletedAt(LocalDate.now());
        productRepository.save(product);
    }
}
