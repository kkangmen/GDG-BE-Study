package com.example.demo.item;

import com.example.demo.item.dto.ItemCreateRequest;
import com.example.demo.item.dto.ItemUpdateRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    // 상품 정보 등록
    @Transactional
    public Long createItem(ItemCreateRequest request){
        Item item = new Item(request.getItemName(), request.getPrice(), request.getQuantity());

        itemRepository.save(item);
        return item.getItemId();
    }

    // 상품 목록 조회
    @Transactional
    public List<Item> getAllItems(){
        return itemRepository.findAll();
    }

    // 개별 상품 정보 상세 조회
    @Transactional
    public Item getItem(Long itemId){
        Item item = itemRepository.findById(itemId);

        if (item == null){
            throw new RuntimeException("아이템이 존재하지 않습니다.");
        }

        return item;
    }

    // 상품 정보 수정
    @Transactional
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
    public void deleteItem(Long itemId){
        itemRepository.delete(itemId);
    }
}
