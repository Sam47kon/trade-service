package com.tradeservice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@Table(name = "order_item")
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {

  public OrderItem(Product product, int count) {
    this.product = product;
    this.count = count;
  }

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
}
