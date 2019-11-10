package com.anacleto.shoppingcart.resource;

import com.anacleto.shoppingcart.model.Item;
import com.anacleto.shoppingcart.service.ItemService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.singletonList;
import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(ItemResource.class)
public class ItemResourceTest {

    private static Item itemMock;

    static {
        itemMock = new Item();
        itemMock.setId("id");
        itemMock.setName("name");
        itemMock.setPrice(new BigDecimal(20));
    }
    /*
    @Autowired
    private MockMvc mockMvc; */

    @Mock
    private ItemService itemServiceMock;

    @InjectMocks
    private ItemResource itemResource;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void whenGetAllItemsIsCalledThenItShouldReturnItemListTest() {
        when(itemServiceMock.getAllItems()).thenReturn(singletonList(itemMock));

        List<Item> items = itemResource.getItemList();

        assertEquals("name", items.get(0).getName());
    }

    @Test
    public void whenFindItemByIdIsCalledAndItExistsOnDBThenItShouldReturnItTest() {
        when(itemServiceMock.getItemById(any())).thenReturn(Optional.of(itemMock));

        assertEquals("name", requireNonNull(itemResource.findItemById("id").getBody()).getName());
    }

    @Test
    public void w1henSaveItemIsCalledThenItShouldReturnSavedItemTest() {
        when(itemServiceMock.saveItem(any())).thenReturn(itemMock);

        assertEquals("name", requireNonNull(itemResource.saveProduct(itemMock).getBody()).getName());
    }

    @Test
    public void whenUpdateItemIsCalledThenItShouldUpdateTheItemTest() {
        when(itemServiceMock.updateItem(any(), any())).thenReturn(itemMock);

        assertEquals("name", requireNonNull(
                                itemResource.updateItem("id", itemMock).getBody()).getName());
    }

    @Test
    public void whenRemoveItemIsCalledThenItShouldDeleteTheItemTest() {
        doNothing().when(itemServiceMock).deleteItem(any());

        itemResource.removeItem("id");
    }
}
