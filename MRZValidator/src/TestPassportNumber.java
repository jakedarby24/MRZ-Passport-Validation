import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.*;

// These unit tests check that the console output for the passport number checks is as expected.
public class TestPassportNumber {
    
    // Initialise variables needed for testing
    public final MRZ newMRZ = new MRZ("8622816409GBR9107119M1601013<<<<<<<<<<<<<<06");
    public Args newArgs = new Args();
    public final MRZValidator validateClass = new MRZValidator();

    // Set up a variable to store the contents of the console
    private final ByteArrayOutputStream consoleOutput = new ByteArrayOutputStream();
    private final PrintStream originalOutput = System.out;

    // This runs before every unit test
    @Before
    public void setStream() {
        System.setOut(new PrintStream(consoleOutput));
        newArgs.dateOfBirth = "910711"; newArgs.nationality = "GBR"; newArgs.expiryDate = "160101";
    }

    // This runs after every unit test
    @After
    public void resetStream() {
        System.setOut(originalOutput);
    }

    @Test
    public void testMRZMismatchPassport() {
        newArgs.passportNumber = "101010101";
        MRZValidator.validatePassport(newArgs, newMRZ);
        assertEquals(MRZValidator.TERMINAL_RED + "MRZ validation failure" + MRZValidator.TERMINAL_RESET + ". Input passport number '" + newArgs.passportNumber + "' does not match passport number in MRZ '" + newMRZ.getPassportNumber() + "'\n", consoleOutput.toString());
    }

    @Test
    public void testMRZMatchPassport() {
        newArgs.passportNumber = "862281640";
        MRZValidator.validatePassport(newArgs, newMRZ);
        assertEquals(MRZValidator.TERMINAL_GREEN + "Passport MRZ is valid." + MRZValidator.TERMINAL_RESET + "\n", consoleOutput.toString());
    }

    @Test
    public void testMRZShortPassport() {
        newArgs.passportNumber = "77";
        MRZValidator.validatePassport(newArgs, newMRZ);
        assertEquals(MRZValidator.TERMINAL_RED + "MRZ validation failure" + MRZValidator.TERMINAL_RESET + ". Input passport number '" + newArgs.passportNumber + "' does not match passport number in MRZ '" + newMRZ.getPassportNumber() + "'\n", consoleOutput.toString());
    }

    @Test
    public void testMRZLongPassport() {
        newArgs.passportNumber = "12345678901234567890";
        MRZValidator.validatePassport(newArgs, newMRZ);
        assertEquals(MRZValidator.TERMINAL_RED + "MRZ validation failure" + MRZValidator.TERMINAL_RESET + ". Input passport number '" + newArgs.passportNumber + "' does not match passport number in MRZ '" + newMRZ.getPassportNumber() + "'\n", consoleOutput.toString());
    }

    @Test
    public void testMRZNonNumericPassport() {
        newArgs.passportNumber = "^^pLg\\n`~";
        MRZValidator.validatePassport(newArgs, newMRZ);
        assertEquals(MRZValidator.TERMINAL_RED + "MRZ validation failure" + MRZValidator.TERMINAL_RESET + ". Input passport number '" + newArgs.passportNumber + "' does not match passport number in MRZ '" + newMRZ.getPassportNumber() + "'\n", consoleOutput.toString());
    }
}
