package com.anacleto.shoppingcart.repository;

import com.anacleto.shoppingcart.model.Item;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ItemRepository extends MongoRepository<Item, String> {
}
