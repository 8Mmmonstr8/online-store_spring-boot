package ua.hubanov.onlinestore_springboot.entity;

import lombok.*;

import javax.persistence.*;
import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Getter
@Setter

@Entity
@Table(name="carts")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", insertable = false, updatable = false, nullable = false)
    private Long id;

//    @JoinColumn(name = "product_id")
//    @OneToMany(fetch = FetchType.EAGER)
//    private Set<Product> products;

    @Transient
    private Map<Product, Integer> productsWithQuantity = new HashMap<>();

    @JoinColumn(name = "order_id")
    @OneToMany(fetch = FetchType.EAGER)
    private Set<Order> orders;

//    @JoinColumn(name="user_id")
//    @OneToOne(fetch = FetchType.EAGER)
//    private User user;

}
