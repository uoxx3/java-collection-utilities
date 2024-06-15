package uoxx3.collection.internal.collection;

import org.jetbrains.annotations.NotNull;
import uoxx3.UAssert;
import uoxx3.collection.UCollectionConstants;

import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
import java.util.Vector;
import java.util.function.Function;
import java.util.function.Supplier;

public abstract class UCollectionCommon {
	
	/* -----------------------------------------------------
	 * Collection capacity actions
	 * ----------------------------------------------------- */
	
	/**
	 * Calculates the threshold for ensuring capacity in a collection.
	 *
	 * @param capacity the current capacity of the collection
	 * @return the threshold for ensuring capacity
	 */
	protected static int ensureCapacityThreshold(int capacity) {
		return Math.abs(capacity) + UCollectionConstants.COLLECTION_SIZE_THRESHOLD;
	}
	
	/**
	 * Calculates the threshold for ensuring capacity in a hash-based collection.
	 *
	 * @param capacity the current capacity of the collection
	 * @param reserved the reserved capacity for the collection
	 * @return the threshold for ensuring capacity
	 */
	protected static int ensureCapacityHashThreshold(int capacity, int reserved) {
		int amount = Math.abs(capacity) + Math.abs(reserved);
		int loadFactor = (int) (amount / UCollectionConstants.COLLECTION_HASH_FACTOR);
		
		return 1 + loadFactor;
	}
	
	/**
	 * Calculates the threshold for ensuring capacity in a hash-based collection, using a default reserved capacity.
	 *
	 * @param capacity the current capacity of the collection
	 * @return the threshold for ensuring capacity
	 */
	protected static int ensureCapacityHashThreshold(int capacity) {
		return ensureCapacityHashThreshold(capacity, UCollectionConstants.COLLECTION_SIZE_THRESHOLD);
	}
	
	/* -----------------------------------------------------
	 * Collection bulk actions
	 * ----------------------------------------------------- */
	
	/**
	 * Adds all elements from the specified array to the given collection.
	 *
	 * @param <I>        the type of elements in the collection
	 * @param <C>        the type of collection
	 * @param collection the collection to which the elements are to be added
	 * @param elements   the array containing elements to be added to the collection
	 * @return the same collection, but with all the elements from the array added
	 */
	@SuppressWarnings({"UseBulkOperation", "ManualArrayToCollectionCopy"})
	protected static <I, C extends Collection<I>> @NotNull C collectionAddAll(@NotNull C collection, I @NotNull [] elements) {
		UAssert.paramNotNull(collection, "<C extends Collection<I>> collection");
		UAssert.paramNotNull(elements, "I[] elements");
		
		// We perform a manual copy although some code analyzers advise against it since
		// they are bulk operations. But this creates new unnecessary objects that
		// ultimately perform the same behavior.
		for (I item : elements) {
			collection.add(item);
		}
		
		// We return the same collection, but with all the elements inside.
		return collection;
	}
	
	/**
	 * Adds all elements from the specified iterator to the given collection.
	 *
	 * @param <I>        the type of elements in the collection
	 * @param <C>        the type of collection
	 * @param collection the collection to which the elements are to be added
	 * @param iterator   the iterator providing elements to be added to the collection
	 * @return the same collection, but with all the elements from the iterator added
	 */
	protected static <I, C extends Collection<I>> @NotNull C collectionAddAll(@NotNull C collection,
		@NotNull Iterator<I> iterator) {
		UAssert.paramNotNull(collection, "<C extends Collection<I>> collection");
		UAssert.paramNotNull(iterator, "Iterator<I> iterator");
		// We use the functional way of traversing a collection and add that same
		// data to the collection.
		iterator.forEachRemaining(collection::add);
		
		// We return the same collection, but with all the elements inside.
		return collection;
	}
	
	/* -----------------------------------------------------
	 * Vector bulk actions
	 * ----------------------------------------------------- */
	
	/**
	 * Adds all elements from the specified array to the given vector.
	 *
	 * @param <I>      the type of elements in the vector
	 * @param <V>      the type of the vector
	 * @param vector   the vector to which the elements are to be added
	 * @param elements the array containing elements to be added to the vector
	 * @return the same vector, but with all the elements from the array added
	 */
	protected static <I, V extends Vector<I>> @NotNull V vectorAddAll(@NotNull V vector, I @NotNull [] elements) {
		UAssert.paramNotNull(vector, "<V extends Vector<I>> vector");
		UAssert.paramNotNull(elements, "I[] elements");
		
		// We perform a manual copy although some code analyzers advise against it since
		// they are bulk operations. But this creates new unnecessary objects that
		// ultimately perform the same behavior.
		for (I item : elements) {
			vector.addElement(item);
		}
		
		// We return the same vector, but with all the elements inside.
		return vector;
	}
	
	/**
	 * Adds all elements from the specified iterator to the given vector.
	 *
	 * @param <I>      the type of elements in the vector
	 * @param <V>      the type of the vector
	 * @param vector   the vector to which the elements are to be added
	 * @param iterator the iterator providing elements to be added to the vector
	 * @return the same vector, but with all the elements from the iterator added
	 */
	protected static <I, V extends Vector<I>> @NotNull V vectorAddAll(@NotNull V vector, @NotNull Iterator<I> iterator) {
		UAssert.paramNotNull(vector, "<V extends Vector<I>> vector");
		UAssert.paramNotNull(iterator, "Iterator<I> iterator");
		// We use the functional way of traversing the iterator and add that same
		// data to the vector.
		iterator.forEachRemaining(vector::addElement);
		
		// We return the same vector, but with all the elements inside.
		return vector;
	}
	
