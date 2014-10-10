package sc;

import java.math.BigDecimal;

/**
 * Created by vivek on 07/10/2014.
 */
public class Item implements ShoppingCartElement {
    private String name;
    private int quantity;
    private BigDecimal price;

    private ShoppingCart shoppingCart;

    public Item(String name, int quantity, BigDecimal price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    @Override
    public void accept(ShoppingVisitor shoppingVisitor) {
        shoppingVisitor.visitCartItem(this);
    }
}
