package com.tradeservice.testproject.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "OrderLine")
public class OrderLine {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @ManyToOne()
  @JoinColumn(name = "orderId")
  private Order order_items;

  @ManyToOne()
  @JoinColumn(name = "goodsId")
  private Goods goods;

  @Column(name = "Count")
  private int count;

  public OrderLine() {
  }

  public OrderLine(Order order_items, Goods goods, int count) {
    this.order_items = order_items;
    this.goods = goods;
    this.count = count;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Order getOrder_items() {
    return order_items;
  }

  public void setOrder_items(Order order_items) {
    this.order_items = order_items;
  }

  public Goods getGoods() {
    return goods;
  }

  public void setGoods(Goods goods) {
    this.goods = goods;
  }

  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }

  @Override
  public String toString() {
    return "OrderLine{" +
        "id=" + id +
        ", order_items=" + order_items +
        ", goods=" + goods +
        ", count=" + count +
        '}';
  }
}
