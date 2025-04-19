package com.example.demo.service;

import com.example.demo.entity.Image;
import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductByName(String name) {
        return  productRepository.findProductByName(name);
    }

    public Product saveProduct(Product product) {
        if (productRepository.existsByName(product.getName()))
            return null;

        Image imageRef = productRepository.findImageByUrl(product.getImage().getUrl());
        if (imageRef != null){
            product.setImage(imageRef);
        }

        return productRepository.save(product);
    }

    public void deleteProduct(String name) {
        productRepository.deleteProductByName(name);
    }

    public void updateProduct(Product updatedProduct) {

        Product oldProduct = productRepository.findProductByName(updatedProduct.getName());

        if (oldProduct == null)
            return;

        if (updatedProduct.getPrice() != 0) {
            oldProduct.setPrice(updatedProduct.getPrice());
        }

        if (updatedProduct.getDescription() != null) {
            oldProduct.setDescription(updatedProduct.getDescription());
        }

        if (updatedProduct.getDeliveryTime() != 0) {
            oldProduct.setDeliveryTime(updatedProduct.getDeliveryTime());
        }

        if (updatedProduct.getNrInStock() != 0) {
            oldProduct.setNrInStock(updatedProduct.getNrInStock());
        }

        productRepository.save(oldProduct);
    }
}
