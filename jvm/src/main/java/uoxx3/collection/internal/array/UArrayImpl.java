package uoxx3.collection.internal.array;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.UnmodifiableView;
import uoxx3.UAssert;
import uoxx3.UClass;
import uoxx3.collection.UCollectionConstants;
import uoxx3.collection.array.UArray;
import uoxx3.collection.collection.UList;
import uoxx3.collection.collection.USet;
import uoxx3.collection.collection.UStack;
import uoxx3.collection.collection.UVector;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public final class UArrayImpl<T> implements UArray<T> {
	
	/* -----------------------------------------------------
	 * Properties
	 * ----------------------------------------------------- */
	
	private T[] array;
	private final Class<T> componentType;
	
	/* -----------------------------------------------------
	 * Constructor
	 * ----------------------------------------------------- */
	
	@SafeVarargs
	public UArrayImpl(T @Nullable ... elements) {
		UAssert.paramNotNull(elements, "T[] elements");
		array = elements;
		componentType = UClass.arrayComponentType(elements);
	}
	
	@SuppressWarnings("unchecked")
	public UArrayImpl(@NotNull Class<T> cls) {
		UAssert.paramNotNull(cls, "Class<T> cls");
		array = (T[]) Array.newInstance(cls, UCollectionConstants.EMPTY_SIZE);
		componentType = cls;
	}
	
	/* -----------------------------------------------------
	 * Methods
	 * ----------------------------------------------------- */
	
	/**
	 * Add a new element to the array
	 *
	 * @param element The element to add
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void add(@Nullable T element) {
		// Generate new array
		T[] targetArray = (T[]) Array.newInstance(componentType(), size() + 1);
		
		// Copy the old information to the target array
		System.arraycopy(getArray(), 0, targetArray, 0, size());
		targetArray[size()] = element;
		
		// Replace the instance array
		array = targetArray;
	}
	
	/**
	 * Add multiple elements to the array
	 *
	 * @param elements All the elements wants to add
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void addAll(T @Nullable ... elements) {
		UAssert.paramNotNull(elements, "T[] elements");
		// Generate new array
		final int newSize = size() + elements.length;
		T[] targetArray = (T[]) Array.newInstance(componentType(), newSize);
		
		// Copy the old array information
		System.arraycopy(getArray(), 0, targetArray, 0, size());
		System.arraycopy(elements, 0, targetArray, size(), elements.length);
		
		// Replace the instance array
		array = targetArray;
	}
	
	/**
	 * Gets the type of the array component.
	 *
	 * @return the component type of the object
	 */
	@Override
	public @NotNull Class<T> componentType() {
		return componentType;
	}
	
	/**
	 * Checks if the array contains the specified value.
	 *
	 * @param value The value to check for.
	 * @return true if the array contains the value, false otherwise.
	 */
	@Override
	public boolean contains(@Nullable T value) {
		return indexOf(value) != UCollectionConstants.INDEX_NOT_FOUND;
	}
	
	/**
	 * Returns an Optional containing the first element of the array, or an empty Optional if the array is empty.
	 *
	 * @return An Optional containing the first element of the array, or an empty Optional if the array is empty.
	 */
	@Override
	public @NotNull Optional<T> first() {
		if (isEmpty()) return Optional.empty();
		return Optional.ofNullable(get(0));
	}
	
	/**
	 * Gets the native wrapped array.
	 *
	 * @return a native array
	 */
	@Override
	public T[] getArray() {
		return array;
	}
	
	/**
	 * Returns the index of the first occurrence of the specified value in the array,
	 * or {@link UCollectionConstants#INDEX_NOT_FOUND} if the value is not found.
	 *
	 * @param value The value to search for.
	 * @return The index of the first occurrence of the value, or {@link UCollectionConstants#INDEX_NOT_FOUND} if the
	 * 	value is not found.
	 */
	@Override
	public int indexOf(@Nullable T value) {
		checkArray:
		{
			if (isEmpty()) break checkArray;
			// Check if the element is a <null> case
			if (value == null) {
				for (int i = 0; i < size(); i++) {
					T element = get(i);
					
					// In this specific case, it is not necessary to
					// determine if the value is equal to the one in the array
					// because both must be null for equality to exist. Then it is only
					// checked if the value is null to determine that they are equal.
					if (element == null) return i;
				}
			} else {
				for (int i = 0; i < size(); i++) {
					T element = get(i);
					
					// In this case we verify that the object is equal to any of
					// the elements of the array.
					if (Objects.equals(value, element)) return i;
				}
			}
		}
		
		return UCollectionConstants.INDEX_NOT_FOUND;
	}
	
	/**
	 * Returns an Optional containing the last element of the array, or an empty Optional if the array is empty.
	 *
	 * @return An Optional containing the last element of the array, or an empty Optional if the array is empty.
	 */
	@Override
	public @NotNull Optional<T> last() {
		if (isEmpty()) return Optional.empty();
		return Optional.ofNullable(get(size() - 1));
	}
	
	/**
	 * Returns the index of the last occurrence of the specified value in the array,
	 * or {@link UCollectionConstants#INDEX_NOT_FOUND} if the value is not found.
	 *
	 * @param value The value to search for.
	 * @return The index of the last occurrence of the value, or {@link UCollectionConstants#INDEX_NOT_FOUND}
	 * 	if the value is not found.
	 */
	@Override
	public int lastIndexOf(@Nullable T value) {
		checkArray:
		{
			if (isEmpty()) break checkArray;
			// Check if the element is a <null> case
			if (value == null) {
				for (int i = size() - 1; i >= 0; i--) {
					T element = get(i);
					
					// In this specific case, it is not necessary to
					// determine if the value is equal to the one in the array
					// because both must be null for equality to exist. Then it is only
					// checked if the value is null to determine that they are equal.
					if (element == null) return i;
				}
			} else {
				for (int i = size() - 1; i >= 0; i--) {
					T element = get(i);
					
					// In this case we verify that the object is equal to any of
					// the elements of the array.
					if (Objects.equals(value, element)) return i;
				}
			}
		}
		
		return UCollectionConstants.INDEX_NOT_FOUND;
	}
	
	/**
	 * Returns the size of the array.
	 *
	 * @return The size of the array.
	 */
	@Override
	public int size() {
		return array.length;
	}
	
	/**
	 * Gets a sequential Stream with the elements of the array as its source.
	 *
	 * @return A Stream of the elements in the array.
	 */
	@Override
	public @NotNull Stream<T> stream() {
		if (isEmpty()) return Stream.empty();
		// Generate new array stream
		return StreamSupport.stream(
			Arrays.spliterator(getArray(), 0, size()),
			false
		);
	}
	
	/**
	 * Returns an iterator over elements of type {@code T}.
	 *
	 * @return an Iterator.
	 */
	@NotNull
	@Override
	public Iterator<T> iterator() {
		if (isEmpty()) return Collections.emptyIterator();
		// Generate new array iterator
		return stream().iterator();
	}
	
	/**
	 * Creates a deep copy of the object.
	 *
	 * @return A deep copy of the object.
	 */
	@Override
	public @NotNull UArray<T> copy() {
		return new UArrayImpl<>(getArray().clone());
	}
	
	/**
	 * Object string representation
	 *
	 * @return object string representation
	 */
	@Override
	public @NotNull String toString() {
		return Arrays.toString(getArray());
	}
	
	/* -----------------------------------------------------
	 * Conversions
	 * ----------------------------------------------------- */
	
	/**
	 * Converts the array to an unmodifiable list.
	 *
	 * @return an unmodifiable view of the array as a list
	 */
	@Override
	public @NotNull @UnmodifiableView List<T> toList() {
		return UList.make(getArray());
	}
	
	/**
	 * Converts the array to a mutable list.
	 *
	 * @return a mutable list containing the elements of the array
	 */
	@Override
	public @NotNull List<T> toMutableList() {
		return UList.makeMutable(getArray());
	}
	
	/**
	 * Converts the array to an unmodifiable set.
	 *
	 * @return an unmodifiable view of the array as a set
	 */
	@Override
	public @NotNull @UnmodifiableView Set<T> toSet() {
		return USet.make(getArray());
	}
	
	/**
	 * Converts the array to a mutable set.
	 *
	 * @return a mutable set containing the elements of the array
	 */
	@Override
	public @NotNull Set<T> toMutableSet() {
		return USet.makeMutable(getArray());
	}
	
	/**
	 * Converts the array to a vector.
	 *
	 * @return a vector containing the elements of the array
	 */
	@Override
	public @NotNull Vector<T> toVector() {
		return UVector.make(getArray());
	}
	
	/**
	 * Converts the array to a stack.
	 *
	 * @return a stack containing the elements of the array
	 */
	@Override
	public @NotNull Stack<T> toStack() {
		return UStack.make(getArray());
	}
	
}
