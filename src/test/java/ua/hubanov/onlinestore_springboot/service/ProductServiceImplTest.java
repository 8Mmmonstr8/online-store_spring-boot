package ua.hubanov.onlinestore_springboot.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ua.hubanov.onlinestore_springboot.entity.Category;
import ua.hubanov.onlinestore_springboot.entity.Product;
import ua.hubanov.onlinestore_springboot.repository.ProductRepository;
import ua.hubanov.onlinestore_springboot.service.impl.ProductServiceImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;


    @Test
    public void findAllTest() {
        List<Product> products = createProducts();

        when(productRepository.findAll()).thenReturn(products);

        List<Product> empList = productService.findAll();

        assertEquals(5, empList.size());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    public void findAllByCategoryIdTest() {
        List<Product> products = createProducts();
        List<Product> productsByCategory3 = products.stream().filter(x -> x.getCategory().getId() == 3L).collect(Collectors.toList());
        when(productRepository.findAllByCategoryId(3L)).thenReturn(productsByCategory3);

        List<Product> empList = productService.findAllByCategoryId(3L);

        assertEquals(3, empList.size());
        verify(productRepository, times(1)).findAllByCategoryId(3L);
    }

    @Test
    public void findProductByIdTest() {
        List<Product> products = createProducts();
        Product product = products.stream().filter(x -> x.getId() == 1L).findAny()
                .orElseThrow(() -> new UsernameNotFoundException("user not found"));

        when(productRepository.findById(1L)).thenReturn(Optional.ofNullable(product));

        Optional<Product> testProduct = productService.findProductById(1L);

        assertEquals("Iphone 4", testProduct.get().getName());
        verify(productRepository, times(1)).findById(1L);
    }




    public List<Product> createProducts() {
        List<Product> products = new ArrayList<>();

        Product product1 = Product.builder().id(1L).name("Iphone 4")
                .category(Category.builder().id(3L).name("Phones").build())
                .description("cool old phone")
                .price(BigDecimal.valueOf(1000L))
                .quantity(10)
                .publicationDate(new Date())
                .build();
        Product product2 = Product.builder().id(2L).name("Iphone 12")
                .category(Category.builder().id(3L).name("Phones").build())
                .description("new Iphone")
                .price(BigDecimal.valueOf(2000L))
                .quantity(2)
                .publicationDate(new Date())
                .build();
        Product product3 = Product.builder().id(3L).name("Iphone 15")
                .category(Category.builder().id(3L).name("Phones").build())
                .description("chinese mock")
                .price(BigDecimal.valueOf(100L))
                .quantity(100)
                .publicationDate(new Date())
                .build();
        Product product4 = Product.builder().id(4L).name("Bear")
                .category(Category.builder().id(2L).name("Toys").build())
                .description("child's toy")
                .price(BigDecimal.valueOf(50L))
                .quantity(500)
                .publicationDate(new Date())
                .build();
        Product product5 = Product.builder().id(5L).name("Tom Sawyer")
                .category(Category.builder().id(1L).name("Books").build())
                .description("Mark Twain's book")
                .price(BigDecimal.valueOf(10L))
                .quantity(900)
                .publicationDate(new Date())
                .build();
        products.add(product1);
        products.add(product2);
        products.add(product3);
        products.add(product4);
        products.add(product5);

        return products;
    }

}
