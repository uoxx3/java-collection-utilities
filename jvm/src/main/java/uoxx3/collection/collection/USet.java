package uoxx3.collection.collection;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.UnmodifiableView;
import uoxx3.collection.UCollectionConstants;
import uoxx3.collection.internal.collection.UCollectionCommon;

import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;

public final class USet extends UCollectionCommon {
	
	/**
	 * This class cannot be instantiated
	 */
	private USet() {
		throw new IllegalStateException("This class cannot be instantiated");
	}
	
	/* -----------------------------------------------------
	 * Unmodifiable set generator methods
	 * ----------------------------------------------------- */
	
	/**
	 * Creates an unmodifiable view of a set containing the specified elements.
	 *
	 * @param elements the elements to include in the set
	 * @param <T>      the type of elements in the set
	 * @return an unmodifiable view of a set containing the specified elements
	 */
	@SafeVarargs
	public static <T> @NotNull @UnmodifiableView Set<T> make(T @NotNull ... elements) {
		return Collections.unmodifiableSet(
			makeMutable(elements.length, elements)
		);
	}
	
	/**
	 * Creates an unmodifiable view of a set containing the elements from the specified collection.
	 *
	 * @param collection the collection providing the elements for the set
	 * @param <T>        the type of elements in the set
	 * @return an unmodifiable view of a set containing the elements from the collection
	 */
	public static <T> @NotNull @UnmodifiableView Set<T> make(@NotNull Collection<T> collection) {
		return Collections.unmodifiableSet(
			makeMutable(collection.size(), collection)
		);
	}
	
	/**
	 * Creates an unmodifiable view of a set containing the elements from the specified iterator.
	 *
	 * @param iterator the iterator providing the elements for the set
	 * @param <T>      the type of elements in the set
	 * @return an unmodifiable view of a set containing the elements from the iterator
	 */
	public static <T> @NotNull @UnmodifiableView Set<T> make(@NotNull Iterator<T> iterator) {
		return Collections.unmodifiableSet(
			makeMutable(iterator)
		);
	}
	
	/* -----------------------------------------------------
	 * Mutable set generator methods
	 * ----------------------------------------------------- */
	
	/**
	 * Creates a mutable set containing the specified elements with an initial size hint.
	 *
	 * @param size     the initial size hint for the set
	 * @param elements the elements to include in the set
	 * @param <T>      the type of elements in the set
	 * @return a mutable set containing the specified elements
	 */
	@SafeVarargs
	public static <T> @NotNull Set<T> makeMutable(int size, T @NotNull ... elements) {
		int capacityFactor = ensureCapacityHashThreshold(size, UCollectionConstants.EMPTY_SIZE);
		return collectionAddAll(new HashSet<>(capacityFactor), elements);
	}
	
	/**
	 * Creates a mutable set containing the specified elements.
	 *
	 * @param elements the elements to include in the set
	 * @param <T>      the type of elements in the set
	 * @return a mutable set containing the specified elements
	 */
	@SafeVarargs
	public static <T> @NotNull Set<T> makeMutable(T @NotNull ... elements) {
		int sizeThreshold = ensureCapacityThreshold(elements.length);
		return makeMutable(sizeThreshold, elements);
	}
	
	/**
	 * Creates a mutable set containing the elements from the specified iterator with an initial size hint.
	 *
	 * @param size     the initial size hint for the set
	 * @param iterator the iterator providing the elements for the set
	 * @param <T>      the type of elements in the set
	 * @return a mutable set containing the elements from the iterator
	 */
	public static <T> @NotNull Set<T> makeMutable(int size, @NotNull Iterator<T> iterator) {
		int capacityFactor = ensureCapacityHashThreshold(size, UCollectionConstants.EMPTY_SIZE);
		return collectionAddAll(new HashSet<>(capacityFactor), iterator);
	}
	
	/**
	 * Creates a mutable set containing the elements from the specified iterator.
	 *
	 * @param iterator the iterator providing the elements for the set
	 * @param <T>      the type of elements in the set
	 * @return a mutable set containing the elements from the iterator
	 */
	public static <T> @NotNull Set<T> makeMutable(@NotNull Iterator<T> iterator) {
		return collectionAddAll(new HashSet<>(), iterator);
	}
	
