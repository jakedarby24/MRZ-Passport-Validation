/* This class is responsible for handling arguments that are needed
   when the program is run on the commandline */

import com.beust.jcommander.*;
import java.util.*;

public class Args {
    // A list containing all of the parameters input
    @Parameter
    public List<String> parameters = new ArrayList<>();

    // The inputted date of birth in the terminal
    @Parameter(names = {"-dob", "-dateofbirth"}, description = "The date of birth of the person whose passport is being checked.",
    validateWith = SixDigitValidation.class, required = true)
    public String dateOfBirth;

    // The inputted expiry date in the terminal
    @Parameter(names = {"-expiry", "-exp", "-expirydate"}, description = "The expiry date of the passport",
    validateWith = SixDigitValidation.class, required = true)
    public String expiryDate;

    // The inputted passport number in the terminal
    @Parameter(names = {"-passportno", "-passportnumber"}, description = "The passport number of the passport", required = true)
    public String passportNumber;

    // The inputted nationality in the terminal
    @Parameter(names = "-nationality", description = "The nationality of the person, abbreviated to a three-digit code",
    validateWith = ThreeDigitValidation.class, required = true)
    public String nationality;

    // The inputted issuing state in the terminal
    @Parameter(names = "-state", description = "The issuing state of the passport, abbreviated to a three-digit code",
    validateWith = ThreeDigitValidation.class, required = true)
    public String issueState;

    // The inputted second line of the MRZ in the terminal
    @Parameter(names = {"-mrz", "-mrzl2"}, description = "The second line of the MRZ", required = true)
    public String mrz;
}
