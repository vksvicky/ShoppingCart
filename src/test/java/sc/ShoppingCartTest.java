package sc;

import org.hamcrest.Matchers;
import org.hamcrest.core.IsNull;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * Created by vivek on 07/10/2014.
 */
public class ShoppingCartTest {

    ShoppingCart shoppingCart;

    @Before
    public void setUp() {
        shoppingCart = new ShoppingCart();
    }

    @Test
    public void testShoppingCartIsEmpty() {
        assertThat(shoppingCart.getShoppingItems(), is(IsNull.notNullValue()));
    }

    @Test
    public void testOnlyOneItemInCart() {
        shoppingCart.addShoppingItem(new Item(ItemEnum.Apple.toString(), 1, 0.60));

        int numberOfItems = shoppingCart.getShoppingItems().size();

        assertThat(numberOfItems, is(1));
    }

    @Test
    public void testOnlyTwoItemsInCart() {
        shoppingCart.addShoppingItem(new Item(ItemEnum.Apple.toString(), 1, 0.60));
        shoppingCart.addShoppingItem(new Item(ItemEnum.Orange.toString(), 1, 0.25));

        int numberOfItems = shoppingCart.getShoppingItems().size();

        assertThat(numberOfItems, is(2));
    }

    @Test
    public void testShoppingCartContainsApplesAndThreeApples() {
        shoppingCart.addShoppingItem(new Item(ItemEnum.Apple.toString(), 3, 0.60));

//        assertThat(shoppingCart.getShoppingItems(), containsInAnyOrder(hasProperty("name", is("Apple")), hasProperty("quantity", equalTo(3))));

        assertThat(shoppingCart.getShoppingItems(), containsInAnyOrder(hasProperty("name", is(ItemEnum.Apple.toString()))));
        assertThat(shoppingCart.getShoppingItems(), hasItem(Matchers.<Item>hasProperty("quantity", equalTo(3))));

    }

    @Test(expected = IllegalArgumentException.class)
    public void testShoppingCartDoesNotContainItemsInSet() {
        //shoppingCart.addShoppingItem(new Item(ItemEnum.Banana.toString(), 1, 0.20));
        shoppingCart.addShoppingItem(new Item("Strawberry", 1, 0.15));

        for (Item _item : shoppingCart.getShoppingItems()) {
            assertEquals(false, ItemEnum.valueOf(_item.getName()));
        }

    }

    @Test
    public void testTotalShoppingCartValue() {
        shoppingCart.addShoppingItem(new Item(ItemEnum.Apple.toString(), 3, 0.60));
        shoppingCart.addShoppingItem(new Item(ItemEnum.Orange.toString(), 2, 0.25));

        shoppingCart.accept(new GenerateBill());

        assertThat(shoppingCart.getTotalBill(), is(1.7));
    }

    @Test
    public void testDiscountOnApple() {
        shoppingCart.addShoppingItem(new Item(ItemEnum.Apple.toString(), 3, 0.60));

        shoppingCart.accept(new GenerateBill());

        assertThat(shoppingCart.getTotalBill(), is(1.2));
    }

    @Test
    public void testDiscountOnAppleAndOrange() {
        shoppingCart.addShoppingItem(new Item(ItemEnum.Apple.toString(), 4, 0.60));
        shoppingCart.addShoppingItem(new Item(ItemEnum.Orange.toString(), 8, 0.25));

        shoppingCart.accept(new GenerateBill());

        assertThat(shoppingCart.getTotalBill(), is(2.7));
    }


    @Test(expected = IllegalArgumentException.class)
    public void testUnknowItem() {
        shoppingCart.addShoppingItem(new Item("Peaches", 4, 0.20));
        shoppingCart.accept(new GenerateBill());

        assertThat(shoppingCart.getTotalBill(), is(2.7));
    }

    @Test
    public void testOneBanana() {
        shoppingCart.addShoppingItem(new Item(ItemEnum.Banana.toString(), 1, 0.20));

        assertThat(shoppingCart.getShoppingItems().size(), is(1));
        assertThat(shoppingCart.getShoppingItems().get(0).getName(), is(ItemEnum.Banana.toString()));

        shoppingCart.accept(new GenerateBill());

        assertThat(shoppingCart.getTotalBill(), is(0.20));
    }

    @Test
    public void testNewItem() {
        shoppingCart.addShoppingItem(new Item(ItemEnum.Banana.toString(), 4, 0.20));

        shoppingCart.accept(new GenerateBill());

        assertThat(shoppingCart.getTotalBill(), is(0.4));
    }


}
