package main.java;

public class ComparisonCompactor {

    private static final String ELLIPSIS = "...";
    private static final String DELTA_END = "]";
    private static final String DELTA_START = "[";

    private final int fContextLength;
    private final String fExpected;
    private final String fActual;

    public ComparisonCompactor(int contextLength,
                               String expected,
                               String actual) {
        fContextLength = contextLength;
        fExpected = expected;
        fActual = actual;
    }

    public String compact() {

        if (fExpected == null || fActual == null) {
            return "Cannot compare null arguments!";
        }
        if (fExpected.equals(fActual)) {
            return "Strings are matching!";
        }

        String prefix = findCommonPrefix();
        String suffix = findCommonSuffix();
        String expected = compactString(prefix, suffix, fExpected);
        String actual = compactString(prefix, suffix, fActual);

        return "Expected: " + expected + " But Got: " + actual;
    }

    private String findCommonPrefix() {

        int i = 0;
        for (; i < fExpected.length() - 1 ; i++) {
            if (fExpected.charAt(i) != fActual.charAt(i)) {
                break;
            }
        }

        return fExpected.substring(0, i);
    }

    private String findCommonSuffix() {

        int i = fExpected.length();
        for (; i > 0 ; i--) {
            if (fExpected.charAt(i - 1) != fActual.charAt(i - 1)) {
                break;
            }
        }

        return fExpected.substring(i);
    }

    private String compactString(String prefix, String suffix, String source) {

        String difference = DELTA_START +
                source.substring(prefix.length(), source.length() - suffix.length()) +
                DELTA_END;
        String fPrefix = compactPrefix(prefix);
        String fSuffix = compactSuffix(suffix);

        return fPrefix + difference + fSuffix;
    }

    private String compactPrefix(String prefix) {

        StringBuilder fPrefix = new StringBuilder();
        if (prefix.length() <= fContextLength) {
            fPrefix = new StringBuilder(prefix);
        } else {
            for (int i = 0 ; i < fContextLength ; i++) {
                fPrefix.insert(0, prefix.charAt(prefix.length() - 1 - i));
            }
            fPrefix.insert(0, ELLIPSIS);
        }

        return fPrefix.toString();
    }

    private String compactSuffix(String suffix) {

        StringBuilder fSuffix = new StringBuilder();
        if (suffix.length() <= fContextLength) {
            fSuffix = new StringBuilder(suffix);
        } else {
            for (int i = 0 ; i < fContextLength ; i++) {
                fSuffix.append(suffix.charAt(i));
            }
            fSuffix.append(ELLIPSIS);
        }

        return fSuffix.toString();
    }

}
