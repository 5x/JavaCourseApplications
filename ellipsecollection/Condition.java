package ellipsecollection;

public interface Condition<E> {

    /**
     * Return {@code true} to keep the element, {@code false} otherwise,
     * taking one arguments(element).
     *
     * @param element An element to check.
     * @return Boolean equivalent of the element check.
     */
    boolean check(E element);
}
