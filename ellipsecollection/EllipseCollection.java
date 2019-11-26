package ellipsecollection;

import ellipse.Ellipse;

import java.util.Collection;


/**
 * Collection for Ellipse Objects with an extended searchable behavior.
 */
public class EllipseCollection extends SearchableCollection<Ellipse> {
    /**
     * Constructs an empty collection.
     */
    public EllipseCollection() {
        super();
    }

    /**
     * Constructs a collection containing the elements of the specified
     * collection, in the order they are returned by the collection's
     * iterator.
     *
     * @param c the collection whose elements are to be placed into this list
     * @throws NullPointerException if the specified collection is null
     */
    public EllipseCollection(Collection<? extends Ellipse> c) throws NullPointerException {
        super(c);
    }
}
