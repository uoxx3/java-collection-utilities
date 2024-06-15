module uo.collection.utilities {
	/* Library module dependencies */
	requires uo.core.utilities;
	requires static org.jetbrains.annotations;
	
	/* Library module package exports */
	exports uoxx3.collection.array;
	exports uoxx3.collection.collection;
	exports uoxx3.collection;
	
	/* Resource module exports */
	opens uoxx3.collection;
}