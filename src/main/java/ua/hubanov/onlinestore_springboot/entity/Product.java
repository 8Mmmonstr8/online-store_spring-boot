package ua.hubanov.onlinestore_springboot.entity;

import lombok.*;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Getter
@Setter
@EqualsAndHashCode

@Entity
@Table(name="products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", insertable = false, updatable = false, nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    @NotNull
    @Pattern(regexp = "^[^#$%^&*()']*$")
    private String name;

    @Column(name = "quantity", nullable = false)
    @NotNull
    private Integer quantity;

    @Column(name = "price", nullable = false)
    @NotNull
    @DecimalMin(value = "0.00", message = "*Price has to be non negative number")
    private BigDecimal price;

   // @Column(name = "category")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="category_id")
    private Category category;

    @Column(name = "description")
    private String description;

    @Column(name = "date", nullable = false)
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date publicationDate;



}
