package uoxx3.collection.internal;

import androidx.annotation.NonNull;
import uoxx3.UAssert;
import uoxx3.functional.function.UEmptyFun;

import java.util.Optional;

public interface UArrayType<T> extends Iterable<T> {
	
	/**
	 * Returns an Optional containing the first element of the array, or an empty Optional if the array is empty.
	 *
	 * @return An Optional containing the first element of the array, or an empty Optional if the array is empty.
	 */
	@NonNull
	Optional<T> first();
	
	/**
	 * Gets the type of the array component.
	 *
	 * @return the component type of the object
	 */
	@NonNull
	Class<T> componentType();
	
	/**
	 * Performs the given action on each item in the array, passing the index of the item as a parameter.
	 *
	 * @param consumer The action to perform on each item in the array.
	 */
	default void forEachIndexed(@NonNull UEmptyFun.UEmptyFun2<Integer, T> consumer) {
		UAssert.paramNotNull(consumer, "UEmptyFun.UEmptyFun2<Integer, Integer> consumer");
		
		int counter = 0;
		for (T element : this) {
			consumer.invoke(counter++, element);
		}
	}
	
	/**
	 * Returns true if the array is empty, false otherwise.
	 *
	 * @return true if the array is empty, false otherwise.
	 */
	default boolean isEmpty() {
		return size() == 0;
	}
	
	/**
	 * Returns true if the array is not empty, false otherwise.
	 *
	 * @return true if the array is not empty, false otherwise.
	 */
	default boolean isNotEmpty() {
		return size() > 0;
	}
	
	/**
	 * Returns an Optional containing the last element of the array, or an empty Optional if the array is empty.
	 *
	 * @return An Optional containing the last element of the array, or an empty Optional if the array is empty.
	 */
	@NonNull
	Optional<T> last();
	
	/**
	 * Returns the size of the array.
	 *
	 * @return The size of the array.
	 */
	int size();
	
}
