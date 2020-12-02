package ua.hubanov.onlinestore_springboot.entity;

import lombok.*;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Getter
@Setter

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
    private Double price;

   // @Column(name = "category")
    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;

    @Column(name = "description")
    private String description;

    @Column(name = "date", nullable = false)
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date publicationDate;



}
