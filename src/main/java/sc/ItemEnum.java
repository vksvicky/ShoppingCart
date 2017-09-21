package sc;

/**
 * Created by vivek.
 */
public enum ItemEnum {
    Apple("Apple"),
    Orange("Orange");

    private final String value;

    /**
     * @param value
     */
    ItemEnum(final String value) {
        this.value = value;
    }

    /* (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return value;
    }

}
