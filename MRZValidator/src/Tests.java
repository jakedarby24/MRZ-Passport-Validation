import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.*;

public class Tests {

    public final MRZ newMRZ = new MRZ("8622816409GBR9107119M1601013<<<<<<<<<<<<<<06");
    public Args newArgs = new Args();
    public final MRZValidator validateClass = new MRZValidator();

    // Tests whether the correct output is achieved when a mismatching DOB is inputted.
    @Test
    public void testMRZIncorrectDOB() {
        newArgs.dateOfBirth = "270819";
        newArgs.expiryDate = "160101"; newArgs.nationality = "GBR"; newArgs.passportNumber = "862281640";
        assertEquals(false, MRZValidator.validatePassport(newArgs, newMRZ));
    }

    @Test
    public void testMRZCorrectDOB() {
        newArgs.dateOfBirth = "910711"; 
        newArgs.expiryDate = "160101"; newArgs.nationality = "GBR"; newArgs.passportNumber = "862281640";
        assertEquals(true, MRZValidator.validatePassport(newArgs, newMRZ));
    }

    @Test
    public void testMRZShortDOB() {
        newArgs.dateOfBirth = "910";
        newArgs.expiryDate = "160101"; newArgs.nationality = "GBR"; newArgs.passportNumber = "862281640";
        assertEquals(false, MRZValidator.validatePassport(newArgs, newMRZ));
    }

    @Test
    public void testMRZNonNumericDOB() {
        newArgs.dateOfBirth = "A-'s]{";
        newArgs.expiryDate = "160101"; newArgs.nationality = "GBR"; newArgs.passportNumber = "862281640";
        assertEquals(false, MRZValidator.validatePassport(newArgs, newMRZ));
    }

    @Test
    public void testMRZNonNumericExpiry() {
        newArgs.expiryDate = "__[]=\\aBp";
        newArgs.dateOfBirth = "910711"; newArgs.nationality = "GBR"; newArgs.passportNumber = "862281640";
        assertEquals(false, MRZValidator.validatePassport(newArgs, newMRZ));
    }
}
