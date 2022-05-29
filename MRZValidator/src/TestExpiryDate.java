import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.*;

// These unit tests check that the console output for the expiry date checks is as expected.
public class TestExpiryDate {
    
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
        newArgs.dateOfBirth = "910711"; newArgs.nationality = "GBR"; newArgs.passportNumber = "862281640";
    }

    // This runs after every unit test
    @After
    public void resetStream() {
        System.setOut(originalOutput);
    }

    @Test
    public void testMRZMismatchExpiry() {
        newArgs.expiryDate = "191010";
        MRZValidator.validatePassport(newArgs, newMRZ);
        assertEquals(MRZValidator.TERMINAL_RED + "MRZ validation failure" + MRZValidator.TERMINAL_RESET + ". Input expiry date '" + newArgs.expiryDate + "' does not match expiry date in MRZ '" + newMRZ.getExpiry() + "'\n", consoleOutput.toString());
    }

    @Test
    public void testMRZMatchExpiry() {
        newArgs.expiryDate = "160101";
        MRZValidator.validatePassport(newArgs, newMRZ);
        assertEquals(MRZValidator.TERMINAL_GREEN + "Passport MRZ is valid." + MRZValidator.TERMINAL_RESET + "\n", consoleOutput.toString());
    }

    @Test
    public void testMRZShortExpiry() {
        newArgs.expiryDate = "05";
        MRZValidator.validatePassport(newArgs, newMRZ);
        assertEquals(MRZValidator.TERMINAL_RED + "MRZ validation failure" + MRZValidator.TERMINAL_RESET + ". Input expiry date '" + newArgs.expiryDate + "' does not match expiry date in MRZ '" + newMRZ.getExpiry() + "'\n", consoleOutput.toString());
    }

    @Test
    public void testMRZLongExpiry() {
        newArgs.expiryDate = "01st January 2016";
        MRZValidator.validatePassport(newArgs, newMRZ);
        assertEquals(MRZValidator.TERMINAL_RED + "MRZ validation failure" + MRZValidator.TERMINAL_RESET + ". Input expiry date '" + newArgs.expiryDate + "' does not match expiry date in MRZ '" + newMRZ.getExpiry() + "'\n", consoleOutput.toString());
    }

    @Test
    public void testMRZNonNumericExpiry() {
        newArgs.expiryDate = "__[]=\\aBp";
        MRZValidator.validatePassport(newArgs, newMRZ);
        assertEquals(MRZValidator.TERMINAL_RED + "MRZ validation failure" + MRZValidator.TERMINAL_RESET + ". Input expiry date '" + newArgs.expiryDate + "' does not match expiry date in MRZ '" + newMRZ.getExpiry() + "'\n", consoleOutput.toString());
    }

}
