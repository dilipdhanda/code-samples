package com.c1.spring.PessimisticLock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import com.c1.spring.PessimisticLock.repository.ProductRepository;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class ApplicationPessimisticLock {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationPessimisticLock.class, args);
    }
}

@Service
class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public Product updateProductQuantity(Long productId, int quantityChange) throws InterruptedException {
        Optional<Product> productOpt = productRepository.findByIdWithLock(productId);
        if (productOpt.isPresent()) {
            Product product = productOpt.get();
            product.setQuantity(product.getQuantity() + quantityChange);
            TimeUnit.SECONDS.sleep(5); // Simulate processing delay
            return productRepository.save(product);
        }
        throw new RuntimeException("Product not found");
    }
}

@RestController
@RequestMapping("/products")
class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}/update")
    public Product updateQuantity(@PathVariable Long id, @RequestParam int quantity) throws InterruptedException {
        return productService.updateProductQuantity(id, quantity);
    }
}


