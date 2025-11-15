package com.example.demo.order.repository;

import com.example.demo.order.entity.Order;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Primary
public class JpaOrderRepository implements OrderRepository{

    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(Order order){
        em.persist(order);
    }

    @Override
    public List<Order> findAll(){
        return em.createQuery("select o from Order o", Order.class).getResultList();
    }

    @Override
    public Order findById(Long orderId){
        return em.find(Order.class, orderId);
    }

    @Override
    public void delete(Long orderId){
        Order order = em.find(Order.class, orderId);
        em.remove(order);
    }
}
