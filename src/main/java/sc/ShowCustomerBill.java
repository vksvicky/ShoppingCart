package sc;

/**
 * Created by vivek on 07/10/2014.
 */
public class ShowCustomerBill {

    public static void main(String[] args) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addShoppingItem(new Item("Apple", 4, 0.60));
        shoppingCart.addShoppingItem(new Item("Orange", 8, 0.25));

        shoppingCart.accept(new GenerateBill());
    }
}
