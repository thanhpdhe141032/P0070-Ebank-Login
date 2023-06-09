package main;

import service.Display;
import service.Services;
import service.Validate;


import java.util.Locale;

public class Main {
    static void display() {
        Services service = new Services();
        Locale english = Locale.ENGLISH;
        Locale vietnamese = new Locale("vi");
        Display display = new Display();
        display.display();
        int choice = Validate.checkInputIntLimit(1, 3, english);
        switch (choice) {
            case 1:
                service.login(vietnamese);
                break;
            case 2:
                service.login(english);
                break;
            case 3:
                return;
        }
    }

    public static void main(String[] args) {
        display();
    }
}
