package ua.hubanov.onlinestore_springboot.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.method.P;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Getter
@Setter

@Entity
@Table(name="ordered_products")
public class OrderedProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", insertable = false, updatable = false, nullable = false)
    private Long id;

    @OneToOne(cascade={CascadeType.PERSIST})
    @JoinColumn(name="product_id")
    private Product product;

    @Column(name = "name", nullable = false)
    @NotNull
    @Pattern(regexp = "^[^#$%^&*()']*$")
    private String name;

    @Column(name = "quantity", nullable = false)
    @NotNull
    private Integer quantity;

    @Column(name = "price", nullable = false)
    @NotNull
    private Double price;

    // @Column(name = "category")
    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name="order_id")
    private Order order;

}
