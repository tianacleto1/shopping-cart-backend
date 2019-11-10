package com.anacleto.shoppingcart.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Getter @Setter @ToString
@EqualsAndHashCode(exclude = {"name", "price"})
@Document(collection = "items")
public class Item {

    @Id
    private String id;

    @NotEmpty(message = "Item name can not be empty!")
    @Size(max = 80, message = "Item name can not be more than 80 characters!")
    @Indexed(name = "prodName")
    private String name;

    @Range(min = 1, message = "Price can not be less or equal to 0!")
    private BigDecimal price;

    private int quantity;
}
