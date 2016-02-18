package com.pm.hibernate.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * Created by pmackiewicz on 2016-02-18.
 */
@Entity
@Table(name = "order_items")
@Data
@ToString(exclude = "order")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="item_id")
    private Long id;

    @Column(name="name", nullable = false, length = 50)
    private String name;

    @Column(name="number", nullable = false)
    private Long number;

    @Column(name="price", nullable = false, scale=10, precision=2)
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    protected OrderItem() {
    }

    public OrderItem(String name, Long number, BigDecimal price, Order order) {
        this.name = name;
        this.number = number;
        this.price = price;
        this.order = order;
    }

}
