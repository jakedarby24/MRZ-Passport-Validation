/* This class is responsible for handling arguments that are needed
   when the program is run on the commandline */

import com.beust.jcommander.*;
import java.util.*;

public class Args {
    @Parameter
    public List<String> parameters = new ArrayList<>();

    @Parameter(names = {"-dob", "-dateofbirth"}, description = "The date of birth of the person whose passport is being checked.",
    validateWith = SixDigitValidation.class, required = true)
    public String dateOfBirth;

    @Parameter(names = {"-expiry", "-exp", "-expirydate"}, description = "The expiry date of the passport",
    validateWith = SixDigitValidation.class, required = true)
    public String expiryDate;

    @Parameter(names = {"-passportno", "-passportnumber"}, description = "The passport number of the passport", required = true)
    public String passportNumber;

    @Parameter(names = "-nationality", description = "The nationality of the person, abbreviated to a three-digit code",
    validateWith = ThreeDigitValidation.class, required = true)
    public String nationality;

    @Parameter(names = "-state", description = "The issuing state of the passport, abbreviated to a three-digit code",
    validateWith = ThreeDigitValidation.class, required = true)
    public String issueState;

    @Parameter(names = {"-mrz", "-mrzl2"}, description = "The second line of the MRZ", required = true)
    public String mrz;
}
