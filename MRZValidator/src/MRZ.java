// Custom class for holding all of the contents of the inputted MRZ
public class MRZ {

    // Class attributes
    private String passportNumber;
    private int passportNumberCheckDigit;
    private String nationality;
    private String dateOfBirth;
    private int dateOfBirthCheckDigit;
    private String expiryDate;
    private int expiryDateCheckDigit;
    private int fullCheckDigit;

    // Constructor
    public MRZ(String inputtedMRZ) {
        this.passportNumber = inputtedMRZ.substring(0, 9);
        this.passportNumberCheckDigit = Integer.parseInt(inputtedMRZ.substring(9, 10));
        this.nationality = inputtedMRZ.substring(10, 13);
        this.dateOfBirth = inputtedMRZ.substring(13, 19);
        this.dateOfBirthCheckDigit = Integer.parseInt(inputtedMRZ.substring(19, 20));
        this.expiryDate = inputtedMRZ.substring(21, 27);
        this.expiryDateCheckDigit = Integer.parseInt(inputtedMRZ.substring(27, 28));
        this.fullCheckDigit = Integer.parseInt(inputtedMRZ.substring(43, 44));
    }

    // Getters
    public String getPassportNumber() {
        return passportNumber;
    }
    public int getPassportNumberCheckDigit() {
        return passportNumberCheckDigit;
    }
    public String getNationality() {
        return nationality;
    }
    public String getDOB() {
        return dateOfBirth;
    }
    public int getDOBCheckDigit() {
        return dateOfBirthCheckDigit;
    }
    public String getExpiry() {
        return expiryDate;
    }
    public int getExpiryCheckDigit() {
        return expiryDateCheckDigit;
    }
    public int getFullCheckDigit() {
        return fullCheckDigit;
    }

    // Overrides the toString() method to output each aspect of the MRZ in the terminal if a println() command is used.
    public String toString() {
        return "Passport number: " + passportNumber + "\n" +
               "Passport number check digit: " + passportNumberCheckDigit + "\n" +
               "Nationality: " + nationality + "\n" +
               "Date of birth: " + dateOfBirth + "\n" +
               "Date of birth check digit: " + dateOfBirthCheckDigit + "\n" +
               "Expiry date: " + expiryDate + "\n" +
               "Expiry date check digit: " + expiryDateCheckDigit + "\n" +
               "Full Check Digit: " + fullCheckDigit;
    }
}
