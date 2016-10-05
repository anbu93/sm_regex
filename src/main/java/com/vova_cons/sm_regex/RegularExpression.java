package com.vova_cons.sm_regex;

/**
 * Regular Expression interface.
 * Having regex pattern and matching it
 * WARNING: unsafe for concurrent operations (mutable object)
 * If you want use it in concurrent operations, use Adapter pattern (GoF)
 */
public interface RegularExpression {
    /** Is full math found in line
     * @param line input line
     * @return full matched or not (boolean)
     */
    boolean isFullMatch(String line);

    /**
     * Matching found in this line
     * @param line input line
     * @return matched or not (boolean)
     */
    boolean isMatch(String line);

    /**
     * Get matched part of line (first part)
     * @param line input line
     * @return matched part of line
     */
    String mathedString(String line);

    /**
     * Get unmatched part of line (last part)
     * @param line input line
     * @return unmatched part of line
     */
    String unmatchedString(String line);

    /**
     * Get regular expression pattern
     * @return regex pattern
     */
    String getPattern();
}
