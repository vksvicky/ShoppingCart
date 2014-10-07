package sc;

/**
 * Created by vivek on 07/10/2014.
 */
public class GenerateBill implements ShoppingVisitor {

    @Override
    public void visitShoppingCart(ShoppingCart shoppingCart) {
        for (int i = 0; i < shoppingCart.getShoppingItems().size(); i++) {
            shoppingCart.getShoppingItems().get(i).accept(this);
        }

        System.out.println("\nAmount to pay : £" + shoppingCart.getTotalBill());
    }

    @Override
    public void visitCartItem(Item item) {
        double itemCost = item.getQuantity() * item.getPrice();
        item.getShoppingCart().setTotalBill(
                item.getShoppingCart().getTotalBill() + itemCost);

        System.out.println(item.getQuantity() + " x " +
                item.getName().toUpperCase() +
                " @ " + item.getPrice() + " - £" + itemCost);

    }
}
