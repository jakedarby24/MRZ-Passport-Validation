import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.ParameterException;

public class ThreeDigitValidation implements IParameterValidator {
    public void validate(String name, String value) throws ParameterException {
        if (value.length() != 3) {
            throw new ParameterException("Parameter " + name + " should be exactly 3 digits long (found " + value.length() +" digits)");
        }
    }
}
