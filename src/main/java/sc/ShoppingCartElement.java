package sc;

/**
 * Created by vivek on 07/10/2014.
 */
public interface ShoppingCartElement {
    public void accept(ShoppingVisitor shoppingVisitor);
}
