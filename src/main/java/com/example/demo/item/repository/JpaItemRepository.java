package com.example.demo.item.repository;

import com.example.demo.item.entity.Item;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Primary
public class JpaItemRepository implements ItemRepository{

    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(Item item){
        em.persist(item); // insert
    }

    @Override
    public List<Item> findAll(){
        return em.createQuery("select i from Item i", Item.class).getResultList();
    }

    @Override
    public Item findById(Long itemId){
        return em.find(Item.class, itemId);
    }

    @Override
    public void update(Long itemId, Item updateParam){
        Item item = em.find(Item.class, itemId);

        item.setItemName(updateParam.getItemName());
        item.setPrice(updateParam.getPrice());
        item.setQuantity(updateParam.getQuantity());
    }

    @Override
    public void delete(Long itemId){
        Item item = em.find(Item.class, itemId);
        em.remove(item);
    }
}
