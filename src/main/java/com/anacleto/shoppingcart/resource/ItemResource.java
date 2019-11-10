package com.anacleto.shoppingcart.resource;

import com.anacleto.shoppingcart.model.Item;
import com.anacleto.shoppingcart.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/shopping_cart/items")
public class ItemResource {

    @Autowired
    private ItemService itemService;

    /**
     * Endpoint that returns items list from db
     *
     * @return list of items
     */
    @GetMapping
    public List<Item> getItemList() {
        return itemService.getAllItems();
    }

    /**
     * Endpoint receives an idItem and query the db to recover it
     *
     * @param itemId
     * @return item
     */
    @GetMapping("/{itemId}")
    public ResponseEntity<Item> findItemById(@PathVariable String itemId) {
        Optional<Item> itemOp = itemService.getItemById(itemId);

        return itemOp.isPresent() ? ResponseEntity.ok().body(itemOp.get())
                                  : ResponseEntity.notFound().build();
    }

    /**
     * Endpoint receives an Item entity from request body
     * and passes it to service layer to be saved on DB.
     *
     * @param item
     * @return item entity saved
     */
    @PostMapping
    public ResponseEntity<Item> saveProduct(@Valid @RequestBody Item item) {
        Item savedProduct = itemService.saveItem(item);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }

    /**
     * Endpoint receives an Item emtity from request body
     * and passes it to service layer to be updated if it exists on DB.
     * @param itemId
     * @param item
     * @return item
     */
    @PutMapping("/{itemId}")
    public ResponseEntity<Item> updateItem(@PathVariable String itemId, @Valid @RequestBody Item item) {
        return ResponseEntity.ok(itemService.updateItem(itemId, item));
    }

    /**
     * Deletes an item based on whether it exists on DB
     *
     * @param itemId
     */
    @DeleteMapping("/{itemId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeItem(@PathVariable String itemId) {
        itemService.deleteItem(itemId);
    }
}
