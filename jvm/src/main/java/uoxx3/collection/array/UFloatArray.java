package uoxx3.collection.array;

import org.jetbrains.annotations.NotNull;
import uoxx3.UCopyable;
import uoxx3.collection.UCollectionConstants;
import uoxx3.collection.internal.UArrayType;

import java.util.stream.DoubleStream;

public interface UFloatArray extends UArrayType<Float>, UCopyable<UFloatArray> {
	
	/**
	 * Add a new element to the array
	 *
	 * @param element The element to add
	 */
	void add(float element);
	
	/**
	 * Add multiple elements to the array
	 *
	 * @param elements All the elements wants to add
	 */
	void addAll(float... elements);
	
	/**
	 * Checks if the array contains the specified value.
	 *
	 * @param value The value to check for.
	 * @return true if the array contains the value, false otherwise.
	 */
	boolean contains(float value);
	
	/**
	 * Gets the index of the array
	 *
	 * @param index The index of the element want to access
	 * @return the element of the array
	 * @throws IndexOutOfBoundsException if the array index is not part of its limits
	 */
	default float get(int index) {
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
	float[] getArray();
	
	/**
	 * Returns the index of the first occurrence of the specified value in the array,
	 * or {@link UCollectionConstants#INDEX_NOT_FOUND} if the value is not found.
	 *
	 * @param value The value to search for.
	 * @return The index of the first occurrence of the value, or {@link UCollectionConstants#INDEX_NOT_FOUND} if the
	 * 	value is not found.
	 */
	int indexOf(float value);
	
	/**
	 * Returns the index of the last occurrence of the specified value in the array,
	 * or {@link UCollectionConstants#INDEX_NOT_FOUND} if the value is not found.
	 *
	 * @param value The value to search for.
	 * @return The index of the last occurrence of the value, or {@link UCollectionConstants#INDEX_NOT_FOUND}
	 * 	if the value is not found.
	 */
	int lastIndexOf(float value);
	
	/**
	 * Gets a sequential Stream with the elements of the array as its source.
	 *
	 * @return A Stream of the elements in the array.
	 */
	@NotNull
	DoubleStream stream();
	
}