package com.tradeservice.project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "product")
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    @Column(name = "name", nullable = false, unique = true)
    private String name;
    @Column(name = "price", nullable = false)
    private Double price;

    public Product(String name, Double price) {
        this.name = name;
        this.price = price;
    }
}
