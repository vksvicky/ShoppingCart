package sc;

/**
 * Created by vivek.
 */
public interface ShoppingVisitor {
    void visitShoppingCart(ShoppingCart shoppingCart);

    void visitCartItem(Item cartItem);
}
