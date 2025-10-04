package com.example.demo.item;

import com.example.demo.item.dto.ItemCreateRequest;
import com.example.demo.item.dto.ItemUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    // 상품 등록
    public Long createItem(ItemCreateRequest request){
        Item item = new Item(request.getItemName(), request.getPrice(), request.getQuantity());

        itemRepository.save(item);
        return item.getItemId();
    }

    // 상품 목록 조회
    public List<Item> getAllItems(){
        return itemRepository.findAll();
    }

    // 개별 상품 상세 조회
    public Item getItem(Long itemId){
        return itemRepository.findById(itemId);
    }

    // 상품 정보 수정
    public void updateItem(Long itemId, ItemUpdateRequest request){
        Item updateParam = new Item(request.getItemName(), request.getPrice(), request.getQuantity());
        itemRepository.update(itemId, updateParam);
    }

    // 상품 삭제
    public void deleteItem(Long itemId){
        itemRepository.delete(itemId);
    }
}
