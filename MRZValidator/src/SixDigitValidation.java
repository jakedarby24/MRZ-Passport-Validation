// This class is used to do basic validation on the parameters provided in the commandline

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.ParameterException;

public class SixDigitValidation implements IParameterValidator {
    public void validate(String name, String value) throws ParameterException {
        if (value.length() != 6) {
            throw new ParameterException("Parameter " + name + " should be exactly 6 digits long (found " + value.length() +" digits)");
        }
    }
}
