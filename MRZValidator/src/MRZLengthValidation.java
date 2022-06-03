// This class is used to do basic length validation on the second line of the MRZ provided in the commandline

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.ParameterException;

public class MRZLengthValidation implements IParameterValidator {
    public void validate(String name, String value) throws ParameterException {
        if (value.length() != 44) {
            throw new ParameterException("Parameter " + name + " should be exactly 44 digits long (found " + value.length() + " digits)");
        }
    }
}
