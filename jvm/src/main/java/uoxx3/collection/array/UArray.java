package uoxx3.collection.array;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.UnmodifiableView;
import uoxx3.UAssert;
import uoxx3.UCopyable;
import uoxx3.collection.UCollectionConstants;
import uoxx3.collection.internal.UArrayType;
import uoxx3.functional.function.UEmptyFun;

import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.Vector;
import java.util.function.Consumer;
import java.util.stream.Stream;

public interface UArray<T> extends UArrayType<T>, UCopyable<UArray<T>> {
	
	/**
	 * Add a new element to the array
	 *
	 * @param element The element to add
	 */
	void add(@Nullable T element);
	
	/**
	 * Add multiple elements to the array
	 *
	 * @param elements All the elements wants to add
	 */
	@SuppressWarnings("unchecked")
	void addAll(T @Nullable ... elements);
	
	/**
	 * Checks if the array contains the specified value.
	 *
	 * @param value The value to check for.
	 * @return true if the array contains the value, false otherwise.
	 */
	boolean contains(@Nullable T value);
	
	/**
	 * Performs the given action on each item in the array, ignoring all {@code null} elements.
	 *
	 * @param consumer The action to perform on each item in the array.
	 */
	default void forEachNotNull(@NotNull Consumer<? super T> consumer) {
		UAssert.paramNotNull(consumer, "Consumer<? extends T> consumer");
		for (T element : this) {
			if (element != null) consumer.accept(element);
		}
	}
	
	/**
	 * Performs the given action on each item in the array, ignoring all {@code null} elements.
	 *
	 * @param consumer The action to perform on each item in the array.
	 */
	default void forEachIndexedNotNull(@NotNull UEmptyFun.UEmptyFun2<Integer, T> consumer) {
		UAssert.paramNotNull(consumer, "UEmptyFun.UEmptyFun2<Integer, T> consumer");
		
		int counter = 0;
		for (T element : this) {
			if (element != null) consumer.invoke(counter, element);
			counter++;
		}
	}
	
	/**
	 * Gets the index of the array
	 *
	 * @param index The index of the element want to access
	 * @return the element of the array
	 * @throws IndexOutOfBoundsException if the array index is not part of its limits
	 */
	default T get(int index) {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException("Index out of range: " + index);
		}
		
		return getArray()[index];
	}
	
	/**
	 * Gets the native wrapped array.
	 *
	 * @return a native array
	 */
	T[] getArray();
	
	/**
	 * Returns the index of the first occurrence of the specified value in the array,
	 * or {@link UCollectionConstants#INDEX_NOT_FOUND} if the value is not found.
	 *
	 * @param value The value to search for.
	 * @return The index of the first occurrence of the value, or {@link UCollectionConstants#INDEX_NOT_FOUND} if the
	 * 	value is not found.
	 */
	int indexOf(@Nullable T value);
	
	/**
	 * Returns the index of the last occurrence of the specified value in the array,
	 * or {@link UCollectionConstants#INDEX_NOT_FOUND} if the value is not found.
	 *
	 * @param value The value to search for.
	 * @return The index of the last occurrence of the value, or {@link UCollectionConstants#INDEX_NOT_FOUND}
	 * 	if the value is not found.
	 */
	int lastIndexOf(@Nullable T value);
	
	/**
	 * Gets a sequential Stream with the elements of the array as its source.
	 *
	 * @return A Stream of the elements in the array.
	 */
	@NotNull
	Stream<T> stream();
	
	/* -----------------------------------------------------
	 * Conversions
	 * ----------------------------------------------------- */
	
	/**
	 * Converts the array to an unmodifiable list.
	 *
	 * @return an unmodifiable view of the array as a list
	 */
	@NotNull
	@UnmodifiableView
	List<T> toList();
	
	/**
	 * Converts the array to a mutable list.
	 *
	 * @return a mutable list containing the elements of the array
	 */
	@NotNull
	List<T> toMutableList();
	
	/**
	 * Converts the array to an unmodifiable set.
	 *
	 * @return an unmodifiable view of the array as a set
	 */
	@NotNull
	@UnmodifiableView
	Set<T> toSet();
	
	/**
	 * Converts the array to a mutable set.
	 *
	 * @return a mutable set containing the elements of the array
	 */
	@NotNull
	Set<T> toMutableSet();
	
	/**
	 * Converts the array to a vector.
	 *
	 * @return a vector containing the elements of the array
	 */
	@NotNull
	Vector<T> toVector();
	
	/**
	 * Converts the array to a stack.
	 *
	 * @return a stack containing the elements of the array
	 */
	@NotNull
	Stack<T> toStack();
	
}