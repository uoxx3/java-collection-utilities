package uoxx3.collection;

import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import uoxx3.UObjects;
import uoxx3.collection.array.*;
import uoxx3.collection.internal.array.*;

public final class UArrays {
	
	/**
	 * This class cannot be instantiated
	 */
	private UArrays() {
		throw new IllegalStateException("This class cannot be instantiated");
	}
	
	/* -----------------------------------------------------
	 * Method generators
	 * ----------------------------------------------------- */
	
	/**
	 * Creates a new UArray with the specified elements.
	 *
	 * @param <T>      the type of the elements
	 * @param elements the elements to include in the array, can be null
	 * @return a new UArray containing the specified elements
	 */
	@SafeVarargs
	public static <T> @NonNull UArray<T> of(@Nullable T... elements) {
		return new UArrayImpl<>(elements);
	}
	
	/**
	 * Creates a new UArray for the specified component type.
	 *
	 * @param <T>       the type of the elements
	 * @param component the class of the component type
	 * @return a new UArray for the specified component type
	 */
	public static <T> @NonNull UArray<T> of(@NonNull Class<T> component) {
		return new UArrayImpl<>(component);
	}
	
	/**
	 * Creates a new UByteArray with the specified byte elements.
	 *
	 * @param elements the byte elements to include in the array
	 * @return a new UByteArray containing the specified elements
	 */
	public static @NonNull UByteArray ofByte(byte... elements) {
		return new UByteArrayImpl(elements);
	}
	
	/**
	 * Creates a new UByteArray with the specified int elements, treating them as bytes.
	 *
	 * @param elements the int elements to include in the array, within the range of a byte
	 * @return a new UByteArray containing the specified elements
	 */
	public static @NonNull UByteArray ofByte(@IntRange(from = Byte.MIN_VALUE, to = Byte.MAX_VALUE) int... elements) {
		return UObjects.wrapper(new UByteArrayImpl())
			.also(w -> w.addAll(elements))
			.get();
	}
	
	/**
	 * Creates a new UCharacterArray with the specified char elements.
	 *
	 * @param elements the char elements to include in the array
	 * @return a new UCharacterArray containing the specified elements
	 */
	public static @NonNull UCharacterArray ofChar(char... elements) {
		return new UCharacterArrayImpl(elements);
	}
	
	/**
	 * Creates a new UCharacterArray with the specified int elements, treating them as chars.
	 *
	 * @param elements the int elements to include in the array, within the range of a char
	 * @return a new UCharacterArray containing the specified elements
	 */
	public static @NonNull UCharacterArray ofChar(
		@IntRange(from = Character.MIN_VALUE, to = Character.MAX_VALUE) int... elements) {
		return UObjects.wrapper(new UCharacterArrayImpl())
			.also(w -> w.addAll(elements))
			.get();
	}
	
	/**
	 * Creates a new UDoubleArray with the specified double elements.
	 *
	 * @param elements the double elements to include in the array
	 * @return a new UDoubleArray containing the specified elements
	 */
	public static @NonNull UDoubleArray ofDouble(double... elements) {
		return new UDoubleArrayImpl(elements);
	}
	
	/**
	 * Creates a new UFloatArray with the specified float elements.
	 *
	 * @param elements the float elements to include in the array
	 * @return a new UFloatArray containing the specified elements
	 */
	public static @NonNull UFloatArray ofFloat(float... elements) {
		return new UFloatArrayImpl(elements);
	}
	
	/**
	 * Creates a new UIntegerArray with the specified int elements.
	 *
	 * @param elements the int elements to include in the array
	 * @return a new UIntegerArray containing the specified elements
	 */
	public static @NonNull UIntegerArray ofInt(int... elements) {
		return new UIntegerArrayImpl(elements);
	}
	
	/**
	 * Creates a new ULongArray with the specified long elements.
	 *
	 * @param elements the long elements to include in the array
	 * @return a new ULongArray containing the specified elements
	 */
	public static @NonNull ULongArray ofLong(long... elements) {
		return new ULongArrayImpl(elements);
	}
	
	/**
	 * Creates a new UShortArray with the specified short elements.
	 *
	 * @param elements the short elements to include in the array
	 * @return a new UShortArray containing the specified elements
	 */
	public static @NonNull UShortArray ofShort(short... elements) {
		return new UShortArrayImpl(elements);
	}
	
	/**
	 * Creates a new UShortArray with the specified int elements, treating them as shorts.
	 *
	 * @param elements the int elements to include in the array, within the range of a short
	 * @return a new UShortArray containing the specified elements
	 */
	public static @NonNull UShortArray ofShort(@IntRange(from = Short.MIN_VALUE, to = Short.MAX_VALUE) int... elements) {
		return UObjects.wrapper(new UShortArrayImpl())
			.also(w -> w.addAll(elements))
			.get();
	}
	
}