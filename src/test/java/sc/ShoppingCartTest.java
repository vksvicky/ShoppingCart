package sc;

import org.hamcrest.Matchers;
import org.hamcrest.core.IsNull;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

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
    static BigDecimal appleCost = new BigDecimal(0.60);
    static BigDecimal orangeCost = new BigDecimal(0.25);
    static BigDecimal bananaCost = new BigDecimal(0.20);

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
        shoppingCart.addShoppingItem(new Item(ItemEnum.Apple.toString(), 1, appleCost));

        int numberOfItems = shoppingCart.getShoppingItems().size();

        assertThat(numberOfItems, is(1));
    }

    @Test
    public void testOnlyTwoItemsInCart() {
        shoppingCart.addShoppingItem(new Item(ItemEnum.Apple.toString(), 1, appleCost));
        shoppingCart.addShoppingItem(new Item(ItemEnum.Orange.toString(), 1, orangeCost));

        int numberOfItems = shoppingCart.getShoppingItems().size();

        assertThat(numberOfItems, is(2));
    }

    @Test
    public void testShoppingCartContainsApplesAndThreeApples() {
        shoppingCart.addShoppingItem(new Item(ItemEnum.Apple.toString(), 3, appleCost));

//        assertThat(shoppingCart.getShoppingItems(), containsInAnyOrder(hasProperty("name", is("Apple")), hasProperty("quantity", equalTo(3))));

        assertThat(shoppingCart.getShoppingItems(), containsInAnyOrder(hasProperty("name", is(ItemEnum.Apple.toString()))));
        assertThat(shoppingCart.getShoppingItems(), hasItem(Matchers.<Item>hasProperty("quantity", equalTo(3))));

    }

    @Test(expected = IllegalArgumentException.class)
    public void testShoppingCartDoesNotContainItemsInSet() {
        //shoppingCart.addShoppingItem(new Item(ItemEnum.Banana.toString(), 1, bananaCost));
        shoppingCart.addShoppingItem(new Item("Strawberry", 1, BigDecimal.valueOf(0.15)));

        for (Item _item : shoppingCart.getShoppingItems()) {
            assertEquals(false, ItemEnum.valueOf(_item.getName()));
        }

    }

    @Test
    public void testTotalShoppingCartValue() {
        shoppingCart.addShoppingItem(new Item(ItemEnum.Apple.toString(), 3, appleCost));
        shoppingCart.addShoppingItem(new Item(ItemEnum.Orange.toString(), 2, orangeCost));

        shoppingCart.accept(new GenerateBill());

        assertThat(shoppingCart.getTotalBill().setScale(2, RoundingMode.HALF_UP), is(new BigDecimal(1.7).setScale(2, RoundingMode.HALF_UP)));
    }

    @Test
    public void testDiscountOnApple() {
        shoppingCart.addShoppingItem(new Item(ItemEnum.Apple.toString(), 3, appleCost));

        shoppingCart.accept(new GenerateBill());

        assertThat(shoppingCart.getTotalBill().setScale(2, RoundingMode.HALF_UP), is(new BigDecimal(1.2).setScale(2, RoundingMode.HALF_UP)));
    }

    @Test
    public void testDiscountOnAppleAndOrange() {
        shoppingCart.addShoppingItem(new Item(ItemEnum.Apple.toString(), 4, appleCost));
        shoppingCart.addShoppingItem(new Item(ItemEnum.Orange.toString(), 8, orangeCost));

        shoppingCart.accept(new GenerateBill());

        assertThat(shoppingCart.getTotalBill().setScale(2, RoundingMode.HALF_UP), is(new BigDecimal(2.7).setScale(2, RoundingMode.HALF_UP)));
    }


    @Test(expected = IllegalArgumentException.class)
    public void testUnknowItem() {
        shoppingCart.addShoppingItem(new Item("Peaches", 4, bananaCost));
        shoppingCart.accept(new GenerateBill());

        assertThat(shoppingCart.getTotalBill(), is(2.7));
    }

    @Test
    public void testOneBanana() {
        shoppingCart.addShoppingItem(new Item(ItemEnum.Banana.toString(), 1, bananaCost));

        assertThat(shoppingCart.getShoppingItems().size(), is(1));
        assertThat(shoppingCart.getShoppingItems().get(0).getName(), is(ItemEnum.Banana.toString()));

        shoppingCart.accept(new GenerateBill());

        assertThat(shoppingCart.getTotalBill(), is(bananaCost));
    }

    @Test
    public void testNewItem() {
        shoppingCart.addShoppingItem(new Item(ItemEnum.Banana.toString(), 4, bananaCost));

        shoppingCart.accept(new GenerateBill());

        assertThat(shoppingCart.getTotalBill().setScale(2, RoundingMode.HALF_UP), is(new BigDecimal(0.4).setScale(2, RoundingMode.HALF_UP)));
    }

}
