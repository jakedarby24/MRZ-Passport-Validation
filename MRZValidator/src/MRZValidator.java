// The main class
// This establishes the arguments from the commandline and runs the validation.

import com.beust.jcommander.JCommander;

public class MRZValidator {

    // Colours for the terminal, if on macOS or Linux.
    public static String TERMINAL_RED = "\u001B[31m";
    public static String TERMINAL_GREEN = "\u001B[32m";
    public static String TERMINAL_RESET = "\u001B[0m";

    // Main method
    public static void main(String[] argv) throws Exception {
        setTerminalColours();

        // Sets up the arguments inputted from the commandline
        Args args = new Args();
        JCommander.newBuilder()
        .addObject(args)
        .build()
        .parse(argv);
        
        // Creates a new MRZ class and uses the arguments that have just been set up
        MRZ inputMRZ = new MRZ(args.mrz);
        // Validate the MRZ
        validatePassport(args, inputMRZ);
    }

    // Function for calculating the check digits of each part of the MRZ
    public static int calculateCheckDigit(String input) {
        int sum = 0;
        int weight = 7;
        for (int i = 0; i < input.length(); i++) {
            String character = input.substring(i,i+1);
            sum += Integer.parseInt(character) * weight;
            if (weight == 7) {
                weight = 3;
            }
            else if (weight == 3) {
                weight = 1;
            }
            else {
                weight = 7;
            }
        }
        return sum % 10;
    }

    // Function for checking that each part of the MRZ matches the input arguments
    public static boolean validatePassport(Args args, MRZ inputMRZ) {

        // Boolean values holding true if a condition holds or false if a condition fails.
        boolean passportNumberCorrect = args.passportNumber.equals(inputMRZ.getPassportNumber());
        boolean nationalityCorrect = args.nationality.equals(inputMRZ.getNationality());
        boolean dateOfBirthCorrect = args.dateOfBirth.equals(inputMRZ.getDOB());
        boolean expiryCorrect = args.expiryDate.equals(inputMRZ.getExpiry());

        // Boolean values which check to make sure that the checksums hold for each part of the MRZ
        int passportCheckDigit = calculateCheckDigit(args.passportNumber);
        boolean passportChecksumCorrect = passportCheckDigit == (inputMRZ.getPassportNumberCheckDigit());
        int dobCheckDigit = calculateCheckDigit(args.dateOfBirth);
        boolean dobChecksumCorrect = dobCheckDigit == (inputMRZ.getDOBCheckDigit());
        int expiryCheckDigit = calculateCheckDigit(args.expiryDate);
        boolean expiryChecksumCorrect = expiryCheckDigit == (inputMRZ.getExpiryCheckDigit());
        int completeChecksum = calculateCheckDigit(args.passportNumber + String.valueOf(passportCheckDigit) + args.dateOfBirth + String.valueOf(dobCheckDigit) + args.expiryDate + String.valueOf(expiryCheckDigit));
        boolean completeChecksumCorrect = completeChecksum == (inputMRZ.getFullCheckDigit());


        // Checks that all of the conditions for validating an MRZ hold
        if (passportNumberCorrect && nationalityCorrect && dateOfBirthCorrect && expiryCorrect && passportChecksumCorrect && dobChecksumCorrect && expiryChecksumCorrect && completeChecksumCorrect) {
            System.out.println(TERMINAL_GREEN + "Passport MRZ is valid." + TERMINAL_RESET);
            return true;
        }

        // If not, output the appropriate failure to the terminal
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
        else if (!passportChecksumCorrect) {
            System.out.println(TERMINAL_RED + "MRZ validation failure" + TERMINAL_RESET + ". Passport number checksum value " + inputMRZ.getPassportNumberCheckDigit() + " does not match calculated checksum with value " + passportCheckDigit);
        }
        else if (!dobChecksumCorrect) {
            System.out.println(TERMINAL_RED + "MRZ validation failure" + TERMINAL_RESET + ". Date of birth checksum value " + inputMRZ.getDOBCheckDigit() + " does not match calculated checksum with value " + dobCheckDigit);
        }
        else if (!expiryChecksumCorrect) {
            System.out.println(TERMINAL_RED + "MRZ validation failure" + TERMINAL_RESET + ". Expiry date checksum value " + inputMRZ.getExpiryCheckDigit() + " does not match calculated checksum with value " + expiryCheckDigit);
        }
        else if (!completeChecksumCorrect) {
            System.out.println(TERMINAL_RED + "MRZ validation failure" + TERMINAL_RESET + ". Final MRZ checksum value " + inputMRZ.getFullCheckDigit() + " does not match calculated checksum with value " + completeChecksum);
        }
        return false;
    }

    // Colour support is not present in the Windows command prompt, whereas it is in Unix
    // If the operating system is Windows, disable colour to avoid ANSI codes being outputted in the terminal
    public static void setTerminalColours() {
        String operatingSystem = System.getProperty("os.name");
        if (operatingSystem.equals("Windows 10") || operatingSystem.equals("Windows 11")) {
            TERMINAL_GREEN = "";
            TERMINAL_RED = "";
            TERMINAL_RESET = "";
        }
    }
}