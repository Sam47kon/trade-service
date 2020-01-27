package com.tradeservice.project.entity;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Table(name = "order_item")
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "parent_order_ID")
    @Getter(AccessLevel.NONE)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Order parentOrder;

    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;

    @Column(name = "count")
    private int count;

    public OrderItem(Product product, int count) {
        this.product = product;
        this.count = count;
    }
}
