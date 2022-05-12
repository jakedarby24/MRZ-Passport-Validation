/* This class is responsible for handling arguments that are needed
   when the program is run on the commandline */

import com.beust.jcommander.*;
import java.util.*;

public class Args {
    @Parameter
    private List<String> parameters = new ArrayList<>();

    @Parameter(names = {"-dob", "-dateofbirth"}, description = "The date of birth of the person whose passport is being checked.")
    private String dateOfBirth;
}
