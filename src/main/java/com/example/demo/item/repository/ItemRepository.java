package com.example.demo.item.repository;

import com.example.demo.item.entity.Item;

import java.util.List;

public interface ItemRepository {
    void save(Item item);
    List<Item> findAll();
    Item findById(Long itemId);
    void update(Long itemId, Item updateParam);
    void delete(Long itemId);
}
