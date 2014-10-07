package sc;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vivek on 07/10/2014.
 */
public class ShoppingCart implements ShoppingCartElement {

    private double totalBill;

    private List<Item> shoppingItems = new ArrayList();

    public void addShoppingItem(Item shoppingCartItem) {
        shoppingCartItem.setShoppingCart(this);
        shoppingItems.add(shoppingCartItem);
    }

    public List<Item> getShoppingItems() {
        return shoppingItems;
    }

    public void setShoppingItems(List<Item> shoppingItems) {
        this.shoppingItems = shoppingItems;
    }

    public double getTotalBill() {
        return totalBill;
    }

    public void setTotalBill(double totalBill) {
        this.totalBill = totalBill;
    }

    @Override
    public void accept(ShoppingVisitor shoppingVisitor) {
        shoppingVisitor.visitShoppingCart(this);
    }
}
