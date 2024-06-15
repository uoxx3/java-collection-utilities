package uoxx3.collection.collection;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.UnmodifiableView;
import uoxx3.collection.UCollectionConstants;
import uoxx3.collection.internal.collection.UCollectionCommon;

import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;

public final class UList extends UCollectionCommon {
	
	/**
	 * This class cannot be instantiated
	 */
	private UList() {
		throw new IllegalStateException("This class cannot be instantiated");
	}
	
	/* -----------------------------------------------------
	 * Unmodifiable list generator methods
	 * ----------------------------------------------------- */
	
	/**
	 * Creates an unmodifiable view of a list containing the specified elements.
	 *
	 * @param <T>      the type of elements in the list
	 * @param elements the elements to be added to the list
	 * @return an unmodifiable view of a list containing the specified elements
	 */
	@SafeVarargs
	public static <T> @NotNull @UnmodifiableView List<T> make(T @NotNull ... elements) {
		return Collections.unmodifiableList(
			makeMutable(elements.length, elements)
		);
	}
	
	/**
	 * Creates an unmodifiable view of a list containing the elements from a collection.
	 *
	 * @param <T>        the type of elements in the list
	 * @param collection the collection providing elements to be added to the list
	 * @return an unmodifiable view of a list containing the specified elements
	 */
	public static <T> @NotNull @UnmodifiableView List<T> make(@NotNull Collection<T> collection) {
		return Collections.unmodifiableList(
			makeMutable(collection.size(), collection)
		);
	}
	
	/**
	 * Creates an unmodifiable view of a list containing the elements from an iterator.
	 *
	 * @param <T>      the type of elements in the list
	 * @param iterator the iterator providing elements to be added to the list
	 * @return an unmodifiable view of a list containing the specified elements
	 */
	public static <T> @NotNull @UnmodifiableView List<T> make(@NotNull Iterator<T> iterator) {
		return Collections.unmodifiableList(
			makeMutable(iterator)
		);
	}
	
	/* -----------------------------------------------------
	 * Mutable list generator methods
	 * ----------------------------------------------------- */
	
	/**
	 * Creates a mutable list with the specified size and elements.
	 *
	 * @param <T>      the type of elements in the list
	 * @param size     the initial size of the list
	 * @param elements the elements to be added to the list
	 * @return a mutable list containing the specified elements
	 */
	@SafeVarargs
	public static <T> @NotNull List<T> makeMutable(int size, T @NotNull ... elements) {
		return collectionAddAll(new ArrayList<>(size), elements);
	}
	
	/**
	 * Creates a mutable list with the specified elements.
	 *
	 * @param <T>      the type of elements in the list
	 * @param elements the elements to be added to the list
	 * @return a mutable list containing the specified elements
	 */
	@SafeVarargs
	public static <T> @NotNull List<T> makeMutable(T @NotNull ... elements) {
		// Calculate memory threshold for list of items
		int sizeThreshold = ensureCapacityThreshold(elements.length);
		return makeMutable(sizeThreshold, elements);
	}
	
	/**
	 * Creates a mutable list with the specified size and elements from an iterator.
	 *
	 * @param <T>      the type of elements in the list
	 * @param size     the initial size of the list
	 * @param iterator the iterator providing elements to be added to the list
	 * @return a mutable list containing the specified elements
	 */
	public static <T> @NotNull List<T> makeMutable(int size, @NotNull Iterator<T> iterator) {
		return collectionAddAll(new ArrayList<>(size), iterator);
	}
	
	/**
	 * Creates a mutable list with the elements from an iterator.
	 *
	 * @param <T>      the type of elements in the list
	 * @param iterator the iterator providing elements to be added to the list
	 * @return a mutable list containing the specified elements
	 */
	public static <T> @NotNull List<T> makeMutable(@NotNull Iterator<T> iterator) {
		// Calculate memory threshold for list of items
		int sizeThreshold = ensureCapacityThreshold(UCollectionConstants.EMPTY_SIZE);
		return makeMutable(sizeThreshold, iterator);
	}
	
	/**
	 * Creates a mutable list with the specified size and elements from a collection.
	 *
	 * @param <T>        the type of elements in the list
	 * @param size       the initial size of the list
	 * @param collection the collection providing elements to be added to the list
	 * @return a mutable list containing the specified elements
	 */
	public static <T> @NotNull List<T> makeMutable(int size, @NotNull Collection<T> collection) {
		return makeMutable(size, collection.iterator());
	}
	
	/**
	 * Creates a mutable list with the elements from a collection.
	 *
	 * @param <T>        the type of elements in the list
	 * @param collection the collection providing elements to be added to the list
	 * @return a mutable list containing the specified elements
	 */
	public static <T> @NotNull List<T> makeMutable(@NotNull Collection<T> collection) {
		// Calculate memory threshold for list of items
		int sizeThreshold = ensureCapacityThreshold(collection.size());
		return makeMutable(sizeThreshold, collection);
	}
	
	/* -----------------------------------------------------
	 * Linked list generator methods
	 * ----------------------------------------------------- */
	
	/**
	 * Creates a linked list with the specified elements.
	 *
	 * @param <T>      the type of elements in the list
	 * @param elements the elements to be added to the list
	 * @return a linked list containing the specified elements
	 */
	@SafeVarargs
	public static <T> @NotNull List<T> makeLinked(T @NotNull ... elements) {
		return collectionAddAll(new LinkedList<>(), elements);
	}
	
	/**
	 * Creates a linked list with the elements from an iterator.
	 *
	 * @param <T>      the type of elements in the list
	 * @param iterator the iterator providing elements to be added to the list
	 * @return a linked list containing the specified elements
	 */
	public static <T> @NotNull List<T> makeLinked(@NotNull Iterator<T> iterator) {
		return collectionAddAll(new LinkedList<>(), iterator);
	}
	
	/**
	 * Creates a linked list with the elements from a collection.
	 *
	 * @param <T>        the type of elements in the list
	 * @param collection the collection providing elements to be added to the list
	 * @return a linked list containing the specified elements
	 */
	public static <T> @NotNull List<T> makeLinked(@NotNull Collection<T> collection) {
		return makeLinked(collection.iterator());
	}
	
	/* -----------------------------------------------------
	 * List transform methods
	 * ----------------------------------------------------- */
	
	/**
	 * Transforms the elements of the original list using the provided mapper function
	 * and collects the results into a new list using the specified supplier.
	 *
	 * @param <O>      the type of elements in the original list
	 * @param <R>      the type of elements in the resulting list
	 * @param original the original list of elements to transform
	 * @param mapper   the function to apply to each element for transformation
	 * @param supplier the supplier for the resulting list
	 * @return a new list containing the transformed elements
	 */
	public static <O, R> @NotNull List<R> transform(
		@NotNull List<O> original,
		@NotNull Function<O, R> mapper,
		@NotNull Supplier<List<R>> supplier
	) {
		return collectionTransform(original, mapper, supplier);
	}
	
	/**
	 * Transforms the elements of the original list using the provided mapper function
	 * and collects the results into a new ArrayList.
	 *
	 * @param <O>      the type of elements in the original list
	 * @param <R>      the type of elements in the resulting list
	 * @param original the original list of elements to transform
	 * @param mapper   the function to apply to each element for transformation
	 * @return a new ArrayList containing the transformed elements
	 */
	public static <O, R> @NotNull List<R> transform(
		@NotNull List<O> original,
		@NotNull Function<O, R> mapper
	) {
		return transform(original, mapper, ArrayList::new);
	}
	
}
