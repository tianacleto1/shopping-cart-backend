package com.anacleto.shoppingcart.service;

import com.anacleto.shoppingcart.model.Item;
import com.anacleto.shoppingcart.model.ShoppingCart;
import com.anacleto.shoppingcart.repository.ItemRepository;
import com.anacleto.shoppingcart.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ShoppingCartService {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ItemService itemService;

    /**
     * Get the carts
     * @return
     */
    public List<ShoppingCart> getShoppingCart() {
        return shoppingCartRepository.findAll();
    }

    /**
     * Save ShoppingCart with items on db
     *
     * @param ShoppingCart
     * @return ShoppingCart
     */
    public ShoppingCart saveShoppingCart(ShoppingCart shoppingCart) {
        shoppingCart.getItems().forEach(i -> {
            Item item = itemRepository.findById(i.getId()).get();
            i.setPrice(item.getPrice());
            i.setQuantity(item.getQuantity());
        });

        BigDecimal totalPrice = shoppingCart.getItems().stream()
                                    .map(i -> i.getPrice().multiply(BigDecimal.valueOf(i.getQuantity())))
                                    .reduce(BigDecimal.ZERO, BigDecimal::add);

        shoppingCart.setTotal(totalPrice);

        return shoppingCartRepository.save(shoppingCart);
    }

}
