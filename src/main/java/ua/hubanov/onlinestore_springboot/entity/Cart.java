package ua.hubanov.onlinestore_springboot.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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

    @JoinColumn(name = "product_id")
    @OneToMany
    private Set<Product> products;

    @JoinColumn(name = "order_id")
    @OneToMany
    private Set<Order> orders = new HashSet<>();

    @JoinColumn(name="user_id")
    @OneToOne
    private User user;

}
