package com.anacleto.shoppingcart.service;

import com.anacleto.shoppingcart.model.Item;
import com.anacleto.shoppingcart.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    /**
     * Save Item to db
     *
     * @param item
     * @return
     */
    public Item saveItem(Item item) {
        return itemRepository.save(item);
    }

    /**
     * updates an item if it exists on db
     * if not throws EmptyResultDataAccessException
     * @param itemId
     * @param item
     * @return
     */
    public Item updateItem(String itemId, Item item) {
        Optional<Item> itemOptional = itemRepository.findById(itemId);

        if (!itemOptional.isPresent()) {
            throw new EmptyResultDataAccessException(1);
        }

        item.setId(itemOptional.get().getId());
        return itemRepository.save(item);
    }
}
