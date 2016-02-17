package com.pm.hibernate.service;

import com.pm.hibernate.dto.SampleDTO;
import com.pm.hibernate.model.Employee;
import com.pm.hibernate.model.Phone;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;

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
    public SampleDTO get() {
        logger.info("===get===");
        Employee employee = new Employee("Bob", "Way",
                new BigDecimal(100.50).setScale(2),
                LocalDate.of(1981, Month.JULY, 17));
        employee.addPhone( new Phone("home", "613", "792-0001"));
        employee.addPhone( new Phone("work", "613", "494-1234"));
        entityManager.persist(employee);
        logger.info("(1)" + employee);
        employee.setFirstName("Bobik");
        employee.setLastName("Wayek");
        logger.info("(2)" + employee);

        Employee employee2 = new Employee("Joe", "Smith",
                new BigDecimal(300.00).setScale(2),
                LocalDate.of(1941, Month.MARCH, 25));
        employee2.addPhone( new Phone("work", "416", "892-0005"));
        entityManager.persist(employee2);
        logger.info("(3)" + employee2);

        Employee employeeFind = entityManager.find(Employee.class, employee.getId());
        logger.info("(4)" + employeeFind);
        Employee employeeFind2 = entityManager.find(Employee.class, employee2.getId());
        logger.info("(5)" + employeeFind2);

        employeeFind.setFirstName("Bobas");
        employeeFind.setLastName("Wayas");
        logger.info("(6)" + employeeFind);
        entityManager.remove(employeeFind);
        entityManager.remove(employeeFind2);

        return new SampleDTO();
    }

    @Override
    public SampleDTO find() {
        logger.info("===find===");
        return new SampleDTO();
    }

    @Override
    public SampleDTO delete() {
        logger.info("===delete===");
        return new SampleDTO();
    }
}
