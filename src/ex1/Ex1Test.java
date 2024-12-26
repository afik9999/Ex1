package ex1;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This JUnit class represents a very partial test class for Ex1.
 * Make sure you complete all the needed JUnits
 */
public class Ex1Test {
    @Test
    void computeNumberTest() {
        String s2 = "1011b2";
        int v = Ex1.number2Int(s2);
        assertEquals(v,11);
        String s10 = "1011bA";
        v = Ex1.number2Int(s10);
        s2 = Ex1.int2Number(v,2);
        int v2 = Ex1.number2Int(s2);
        assertEquals(v,v2);
        assertTrue(Ex1.equals(s10,s2));
    }

    @Test
    void isBasisNumberTest() {
        String[] good = {"1", "1b2", "01b2", "123bA", "ABbG", "0bA"};
        for(int i=0;i<good.length;i=i+1) {
            boolean ok = Ex1.isNumber(good[i]);
            assertTrue(ok);
        }
        String[] not_good = {"b2", "2b2", "1G3bG", " BbG", "0bbA", "abB", "!@b2", "A", "1bb2"};
        for(int i=0;i<not_good.length;i=i+1) {
            boolean not_ok = Ex1.isNumber(not_good[i]);
            assertFalse(not_ok);
        }
    }
    @Test
    void int2NumberTest() {
        assertEquals("1b2", Ex1.int2Number(1, 2));
        assertEquals("10b2", Ex1.int2Number(2, 2));
        assertEquals("7b8", Ex1.int2Number(7, 8));
        assertEquals("1EbG", Ex1.int2Number(30, 16));
        assertEquals("", Ex1.int2Number(-1, 10));
        assertEquals("", Ex1.int2Number(10, 17));
    }

    @Test
    void number2IntTest() {
        assertEquals(11, Ex1.number2Int("1011b2"));
        assertEquals(255, Ex1.number2Int("FFbG"));
        assertEquals(-1, Ex1.number2Int("1GbF"));  // Invalid number
        assertEquals(-1, Ex1.number2Int("29AbI"));  // Invalid base
        assertEquals(-1, Ex1.number2Int(""));      // Empty string
        assertEquals(-1, Ex1.number2Int(null));    // Null input
    }

    @Test
    void equalsTest() {
        assertTrue(Ex1.equals("1010b2", "10"));
        assertTrue(Ex1.equals("FFbG", "255bA"));
        assertFalse(Ex1.equals("FFbG", "256bA"));
        assertFalse(Ex1.equals("FFbG", null));
        assertFalse(Ex1.equals("589bB", "255bA"));
    }

    @Test
    void maxIndexTest() {
        String[] arr = {"10b2", "10", "111b2", "FFbG"};
        assertEquals(3, Ex1.maxIndex(arr));

        String[] arrWithInvalids = {"10b2", "FbG", null, "10"};
        assertEquals(1, Ex1.maxIndex(arrWithInvalids));

        String[] allInvalid = {"G", null, "!!b2"};
        assertEquals(-1, Ex1.maxIndex(allInvalid));
    }

    @Test
    void baseValidationTest() {
        assertTrue(Ex1.isNumber("123bA")); // Valid base
        assertFalse(Ex1.isNumber("123bQ")); // Invalid base
        assertFalse(Ex1.isNumber("123b")); // Missing base
        assertFalse(Ex1.isNumber("123b17")); // Base out of range
        assertFalse(Ex1.isNumber("123b0")); // Base less than minimum
    }

    @Test
    void numberValidationTest() {
        assertTrue(Ex1.isNumber("101b2")); // Valid binary
        assertFalse(Ex1.isNumber("102b2")); // Invalid digit in binary
        assertTrue(Ex1.isNumber("FFbG")); // Valid hexadecimal
        assertFalse(Ex1.isNumber("FGbG")); // Invalid digit in hexadecimal
    }

    @Test
    void edgeCaseTest() {
        // Edge cases
        assertEquals("0bA", Ex1.int2Number(0, 10)); // Zero value
        assertEquals("", Ex1.int2Number(10, 1)); // Invalid base
        assertEquals("", Ex1.int2Number(10, 17)); // Invalid base
        assertEquals(-1, Ex1.number2Int("0b1")); // Invalid base
        assertEquals(-1, Ex1.number2Int("123b")); // Missing base
    }
}