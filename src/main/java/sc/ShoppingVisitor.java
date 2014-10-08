package sc;

/**
 * Created by vivek on 07/10/2014.
 */
public interface ShoppingVisitor {
    public void visitShoppingCart(ShoppingCart shoppingCart) throws ShoppingCartException;
    public void visitCartItem(Item cartItem) throws ShoppingCartException;
}
