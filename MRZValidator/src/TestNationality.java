import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.*;

// These unit tests check that the console output for the passport number checks is as expected.
public class TestNationality {
    
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
        newArgs.dateOfBirth = "910711"; newArgs.passportNumber = "862281640"; newArgs.expiryDate = "160101";
    }

    // This runs after every unit test
    @After
    public void resetStream() {
        System.setOut(originalOutput);
    }

    @Test
    public void testMRZMismatchNationality() {
        newArgs.nationality = "BNA";
        MRZValidator.validatePassport(newArgs, newMRZ);
        assertEquals(MRZValidator.TERMINAL_RED + "MRZ validation failure" + MRZValidator.TERMINAL_RESET + ". Input nationality '" + newArgs.nationality + "' does not match nationality in MRZ '" + newMRZ.getNationality() + "'\n", consoleOutput.toString());
    }

    @Test
    public void testMRZMatchNationality() {
        newArgs.nationality = "GBR";
        MRZValidator.validatePassport(newArgs, newMRZ);
        assertEquals(MRZValidator.TERMINAL_GREEN + "Passport MRZ is valid." + MRZValidator.TERMINAL_RESET + "\n", consoleOutput.toString());
    }
}
