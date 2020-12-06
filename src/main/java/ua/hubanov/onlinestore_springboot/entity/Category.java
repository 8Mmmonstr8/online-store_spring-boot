package ua.hubanov.onlinestore_springboot.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Getter
@Setter

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", insertable = false, updatable = false, nullable = false)
    private Long id;

    @NotEmpty
    @Column(name = "name", nullable = false)
    @Size(max = 25)
    private String name;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
//    private Set<Product> products;

}