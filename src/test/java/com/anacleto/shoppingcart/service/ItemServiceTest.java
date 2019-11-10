package com.anacleto.shoppingcart.service;

import com.anacleto.shoppingcart.model.Item;
import com.anacleto.shoppingcart.repository.ItemRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

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
    public void whenGetProductsIsCalled_itShouldReturnListOfProductsTest() {
        when(itemRepositoryMock.findAll()).thenReturn(Arrays.asList(itemMock));

        List<Item> items = itemService.getAllItems();

        Assert.assertEquals("id", items.get(0).getId());
        Assert.assertEquals("name", items.get(0).getName());
        Assert.assertEquals(BigDecimal.valueOf(20), items.get(0).getPrice());
    }

}
