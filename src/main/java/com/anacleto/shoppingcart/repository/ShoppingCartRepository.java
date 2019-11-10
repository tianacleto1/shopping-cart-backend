package com.anacleto.shoppingcart.repository;

import com.anacleto.shoppingcart.model.ShoppingCart;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ShoppingCartRepository extends MongoRepository<ShoppingCart, String> {
}
