package converter.tests;

import converter.ElbonianArabicConverter;
import converter.exceptions.MalformedNumberException;
import converter.exceptions.ValueOutOfBoundsException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test cases for the ElbonianArabicConverter class.
 */
public class ConverterTests {

    // Test universal input

    @Test(expected = MalformedNumberException.class)
    public void invalidCharacterTest() throws MalformedNumberException, ValueOutOfBoundsException {
        ElbonianArabicConverter converter = new ElbonianArabicConverter("error");
    }

    @Test(expected = MalformedNumberException.class)
    public void mixedTypeNumberTest() throws MalformedNumberException, ValueOutOfBoundsException {
        ElbonianArabicConverter converter = new ElbonianArabicConverter("V8");
    }

    @Test
    public void trailingLeadingSpaceTest() throws MalformedNumberException, ValueOutOfBoundsException {
        ElbonianArabicConverter converter = new ElbonianArabicConverter(" 99 ");
        assertEquals(converter.toArabic(), 99);
    }

    @Test(expected = MalformedNumberException.class)
    public void insideSpaceTest() throws MalformedNumberException, ValueOutOfBoundsException {
        ElbonianArabicConverter converter = new ElbonianArabicConverter("9 9");
    }


    // Test Elbonian Conversions

    @Test
    public void elbonianToArabicSampleTest() throws MalformedNumberException, ValueOutOfBoundsException {
        ElbonianArabicConverter converter = new ElbonianArabicConverter("eDmLwV");
        assertEquals(converter.toArabic(), 999);
    }

    @Test(expected = MalformedNumberException.class)
    public void illegalMultiplesElbonianTest() throws MalformedNumberException, ValueOutOfBoundsException {
        ElbonianArabicConverter converter = new ElbonianArabicConverter("MMMM");
    }

    @Test(expected = MalformedNumberException.class)
    public void legalMultiplesElbonianTest() throws MalformedNumberException, ValueOutOfBoundsException {
        ElbonianArabicConverter converter = new ElbonianArabicConverter("MMM");
    }

    @Test
    public void factorElbonianTest() throws MalformedNumberException, ValueOutOfBoundsException {
        ElbonianArabicConverter converter = new ElbonianArabicConverter("eL");
        assertEquals(converter.toElbonian(), "eL");
    }

    @Test(expected = MalformedNumberException.class)
    public void outOfOrderElbonianTest() throws MalformedNumberException, ValueOutOfBoundsException {
        ElbonianArabicConverter converter = new ElbonianArabicConverter("VIM");
    }

    @Test(expected = MalformedNumberException.class)
    public void ambigiousNumberTest() throws MalformedNumberException, ValueOutOfBoundsException {
        ElbonianArabicConverter converter = new ElbonianArabicConverter("eC");
    }



    // Test Arabic Conversions

    @Test
    public void arabicToElbonianSampleTest() throws MalformedNumberException, ValueOutOfBoundsException {
        ElbonianArabicConverter converter = new ElbonianArabicConverter("1");
        assertEquals(converter.toElbonian(), "I");
    }

    @Test
    public void arabicToElbonianTest() throws MalformedNumberException, ValueOutOfBoundsException {
        ElbonianArabicConverter converter = new ElbonianArabicConverter("3999");
        assertEquals(converter.toElbonian(), "MMMeDmLwV");
    }

    @Test(expected = ValueOutOfBoundsException.class)
    public void valueOutOfBoundsTest() throws MalformedNumberException, ValueOutOfBoundsException {
        ElbonianArabicConverter converter = new ElbonianArabicConverter("0");
    }
}
