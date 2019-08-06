package com.tradeservice.entities;

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
@Table(name = "order_lines")
@NoArgsConstructor
@AllArgsConstructor
public class OrderLine {

  public OrderLine(Goods goods, int count) {
    this.goods = goods;
    this.count = count;
  }


  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "orderId")
  @Getter(AccessLevel.NONE)
  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  private Order orderItem;

  @ManyToOne
  @JoinColumn(name = "goodsId")
  private Goods goods;

  @Column(name = "Count")
  private int count;
}
