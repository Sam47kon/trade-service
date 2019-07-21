package com.tradeservice.TestProject.Entities;

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
  private Order1 order1;

  @ManyToOne()
  @JoinColumn(name = "goodsId")
  private Goods goods;

  @Column(name = "Count")
  private int count;

  public OrderLine() {
  }

  public OrderLine(Order1 order1, Goods goods, int count) {
    this.order1 = order1;
    this.goods = goods;
    this.count = count;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Order1 getOrder1() {
    return order1;
  }

  public void setOrder1(Order1 order1) {
    this.order1 = order1;
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
        ", order1=" + order1 +
        ", goods=" + goods +
        ", count=" + count +
        '}';
  }
}
