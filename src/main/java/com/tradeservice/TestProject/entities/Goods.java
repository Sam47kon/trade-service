package com.tradeservice.TestProject.entities;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Goods")
public class Goods {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long goodsId;

  @Column(name = "Name", nullable = false)
  private String name;

  @Column(name = "Price")
  private Double price;

  @OneToMany(mappedBy = "goods")
  private
  Set<OrderLine> orderLineSet;

  public Goods() {
  }

  public Goods(String name, Double price) {
    this.name = name;
    this.price = price;
  }

  public Long getGoodsId() {
    return goodsId;
  }

  public void setGoodsId(Long goodsId) {
    this.goodsId = goodsId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public Set<OrderLine> getOrderLineSet() {
    return orderLineSet;
  }

  public void setOrderLineSet(Set<OrderLine> orderLineSet) {
    this.orderLineSet = orderLineSet;
  }

  @Override
  public String toString() {
    return "Goods{" +
        "goodsId=" + goodsId +
        ", name='" + name + '\'' +
        ", price=" + price +
        '}';
  }
}
