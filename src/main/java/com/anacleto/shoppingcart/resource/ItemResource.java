package com.anacleto.shoppingcart.resource;

import com.anacleto.shoppingcart.model.Item;
import com.anacleto.shoppingcart.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/shopping_cart/items")
public class ItemResource {

    @Autowired
    private ItemService itemService;

    /**
     * Endpoint that returns items list from db
     *
     * @return list of items
     */
    @GetMapping
    public List<Item> getProductList() {
        return itemService.getAllItems();
    }
}
