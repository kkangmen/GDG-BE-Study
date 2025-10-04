package com.example.demo.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach(){
        itemRepository.clear();
    }

    @Test
    void save() {
        // given
        Item item = new Item("itemA", 10000, 100);

        // when
        Item savedItem = itemRepository.save(item);

        // then
        Item findItem = itemRepository.findById(item.getItemId());
        assertThat(savedItem.getItemName()).isEqualTo(findItem.getItemName());
    }

    @Test
    void findAll() {
        // given
        Item itemA = new Item("itemA", 10000, 100);
        Item itemB = new Item("itemB", 20000, 200);
        itemRepository.save(itemA);
        itemRepository.save(itemB);

        // when
        List<Item> itemList = itemRepository.findAll();

        // then
        assertThat(itemList.size()).isEqualTo(2);
        assertThat(itemList).contains(itemA, itemB);
    }

    @Test
    void findById() {
        // given
        Item item = new Item("itemA", 10000, 100);
        itemRepository.save(item);

        // when
        Item findItem = itemRepository.findById(item.getItemId());

        // then
        assertThat(findItem.getItemId()).isEqualTo(item.getItemId());
    }

    @Test
    void update() {
        // given
        Item item = new Item("itemA", 10000, 100);
        itemRepository.save(item);
        Long itemId = item.getItemId();

        // when
        Item updateItem = new Item("itemB", 20000, 200);
        itemRepository.update(item.getItemId(), updateItem);

        // then
        assertThat(itemRepository.findById(itemId).getItemName()).isEqualTo("itemB");
        assertThat(itemRepository.findById(itemId).getPrice()).isEqualTo(20000);
        assertThat(itemRepository.findById(itemId).getQuantity()).isEqualTo(200);
    }

    @Test
    void delete() {
        // given
        Item itemA = new Item("itemA", 10000, 100);
        itemRepository.save(itemA);

        // when
        itemRepository.delete(itemA.getItemId());

        // then
        assertThat(itemRepository.findAll()).doesNotContain(itemA);
    }

    @Test
    void clear() {
    }
}