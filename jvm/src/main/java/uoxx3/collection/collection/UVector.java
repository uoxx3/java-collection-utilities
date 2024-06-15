package uoxx3.collection.collection;

import org.jetbrains.annotations.NotNull;
import uoxx3.collection.UCollectionConstants;
import uoxx3.collection.internal.collection.UCollectionCommon;

import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;
import java.util.function.Function;

public final class UVector extends UCollectionCommon {
	
	/**
	 * This class cannot be instantiated
	 */
	private UVector() {
		throw new IllegalStateException("This class cannot be instantiated");
	}
	
	/* -----------------------------------------------------
	 * Generator methods
	 * ----------------------------------------------------- */
	
	/**
	 * Creates a new vector with the specified size and elements.
	 *
	 * @param size     the size of the new vector
	 * @param elements the elements to add to the vector
	 * @param <T>      the type of elements in the vector
	 * @return a new vector with the specified size and elements
	 */
	@SafeVarargs
	public static <T> @NotNull Vector<T> make(int size, T @NotNull ... elements) {
		return vectorAddAll(new Vector<>(size), elements);
	}
	
	/**
	 * Creates a new vector with the specified elements.
	 *
	 * @param elements the elements to add to the vector
	 * @param <T>      the type of elements in the vector
	 * @return a new vector with the specified elements
	 */
	@SafeVarargs
	public static <T> @NotNull Vector<T> make(T @NotNull ... elements) {
		// Calculate memory threshold for vector of items
		int sizeThreshold = ensureCapacityThreshold(elements.length);
		return make(sizeThreshold, elements);
	}
	
	/**
	 * Creates a new vector with the specified size and elements from the iterator.
	 *
	 * @param size     the size of the new vector
	 * @param iterator the iterator providing the elements for the vector
	 * @param <T>      the type of elements in the vector
	 * @return a new vector with the specified size and elements from the iterator
	 */
	public static <T> @NotNull Vector<T> make(int size, @NotNull Iterator<T> iterator) {
		return vectorAddAll(new Vector<>(size), iterator);
	}
	
	/**
	 * Creates a new vector with the elements from the iterator.
	 *
	 * @param iterator the iterator providing the elements for the vector
	 * @param <T>      the type of elements in the vector
	 * @return a new vector with the elements from the iterator
	 */
	public static <T> @NotNull Vector<T> make(@NotNull Iterator<T> iterator) {
		// Calculate memory threshold for vector of items
		int sizeThreshold = ensureCapacityThreshold(UCollectionConstants.EMPTY_SIZE);
		return make(sizeThreshold, iterator);
	}
	
	/**
	 * Creates a new vector with the specified size and elements from the collection.
	 *
	 * @param size       the size of the new vector
	 * @param collection the collection providing the elements for the vector
	 * @param <T>        the type of elements in the vector
	 * @return a new vector with the specified size and elements from the collection
	 */
	public static <T> @NotNull Vector<T> make(int size, @NotNull Collection<T> collection) {
		return make(size, collection.iterator());
	}
	
	/**
	 * Creates a new vector with the elements from the collection.
	 *
	 * @param collection the collection providing the elements for the vector
	 * @param <T>        the type of elements in the vector
	 * @return a new vector with the elements from the collection
	 */
	public static <T> @NotNull Vector<T> make(@NotNull Collection<T> collection) {
		// Calculate memory threshold for vector of items
		int sizeThreshold = ensureCapacityThreshold(collection.size());
		return make(sizeThreshold, collection);
	}
	
	/* -----------------------------------------------------
	 * Vector transform methods
	 * ----------------------------------------------------- */
	
	/**
	 * Transforms a vector of elements from one type to another using the provided mapper function.
	 *
	 * @param original the original vector of elements
	 * @param mapper   the function to transform elements from type O to type R
	 * @param <O>      the type of elements in the original vector
	 * @param <R>      the type of elements in the resulting vector
	 * @return a new vector containing the transformed elements
	 */
	public static <O, R> @NotNull Vector<R> transform(@NotNull Vector<O> original, @NotNull Function<O, R> mapper) {
		return vectorTransform(original, mapper, Vector::new);
	}
	
}
