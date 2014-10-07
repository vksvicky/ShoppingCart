package sc;

import org.hamcrest.Matchers;
import org.hamcrest.core.IsNull;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Created by vivek on 07/10/2014.
 */
public class ShoppingCartTest {

    public static final String APPLE = "Apple";
    public static final String ORANGE = "Orange";
    ShoppingCart shoppingCart;
    Set<String> defaultItems;

    @Before
    public void setUp() {
        shoppingCart = new ShoppingCart();
        defaultItems = new HashSet<>(Arrays.asList(APPLE, ORANGE));
    }

    @Test
    public void testShoppingCartIsEmpty() {
        assertThat(shoppingCart.getShoppingItems(), is(IsNull.notNullValue()));
    }

    @Test
    public void testOnlyOneItemInCart() {
        shoppingCart.addShoppingItem(new Item(APPLE, 1, 0.60));

        int numberOfItems = shoppingCart.getShoppingItems().size();

        assertThat(numberOfItems, is(1));
    }

    @Test
    public void testOnlyTwoItemsInCart() {
        shoppingCart.addShoppingItem(new Item(APPLE, 1, 0.60));
        shoppingCart.addShoppingItem(new Item(ORANGE, 1, 0.25));

        int numberOfItems = shoppingCart.getShoppingItems().size();

        assertThat(numberOfItems, is(2));
    }

    @Test
    public void testShoppingCartContainsApplesAndThreeApples() {
        shoppingCart.addShoppingItem(new Item(APPLE, 3, 0.60));

//        assertThat(shoppingCart.getShoppingItems(), containsInAnyOrder(hasProperty("name", is("Apple")), hasProperty("quantity", equalTo(3))));

        assertThat(shoppingCart.getShoppingItems(), containsInAnyOrder(hasProperty("name", is(APPLE))));
        assertThat(shoppingCart.getShoppingItems(), hasItem(Matchers.<Item>hasProperty("quantity", equalTo(3))));

    }

    @Test
    public void testShoppingCartDoesNotContainItemsInSet() {
        shoppingCart.addShoppingItem(new Item("Banana", 1, 0.20));
        shoppingCart.addShoppingItem(new Item("Strawberry", 1, 0.15));

        for (Item _item : shoppingCart.getShoppingItems()) {
            assertEquals(false, defaultItems.contains(_item.getName()));
        }

    }

    @Test
    public void testTotalShoppingCartVale() {
        shoppingCart.addShoppingItem(new Item(APPLE, 3, 0.60));
        shoppingCart.addShoppingItem(new Item(ORANGE, 2, 0.25));

        double itemCost = 0.0;

        for (Item _item : shoppingCart.getShoppingItems()) {
            itemCost += _item.getQuantity() * _item.getPrice();
        }

        assertTrue(itemCost==2.3);
    }


}
