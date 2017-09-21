package sc;

/**
 * Created by vivek on 18/03/2017.
 */
public interface ShoppingVisitor {
    void visitShoppingCart(ShoppingCart shoppingCart);

    void visitCartItem(Item cartItem);
}
