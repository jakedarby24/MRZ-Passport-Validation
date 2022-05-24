# MRZ-Passport-Validation
 A simple commandline program that validates the Machine Readable Zone of a passport, given relevant information
 
 This program should be able to take the input "java -jar MRZValidator.jar -dob 910711 -exp 160101 -passportno 862281640 -nationality GBR -state GBR -mrzl2 8622816409GBR9107119M1601013<<<<<<<<<<<<<<06" and provide feedback on whether the validation was successful or not, as well as give a reason for failure.
 
 backup: java -jar MRZValidator.jar -dob 910711 -exp 160101 -passportno 86228164022 -nationality GBR -state GBR -mrzl2 8622816409GBR9107119M1601013\\<\\<\\<\\<\\<\\<\\<\\<\\<\\<\\<\\<\\<\\<06
