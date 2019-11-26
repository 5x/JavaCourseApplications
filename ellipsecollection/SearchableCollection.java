package ellipsecollection;


import java.util.*;

/**
 * Provide collection with an extended searchable behavior.
 *
 * @param <E> the type of collection elements.
 */
public class SearchableCollection<E> implements Iterable<E> {
    /**
     * Default search condition, that matches to all elements.
     */
    private final Condition<E> DEFAULT_CONDITION = (Condition<E>) element -> true;

    /**
     * Data store for the elements.
     */
    private ArrayList<E> elementCollection;

    /**
     * Condition that implement {@code check()} for filter collection elements.
     * If {@code check(element)} return {@code true} for element it remain in
     * the result of filtering
     */
    private Condition<E> condition = DEFAULT_CONDITION;

    /**
     * Constructs an empty collection.
     */
    public SearchableCollection() {
        this.elementCollection = new ArrayList<>();
    }

    /**
     * Constructs a collection containing the elements of the specified
     * collection, in the order they are returned by the collection's
     * iterator.
     *
     * @param c the collection whose elements are to be used as initial values
     * @throws NullPointerException if the specified collection is null
     */
    public SearchableCollection(Collection<? extends E> c) throws NullPointerException {
        this.elementCollection = new ArrayList<>(c);
    }

    /**
     * Return reference to the condition instance.
     *
     * @return instance of interface {@code Condition<E>}
     */
    private Condition<E> getCondition() {
        return condition;
    }

    /**
     * Set search condition.
     *
     * @param condition implementation of interface {@code Condition<E>}, with
     *                  method {@code check} used to test each element of the
     *                  collection. Return {@code true} to keep the element,
     *                  {@code false} otherwise, taking one arguments(element).
     */
    public void setCondition(Condition<E> condition) {
        condition = Objects.requireNonNullElse(condition, this.DEFAULT_CONDITION);
        this.condition = condition;
    }

    /**
     * Returns the element at the specified position in this collection.
     *
     * @param index index of the element to return
     * @return the element at the specified position in this collection
     * @throws IndexOutOfBoundsException if the {@code index} is out-of-bounds
     */
    public E get(int index) throws IndexOutOfBoundsException {
        return this.elementCollection.get(index);
    }

    /**
     * Replaces the element at the specified position in this collection with
     * the specified element.
     *
     * @param index   index of the element to replace
     * @param element element to be stored at the specified position
     * @return the element previously at the specified position
     * @throws IndexOutOfBoundsException if the {@code index} is out-of-bounds
     */
    public E set(int index, E element) throws IndexOutOfBoundsException {
        return this.elementCollection.set(index, element);
    }

    /**
     * Appends the specified element to the end of this collection.
     *
     * @param element element to be appended to this collection
     * @return {@code true} (as specified by {@link Collection#add})
     */
    public boolean add(E element) {
        return this.elementCollection.add(element);
    }

    /**
     * Inserts the specified element at the specified position in this
     * collection. Shifts the element currently at that position (if any) and
     * any subsequent elements to the right (adds one to their indices).
     *
     * @param index   index at which the specified element is to be inserted
     * @param element element to be inserted
     * @throws IndexOutOfBoundsException if the {@code index} is out-of-bounds
     */
    public void add(int index, E element) throws IndexOutOfBoundsException {
        this.elementCollection.add(index, element);
    }

    /**
     * Removes the element at the specified position in this collection.
     * Shifts any subsequent elements to the left (subtracts one from their
     * indices).
     *
     * @param index the index of the element to be removed
     * @return the element that was removed from the collection
     * @throws IndexOutOfBoundsException if the {@code index} is out-of-bounds
     */
    public E remove(int index) throws IndexOutOfBoundsException {
        return this.elementCollection.remove(index);
    }

    /**
     * Creates a new collection with all elements that pass the test
     * implemented by the provided condition.
     *
     * @param condition implementation of interface {@code Condition<E>},
     *                  method {@code check} apply to test each element of the
     *                  collection. Return {@code true} to keep the element,
     *                  {@code false} otherwise, taking one arguments(element).
     * @return Collection with elements that successfully passed test check.
     */
    public Collection<E> filter(Condition<E> condition) {
        Condition<E> prevCondition = this.getCondition();
        this.setCondition(condition);
        List<E> filtered = new ArrayList<>();
        this.iterator().forEachRemaining(filtered::add);
        this.setCondition(prevCondition);

        return filtered;
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<E> iterator() {
        return new CollectionIterator();
    }

    private class CollectionIterator implements Iterator<E> {

        /**
         * Store current index for iteration.
         */
        private int currentIndex = -1;
        /**
         * The cached index of the next successful found element in the
         * collection.
         */
        private int cacheNextIndex = -1;

        /**
         * Returns {@code true} if the iteration has more elements.
         * (In other words, returns {@code true} if {@link #next} would
         * return an element rather than throwing an exception.)
         *
         * @return {@code true} if the iteration has more elements
         */
        @Override
        public boolean hasNext() {
            this.cacheNextIndex = this.indexOf(this.currentIndex);

            return this.cacheNextIndex >= 0;
        }

        /**
         * Returns the first index at which a given element, that approve
         * condition can be found in the collection, or -1 if it is not
         * present.
         *
         * @param fromIndex The index to start the search at. If the index is
         *                  greater than or equal to the collection size, -1 is
         *                  returned, which means the collection will not be
         *                  searched
         * @return The first index of the element in the collection; -1 if no
         * found.
         * @throws ImpossibleConditionCheckException The condition cannot be
         *                                           checked for the passed value.
         * @throws IllegalArgumentException          Passed to check value is invalid.
         */
        private int indexOf(int fromIndex) {
            while (fromIndex < elementCollection.size() - 1) {
                fromIndex++;
                E value = get(fromIndex);
                if (getCondition().check(value)) {
                    return fromIndex;
                }
            }

            return -1;
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration
         * @throws NoSuchElementException if the iteration has no more elements
         */
        @Override
        public E next() {
            if (this.cacheNextIndex > this.currentIndex || hasNext()) {
                this.currentIndex = this.cacheNextIndex;
                
                return get(this.currentIndex);
            }

            throw new NoSuchElementException();
        }

    }

}
