package SpaceExplorer.Utilities;

public class NameGenerator {
	private String[] prefixes;
	private String[] baseNames;
	private String[] suffixes;
	private boolean usesPrefix;
	private boolean usesSuffix;

	public NameGenerator(String[] prefixes, String[] baseNames, String[] suffixes) {
		this.prefixes = prefixes;
		this.baseNames = baseNames;
		this.suffixes = suffixes;
		this.usesPrefix = true;
		this.usesSuffix = true;
	}
	
	public NameGenerator(String[] prefixes, String[] baseNames, String[] suffixes,
			boolean usesPrefix, boolean usesSuffix) {
		this.prefixes = prefixes;
		this.baseNames = baseNames;
		this.suffixes = suffixes;
		this.usesPrefix = usesPrefix;
		this.usesSuffix = usesSuffix;
	}
	
	public String generateName() {
        boolean hasPrefix = usesPrefix && (Math.random() * 100 >= 50);
        boolean hasSuffix = usesSuffix && (Math.random() * 100 >= 50);

        int prefixIndex = hasPrefix ? (int)(Math.random() * prefixes.length) : -1;
        int middleIndex = (int)(Math.random() * baseNames.length);
        int suffixIndex = hasSuffix ? (int)(Math.random() * suffixes.length) : -1;

        String name = "";
        if (hasPrefix) {
            name += prefixes[prefixIndex];
            name += " ";
        }
        name += baseNames[middleIndex];
        if (hasSuffix) {
            name += " ";
            name += suffixes[suffixIndex];
        }

        return name;
	}
}
