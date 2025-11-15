package com.example.demo.item.service;

import com.example.demo.item.dto.ItemCreateRequest;
import com.example.demo.item.dto.ItemUpdateRequest;
import com.example.demo.item.entity.Item;
import com.example.demo.item.repository.ItemRepository;
import com.example.demo.item.repository.JpaItemRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Primary
public class ItemServiceImpl implements ItemService{

    private final ItemRepository itemRepository;

    // 상품 정보 등록
    @Transactional
    @Override
    public Long createItem(ItemCreateRequest request){
        Item item = new Item(request.getItemName(), request.getPrice(), request.getQuantity());

        itemRepository.save(item);
        return item.getItemId();
    }

    // 상품 목록 조회
    @Transactional
    @Override
    public List<Item> getAllItems(){
        return itemRepository.findAll();
    }

    // 개별 상품 정보 상세 조회
    @Transactional
    @Override
    public Item getItem(Long itemId){
        Item item = itemRepository.findById(itemId);

        if (item == null){
            throw new RuntimeException("아이템이 존재하지 않습니다.");
        }

        return item;
    }

    // 상품 정보 수정
    @Transactional
    @Override
    public void updateItem(Long itemId, ItemUpdateRequest request){
        Item item = itemRepository.findById(itemId);

        if (item == null){
            throw new RuntimeException("아이템이 존재하지 않습니다.");
        }

        Item updateParam = new Item(request.getItemName(), request.getPrice(), request.getQuantity());
        itemRepository.update(itemId, updateParam);
    }

    // 상품 삭제
    @Transactional
    @Override
    public void deleteItem(Long itemId){
        itemRepository.delete(itemId);
    }
}
