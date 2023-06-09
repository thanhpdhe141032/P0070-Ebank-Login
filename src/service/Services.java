package service;

import java.util.Locale;
import java.util.ResourceBundle;


public class Services {
    private static final char[] chars = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();


    public String generateCaptchaText() {
        final int LENGTH = 6;
        StringBuilder sb = new StringBuilder();
        int index = 0;
        for (int i = 0; i < LENGTH; i++) {
            index = (int) (Math.random() * (chars.length - 1));
            sb.append(chars[index]);
        }
        return sb.toString();
    }

    public void getWordLanguage(Locale curLocate, String key) {
        ResourceBundle words
                = ResourceBundle.getBundle("language/" + curLocate, curLocate);
        String value = words.getString(key);
        System.out.printf(value);
    }

    public boolean checkInputCaptcha(String captchaGenerated, Locale language) {
        System.out.println(captchaGenerated);
        getWordLanguage(language, "input-enter-capcha");
        String captchaInput = Validate.checkInputString(language);
        for (int i = 0; i < captchaInput.length(); i++) {
            if (!captchaGenerated.contains(Character.toString(captchaInput.charAt(i)))) {
                return false;
            }
        }
        return true;
    }

    public void login(Locale language) {
        getWordLanguage(language, "input-account-number");
        Validate.checkInputAccount(language);
        getWordLanguage(language, "input-account-password");
        Validate.checkInputPassword(language);
        String captchaGenerated = generateCaptchaText();
        while (true) {
            if (checkInputCaptcha(captchaGenerated, language)) {
                getWordLanguage(language, "success-login");
                System.out.println();
                return;
            } else {
                getWordLanguage(language, "error-check-capcha");
                System.out.println();
            }
        }
    }
}
