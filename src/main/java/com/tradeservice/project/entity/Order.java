package com.tradeservice.project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;

import static javax.persistence.CascadeType.*;

@Data
@Entity
@Table(name = "order_")
@NoArgsConstructor
@AllArgsConstructor
public class Order {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @Column(name = "clientName", nullable = false)
    private String clientName;

    @Column(name = "date")
//  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd")
//  @JsonFormat(pattern = "yyyy-MM-dd") // TODO  JsonDeserialize???
    private LocalDateTime date;

    @Column(name = "address", nullable = false)
    private String address;

    @OneToMany(fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "parentOrder",
            cascade = {MERGE, PERSIST, REFRESH})
    private Collection<OrderItem> orderItems = new HashSet<>();

    public Order(String clientName, LocalDateTime date, String address) {
        this.clientName = clientName;
        this.date = date;
        this.address = address;
    }

    public Order(String clientName, LocalDateTime date, String address,
                 Collection<OrderItem> orderItems) {
        this.clientName = clientName;
        this.date = date;
        this.address = address;
        this.orderItems = orderItems;
    }

    public void setOrderItems(Collection<OrderItem> orderItems) {
        orderItems.forEach(this::addOrderItem);
    }

    private void addOrderItem(OrderItem orderItem) {
        orderItem.setParentOrder(this);
        this.orderItems.add(orderItem);
    }
}
