package com.example.demo.item.service;

import com.example.demo.item.dto.ItemCreateRequest;
import com.example.demo.item.dto.ItemUpdateRequest;
import com.example.demo.item.entity.Item;

import java.util.List;

public interface ItemService {
    Long createItem(ItemCreateRequest request);
    List<Item> getAllItems();
    Item getItem(Long itemId);
    void updateItem(Long itemId, ItemUpdateRequest requeset);
    void deleteItem(Long itemId);
}