	/**
	 * Creates a mutable set containing the elements from the specified collection with an initial size hint.
	 *
	 * @param size       the initial size hint for the set
	 * @param collection the collection providing the elements for the set
	 * @param <T>        the type of elements in the set
	 * @return a mutable set containing the elements from the collection
	 */
	public static <T> @NotNull Set<T> makeMutable(int size, @NotNull Collection<T> collection) {
		return makeMutable(size, collection.iterator());
	}
	
	/**
	 * Creates a mutable set containing the elements from the specified collection.
	 *
	 * @param collection the collection providing the elements for the set
	 * @param <T>        the type of elements in the set
	 * @return a mutable set containing the elements from the collection
	 */
	public static <T> @NotNull Set<T> makeMutable(@NotNull Collection<T> collection) {
		int sizeThreshold = ensureCapacityThreshold(collection.size());
		return makeMutable(sizeThreshold, collection);
	}
	
	/* -----------------------------------------------------
	 * Linked set generator methods
	 * ----------------------------------------------------- */
	
	/**
	 * Creates a linked hash set containing the specified elements with an initial size hint.
	 *
	 * @param size     the initial size hint for the set
	 * @param elements the elements to include in the set
	 * @param <T>      the type of elements in the set
	 * @return a linked hash set containing the specified elements
	 */
	@SafeVarargs
	public static <T> @NotNull Set<T> makeLinked(int size, T @NotNull ... elements) {
		int capacityFactor = ensureCapacityHashThreshold(size, UCollectionConstants.EMPTY_SIZE);
		return collectionAddAll(new LinkedHashSet<>(capacityFactor), elements);
	}
	
	/**
	 * Creates a linked hash set containing the specified elements.
	 *
	 * @param elements the elements to include in the set
	 * @param <T>      the type of elements in the set
	 * @return a linked hash set containing the specified elements
	 */
	@SafeVarargs
	public static <T> @NotNull Set<T> makeLinked(T @NotNull ... elements) {
		int sizeThreshold = ensureCapacityThreshold(elements.length);
		return makeLinked(sizeThreshold, elements);
	}
	
	/**
	 * Creates a linked hash set containing the elements from the specified iterator with an initial size hint.
	 *
	 * @param size     the initial size hint for the set
	 * @param iterator the iterator providing the elements for the set
	 * @param <T>      the type of elements in the set
	 * @return a linked hash set containing the elements from the iterator
	 */
	public static <T> @NotNull Set<T> makeLinked(int size, @NotNull Iterator<T> iterator) {
		int capacityFactor = ensureCapacityHashThreshold(size, UCollectionConstants.EMPTY_SIZE);
		return collectionAddAll(new LinkedHashSet<>(capacityFactor), iterator);
	}
	
	/**
	 * Creates a linked hash set containing the elements from the specified iterator.
	 *
	 * @param iterator the iterator providing the elements for the set
	 * @param <T>      the type of elements in the set
	 * @return a linked hash set containing the elements from the iterator
	 */
	public static <T> @NotNull Set<T> makeLinked(@NotNull Iterator<T> iterator) {
		return collectionAddAll(new LinkedHashSet<>(), iterator);
	}
	
	/**
	 * Creates a linked hash set containing the elements from the specified collection with an initial size hint.
	 *
	 * @param size       the initial size hint for the set
	 * @param collection the collection providing the elements for the set
	 * @param <T>        the type of elements in the set
	 * @return a linked hash set containing the elements from the collection
	 */
	public static <T> @NotNull Set<T> makeLinked(int size, @NotNull Collection<T> collection) {
		return makeLinked(size, collection.iterator());
	}
	
	/**
	 * Creates a linked hash set containing the elements from the specified collection.
	 *
	 * @param collection the collection providing the elements for the set
	 * @param <T>        the type of elements in the set
	 * @return a linked hash set containing the elements from the collection
	 */
	public static <T> @NotNull Set<T> makeLinked(@NotNull Collection<T> collection) {
		int sizeThreshold = ensureCapacityThreshold(collection.size());
		return makeLinked(sizeThreshold, collection);
	}
	
