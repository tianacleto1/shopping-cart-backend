package com.anacleto.shoppingcart.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.List;

@Getter @Setter
@EqualsAndHashCode(exclude = {"items", "total"})
@Document(collection = "shopping_cart")
public class ShoppingCart {

    @Id
    private String id;

    @DBRef
    private List<Item> items;
    private BigDecimal total;
}
