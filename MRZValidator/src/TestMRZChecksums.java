import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.*;

// These unit tests check that the console output and checksum function work as expected.
public class TestMRZChecksums {
    
    // Initialise variables needed for testing
    public Args newArgs = new Args();
    public final MRZValidator validateClass = new MRZValidator();

    // Set up a variable to store the contents of the console
    private final ByteArrayOutputStream consoleOutput = new ByteArrayOutputStream();
    private final PrintStream originalOutput = System.out;

    // This runs before every unit test
    @Before
    public void setStream() {
        System.setOut(new PrintStream(consoleOutput));
        newArgs.dateOfBirth = "910711"; newArgs.passportNumber = "862281640"; newArgs.expiryDate = "160101"; newArgs.nationality = "GBR";
    }

    // This runs after every unit test
    @After
    public void resetStream() {
        System.setOut(originalOutput);
    }

    // The passport check digit below is correct. It should equal 9 for the given passport number.
    @Test
    public void testPassportNumberCheckDigitCorrect() {
        MRZ newMRZ = new MRZ("8622816409GBR9107119M1601013<<<<<<<<<<<<<<06");
        int calculatedCheckdigit = MRZValidator.calculateCheckDigit(newMRZ.getPassportNumber());
        assertEquals(calculatedCheckdigit, newMRZ.getPassportNumberCheckDigit());
    }

    // The passport check digit is intentionally incorrect in the MRZ. It is 8 in the MRZ but should be 9.
    @Test
    public void testPassportNumberCheckDigitIncorrect() {
        MRZ newMRZ = new MRZ("8622816408GBR9107119M1601013<<<<<<<<<<<<<<06");
        int calculatedCheckdigit = MRZValidator.calculateCheckDigit(newMRZ.getPassportNumber());
        assertNotEquals(calculatedCheckdigit, newMRZ.getPassportNumberCheckDigit());
    }

    // The date of birth check digit below is correct. It should equal 9 for the given date of birth.
    @Test
    public void testDOBCheckDigitCorrect() {
        MRZ newMRZ = new MRZ("8622816409GBR9107119M1601013<<<<<<<<<<<<<<06");
        int calculatedCheckdigit = MRZValidator.calculateCheckDigit(newMRZ.getDOB());
        assertEquals(calculatedCheckdigit, newMRZ.getDOBCheckDigit());
    }

    // The date of birth check digit is intentionally incorrect in the MRZ. It is 1 in the MRZ but should be 9.
    @Test
    public void testDOBCheckDigitIncorrect() {
        MRZ newMRZ = new MRZ("8622816409GBR9107111M1601013<<<<<<<<<<<<<<06");
        int calculatedCheckdigit = MRZValidator.calculateCheckDigit(newMRZ.getDOB());
        assertNotEquals(calculatedCheckdigit, newMRZ.getDOBCheckDigit());
    }

    // The expiry check digit below is correct. It should equal 3 for the given expiry date.
    @Test
    public void testExpiryCheckDigitCorrect() {
        MRZ newMRZ = new MRZ("8622816409GBR9107119M1601013<<<<<<<<<<<<<<06");
        int calculatedCheckdigit = MRZValidator.calculateCheckDigit(newMRZ.getExpiry());
        assertEquals(calculatedCheckdigit, newMRZ.getExpiryCheckDigit());
    }

    // The expiry check digit below is intentionally incorrect. It is 5 in the MRZ but should be 3.
    @Test
    public void testExpiryCheckDigitIncorrect() {
        MRZ newMRZ = new MRZ("8622816409GBR9107119M1601015<<<<<<<<<<<<<<06");
        int calculatedCheckdigit = MRZValidator.calculateCheckDigit(newMRZ.getExpiry());
        assertNotEquals(calculatedCheckdigit, newMRZ.getExpiryCheckDigit());
    }

    // The check digit of every relevant part of the MRZ here should be 6.
    @Test
    public void testFullMRZCheckCorrect() {
        int calculatedCheckDigit = MRZValidator.calculateCheckDigit("862281640991071191601013");
        assertEquals(calculatedCheckDigit, 6);
    }

    // The check digit should be 6, but the MRZ has had one digit changed from a 9 to 8. The check digit should now be different.
    @Test
    public void testFullMRZCheckIncorrect() {
        int calculatedCheckDigit = MRZValidator.calculateCheckDigit("862281640981071191601013");
        assertNotEquals(calculatedCheckDigit, 6);
    }
}
