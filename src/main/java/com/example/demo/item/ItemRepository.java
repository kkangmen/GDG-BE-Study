package com.example.demo.item;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ItemRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Item item){
        em.persist(item); // insert
    }

    public List<Item> findAll(){
        return em.createQuery("select i from Item i", Item.class).getResultList();
    }

    public Item findById(Long itemId){
        return em.find(Item.class, itemId);
    }

    public void update(Long itemId, Item updateParam){
        Item item = em.find(Item.class, itemId);

        item.setItemName(updateParam.getItemName());
        item.setPrice(updateParam.getPrice());
        item.setQuantity(updateParam.getQuantity());
    }

    public void delete(Long itemId){
        Item item = em.find(Item.class, itemId);
        em.remove(item);
    }
}
