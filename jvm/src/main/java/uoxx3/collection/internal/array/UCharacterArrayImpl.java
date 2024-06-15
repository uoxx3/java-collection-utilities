package uoxx3.collection.internal.array;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;
import uoxx3.UAssert;
import uoxx3.collection.UCollectionConstants;
import uoxx3.collection.array.UCharacterArray;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public final class UCharacterArrayImpl implements UCharacterArray {
	
	/* -----------------------------------------------------
	 * Properties
	 * ----------------------------------------------------- */
	
	private char[] array;
	private final Class<Character> componentType;
	
	/* -----------------------------------------------------
	 * Constructor
	 * ----------------------------------------------------- */
	
	public UCharacterArrayImpl(char... elements) {
		UAssert.paramNotNull(elements, "char[] elements");
		array = elements;
		componentType = char.class;
	}
	
	public UCharacterArrayImpl() {
		this(new char[0]);
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
	public void add(@Range(from = Character.MIN_VALUE, to = Character.MAX_VALUE) int element) {
		add((char) element);
	}
	
	/**
	 * Add a new element to the array
	 *
	 * @param element The element to add
	 */
	@Override
	public void add(char element) {
		// Generate new array
		final int newSize = size() + 1;
		char[] targetArray = new char[newSize];
		
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
	@Override
	public void addAll(int @Range(from = Character.MIN_VALUE, to = Character.MAX_VALUE) ... elements) {
		UAssert.paramNotNull(elements, "int[] elements");
		// Convert the int array to byte array
		char[] charElements = Arrays.stream(elements)
			.mapToObj(v -> (char) v)
			.collect(charArrayCollector());
		
		// Add the elements to the array
		addAll(charElements);
	}
	
	/**
	 * Add multiple elements to the array
	 *
	 * @param elements All the elements wants to add
	 */
	@Override
	public void addAll(char... elements) {
		UAssert.paramNotNull(elements, "char[] elements");
		// Generate new array
		final int newSize = size() + elements.length;
		char[] targetArray = new char[newSize];
		
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
	public boolean contains(@Range(from = Character.MIN_VALUE, to = Character.MAX_VALUE) int value) {
		return indexOf(value) != UCollectionConstants.INDEX_NOT_FOUND;
	}
	
	/**
	 * Checks if the array contains the specified value.
	 *
	 * @param value The value to check for.
	 * @return true if the array contains the value, false otherwise.
	 */
	@Override
	public boolean contains(char value) {
		return indexOf(value) != UCollectionConstants.INDEX_NOT_FOUND;
	}
	
	/**
	 * Gets the native wrapped array.
	 *
	 * @return a native array
	 */
	@Override
	public char[] getArray() {
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
	public int indexOf(char value) {
		checkArray:
		{
			if (isEmpty()) break checkArray;
			// Check if the element is a <null> case
			for (int i = 0; i < size(); i++) {
				char element = get(i);
				
				// In this case we verify that the object is equal to any of
				// the elements of the array.
				if (value == element) return i;
			}
		}
		
		return UCollectionConstants.INDEX_NOT_FOUND;
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
	public int indexOf(@Range(from = Character.MIN_VALUE, to = Character.MAX_VALUE) int value) {
		return indexOf((char) value);
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
	public int lastIndexOf(char value) {
		checkArray:
		{
			if (isEmpty()) break checkArray;
			// Check if the element is a <null> case
			for (int i = size() - 1; i >= 0; i--) {
				char element = get(i);
				
				// In this case we verify that the object is equal to any of
				// the elements of the array.
				if (value == element) return i;
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
	public int lastIndexOf(@Range(from = Character.MIN_VALUE, to = Character.MAX_VALUE) int value) {
		return lastIndexOf((char) value);
	}
	
	/**
	 * Gets a sequential Stream with the elements of the array as its source.
	 *
	 * @return A Stream of the elements in the array.
	 */
	@Override
	public @NotNull Stream<Character> stream() {
		if (isEmpty()) return Stream.empty();
		// Generate new array stream
		return IntStream.range(UCollectionConstants.EMPTY_SIZE, size())
			.map(this::get)
			.mapToObj(v -> (char) v);
	}
	
	/**
	 * Creates a deep copy of the object.
	 *
	 * @return A deep copy of the object.
	 */
	@Override
	public @NotNull UCharacterArray copy() {
		return new UCharacterArrayImpl(getArray().clone());
	}
	
	/**
	 * Returns an Optional containing the first element of the array, or an empty Optional if the array is empty.
	 *
	 * @return An Optional containing the first element of the array, or an empty Optional if the array is empty.
	 */
	@Override
	public @NotNull Optional<Character> first() {
		if (isEmpty()) return Optional.empty();
		return Optional.of(get(0));
	}
	
	/**
	 * Gets the type of the array component.
	 *
	 * @return the component type of the object
	 */
	@Override
	public @NotNull Class<Character> componentType() {
		return componentType;
	}
	
	/**
	 * Returns an Optional containing the last element of the array, or an empty Optional if the array is empty.
	 *
	 * @return An Optional containing the last element of the array, or an empty Optional if the array is empty.
	 */
	@Override
	public @NotNull Optional<Character> last() {
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
	public Iterator<Character> iterator() {
		if (isEmpty()) return Collections.emptyIterator();
		// Generate new array iterator
		return stream().iterator();
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
	private static @NotNull Collector<Character, ?, char[]> charArrayCollector() {
		return Collectors.collectingAndThen(
			Collectors.toList(),
			list -> {
				char[] result = new char[list.size()];
				for (int i = 0; i < result.length; i++) {
					result[i] = list.get(i);
				}
				
				return result;
			}
		);
	}
	
	
}
