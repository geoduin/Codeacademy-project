package logic;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import database.StudentRepository;
import domain.Gender;

public class InputValidations {

    public static boolean validateDate(int day, int month, int year) {
        try {
            LocalDate date = LocalDate.of(year, month, day);
        } catch (DateTimeException e) {
            return false;
        }
        return true;
    }

    public static boolean validateMailAddress(String mailAddress) {
        return mailAddress.toLowerCase().matches("^[a-z0-9._%+-]+@[a-z0-9]+\\.[^\\.]+");
    }

    public static boolean isValidPercentage(int percentage) {
        return (percentage >= 0 && percentage <= 100);
    }

    public static String formatPostalCode(/* non_null */ String postalCode) {
        // Throws exception if postal code is null
        if (postalCode == null) {
            throw new NullPointerException();
        }

        // trims spaces before and after postal code
        postalCode = postalCode.trim();

        // Checks if the given string matches the regular expression for Dutch postal
        // codes.
        // Regular expression found at:
        // https://gist.github.com/jamesbar2/1c677c22df8f21e869cca7e439fc3f5b
        // The regular expression was modified to allow for an infinite number of spaces
        // between the numbers of the postal code and the letters of the postal code and
        // to not accept 0 as the first digit.
        if (postalCode.matches("^[1-9][0-9]{3}\\s*[A-Za-z]{2}$")) {
            // returning the formated postal code
            return postalCode.substring(0, 4) + " " + postalCode.substring(4).toUpperCase().trim();

        }
        // throws IllegalArgumentException if the String does not match the basic Dutch
        // postal code format.
        throw new IllegalArgumentException();
    }

    public static boolean fieldIsNotEmpty(String textFromField) {
        return !(textFromField.isBlank());
    }

    public static boolean areNumbers(String numberValue) {
        return (numberValue.matches("\\d+"));
    }

    public static Gender convertToGender(String Value) {
        if (Value.equals("O")) {
            return Gender.O;
        } else if (Value.equals("F")) {
            return Gender.F;
        } else {
            return Gender.M;
        }
    }

    public static boolean dateIsEarlierThanNow(String day, String month, String year) {
        LocalDate inputDate = null;
        try {
            inputDate = formatDate(year, month, day);
        } catch (Exception e) {
            // TODO: handle exception
            return false;
        }
        return inputDate.isBefore(LocalDate.now()) || inputDate.isEqual(LocalDate.now());
    }

    public static boolean postalCodeHasTheRightFormat(String postalCode) {
        try {
            String post = formatPostalCode(postalCode);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static LocalDate formatDate(String year, String month, String day) {
        if (!(year.matches("//d") || !month.matches("//d") || !day.matches("//d"))) {
            throw new NumberFormatException();
        }
        if (Integer.parseInt(day) < 10) {
            day = "0" + day;
        }
        if (Integer.parseInt(month) < 10) {
            month = "0" + month;
        }
        if (Integer.parseInt(year) < 1000) {
            year = "0" + year;
        }
        return LocalDate.parse(year + "-" + month + "-" + day);
    }

    // Method to check if email contains email
    // TWIJFEL GEVAL, Want deze methode roept emails vanuit de database op, moet
    // hierin blijven of moet het in studentLogic blijven?
    // Op dit moment is er dummy gegevens in de emailExistence klasse om deze
    // methode te kunnen testen
    public static boolean emailExist(String checkedMail) {
        StudentRepository repo = new StudentRepository();
        List<String> emails = new ArrayList<>();
        for (String email : repo.retrieveNameByEmail().values()) {
            emails.add(email);
        }
        return emails.contains(checkedMail);
    }

    public static boolean addresIsValid(String street, String houseNr, String postalCode) {
        boolean addressIsFilled = (fieldIsNotEmpty(street) && fieldIsNotEmpty(postalCode) && fieldIsNotEmpty(houseNr));
        boolean houseNumberIsNumber = areNumbers(houseNr);
        boolean postalCodeIsRight = postalCodeHasTheRightFormat(postalCode);
        return (addressIsFilled && houseNumberIsNumber && postalCodeIsRight);
    }

    public static boolean dateOfBirthIsValid(/* Not null */String day, /* Not null */String month,
            /* Not null */String year) {
        boolean valuesAreNumbers = areNumbers(day) && areNumbers(month) && areNumbers(year);
        boolean dateIsNotNow = dateIsEarlierThanNow(day, month, year);
        boolean dateIsCorrect = validateDate(Integer.parseInt(day), Integer.parseInt(month),
                Integer.parseInt(year));
        boolean dateIsFilled = fieldIsNotEmpty(day) && fieldIsNotEmpty(month) && fieldIsNotEmpty(year);
        return (valuesAreNumbers && dateIsNotNow && dateIsCorrect && dateIsFilled);
    }
}
