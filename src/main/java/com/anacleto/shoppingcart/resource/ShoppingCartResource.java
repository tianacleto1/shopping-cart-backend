package com.anacleto.shoppingcart.resource;

import com.anacleto.shoppingcart.model.Item;
import com.anacleto.shoppingcart.model.ShoppingCart;
import com.anacleto.shoppingcart.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/shopping_cart/cart")
public class ShoppingCartResource {

    @Autowired
    private ShoppingCartService shoppingCartService;

    /**
     * Endpoint that returns shopping cart from db
     *
     * @return shopping cart
     */
    @GetMapping
    public List<ShoppingCart> getShoppingCart() {
        return shoppingCartService.getShoppingCart();
    }
}
