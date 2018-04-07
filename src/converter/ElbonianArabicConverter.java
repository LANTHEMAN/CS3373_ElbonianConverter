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
        String noSpaceString = this.number.replaceAll("\\s","");
        for (int i = 0; i < noSpaceString.length(); i++){
            char c = noSpaceString.charAt(i);
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

        }
        int n = Integer.parseInt(number);
        String Elbonian = "";
        int single = n % 10;
        n -= single;
        int tens = n % 100;
        n -= tens;
        int hundreds = n % 1000;
        n -= hundreds;
        int thousands = n % 10000;
        //thousands
        while((thousands - 1000) >= 0){
            Elbonian = Elbonian+ "M";
            thousands -= 1000;
        }
        //hundreds
        while((hundreds - 900) >= 0){
            Elbonian = Elbonian+"eD";
            hundreds -= 900;
        }
        while((hundreds - 500) >= 0){
            Elbonian = Elbonian + "D";
            hundreds -= 500;
        }
        while((hundreds - 400) >= 0){
            Elbonian = Elbonian + "e";
            hundreds -= 400;
        }
        while((hundreds - 100) >= 0){
            Elbonian = Elbonian + "C";
            hundreds -= 100;
        }
        //tens
        while((tens - 90) >= 0){
            Elbonian = Elbonian+"mL";
            tens -= 90;
        }
        while((tens - 50) >= 0){
            Elbonian = Elbonian + "L";
            tens -= 50;
        }
        while((tens - 40) >= 0){
            Elbonian = Elbonian + "m";
            tens -= 40;
        }
        while((tens - 10) >= 0){
            Elbonian = Elbonian + "X";
            tens -= 10;
        }
        //single
        while((single - 9) >= 0){
            Elbonian = Elbonian+"wV";
            single -= 9;
        }
        while((single - 5) >= 0){
            Elbonian = Elbonian + "V";
            single -= 5;
        }
        while((single - 4) >= 0){
            Elbonian = Elbonian + "w";
            single -= 4;
        }
        while((single - 1) >= 0){
            Elbonian = Elbonian + "I";
            single -= 1;
        }

        return Elbonian;
    }

    /**
     * Will return true if the number of this object is Elbonian, and false if it is not
     *
     * @return whether or not number is Elbonian
     */
    private boolean isElbonian() {
        return true;
    }

    /**
     * Will return true if the number of this object is Arabic, and false if it is not
     *
     * @return whether or not number is Arabic
     */
    private boolean isArabic() {
        return true;
    }
}
