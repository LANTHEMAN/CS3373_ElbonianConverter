package converter.tests;

import converter.ElbonianArabicConverter;
import converter.exceptions.MalformedNumberException;
import converter.exceptions.ValueOutOfBoundsException;
import org.junit.Test;

import static org.junit.Assert.*;

public class ElbonianArabicConverterTest {

    @Test
    public void toArabic() {

    }

    @Test
    public void toElbonian() throws MalformedNumberException, ValueOutOfBoundsException {
        ElbonianArabicConverter converter = new ElbonianArabicConverter("1");
        String s = converter.toElbonian(999);
        System.out.println(s);
    }
}