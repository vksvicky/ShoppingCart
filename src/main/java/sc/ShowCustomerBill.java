package sc;

/**
 * Created by vivek on 07/10/2014.
 */
public class ShowCustomerBill {

    public static void main(String[] args) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addShoppingItem(new Item("Apple", 0, 0.60));
        shoppingCart.addShoppingItem(new Item("Orange", 0, 0.25));

        shoppingCart.accept(new GenerateBill());

        shoppingCart = new ShoppingCart();
        shoppingCart.addShoppingItem(new Item("Apple", 1, 0.60));
        shoppingCart.addShoppingItem(new Item("Orange", 1, 0.25));

        shoppingCart.accept(new GenerateBill());

        shoppingCart = new ShoppingCart();
        shoppingCart.addShoppingItem(new Item("Apple", 2, 0.60));
        shoppingCart.addShoppingItem(new Item("Orange", 2, 0.25));

        shoppingCart.accept(new GenerateBill());

        shoppingCart = new ShoppingCart();
        shoppingCart.addShoppingItem(new Item("Apple", 2, 0.60));
        shoppingCart.addShoppingItem(new Item("Orange", 3, 0.25));

        shoppingCart.accept(new GenerateBill());

        shoppingCart = new ShoppingCart();
        shoppingCart.addShoppingItem(new Item("Apple", 3, 0.60));
        shoppingCart.addShoppingItem(new Item("Orange", 3, 0.25));

        shoppingCart.accept(new GenerateBill());

        shoppingCart = new ShoppingCart();
        shoppingCart.addShoppingItem(new Item("Apple", 4, 0.60));
        shoppingCart.addShoppingItem(new Item("Orange", 3, 0.25));

        shoppingCart.accept(new GenerateBill());

        shoppingCart = new ShoppingCart();
        shoppingCart.addShoppingItem(new Item("Banana", 4, 0.60));
        shoppingCart.addShoppingItem(new Item("Orange", 3, 0.25));

        shoppingCart.accept(new GenerateBill());
    }
}
