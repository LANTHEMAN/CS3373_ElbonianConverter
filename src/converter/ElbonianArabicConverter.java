package converter;

import converter.exceptions.MalformedNumberException;
import converter.exceptions.ValueOutOfBoundsException;

import java.util.HashMap;

/**
 * This class implements a converter that takes a string that represents a number in either the
 * Elbonian or Arabic numeral form. This class has methods that will return a value in the chosen form.
 *
 * @version 3/18/17
 */
public class ElbonianArabicConverter {

    // A string that holds the number (Elbonian or Arabic) you would like to convert
    private final String number;
//TODO 12:30 start pmaida
    /**
     * Constructor for the ElbonianArabic class that takes a string. The string should contain a valid
     * Elbonian or Arabic numeral. The String can have leading or trailing spaces. But there should be no
     * spaces within the actual number (ie. "9 9" is not ok, but " 99 " is ok). If the String is an Arabic
     * number it should be checked to make sure it is within the Elbonian number systems bounds. If the
     * number is Elbonian, it must be a valid Elbonian representation of a number.
     *
     * @param number A string that represents either a Elbonian or Arabic number.
     * @throws MalformedNumberException Thrown if the value is an Elbonian number that does not conform
     * to the rules of the Elbonian number system. Leading and trailing spaces should not throw an error.
     * @throws ValueOutOfBoundsException Thrown if the value is an Arabic number that cannot be represented
     * in the Elbonian number system.
     */
    public ElbonianArabicConverter(String number) throws MalformedNumberException, ValueOutOfBoundsException {

        // TODO check to see if the number is valid, then set it equal to the string
        // will take off any leading or trailing spaces
        String trimmedNumber = number.trim();

        String elbonianCharacters = "MeDCmLXwVI";
        String arabicCharacters = "0123456789";
        boolean elbonian = false;
        boolean arabic = false;

        // check valid input was provided
        for (int i = 0; i < trimmedNumber.length(); i++) {
            char letter = trimmedNumber.charAt(i);
            if (elbonianCharacters.contains("" + letter)) {
                elbonian = true;
            } else if (arabicCharacters.contains("" + letter)) {
                arabic = true;
            } else {
                throw new MalformedNumberException("Invalid character");
            }

            if (elbonian && arabic) {
                // if there is a blend of Elbonian and Arabic characters
                throw new MalformedNumberException("Mixed number");
            }
        }

        // check if the Elbonian number conforms to the number system rules
        if (elbonian) {
            int sameLetterCount = 0;
            char lastLetter = ' ';
            int lastValue = 0;

            for (int i = 0; i < trimmedNumber.length(); i++) {
                char c = trimmedNumber.charAt(i);
                int value = elbonianCharacters.indexOf(c);
                if (c == lastLetter && (c == 'M' || c == 'C' || c == 'X' || c == 'I')) {
                    sameLetterCount++;
                    if (sameLetterCount > 3) {
                        throw new MalformedNumberException("Invalid Elbonian number");
                    }
                    continue;
                }
                // the letters are in order of value; except e, m, and w which come before D, L, or V respectively
                // if there are two of the same numbers or out of order
                if (value <= lastValue) {
                    throw new MalformedNumberException("Invalid Elbonian number");
                }
                // if the string contains an e, m, or w and its singular counterpart
                if ((c == 'e' || c == 'm' || c == 'w') && trimmedNumber.contains("" + elbonianCharacters.charAt(value+2))) {
                    throw new MalformedNumberException("Invalid Elbonian number");
                }
                lastValue = value;
                lastLetter = c;
            }
        }

        // check if the Arabic number is within the bounds
        if (arabic) {
             int num = Integer.parseInt(trimmedNumber);
             if (num <= 0 || num > 3999) {
                 throw new ValueOutOfBoundsException("Arabic number out of bounds");
             }
        }

        // set the number with any leading and trailing spaces
        this.number = number;
    }

