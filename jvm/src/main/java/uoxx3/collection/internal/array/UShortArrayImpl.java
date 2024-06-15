package uoxx3.collection.internal.array;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;
import uoxx3.UAssert;
import uoxx3.collection.UCollectionConstants;
import uoxx3.collection.array.UShortArray;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class UShortArrayImpl implements UShortArray {
	
	/* -----------------------------------------------------
	 * Properties
	 * ----------------------------------------------------- */
	
	private short[] array;
	private final Class<Short> componentType;
	
	/* -----------------------------------------------------
	 * Constructor
	 * ----------------------------------------------------- */
	
	public UShortArrayImpl(short... elements) {
		UAssert.paramNotNull(elements, "short[] elements");
		array = elements;
		componentType = short.class;
	}
	
	public UShortArrayImpl() {
		this(new short[0]);
	}
	
	/* -----------------------------------------------------
	 * Methods
	 * ----------------------------------------------------- */
	
	/**
	 * Add a new element to the array
	 *
	 * @param element The element to add
	 */
	@Override
	public void add(@Range(from = Short.MIN_VALUE, to = Short.MAX_VALUE) int element) {
		// Generate new array
		final int newSize = size() + 1;
		short[] targetArray = new short[newSize];
		
		// Copy the old information to the target array
		System.arraycopy(getArray(), 0, targetArray, 0, size());
		targetArray[size()] = (short) element;
		
		// Replace the instance array
		array = targetArray;
	}
	
	/**
	 * Add multiple elements to the array
	 *
	 * @param elements All the elements wants to add
	 */
	@Override
	public void addAll(int @Range(from = Short.MIN_VALUE, to = Short.MAX_VALUE) ... elements) {
		UAssert.paramNotNull(elements, "int[] elements");
		// Convert the int array to short array
		short[] shortElements = Arrays.stream(elements)
			.mapToObj(v -> (short) v)
			.collect(shortArrayCollector());
		
		// Add the elements to the array
		addAll(shortElements);
	}
	
	/**
	 * Add multiple elements to the array
	 *
	 * @param elements All the elements wants to add
	 */
	public void addAll(short... elements) {
		UAssert.paramNotNull(elements, "byte[] elements");
		// Generate new array
		final int newSize = size() + elements.length;
		short[] targetArray = new short[newSize];
		
		// Copy the old array information
		System.arraycopy(getArray(), 0, targetArray, 0, size());
		System.arraycopy(elements, 0, targetArray, size(), elements.length);
		
		// Replace the instance array
		array = targetArray;
	}
	
	/**
	 * Checks if the array contains the specified value.
	 *
	 * @param value The value to check for.
	 * @return true if the array contains the value, false otherwise.
	 */
	@Override
	public boolean contains(@Range(from = Short.MIN_VALUE, to = Short.MAX_VALUE) int value) {
		return indexOf(value) != UCollectionConstants.INDEX_NOT_FOUND;
	}
	
	/**
	 * Gets the native wrapped array.
	 *
	 * @return a native array
	 */
	@Override
	public short[] getArray() {
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
	public int indexOf(@Range(from = Short.MIN_VALUE, to = Short.MAX_VALUE) int value) {
		checkArray:
		{
			if (isEmpty()) break checkArray;
			// Check if the element is a <null> case
			for (int i = 0; i < size(); i++) {
				short element = get(i);
				
				// In this case we verify that the object is equal to any of
				// the elements of the array.
				if (((short) value) == element) return i;
			}
		}
		
		return UCollectionConstants.INDEX_NOT_FOUND;
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
	public int lastIndexOf(@Range(from = Short.MIN_VALUE, to = Short.MAX_VALUE) int value) {
		checkArray:
		{
			if (isEmpty()) break checkArray;
			// Check if the element is a <null> case
			for (int i = size() - 1; i >= 0; i--) {
				short element = get(i);
				
				// In this case we verify that the object is equal to any of
				// the elements of the array.
				if (((short) value) == element) return i;
			}
		}
		
		return UCollectionConstants.INDEX_NOT_FOUND;
	}
	
	/**
	 * Gets a sequential Stream with the elements of the array as its source.
	 *
	 * @return A Stream of the elements in the array.
	 */
	@Override
	public @NotNull IntStream stream() {
		if (isEmpty()) return IntStream.empty();
		// Generate new array stream
		return IntStream.range(UCollectionConstants.EMPTY_SIZE, size())
			.map(this::get);
	}
	
	/**
	 * Creates a deep copy of the object.
	 *
	 * @return A deep copy of the object.
	 */
	@Override
	public @NotNull UShortArray copy() {
		return new UShortArrayImpl(getArray().clone());
	}
	
	/**
	 * Returns an Optional containing the first element of the array, or an empty Optional if the array is empty.
	 *
	 * @return An Optional containing the first element of the array, or an empty Optional if the array is empty.
	 */
	@Override
	public @NotNull Optional<Short> first() {
		if (isEmpty()) return Optional.empty();
		return Optional.of(get(0));
	}
	
	/**
	 * Gets the type of the array component.
	 *
	 * @return the component type of the object
	 */
	@Override
	public @NotNull Class<Short> componentType() {
		return componentType;
	}
	
	/**
	 * Returns an Optional containing the last element of the array, or an empty Optional if the array is empty.
	 *
	 * @return An Optional containing the last element of the array, or an empty Optional if the array is empty.
	 */
	@Override
	public @NotNull Optional<Short> last() {
		if (isEmpty()) return Optional.empty();
		return Optional.of(get(size() - 1));
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
	 * Returns an iterator over elements of type {@code T}.
	 *
	 * @return an Iterator.
	 */
	@NotNull
	@Override
	public Iterator<Short> iterator() {
		if (isEmpty()) return Collections.emptyIterator();
		// Generate new array iterator
		return stream()
			.mapToObj(v -> (short) v)
			.iterator();
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
	 * Internal methods
	 * ----------------------------------------------------- */
	
	@Contract(" -> new")
	private static @NotNull Collector<Short, ?, short[]> shortArrayCollector() {
		return Collectors.collectingAndThen(
			Collectors.toList(),
			list -> {
				short[] result = new short[list.size()];
				
				for (int i = 0; i < result.length; i++) {
					result[i] = list.get(i);
				}
				
				return result;
			}
		);
	}
	
	
}
