package sc;

import java.math.BigDecimal;

/**
 * Created by vivek.
 */
public enum ItemEnum {
    Apple("Apple", 0.60),
    Orange("Orange", 0.25);

    private String value = null;
    private BigDecimal cost = null;


    ItemEnum(String value, double cost) {
        this.value = value;
        this.cost = new BigDecimal(cost);
    }

    public String getValue() {
        return value;
    }

    public BigDecimal getCost() {
        return cost;
    }

    /* (non-Javadoc)
         * @see java.lang.Enum#toString()
         */
    @Override
    public String toString() {
        return value;
    }

}
