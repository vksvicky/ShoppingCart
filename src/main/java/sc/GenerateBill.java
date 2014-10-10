package sc;

/**
 * Created by vivek on 07/10/2014.
 */
public class GenerateBill implements ShoppingVisitor {
    boolean itemDoesNotExist = false;
    int newQuantity = 0;

    @Override
    public void visitShoppingCart(ShoppingCart shoppingCart) {
        for (int i = 0; i < shoppingCart.getShoppingItems().size(); i++) {
            shoppingCart.getShoppingItems().get(i).accept(this);
        }

        System.out.println("\nAmount to pay : £" + shoppingCart.getTotalBill());
    }

    @Override
    public void visitCartItem(Item item) {
        double itemCost = 0.0;

        DiscountCalculation(item);

        if (!itemDoesNotExist) {
            itemCost = newQuantity * item.getPrice();

            item.getShoppingCart().setTotalBill(
                    item.getShoppingCart().getTotalBill() + itemCost);

            System.out.println(item.getQuantity() + " x " +
                    item.getName().toUpperCase() +
                    " @ " + item.getPrice() + " - £" + itemCost);
        } else {
            throw new IllegalArgumentException("Item such item in Stock!");
        }

    }

    public void DiscountCalculation(Item item) {
        newQuantity = item.getQuantity();

        switch (ItemEnum.valueOf(item.getName())) {
            case Apple:
            case Banana:
                //Specifically checking buy 1 get 1 offer
                //Checking if there are odd apples, then 1 will be need to added explicitly
                if (item.getQuantity() > 1) {
                    newQuantity = item.getQuantity() - (item.getQuantity() / 2);
                }
                break;

            case Orange:
                //Specifically checking 3 for the price of 2
                if (item.getQuantity() > 2) {
                    newQuantity = item.getQuantity() - (item.getQuantity() / 3);
                }
                break;

            default:
                itemDoesNotExist = true;
                break;
        }
    }


}