	/* -----------------------------------------------------
	 * Deque bulk actions
	 * ----------------------------------------------------- */
	
	/**
	 * Adds all elements from the specified array to the end of the deque.
	 *
	 * @param <I>      the type of elements in the deque
	 * @param <D>      the type of deque
	 * @param deque    the deque to which the elements are to be added
	 * @param elements the array containing elements to be added to the deque
	 * @return the same deque, but with all the elements from the array added
	 */
	protected static <I, D extends Deque<I>> @NotNull D dequeAddAll(@NotNull D deque, I @NotNull [] elements) {
		UAssert.paramNotNull(deque, "<D extends Deque<I>> deque");
		UAssert.paramNotNull(elements, "I[] elements");
		
		// We perform a manual copy although some code analyzers advise against it since
		// they are bulk operations. But this creates new unnecessary objects that
		// ultimately perform the same behavior.
		for (I item : elements) {
			deque.addLast(item);
		}
		
		// We return the same deque, but with all the elements inside.
		return deque;
	}
	
	/**
	 * Adds all elements from the specified iterator to the end of the deque.
	 *
	 * @param <I>      the type of elements in the deque
	 * @param <D>      the type of deque
	 * @param deque    the deque to which the elements are to be added
	 * @param iterator the iterator providing elements to be added to the deque
	 * @return the same deque, but with all the elements from the iterator added
	 */
	protected static <I, D extends Deque<I>> @NotNull D dequeAddAll(@NotNull D deque, @NotNull Iterator<I> iterator) {
		UAssert.paramNotNull(deque, "<D extends Deque<I>> deque");
		UAssert.paramNotNull(deque, "Iterator<I> iterator");
		// We use the functional way of traversing the iterator and add that same
		// data to the deque.
		iterator.forEachRemaining(deque::addLast);
		
		// We return the same vector, but with all the elements inside.
		return deque;
	}
	
	/* -----------------------------------------------------
	 * Collection transform actions
	 * ----------------------------------------------------- */
	
	/**
	 * Transforms the elements of the original collection using the provided mapper function
	 * and collects the results into a new collection using the specified supplier.
	 *
	 * @param <O>      the type of elements in the original collection
	 * @param <R>      the type of elements in the resulting collection
	 * @param <CO>     the type of the original collection
	 * @param <CR>     the type of the resulting collection
	 * @param original the original collection of elements to transform
	 * @param mapper   the function to apply to each element for transformation
	 * @param supplier the supplier for the resulting collection
	 * @return a new collection containing the transformed elements
	 */
	protected static <O, R, CO extends Collection<O>, CR extends Collection<R>> @NotNull CR collectionTransform(
		@NotNull CO original,
		@NotNull Function<O, R> mapper,
		@NotNull Supplier<CR> supplier
	) {
		UAssert.paramNotNull(original, "<CO extends Collection<O>> original");
		UAssert.paramNotNull(original, "Function<O, R> mapper");
		UAssert.paramNotNull(original, "Supplier<CR extends Collection<R>> supplier");
		
		return original.stream()
			.map(mapper)
			.collect(supplier, Collection::add, Collection::addAll);
	}
	
	/**
	 * Transforms a vector by applying a mapping function to each element.
	 *
	 * @param <O>      the type of elements in the original vector
	 * @param <R>      the type of elements in the resulting vector
	 * @param <VO>     the type of the original vector
	 * @param <VR>     the type of the resulting vector
	 * @param original the original vector
	 * @param mapper   the mapping function to apply to each element
	 * @param supplier a supplier for the new vector
	 * @return a new vector containing the transformed elements
	 */
	protected static <O, R, VO extends Vector<O>, VR extends Vector<R>> @NotNull VR vectorTransform(
		@NotNull VO original,
		@NotNull Function<O, R> mapper,
		@NotNull Supplier<VR> supplier
	) {
		UAssert.paramNotNull(original, "<VO extends Vector<O>> original");
		UAssert.paramNotNull(original, "Function<O, R> mapper");
		UAssert.paramNotNull(original, "Supplier<VR extends Vector<R>> supplier");
		
		return original.stream()
			.map(mapper)
			.collect(supplier, Vector::addElement, Vector::addAll);
	}
	
	/**
	 * Transforms a deque by applying a mapping function to each element.
	 *
	 * @param <O>      the type of elements in the original deque
	 * @param <R>      the type of elements in the resulting deque
	 * @param <DO>     the type of original deque
	 * @param <DR>     the type of resulting deque
	 * @param original the original deque
	 * @param mapper   the mapping function to apply to each element
	 * @param supplier a supplier function that returns a new instance of the resulting deque
	 * @return a new deque containing the transformed elements
	 */
	protected static <O, R, DO extends Deque<O>, DR extends Deque<R>> @NotNull DR dequeTransform(
		@NotNull DO original,
		@NotNull Function<O, R> mapper,
		@NotNull Supplier<DR> supplier
	) {
		UAssert.paramNotNull(original, "DO extends Deque<O> original");
		UAssert.paramNotNull(original, "Function<O, R> mapper");
		UAssert.paramNotNull(original, "Supplier<DR extends Deque<R>> supplier");
		
		return original.stream()
			.map(mapper)
			.collect(supplier, Deque::addLast, Deque::addAll);
	}
	
}