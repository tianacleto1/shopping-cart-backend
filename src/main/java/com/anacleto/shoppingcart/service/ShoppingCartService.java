package com.anacleto.shoppingcart.service;

import com.anacleto.shoppingcart.model.ShoppingCart;
import com.anacleto.shoppingcart.repository.ItemRepository;
import com.anacleto.shoppingcart.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartService {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private ItemRepository itemRepository;

    public List<ShoppingCart> getShoppingCart() {
        return shoppingCartRepository.findAll();
    }
}
