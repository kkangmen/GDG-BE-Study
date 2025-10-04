package com.example.demo.item;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ItemRepository {

    private static final Map<Long, Item> store = new HashMap<>();
    private static Long sequence = 0L;

    /*상품 등록
    *
    * @param Item
    * return Item*/
    public Item save(Item item){
//        // 같은 상품이름이 존재하는 경우
//        String itemName = item.getItemName();
//        boolean hasSameName = false;
//        Item sameItem = new Item();
//        for (Item i : store.values()){
//            if (i.getItemName().equals(itemName)){
//                hasSameName = true;
//                sameItem = i;
//            }
//        }
//
//        if (hasSameName){
//            sameItem.setQuantity(item.getQuantity() + sameItem.getQuantity());
//        }
//        else {
//            item.setItemId(++sequence);
//            store.put(item.getItemId(), item);
//        }
        item.setItemId(++sequence);
        store.put(item.getItemId(), item);
        return item;
    }

    /*
    * 전체 상품 목록 조회
    *
    * return 상품 리스트*/
    public List<Item> findAll(){
        return new ArrayList<>(store.values());
    }

    /*개별 상품 정보 조회
    *
    * @param itemId
    *
    * return Item*/
    public Item findById(Long itemId){
        return store.get(itemId);
    }

    /*상품 수정
    *
    * @param itemId
    */
    public void update(Long itemId, Item updateParam){
        Item item = store.get(itemId);
        item.setItemName(updateParam.getItemName());
        item.setPrice(updateParam.getPrice());
        item.setQuantity(updateParam.getQuantity());
    }

    /*상품 삭제
    *
    * @param itemId
    * */
    public void delete(Long itemId){
        store.remove(itemId);
    }

    /* 테스트용
    * repository clear*/
    public void clear(){
        store.clear();
    }
}
