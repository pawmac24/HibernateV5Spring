package com.pm.hibernate.service;

import com.pm.hibernate.dto.SampleDTO;
import com.pm.hibernate.model.Order;
import com.pm.hibernate.model.OrderItem;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

/**
 * Created by pmackiewicz on 2016-02-03.
 */
@Service
@Transactional
public class TestServiceImpl implements TestService {

    public final static Logger logger = Logger.getLogger(TestService.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public SampleDTO insertAll() {
        logger.info("===insertAll===");

        Order order1 = new Order("ABC0000001", LocalDate.of(2015, Month.JANUARY, 17));
        entityManager.persist(order1);
        OrderItem orderItem11 = new OrderItem("A", 1L, new BigDecimal(100.50).setScale(2), order1);
        order1.addItem(orderItem11);
        OrderItem orderItem12 = new OrderItem("B", 2L, new BigDecimal(200.00).setScale(2), order1);
        order1.addItem(orderItem12);
        OrderItem orderItem13 = new OrderItem("C", 3L, new BigDecimal(40.00).setScale(2), order1);
        order1.addItem(orderItem13);

        Order order2 = new Order("ABC0000002", LocalDate.of(2015, Month.FEBRUARY, 21));
        entityManager.persist(order2);
        OrderItem orderItem21 = new OrderItem("A", 3L, new BigDecimal(100.50).setScale(2), order2);
        order2.addItem(orderItem21);
        OrderItem orderItem22 = new OrderItem("B", 2L, new BigDecimal(200.00).setScale(2), order2);
        order2.addItem(orderItem22);

        Order order3 = new Order("ABC0000003", LocalDate.of(2015, Month.MARCH, 2));
        entityManager.persist(order3);
        OrderItem orderItem31 = new OrderItem("B", 1L, new BigDecimal(100.50).setScale(2), order3);
        order3.addItem(orderItem31);
        OrderItem orderItem32 = new OrderItem("C", 1L, new BigDecimal(200.00).setScale(2), order3);
        order3.addItem(orderItem32);

        return new SampleDTO();
    }

    @Override
    public SampleDTO findAll() {
        logger.info("===findAll===");

        TypedQuery<Order> query = entityManager.createQuery("SELECT o FROM Order o", Order.class);
        List<Order> orderList = query.getResultList();

        orderList.forEach(order -> logger.info(order));

        return new SampleDTO();
    }

    @Override
    public SampleDTO deleteAll() {
        logger.info("===deleteAll===");

        TypedQuery<Order> query = entityManager.createQuery("SELECT o FROM Order o", Order.class);
        List<Order> orderList = query.getResultList();
        for (Order order :  orderList){
            entityManager.remove(order);
        }

        return new SampleDTO();
    }
}
