package com.furkan.enoca.service;

import com.furkan.enoca.model.dto.ProductRequest;
import com.furkan.enoca.model.dto.ProductDto;
import com.furkan.enoca.model.entity.Product;
import java.util.List;
import java.util.UUID;

public interface ProductService {

    void removeFromStock(Product product, int quantity);

    ProductDto createProduct(ProductRequest productRequest);

    ProductDto findById(UUID id);

    List<ProductDto> findAll();

    void delete(UUID id);

    ProductDto update(UUID id, ProductRequest ProductRequest);

}
