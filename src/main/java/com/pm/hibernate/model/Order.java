package com.pm.hibernate.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pmackiewicz on 2016-02-18.
 */
@Entity
@Table(name = "orders")
@Data
@ToString(exclude = "items")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="order_id")
    private Long id;

    @Column(name = "order_number", length = 10, nullable = false)
    private String orderNumber;

    @Column(name = "order_date", nullable = false)
    private LocalDate orderDate;

    //@OneToMany(mappedBy="order", cascade= CascadeType.ALL, orphanRemoval=true)
    @OneToMany(mappedBy="order", cascade = CascadeType.ALL)
    private List<OrderItem> items;

    protected Order() {
    }

    public Order(String orderNumber, LocalDate orderDate) {
        this.orderNumber = orderNumber;
        this.orderDate = orderDate;
    }


    public void addItem(OrderItem orderItem) {
        if (items == null || items.isEmpty()) {
            items = new ArrayList<>();
        }
        items.add(orderItem);
    }
}
