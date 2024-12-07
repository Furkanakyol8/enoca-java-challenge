package com.furkan.enoca.service.impl;

import com.furkan.enoca.exception.AlreadyExistException;
import com.furkan.enoca.exception.NotFoundException;
import com.furkan.enoca.model.dto.ProductRequest;
import com.furkan.enoca.model.dto.ProductDto;
import com.furkan.enoca.model.entity.Product;
import com.furkan.enoca.repository.ProductRepository;
import com.furkan.enoca.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public void removeFromStock(Product product, int quantity) {
        product.checkHasStock(quantity);
        product.setStock(product.getStock() - quantity);
        productRepository.save(product);
    }

    @Override
    public ProductDto createProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                .name(productRequest.getName())
                .code(productRequest.getCode())
                .unitPrice(productRequest.getUnitPrice())
                .description(productRequest.getDescription())
                .stock(productRequest.getStock())
                .build();

        if (productRepository.existsByCode(product.getCode())) {
            throw new AlreadyExistException("Product already exists with code " + product.getCode());
        }

        productRepository.save(product);
        return new ProductDto(product);

    }

    @Override
    public ProductDto update(UUID id, ProductRequest productRequest) {

        Product product = productRepository.findById(id).orElseThrow(() -> new NotFoundException("Product not found with id" + id));

        product.setName(productRequest.getName());
        product.setCode(productRequest.getCode());
        product.setUnitPrice(productRequest.getUnitPrice());
        product.setDescription(productRequest.getDescription());
        product.setStock(productRequest.getStock());

        productRepository.save(product);

        return new ProductDto(product);
    }

    @Override
    public ProductDto findById(UUID id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new NotFoundException("Product not found with id " + id));
        return new ProductDto(product);
    }

    @Override
    public List<ProductDto> findAll() {

        List<Product> products = productRepository.findAll();

        return products.stream()
                .map(ProductDto::new)
                .toList();
    }

    @Override
    public void delete (UUID id) {
        Product foundProduct = productRepository.findById(id).orElseThrow(() -> new NotFoundException("Product not found with id " + id));
        productRepository.delete(foundProduct);
    }
}
