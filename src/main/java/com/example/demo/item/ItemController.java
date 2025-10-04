package com.example.demo.item;

import com.example.demo.item.dto.ItemCreateRequest;
import com.example.demo.item.dto.ItemUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;

    // 상품 등록
    @PostMapping
    public ResponseEntity<Void> createItem(@RequestBody ItemCreateRequest request){
        Long itemId = itemService.createItem(request);
        return ResponseEntity.created(URI.create("/items/" + itemId)).build();
    }

    // 상품 목록 조회
    @GetMapping
    public ResponseEntity<List<Item>> getAllItems(){
        List<Item> itemList = itemService.getAllItems();
        return ResponseEntity.ok(itemList);
    }

    // 개별 상품 정보 상세 조회
    @GetMapping("/{itemId}")
    public ResponseEntity<Item> getItem(@PathVariable Long itemId){
        Item item = itemService.getItem(itemId);
        return ResponseEntity.ok(item);
    }

    // 상품 정보 수정
    @PatchMapping("/{itemId}")
    public ResponseEntity<Void> updateItem(@PathVariable Long itemId, @RequestBody ItemUpdateRequest request){
        itemService.updateItem(itemId, request);
        return ResponseEntity.ok().build();
    }

    // 상품 삭제
    @DeleteMapping("/{itemId}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long itemId){
        itemService.deleteItem(itemId);
        return ResponseEntity.ok().build();
    }
}
