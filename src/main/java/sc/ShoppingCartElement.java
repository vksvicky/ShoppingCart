package sc;

/**
 * Created by vivek.
 */
public interface ShoppingCartElement {
    void accept(ShoppingVisitor shoppingVisitor);
}
