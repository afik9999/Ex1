package ex1;
/**
 * This class represents a simple solution for Ex1.
 * As defined here: https://docs.google.com/document/d/1AJ9wtnL1qdEs4DAKqBlO1bXCM6r6GJ_J/r/edit/edit
 * In this assignment, we will design a number formatting converter and calculator.
 * In general, we will use Strings as numbers over basis of binary till Hexa.
 * [2-16], 10-16 are represented by A,B,..G.
 * The general representation of the numbers is as a String with the following format:
 * <number><b><base> e.g., “135bA” (i.e., “135”, as 10 is the default base), “100111b2”, “12345b6”,”012b5”, “123bG”, “EFbG”.
 * The following are NOT in the format (not a valid number):
 * “b2”, “0b1”, “123b”, “1234b11”, “3b3”, “-3b5”, “3 b4”, “GbG”, "", null,
 * You should implement the following static functions:
 */
public class Ex1 {
    /**
     * Convert the given number (num) to a decimal representation (as int).
     * It the given number is not in a valid format returns -1.
     * @param num a String representing a number in basis [2,16]
     * @return
     */
    public static int number2Int(String num) {
        if(!isNumber(num)) {
            return -1;
        }
        if (!num.contains("b")) {
            return Integer.parseInt(num);
        }
        String [] numberParts = num.split("b");
        String numberPart = numberParts[0];             
        int base = converter(numberParts[1].charAt(0));     
        int sum = 0;
        for (int i = 0; i < numberPart.length(); i++) {
            char current = numberPart.charAt(i);
            int currentDigit = converter(current);
            sum += currentDigit * (int) (Math.pow(base, numberPart.length()-1-i));
        }
        return sum;
    }
    
    /**
     * This static function checks if the given String (g) is in a valid "number" format.
     * @param a a String representing a number
     * @return true iff the given String is in a number format
     */
    // valid number = <number>b<base>
    // 1. contains 'b'.
    // 2. <number> is not empty
    // 3. <base> is not empty and length is 1.
    // 4. <base> is between 2 and G.
    // 5. <number> contains valid digits (base = 4 , valid digits = 0,1,2,3)
    public static boolean isNumber(String a) {
        if (a == null || a.isEmpty()) {
            return false;
        }
        if (!a.contains("b")) {
            for (int i = 0; i < a.length(); i++) {
                char current = a.charAt(i);
                if (!Character.isDigit(current)) {
                    return false;
                }
            }
            return true;
        } 
        String [] numberParts = a.split("b"); /// a = 123b6, numberParts = ["123", "6"];
        if (numberParts.length != 2) {
            return false;
        }
        String numberPart = numberParts[0]; 
        String basePart = numberParts[1];
        return baseValidation(basePart) && numberValidation(numberPart, basePart.charAt(0));
    }
    
    /**
     * Calculate the number representation (in basis base)
     * of the given natural number (represented as an integer).
     * If num<0 or base is not in [2,16] the function should return "" (the empty String).
     * @param num the natural number (include 0).
     * @param base the basis [2,16]
     * @return a String representing a number (in base) equals to num, or an empty String (in case of wrong input).
     */
    // num = 83, base = 11
    //  83 % 11 = 6 
    // (83 / 11 = 7)
    //  7 % 11 = 7
    // (7 / 11 = 0)
    // (76)11 -> "76bB"

    // num = 76, base = 13
    //  76 % 13 = 11 
    // (76 / 13 = 5)
    //  5 % 13 = 5
    // (5 / 13 = 0)
    // (5B)13 -> "5BbD"
    public static String int2Number(int num, int base) {
        if (num < 0 || base < 2 || base > 16) {
            return "";
        }
        String ans = "";
        while (num > 0) {
            int reminder = num % base; 
            char rem = converter(reminder); 
            ans = rem + ans;       
            num/= base;
        }
        return ans + 'b' + converter(base);
    }

    /**
     * Checks if the two numbers have the same value.
     * @param n1 first number
     * @param n2 second number
     * @return true iff the two numbers have the same values.
     */
    public static boolean equals(String n1, String n2) {
        int num1 = number2Int(n1);
        int num2 = number2Int(n2);
        if (num1 == -1 || num2 == -1) {
            return false;
        }
        return num1 == num2;
    }

    /**
     * This static function search for the array index with the largest number (in value).
     * In case there are more than one maximum - returns the first index.
     * Note: you can assume that the array is not null and is not empty, yet it may contain null or none-valid numbers (with value -1).
     * @param arr an array of numbers
     * @return the index in the array in with the largest number (in value).
     *
     */
    public static int maxIndex(String[] arr) {
        int maxValue = -1;
        int maxIndex = -1;
        for (int i = 0; i < arr.length; i++) {
            int current = number2Int(arr[i]);
            if (current > maxValue) {
                maxValue = current;
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    /**
     * Converts a given character to its integer representation based on its value.
     *
     * - For characters '0' to '9', it returns their numeric value (0-9).
     * - For characters 'A' to 'G', it returns their value as digits in a base 
     *   system where 'A' corresponds to 10 and 'G' corresponds to 16.
     * - For any other character, it returns -1 to indicate an invalid input.
     *
     * @param c the character to be converted.
     * @return the integer representation of the character, or -1 if the character is invalid.
     */
    private static int converter (char c) {
        if (c >= '0' && c <= '9') {
            return c - '0';
        } else if (c >= 'A' && c <= 'G') {
            return c - 'A' + 10;
        } else {
            return -1;
        }
    }

    /**
     * Converts a given integer digit to its corresponding character representation.
     * For digits 0-9, it returns their character representation ('0' to '9').
     * For digits 10-16, it returns their hexadecimal representation ('A' to 'G').
     * If the digit is outside the range 0-16, it returns a hyphen ('-').
     *
     * @param digit the integer digit to be converted. Expected range is 0-16.
     * @return the character representation of the digit, or '-' if the digit is out of range.
     */
    private static char converter (int digit) {
        if (digit < 10) {
            return (char) ('0' + digit);
        } else if (digit <= 16) {
            return (char) ('A' - 10 + digit);
        } else {
            return '-';
        }
    }

    /**
     * Validate that base is between [2,16].
     * @param base a String represent number base
     * @return true if base is valid, false otherwise
     */
    private static boolean baseValidation (String base) {
        if (base == null || base.length() != 1){
            return false;
        }
        int intBase = converter(base.charAt(0));
        if (intBase == -1) {
            return false;
        }
        if (intBase >= 2 && intBase <= 16){
            return true;
        }
        return false;
    }


    /**
     * Validates whether a given string representation of a number is valid 
     * in the specified base.
     *
     * A number is considered valid if:
     * - It is non-null and non-empty.
     * - Its base is valid (convertible from the character representation).
     * - All characters in the number are valid digits for the given base.
     * - No digit in the number is equal to or exceeds the base.
     *
     * @param num the string representation of the number to validate.
     * @param base the character representation of the base (e.g., '10' for decimal, 'A' for hexadecimal).
     * @return {@code true} if the number is valid in the given base; {@code false} otherwise.
     */
    private static boolean numberValidation (String num, char base) {
        if (num == null || num.isEmpty()) {
            return false;
        }
        int intBase = converter(base);
        if (intBase == -1) {
            return false;
        }

        for (int i = 0; i < num.length(); i++) {
            char current = num.charAt(i);
            int intCurrent = converter(current);
            if (intCurrent == -1) {
                return false;
            }
            if (intCurrent >= intBase) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(number2Int("G"));
    }
}