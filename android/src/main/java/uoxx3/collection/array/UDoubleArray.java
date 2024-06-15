package uoxx3.collection.array;

import androidx.annotation.NonNull;
import uoxx3.UCopyable;
import uoxx3.collection.UCollectionConstants;
import uoxx3.collection.internal.UArrayType;

import java.util.stream.DoubleStream;

public interface UDoubleArray extends UArrayType<Double>, UCopyable<UDoubleArray> {
	
	/**
	 * Add a new element to the array
	 *
	 * @param element The element to add
	 */
	void add(double element);
	
	/**
	 * Add multiple elements to the array
	 *
	 * @param elements All the elements wants to add
	 */
	void addAll(double... elements);
	
	/**
	 * Checks if the array contains the specified value.
	 *
	 * @param value The value to check for.
	 * @return true if the array contains the value, false otherwise.
	 */
	boolean contains(double value);
	
	/**
	 * Gets the index of the array
	 *
	 * @param index The index of the element want to access
	 * @return the element of the array
	 * @throws IndexOutOfBoundsException if the array index is not part of its limits
	 */
	default double get(int index) {
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
	double[] getArray();
	
	/**
	 * Returns the index of the first occurrence of the specified value in the array,
	 * or {@link UCollectionConstants#INDEX_NOT_FOUND} if the value is not found.
	 *
	 * @param value The value to search for.
	 * @return The index of the first occurrence of the value, or {@link UCollectionConstants#INDEX_NOT_FOUND} if the
	 * 	value is not found.
	 */
	int indexOf(double value);
	
	/**
	 * Returns the index of the last occurrence of the specified value in the array,
	 * or {@link UCollectionConstants#INDEX_NOT_FOUND} if the value is not found.
	 *
	 * @param value The value to search for.
	 * @return The index of the last occurrence of the value, or {@link UCollectionConstants#INDEX_NOT_FOUND}
	 * 	if the value is not found.
	 */
	int lastIndexOf(double value);
	
	/**
	 * Gets a sequential Stream with the elements of the array as its source.
	 *
	 * @return A Stream of the elements in the array.
	 */
	@NonNull
	DoubleStream stream();
	
}