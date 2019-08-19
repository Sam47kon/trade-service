package com.tradeservice.project.entity;

import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.CascadeType.REFRESH;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "order_")
@NoArgsConstructor
@AllArgsConstructor
public class Order {


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


  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long orderId;

  @Column(name = "clientName", nullable = false)
  private String clientName;

  @Column(name = "date")
//  @JsonFormat(pattern = "yyyy-MM-dd") // TODO  JsonDeserialize???
  private LocalDateTime date;

  @Column(name = "address", nullable = false)
  private String address;

  @OneToMany(fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "parentOrder",
      cascade = {MERGE, PERSIST, REFRESH})
  private Collection<OrderItem> orderItems = new HashSet<>();

  public void setOrderItems(Collection<OrderItem> orderItems) {
    orderItems.forEach(this::addOrderItem);
  }

  private void addOrderItem(OrderItem orderItem) {
    orderItem.setParentOrder(this);
    this.orderItems.add(orderItem);
  }
}
