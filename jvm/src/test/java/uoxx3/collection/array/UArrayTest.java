package uoxx3.collection.array;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import uoxx3.collection.UArrays;

import java.util.concurrent.atomic.AtomicBoolean;

class UArrayTest {
	
	UArray<String> sharedArray = UArrays.of(
		"Hello", ", ", "World", "!"
	);
	
	@Test
	public void makeGenericTest() {
		Assertions.assertEquals(String.class, sharedArray.componentType(),
								"Invalid component type");
		
		System.out.println(sharedArray);
	}
	
	@Test
	public void addTest() {
		String element = "extra";
		
		sharedArray.add(element);
		String last = sharedArray.get(sharedArray.size() - 1);
		
		Assertions.assertEquals(element, last,
								"The element is not equals");
		
		System.out.println(sharedArray);
	}
	
	@Test
	public void addAllTest() {
		String[] elements = {"extra1", "extra2", "extra3"};
		int current = sharedArray.size();
		int newSize = current + elements.length;
		
		sharedArray.addAll(elements);
		Assertions.assertEquals(newSize, sharedArray.size(),
								"Invalid array size");
		
		System.out.println(sharedArray);
	}
	
	@Test
	public void componentTypeTest() {
		Class<?> expected = String.class;
		Assertions.assertEquals(expected, sharedArray.componentType(),
								"Invalid component type");
	}
	
	@Test
	public void containsTest() {
		String validSearch = "!";
		boolean expected = true;
		boolean result = sharedArray.contains(validSearch);
		
		Assertions.assertEquals(expected, result,
								"Invalid result");
		
		// Invalid result
		String invalidSearch = "element";
		expected = false;
		result = sharedArray.contains(invalidSearch);
		
		Assertions.assertEquals(expected, result,
								"Invalid result");
		
		System.out.println(sharedArray);
	}
	
	@Test
	public void firstTest() {
		var element = sharedArray.first();
		
		Assertions.assertTrue(element.isPresent(),
							  "Invalid result");
		Assertions.assertEquals("Hello", element.get(),
								"Content is not equal");
		
		System.out.println(element);
	}
	
	@Test
	public void forEachNotNullTest() {
		// Add some null values
		String[] nullValues = {null, "1", null, "2", null};
		sharedArray.addAll(nullValues);
		
		System.out.printf("Original array: %s%n", sharedArray);
		sharedArray.forEachNotNull(i -> {
			// Assert value
			Assertions.assertNotNull(i, "The element cannot be <null>");
			System.out.printf("Not <null> value: %s%n", i);
		});
	}
	
	@Test
	public void forEachIndexedTest() {
		String[] nullValues = {null, "1", null, "2", null};
		AtomicBoolean executed = new AtomicBoolean(false);
		sharedArray.addAll(nullValues);
		
		sharedArray.forEachIndexed((i, v) -> {
			System.out.printf("Array value index %d: %s%n", i, v);
			executed.set(true);
		});
		
		Assertions.assertTrue(executed.get(),
							  "Must be executed at least once");
	}
	
	@Test
	public void forEachIndexedNotNullTest() {
		String[] nullValues = {null, "1", null, "2", null};
		AtomicBoolean executed = new AtomicBoolean(false);
		sharedArray.addAll(nullValues);
		
		sharedArray.forEachIndexedNotNull((i, v) -> {
			Assertions.assertNotNull(v, "The element cannot be <null>");
			
			System.out.printf("Array value index %d: %s%n", i, v);
			executed.set(true);
		});
		
		Assertions.assertTrue(executed.get(),
							  "Must be executed at least once");
	}
	
	@Test
	public void lastTest() {
		var element = sharedArray.last();
		
		Assertions.assertTrue(element.isPresent(),
							  "Invalid result");
		Assertions.assertEquals("!", element.get(),
								"Content is not equal");
		
		System.out.println(element);
	}
	
}