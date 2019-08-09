package com.tradeservice.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
public class Order {


  public Order(String client, Date date, String address) {
    this.client = client;
    this.date = date;
    this.address = address;
  }


  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long orderId;

  @Column(name = "Client", nullable = false)
  private String client;

  @Column(name = "Date")
  @JsonFormat(pattern = "yyyy-MM-dd")
  private Date date;

  @Column(name = "Address", nullable = false)
  private String address;

  @OneToMany(mappedBy = "orderItem",orphanRemoval = true)
  private Set<OrderLine> orderLines = new HashSet<>();
}
