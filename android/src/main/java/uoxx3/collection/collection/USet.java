package uoxx3.collection.collection;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
	public static <T> @NonNull Set<T> make(@NonNull T... elements) {
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
	public static <T> @NonNull Set<T> make(@NonNull Collection<T> collection) {
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
	public static <T> @NonNull Set<T> make(@NonNull Iterator<T> iterator) {
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
	public static <T> @NonNull Set<T> makeMutable(int size, @NonNull T... elements) {
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
	public static <T> @NonNull Set<T> makeMutable(@NonNull T... elements) {
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
	public static <T> @NonNull Set<T> makeMutable(int size, @NonNull Iterator<T> iterator) {
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
	public static <T> @NonNull Set<T> makeMutable(@NonNull Iterator<T> iterator) {
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
	public static <T> @NonNull Set<T> makeMutable(int size, @NonNull Collection<T> collection) {
		return makeMutable(size, collection.iterator());
	}
	
	/**
	 * Creates a mutable set containing the elements from the specified collection.
	 *
	 * @param collection the collection providing the elements for the set
	 * @param <T>        the type of elements in the set
	 * @return a mutable set containing the elements from the collection
	 */
	public static <T> @NonNull Set<T> makeMutable(@NonNull Collection<T> collection) {
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
	public static <T> @NonNull Set<T> makeLinked(int size, @NonNull T... elements) {
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
	public static <T> @NonNull Set<T> makeLinked(@NonNull T... elements) {
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
	public static <T> @NonNull Set<T> makeLinked(int size, @NonNull Iterator<T> iterator) {
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
	public static <T> @NonNull Set<T> makeLinked(@NonNull Iterator<T> iterator) {
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
	public static <T> @NonNull Set<T> makeLinked(int size, @NonNull Collection<T> collection) {
		return makeLinked(size, collection.iterator());
	}
	
	/**
	 * Creates a linked hash set containing the elements from the specified collection.
	 *
	 * @param collection the collection providing the elements for the set
	 * @param <T>        the type of elements in the set
	 * @return a linked hash set containing the elements from the collection
	 */
	public static <T> @NonNull Set<T> makeLinked(@NonNull Collection<T> collection) {
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
	public static <T> @NonNull Set<T> makeTree(@Nullable Comparator<T> comparator, @NonNull T... elements) {
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
	public static <T> @NonNull Set<T> makeTree(@NonNull T... elements) {
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
	public static <T> @NonNull Set<T> makeTree(@Nullable Comparator<T> comparator, @NonNull Iterator<T> iterator) {
		return collectionAddAll(new TreeSet<>(comparator), iterator);
	}
	
	/**
	 * Creates a tree set containing the elements from the specified iterator using natural ordering.
	 *
	 * @param iterator the iterator providing the elements for the set
	 * @param <T>      the type of elements in the set
	 * @return a tree set containing the elements from the iterator
	 */
	public static <T> @NonNull Set<T> makeTree(@NonNull Iterator<T> iterator) {
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
	public static <T> @NonNull Set<T> makeTree(@Nullable Comparator<T> comparator, @NonNull Collection<T> collection) {
		return makeTree(comparator, collection.iterator());
	}
	
	/**
	 * Creates a tree set containing the elements from the specified collection using natural ordering.
	 *
	 * @param collection the collection providing the elements for the set
	 * @param <T>        the type of elements in the set
	 * @return a tree set containing the elements from the collection
	 */
	public static <T> @NonNull Set<T> makeTree(@NonNull Collection<T> collection) {
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
	public static <O, R> @NonNull Set<R> transform(
		@NonNull Set<O> original,
		@NonNull Function<O, R> mapper,
		@NonNull Supplier<Set<R>> supplier
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
	public static <O, R> @NonNull Set<R> transform(
		@NonNull Set<O> original,
		@NonNull Function<O, R> mapper
	) {
		return collectionTransform(original, mapper, HashSet::new);
	}
	
}
