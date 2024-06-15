package uoxx3.collection;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.PropertyKey;
import org.jetbrains.annotations.UnmodifiableView;
import uoxx3.UObjects;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Represents build information loaded from a resource bundle.
 */
public final class Build {
	
	/* -----------------------------------------------------
	 * Constants
	 * ----------------------------------------------------- */
	
	/**
	 * Resource bundle library location
	 */
	private static final String RESOURCE_BUNDLE_URL = "uoxx3/collection/build.properties";
	
	/**
	 * Resource bundle id-name
	 */
	private static final String RESOURCE_BUNDLE_NAME = "uoxx3.collection.build";
	
	/* -----------------------------------------------------
	 * Properties
	 * ----------------------------------------------------- */
	
	/**
	 * Build properties instance
	 */
	private final Properties buildInfoProperties;
	
	/* -----------------------------------------------------
	 * Constructors
	 * ----------------------------------------------------- */
	
	/**
	 * Private constructor to prevent instantiation.
	 */
	private Build() {
		buildInfoProperties = new Properties();
		
		// Try to load resource content
		ClassLoader loader = ClassLoader.getSystemClassLoader();
		try (InputStream resourceStream = loader.getResourceAsStream(RESOURCE_BUNDLE_URL)) {
			if (resourceStream == null) return;
			// Load properties configuration
			buildInfoProperties.load(resourceStream);
		} catch (IOException e) { /* Do nothing */ }
	}
	
	/* -----------------------------------------------------
	 * Property access methods
	 * ----------------------------------------------------- */
	
	/**
	 * Gets an unmodifiable set view of the mappings contained in this {@code Properties} object.
	 * The set is a transformed view of the original set, where each entry is converted to an
	 * immutable {@code Map.Entry<String, String>} object. Changes to the original set are not
	 * reflected in the returned set. If the original set is modified while an iteration over
	 * the returned set is in progress, the results of the iteration are undefined.
	 *
	 * @return an unmodifiable set view of the mappings contained in this {@code Properties} object
	 */
	public @NotNull @UnmodifiableView Set<Map.Entry<String, String>> entrySet() {
		return buildInfoProperties.entrySet().stream()
			.map(it -> new AbstractMap.SimpleImmutableEntry<>(
				(String) it.getKey(),
				(String) it.getValue())
			).collect(Collectors.toUnmodifiableSet());
	}
	
	/**
	 * Retrieves the value associated with the specified key, or a default value if the key is not found.
	 *
	 * @param key   the key whose associated value is to be returned
	 * @param value the default value to return if the key is not found
	 * @return the value associated with the specified key, or the default value if the key is not found
	 */
	public @NotNull String get(@NotNull @PropertyKey(resourceBundle = RESOURCE_BUNDLE_NAME) String key, @NotNull String value) {
		return get(key).orElse(value);
	}
	
	/**
	 * Retrieves the value associated with the specified key.
	 *
	 * @param key the key whose associated value is to be returned
	 * @return an {@code Optional} containing the value to which the specified key is mapped,
	 * 	or an empty {@code Optional} if this map contains no mapping for the key
	 */
	public @NotNull Optional<String> get(@NotNull @PropertyKey(resourceBundle = RESOURCE_BUNDLE_NAME) String key) {
		return UObjects.wrapper((String) buildInfoProperties.get(key))
			.getNotNull();
	}
	
	/**
	 * Tests if this hashtable maps no keys to values.
	 *
	 * @return {@code true} if this hashtable maps no keys to values;
	 *    {@code false} otherwise.
	 */
	public boolean isEmpty() {
		return buildInfoProperties.isEmpty();
	}
	
	/* -----------------------------------------------------
	 * Static methods
	 * ----------------------------------------------------- */
	
	/**
	 * Returns the singleton instance of the Build class.
	 *
	 * @return the singleton instance of the Build class
	 */
	public static @NotNull Build getInstance() {
		return Holder.INSTANCE;
	}
	
	/* -----------------------------------------------------
	 * Internal types
	 * ----------------------------------------------------- */
	
	/**
	 * Represents a holder class for the singleton instance of {@link Build}.
	 */
	private static class Holder {
		
		/**
		 * The singleton instance of {@link Build}.
		 */
		public static final Build INSTANCE = new Build();
		
	}
	
}

