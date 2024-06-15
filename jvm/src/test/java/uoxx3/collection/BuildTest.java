package uoxx3.collection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Optional;

public class BuildTest {
	
	@Test
	public void getInstanceTest() {
		Build instance = Build.getInstance();
		
		// Assertions
		Assertions.assertFalse(instance.isEmpty(),
							   "Build object cannot be empty");
		
		// Display information
		System.err.println(instance.entrySet());
	}
	
	@Test
	public void getTest() {
		// Property exists
		String[] properties = new String[]{
			"build.group",
			"build.timestamp",
			"build.name",
			"build.java.vendor",
			"build.java.version",
			"build.version.name"};
		int maxSize = Arrays.stream(properties)
			.mapToInt(String::length)
			.max()
			.orElse(0);
		
		// property Assertions
		for (String key : properties) {
			Optional<String> property = Build.getInstance()
				.get(key);
			
			Assertions.assertTrue(
				property.isPresent(),
				"Property %s need to exists".formatted(key));
			
			int nSpaces = maxSize - key.length();
			String offset = " ".repeat(nSpaces);
			String newKey = key + offset;
			
			String format = "%s = %s%n";
			System.err.printf(format, newKey, property);
		}
	}
	
}