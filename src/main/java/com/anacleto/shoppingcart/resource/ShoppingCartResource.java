package com.anacleto.shoppingcart.resource;

import com.anacleto.shoppingcart.model.ShoppingCart;
import com.anacleto.shoppingcart.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    /**
     * Endpoint receives an Cart entity from request body
     * and passes it to service layer to be saved on DB.
     *
     * @param shoppingCart
     * @return shoppingCart entity saved
     */
    @PostMapping
    public ResponseEntity<ShoppingCart> saveShoppingCart(@RequestBody ShoppingCart shoppingCart) {
        ShoppingCart savedCart = shoppingCartService.saveShoppingCart(shoppingCart);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedCart);
    }
}
