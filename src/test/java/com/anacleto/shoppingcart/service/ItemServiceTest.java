package com.anacleto.shoppingcart.service;

import com.anacleto.shoppingcart.model.Item;
import com.anacleto.shoppingcart.repository.ItemRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.dao.EmptyResultDataAccessException;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ItemServiceTest {

    private static Item itemMock;

    static {
        itemMock = new Item();
        itemMock.setId("id");
        itemMock.setName("name");
        itemMock.setPrice(new BigDecimal(20));
    }

    @Mock
    private ItemRepository itemRepositoryMock;

    @InjectMocks
    private ItemService itemService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void whenGetItemsIsCalledThenItShouldReturnListOfItemsTest() {
        when(itemRepositoryMock.findAll()).thenReturn(Collections.singletonList(itemMock));

        List<Item> items = itemService.getAllItems();

        assertEquals("id", items.get(0).getId());
        assertEquals("name", items.get(0).getName());
        assertEquals(BigDecimal.valueOf(20), items.get(0).getPrice());
    }

    @Test
    public void whenSaveItemIsCalledThenItShouldSaveAndReturnSavedItemTest() {
        when(itemRepositoryMock.save(any())).thenReturn(itemMock);

        Item item = itemService.saveItem(any());

        assertEquals("name", item.getName());
    }

    @Test
    public void whenUpdateItemIsCalledWithExistingIdItemOnDBThenItShouldUpdateTheItemTest() {
        when(itemRepositoryMock.findById(any())).thenReturn(Optional.of(itemMock));
        when(itemRepositoryMock.save(any())).thenReturn(itemMock);

        Item savedProduct = itemService.updateItem("id", itemMock);

        assertEquals("name", savedProduct.getName());
    }

    @Test
    public void whenUpdateItemIsCalledWithNonExistingIdItemOnDBThenItShouldThrowExceptionTest() {
        when(itemRepositoryMock.findById(any())).thenReturn(Optional.empty());

        try {
            itemService.updateItem("id", itemMock);
            fail("It should had thrown EmptyResultDataAccessException");
        } catch (EmptyResultDataAccessException e) {
            assertTrue(Objects.requireNonNull(e.getMessage()).contains("Incorrect result size: "));
        }
    }

}
