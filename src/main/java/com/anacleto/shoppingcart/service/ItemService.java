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

    /**
     * get all items from db
     *
     * @return items list
     */
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    /**
     * Query item by id
     *
     * @param itemId
     * @return item
     */
    public Optional<Item> getItemById(String itemId) {
        return itemRepository.findById(itemId);
    }

    /**
     * Save Item to db
     *
     * @param item
     * @return item
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

    /**
     * Recieves an item id, check if it exists on db and if so, delete it
     * else return resource not found
     *
     * @param itemId
     */
    public void deleteItem(String itemId) {
        if (!getItemById(itemId).isPresent()) {
            throw new EmptyResultDataAccessException(1);
        }

        itemRepository.deleteById(itemId);
    }
}
