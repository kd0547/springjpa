package org.example.springweb1.service;

import lombok.RequiredArgsConstructor;
import org.example.springweb1.domain.item.Item;
import org.example.springweb1.repository.ItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;


    @Transactional
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public Item itemOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }




}