    /**
     * Converts the number to an Arabic numeral or returns the current value as an int if it is already
     * in the Arabic form.
     *
     * @return An arabic value
     */
    public int toArabic() {
        // TODO Fill in the method's body
        if (isArabic()) {
            return Integer.parseInt(number.trim());
        }

        HashMap<Character, Integer> toArabicMap = new HashMap<>();
        toArabicMap.put('M',1000);
        toArabicMap.put('C',100);
        toArabicMap.put('D',500);
        toArabicMap.put('e',400);
        toArabicMap.put('X',10);
        toArabicMap.put('L',50);
        toArabicMap.put('m',40);
        toArabicMap.put('I',1);
        toArabicMap.put('V',5);
        toArabicMap.put('w',4);

        int arabicNumber = 0;
        String trimmedNumber = number.trim();
        for (int i = 0; i < trimmedNumber.length(); i++){
            char c = trimmedNumber.charAt(i);
            arabicNumber += toArabicMap.get(c);
        }
        return arabicNumber;
    }

    /**
     * Converts the number to an Elbonian numeral or returns the current value if it is already in the Elbonian form.
     *
     * @return An Elbonian value
     */
    public String toElbonian() {
        // TODO Fill in the method's body
        if (isElbonian()) {
            return number;
        }

        int arabicNum = Integer.parseInt(number.trim());
        String Elbonian = "";
        int ones = arabicNum % 10;
        arabicNum -= ones;
        int tens = arabicNum % 100;
        arabicNum -= tens;
        int hundreds = arabicNum % 1000;
        arabicNum -= hundreds;
        int thousands = arabicNum % 10000;

        // thousands place
        while((thousands - 1000) >= 0){
            Elbonian = Elbonian+ "M";
            thousands -= 1000;
        }

        // hundreds place
        if (hundreds == 900) {
            Elbonian = Elbonian + "eD";
            hundreds -= 900;
        } else if (hundreds >= 500) {
            Elbonian = Elbonian + "D";
            hundreds -= 500;
        } else if (hundreds >= 400) {
            Elbonian = Elbonian + "e";
            hundreds -= 400;
        }
        while(hundreds >= 100) {
            Elbonian = Elbonian + "C";
            hundreds -= 100;
        }

        // tens place
        if (tens == 90){
            Elbonian = Elbonian+"mL";
            tens -= 90;
        } else if (tens >= 50) {
            Elbonian = Elbonian + "L";
            tens -= 50;
        } else if (tens >= 40) {
            Elbonian = Elbonian + "m";
            tens -= 40;
        }
        while(tens >= 10){
            Elbonian = Elbonian + "X";
            tens -= 10;
        }

        // ones place
        if (ones == 9) {
            Elbonian = Elbonian+"wV";
            ones -= 9;
        } else if (ones >= 5) {
            Elbonian = Elbonian + "V";
            ones -= 5;
        } else if (ones >= 4) {
            Elbonian = Elbonian + "w";
            ones -= 4;
        }
        while(ones >= 1){
            Elbonian = Elbonian + "I";
            ones -= 1;
        }

        return Elbonian;
    }

    /**
     * Will return true if number of this object is Elbonian, and false if it is not
     *
     * @return whether or not number is Elbonian
     */
    private boolean isElbonian() {
        // see if every character is Elbonian
        String trimmedNumber = number.trim();
        String elbonianCharacters = "MCDeXLmIVw";
        for (int i = 0; i < trimmedNumber.length(); i++) {
            if (!elbonianCharacters.contains("" + trimmedNumber.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Will return true if number of this object is Arabic, and false if it is not
     *
     * @return whether or not number is Arabic
     */
    private boolean isArabic() {
        // see if the number can be converted to an Integer
        String trimmedNumber = number.trim();
        try {
            Integer.parseInt(trimmedNumber);
            return true;
        } catch (NumberFormatException e){
            return false;
        }
    }
}
