package uoxx3.collection.collection;

import androidx.annotation.NonNull;
import uoxx3.collection.internal.collection.UCollectionCommon;

import java.util.Collection;
import java.util.Iterator;
import java.util.Stack;
import java.util.function.Function;

public final class UStack extends UCollectionCommon {
	
	/**
	 * This class cannot be instantiated
	 */
	private UStack() {
		throw new IllegalStateException("This class cannot be instantiated");
	}
	
	/* -----------------------------------------------------
	 * Generator methods
	 * ----------------------------------------------------- */
	
	/**
	 * Creates a new stack and adds all elements from the specified array to it.
	 *
	 * @param <T>      the type of elements in the stack
	 * @param elements the elements to add to the stack
	 * @return a new stack containing all the elements
	 */
	@SafeVarargs
	public static <T> @NonNull Stack<T> make(@NonNull T... elements) {
		return vectorAddAll(new Stack<>(), elements);
	}
	
	/**
	 * Creates a new stack and adds all elements from the specified iterator to it.
	 *
	 * @param <T>      the type of elements in the stack
	 * @param iterator the iterator providing elements to add to the stack
	 * @return a new stack containing all the elements
	 */
	public static <T> @NonNull Stack<T> make(@NonNull Iterator<T> iterator) {
		return vectorAddAll(new Stack<>(), iterator);
	}
	
	/**
	 * Creates a new stack and adds all elements from the specified collection to it.
	 *
	 * @param <T>        the type of elements in the stack
	 * @param collection the collection providing elements to add to the stack
	 * @return a new stack containing all the elements
	 */
	public static <T> @NonNull Stack<T> make(@NonNull Collection<T> collection) {
		return make(collection.iterator());
	}
	
	/* -----------------------------------------------------
	 * Stack transform methods
	 * ----------------------------------------------------- */
	
	/**
	 * Transforms a stack by applying a mapping function to each element.
	 *
	 * @param <O>      the type of elements in the original stack
	 * @param <R>      the type of elements in the resulting stack
	 * @param original the original stack
	 * @param mapper   the mapping function to apply to each element
	 * @return a new stack containing the transformed elements
	 */
	public static <O, R> @NonNull Stack<R> transform(@NonNull Stack<O> original, @NonNull Function<O, R> mapper) {
		return vectorTransform(original, mapper, Stack::new);
	}
	
}