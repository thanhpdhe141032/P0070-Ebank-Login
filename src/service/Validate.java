package service;

import java.util.Locale;
import java.util.Scanner;

public class Validate {
    private static final String ACCOUNT_NUMBER_VALIDATE_REGEX = "^\\d{10}$";
    private static final Scanner in = new Scanner(System.in);
    private static final Services services = new Services();

    public static int checkInputIntLimit(int min, int max, Locale language) {
        while (true) {
            try {
                int result = Integer.parseInt(in.nextLine());
                if (result < min || result > max) {
                    throw new NumberFormatException();
                }
                return result;
            } catch (NumberFormatException ex) {
                services.getWordLanguage(language, "error-check-input");
                System.out.println();
            }
        }
    }

    public static String checkInputString(Locale language) {
        while (true) {
            String result = in.nextLine();
            if (result.length() == 0) {
                services.getWordLanguage(language, "error-check-string-input");
                System.out.println();
            } else {
                return result;
            }
        }
    }

    public static void checkInputAccount(Locale language) {
        while (true) {
            String result = in.nextLine();
            if (result.matches(ACCOUNT_NUMBER_VALIDATE_REGEX)) {
                Integer.parseInt(result);
                return;
            }
            services.getWordLanguage(language, "error-check-account-number");
            System.out.println();
        }
    }

    public static void checkInputPassword(Locale language) {
        String result;
        while (true) {
            result = checkInputString(language);
            if (isValidPassword(result, language)) {
                return;
            }
        }
    }

    public static boolean isValidPassword(String password, Locale language) {
        int lengthPassword = password.length();
        if (lengthPassword < 8 || lengthPassword > 31) {
            services.getWordLanguage(language, "error-check-account-password");
            System.out.println();
            return false;
        } else {
            int countDigit = 0;
            int countLetter = 0;
            for (int i = 0; i < lengthPassword - 1; i++) {
                if (Character.isDigit(password.charAt(i))) {
                    countDigit++;
                } else if (Character.isLetter(password.charAt(i))) {
                    countLetter++;
                }
            }
            if (countDigit < 1 || countLetter < 1) {
                services.getWordLanguage(language, "error-check-account-password-alpha");
                System.out.println();
                return false;
            }
        }
        return true;
    }


}
