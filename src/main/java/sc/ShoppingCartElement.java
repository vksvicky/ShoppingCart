package sc;

/**
 * Created by vivek on 18/03/2017.
 */
public interface ShoppingCartElement {
    void accept(ShoppingVisitor shoppingVisitor);
}