	/* -----------------------------------------------------
	 * Linked tree generator methods
	 * ----------------------------------------------------- */
	
	/**
	 * Creates a tree set containing the specified elements with the given comparator.
	 *
	 * @param comparator the comparator to use for ordering the elements, or null to use the natural ordering
	 * @param elements   the elements to include in the set
	 * @param <T>        the type of elements in the set
	 * @return a tree set containing the specified elements
	 */
	@SafeVarargs
	public static <T> @NotNull Set<T> makeTree(@Nullable Comparator<T> comparator, T @NotNull ... elements) {
		return collectionAddAll(new TreeSet<>(comparator), elements);
	}
	
	/**
	 * Creates a tree set containing the specified elements using natural ordering.
	 *
	 * @param elements the elements to include in the set
	 * @param <T>      the type of elements in the set
	 * @return a tree set containing the specified elements
	 */
	@SafeVarargs
	public static <T> @NotNull Set<T> makeTree(T @NotNull ... elements) {
		return makeTree(null, elements);
	}
	
	/**
	 * Creates a tree set containing the elements from the specified iterator with the given comparator.
	 *
	 * @param comparator the comparator to use for ordering the elements, or null to use the natural ordering
	 * @param iterator   the iterator providing the elements for the set
	 * @param <T>        the type of elements in the set
	 * @return a tree set containing the elements from the iterator
	 */
	public static <T> @NotNull Set<T> makeTree(@Nullable Comparator<T> comparator, @NotNull Iterator<T> iterator) {
		return collectionAddAll(new TreeSet<>(comparator), iterator);
	}
	
	/**
	 * Creates a tree set containing the elements from the specified iterator using natural ordering.
	 *
	 * @param iterator the iterator providing the elements for the set
	 * @param <T>      the type of elements in the set
	 * @return a tree set containing the elements from the iterator
	 */
	public static <T> @NotNull Set<T> makeTree(@NotNull Iterator<T> iterator) {
		return makeTree(null, iterator);
	}
	
	/**
	 * Creates a tree set containing the elements from the specified collection with the given comparator.
	 *
	 * @param comparator the comparator to use for ordering the elements, or null to use the natural ordering
	 * @param collection the collection providing the elements for the set
	 * @param <T>        the type of elements in the set
	 * @return a tree set containing the elements from the collection
	 */
	public static <T> @NotNull Set<T> makeTree(@Nullable Comparator<T> comparator, @NotNull Collection<T> collection) {
		return makeTree(comparator, collection.iterator());
	}
	
	/**
	 * Creates a tree set containing the elements from the specified collection using natural ordering.
	 *
	 * @param collection the collection providing the elements for the set
	 * @param <T>        the type of elements in the set
	 * @return a tree set containing the elements from the collection
	 */
	public static <T> @NotNull Set<T> makeTree(@NotNull Collection<T> collection) {
		return makeTree(null, collection);
	}
	
	/* -----------------------------------------------------
	 * Set transform methods
	 * ----------------------------------------------------- */
	
	/**
	 * Transforms a set by applying a mapping function to each element.
	 *
	 * @param original the original set
	 * @param mapper   the mapping function to apply to each element
	 * @param supplier a supplier for the new set
	 * @param <O>      the type of elements in the original set
	 * @param <R>      the type of elements in the resulting set
	 * @return a new set containing the transformed elements
	 */
	public static <O, R> @NotNull Set<R> transform(
		@NotNull Set<O> original,
		@NotNull Function<O, R> mapper,
		@NotNull Supplier<Set<R>> supplier
	) {
		return collectionTransform(original, mapper, supplier);
	}
	
	/**
	 * Transforms a set by applying a mapping function to each element, using a default supplier for the new set.
	 *
	 * @param original the original set
	 * @param mapper   the mapping function to apply to each element
	 * @param <O>      the type of elements in the original set
	 * @param <R>      the type of elements in the resulting set
	 * @return a new set containing the transformed elements
	 */
	public static <O, R> @NotNull Set<R> transform(
		@NotNull Set<O> original,
		@NotNull Function<O, R> mapper
	) {
		return collectionTransform(original, mapper, HashSet::new);
	}
	
}
