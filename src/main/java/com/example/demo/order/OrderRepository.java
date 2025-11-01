package com.example.demo.order;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Order order){
        em.persist(order);
    }

    public List<Order> findAll(){
        return em.createQuery("select o from Order o", Order.class).getResultList();
    }

    public Order findById(Long orderId){
        return em.find(Order.class, orderId);
    }

    public void delete(Long orderId){
        Order order = em.find(Order.class, orderId);
        em.remove(order);
    }
}
