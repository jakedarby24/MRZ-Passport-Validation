// The main class
// This establishes the arguments from the commandline and runs the validation.

import com.beust.jcommander.JCommander;

public class MRZValidator {

    public static final String TERMINAL_RED = "\u001B[31m";
    public static final String TERMINAL_GREEN = "\u001B[32m";
    public static final String TERMINAL_RESET = "\u001B[0m";

    public static void main(String[] argv) throws Exception {
        Args args = new Args();
        JCommander.newBuilder()
        .addObject(args)
        .build()
        .parse(argv);
        
        MRZ inputMRZ = new MRZ(args.mrz);

        validatePassport(args, inputMRZ);
    }

    public static boolean validatePassport(Args args, MRZ inputMRZ) {
        boolean passportNumberCorrect = args.passportNumber.equals(inputMRZ.getPassportNumber());
        boolean nationalityCorrect = args.nationality.equals(inputMRZ.getNationality());
        boolean dateOfBirthCorrect = args.dateOfBirth.equals(inputMRZ.getDOB());
        boolean expiryCorrect = args.expiryDate.equals(inputMRZ.getExpiry());

        if (passportNumberCorrect && nationalityCorrect && dateOfBirthCorrect && expiryCorrect) {
            System.out.println(TERMINAL_GREEN + "Passport MRZ is valid." + TERMINAL_RESET);
            return true;
        }
        else if (!passportNumberCorrect) {
            System.out.println(TERMINAL_RED + "MRZ validation failure" + TERMINAL_RESET + ". Input passport number '" + args.passportNumber + "' does not match passport number in MRZ '" + inputMRZ.getPassportNumber() + "'");
        }
        else if (!nationalityCorrect) {
            System.out.println(TERMINAL_RED + "MRZ validation failure" + TERMINAL_RESET + ". Input nationality '" + args.nationality + "' does not match nationality in MRZ '" + inputMRZ.getNationality() + "'");
        }
        else if (!dateOfBirthCorrect) {
            System.out.println(TERMINAL_RED + "MRZ validation failure" + TERMINAL_RESET + ". Input DOB '" + args.dateOfBirth + "' does not match DOB in MRZ '" + inputMRZ.getDOB() + "'");
        }
        else if (!expiryCorrect) {
            System.out.println(TERMINAL_RED + "MRZ validation failure" + TERMINAL_RESET + ". Input expiry date '" + args.expiryDate + "' does not match expiry date in MRZ '" + inputMRZ.getExpiry() + "'");
        }
        return false;
    }
}
