package SpaceExplorer.Utilities;

/**
 * This class implements a random name generator that takes in three
 * String arrays, and then generates a name by picking a random entry
 * from each of the three arrays.
 * 
 * @author Saahil Hari and Isaac Walton
 * @version 1.0, April 2018
 */
public class NameGenerator {
	/**
	 * Name prefixes to choose from
	 */
	private final String[] prefixes;
	
	/**
	 * Base names to choose from
	 */
	private final String[] baseNames;
	
	/**
	 * Name suffixes to choose from
	 */
	private final String[] suffixes;
	
	/**
	 * Should the name generator use the supplied prefixes?
	 */
	private boolean usesPrefix;
	
	/**
	 * Should the name generator use the supplied suffixes?
	 */
	private boolean usesSuffix;
	
	/**
	 * Chance of using a prefix
	 */
	private int prefixPercentage;

	/**
	 * Chance of using a suffix
	 */
	private int suffixPercentage;

	/**
	 * A basic constructor for creating a NameGenerator
	 * 
	 * @param prefixes			Name prefixes to choose from
	 * @param baseNames			Base names to choose from
	 * @param suffixes			Name suffixes to choose from
	 */
	public NameGenerator(String[] prefixes, String[] baseNames, String[] suffixes) {
		this(prefixes, baseNames, suffixes, true, true, 50, 50);
	}

	/**
	 * A constructor for creating a NameGenerator that includes whether or not to use 
	 * suffixes and prefixes
	 * 
	 * @param prefixes			Name prefixes to choose from
	 * @param baseNames			Base names to choose from
	 * @param suffixes			Name suffixes to choose from
	 * @param usesPrefix		Should the name generator use the supplied prefixes?
	 * @param usesSuffix		Should the name generator use the supplied suffixes?
	 * @param prefixPercentage	Chance of using a prefix
	 * @param suffixPercentage	Chance of using a suffix
	 */
	public NameGenerator(String[] prefixes, String[] baseNames, String[] suffixes,
			boolean usesPrefix, boolean usesSuffix, int prefixPercentage, int suffixPercentage) {
		this.prefixes = prefixes;
		this.baseNames = baseNames;
		this.suffixes = suffixes;
		this.usesPrefix = usesPrefix;
		this.usesSuffix = usesSuffix;
		this.prefixPercentage = Math.min(Math.max(0, prefixPercentage), 100);
		this.suffixPercentage = Math.min(Math.max(0, suffixPercentage), 100);
	}

	/**
	 * Generates a name from the prefixes, base names and suffixes stored in the
	 * object.
	 * 
	 * @return				A randomly generated name
	 */
	public String generateName() {
        boolean hasPrefix = usesPrefix && (Math.random() * 100 >= (100 - prefixPercentage));
        boolean hasSuffix = usesSuffix && (Math.random() * 100 >= (100 - suffixPercentage));

        int prefixIndex = hasPrefix ? (int)(Math.random() * prefixes.length) : -1;
        int middleIndex = (int)(Math.random() * baseNames.length);
        int suffixIndex = hasSuffix ? (int)(Math.random() * suffixes.length) : -1;

        String name = "";
        if (hasPrefix) {
            name += prefixes[prefixIndex];
        }
        name += baseNames[middleIndex];
        if (hasSuffix) {
            name += suffixes[suffixIndex];
        }

        return name;
	}
}
