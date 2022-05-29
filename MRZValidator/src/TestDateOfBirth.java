import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.*;

// These unit tests check that the console output for the date of birth checks is as expected.
public class TestDateOfBirth {
    
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
        newArgs.expiryDate = "160101"; newArgs.nationality = "GBR"; newArgs.passportNumber = "862281640";
    }

    // This runs after every unit test
    @After
    public void resetStream() {
        System.setOut(originalOutput);
    }

    // Tests whether the correct output is achieved when a mismatching DOB is inputted.
    @Test
    public void testMRZMismatchDOB() {
        newArgs.dateOfBirth = "270819";
        MRZValidator.validatePassport(newArgs, newMRZ);
        assertEquals(MRZValidator.TERMINAL_RED + "MRZ validation failure" + MRZValidator.TERMINAL_RESET + ". Input DOB '" + newArgs.dateOfBirth + "' does not match DOB in MRZ '" + newMRZ.getDOB() + "'\n", consoleOutput.toString());
    }

    @Test
    public void testMRZMatchDOB() {
        newArgs.dateOfBirth = "910711"; 
        MRZValidator.validatePassport(newArgs, newMRZ);
        assertEquals(MRZValidator.TERMINAL_GREEN + "Passport MRZ is valid." + MRZValidator.TERMINAL_RESET + "\n", consoleOutput.toString());
    }

    @Test
    public void testMRZShortDOB() {
        newArgs.dateOfBirth = "910";
        MRZValidator.validatePassport(newArgs, newMRZ);
        assertEquals(MRZValidator.TERMINAL_RED + "MRZ validation failure" + MRZValidator.TERMINAL_RESET + ". Input DOB '" + newArgs.dateOfBirth + "' does not match DOB in MRZ '" + newMRZ.getDOB() + "'\n", consoleOutput.toString());
    }

    @Test
    public void testMRZLongDOB() {
        newArgs.dateOfBirth = "11th July 1991";
        MRZValidator.validatePassport(newArgs, newMRZ);
        assertEquals(MRZValidator.TERMINAL_RED + "MRZ validation failure" + MRZValidator.TERMINAL_RESET + ". Input DOB '" + newArgs.dateOfBirth + "' does not match DOB in MRZ '" + newMRZ.getDOB() + "'\n", consoleOutput.toString());
    }

    @Test
    public void testMRZNonNumericDOB() {
        newArgs.dateOfBirth = "A-'s]{";
        MRZValidator.validatePassport(newArgs, newMRZ);
        assertEquals(MRZValidator.TERMINAL_RED + "MRZ validation failure" + MRZValidator.TERMINAL_RESET + ". Input DOB '" + newArgs.dateOfBirth + "' does not match DOB in MRZ '" + newMRZ.getDOB() + "'\n", consoleOutput.toString());
    }
}
