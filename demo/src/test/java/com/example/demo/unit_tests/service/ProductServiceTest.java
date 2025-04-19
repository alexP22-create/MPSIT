package com.example.demo.unit_tests.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import com.example.demo.entity.Image;
import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllProducts() {
        List<Product> products = Arrays.asList(new Product(), new Product());

        when(productRepository.findAll()).thenReturn(products);

        List<Product> result = productService.getAllProducts();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    public void testGetProductByName() {
        String name = "Product1";
        Product product = new Product();
        product.setName(name);

        when(productRepository.findProductByName(name)).thenReturn(product);

        Product result = productService.getProductByName(name);

        assertNotNull(result);
        assertEquals(name, result.getName());
        verify(productRepository, times(1)).findProductByName(name);
    }

    @Test
    public void testSaveProduct_NewProduct() {
        Product product = new Product();
        product.setName("Product1");
        Image image = new Image();
        image.setUrl("http://example.com/image1.jpg");
        product.setImage(image);

        when(productRepository.existsByName(product.getName())).thenReturn(false);
        when(productRepository.findImageByUrl(image.getUrl())).thenReturn(null);
        when(productRepository.save(product)).thenReturn(product);

        Product result = productService.saveProduct(product);

        assertNotNull(result);
        assertEquals(product, result);
        verify(productRepository, times(1)).existsByName(product.getName());
        verify(productRepository, times(1)).findImageByUrl(image.getUrl());
        verify(productRepository, times(1)).save(product);
    }

    @Test
    public void testSaveProduct_ExistingProduct() {
        Product product = new Product();
        product.setName("Product1");

        when(productRepository.existsByName(product.getName())).thenReturn(true);

        Product result = productService.saveProduct(product);

        assertNull(result);
        verify(productRepository, times(1)).existsByName(product.getName());
        verify(productRepository, never()).findImageByUrl(anyString());
        verify(productRepository, never()).save(any(Product.class));
    }

    @Test
    public void testSaveProduct_ExistingImage() {
        Product product = new Product();
        product.setName("Product1");
        Image image = new Image();
        image.setUrl("http://example.com/image1.jpg");
        product.setImage(image);

        Image existingImage = new Image();
        existingImage.setUrl("http://example.com/image1.jpg");

        when(productRepository.existsByName(product.getName())).thenReturn(false);
        when(productRepository.findImageByUrl(image.getUrl())).thenReturn(existingImage);
        when(productRepository.save(product)).thenReturn(product);

        Product result = productService.saveProduct(product);

        assertNotNull(result);
        assertEquals(existingImage, result.getImage());
        verify(productRepository, times(1)).existsByName(product.getName());
        verify(productRepository, times(1)).findImageByUrl(image.getUrl());
        verify(productRepository, times(1)).save(product);
    }

    @Test
    public void testDeleteProduct() {
        String name = "Product1";

        doNothing().when(productRepository).deleteProductByName(name);

        productService.deleteProduct(name);

        verify(productRepository, times(1)).deleteProductByName(name);
    }

    @Test
    public void testUpdateProduct_ProductExists() {
        Product oldProduct = new Product();
        oldProduct.setName("Product1");
        oldProduct.setPrice(100);
        oldProduct.setDescription("Old description");
        oldProduct.setDeliveryTime(5);
        oldProduct.setNrInStock(10);

        Product updatedProduct = new Product();
        updatedProduct.setName("Product1");
        updatedProduct.setPrice(200);
        updatedProduct.setDescription("New description");
        updatedProduct.setDeliveryTime(3);
        updatedProduct.setNrInStock(20);

        when(productRepository.findProductByName(updatedProduct.getName())).thenReturn(oldProduct);
        when(productRepository.save(oldProduct)).thenReturn(oldProduct);

        productService.updateProduct(updatedProduct);

        assertEquals(200, oldProduct.getPrice());
        assertEquals("New description", oldProduct.getDescription());
        assertEquals(3, oldProduct.getDeliveryTime());
        assertEquals(20, oldProduct.getNrInStock());
        verify(productRepository, times(1)).findProductByName(updatedProduct.getName());
        verify(productRepository, times(1)).save(oldProduct);
    }

    @Test
    public void testUpdateProduct_ProductDoesNotExist() {
        Product updatedProduct = new Product();
        updatedProduct.setName("Product1");

        when(productRepository.findProductByName(updatedProduct.getName())).thenReturn(null);

        productService.updateProduct(updatedProduct);

        verify(productRepository, times(1)).findProductByName(updatedProduct.getName());
        verify(productRepository, never()).save(any(Product.class));
    }
}

