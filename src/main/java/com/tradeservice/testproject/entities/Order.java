package com.tradeservice.testproject.entities;

import java.util.Date;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Order1")
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long orderId;

  @Column(name = "Client", nullable = false)
  private String client;

  @Column(name = "Date")
  private Date date;

  @Column(name = "Address", nullable = false)
  private String address;

  @OneToMany(mappedBy = "order_items")
  private Set<OrderLine> orderLineSet;

  public Order() {
  }

  public Order(String client, Date date, String address) {
    this.client = client;
    this.date = date;
    this.address = address;
  }

  public Long getOrderId() {
    return orderId;
  }

  public void setOrderId(Long orderId) {
    this.orderId = orderId;
  }

  public String getClient() {
    return client;
  }

  public void setClient(String client) {
    this.client = client;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  @Override
  public String toString() {
    return "Order{" +
        "orderId=" + orderId +
        ", client='" + client + '\'' +
        ", date=" + date +
        ", address='" + address + '\'' +
        '}';
  }
}


