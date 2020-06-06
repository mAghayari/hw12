package view;

import java.util.InputMismatchException;
import java.util.Scanner;

public class GetUserInputs {
    static Scanner scanner = new Scanner(System.in);

    public static int getInBoundDigitalInput(int bound) {
        if (bound == 0) {
            System.out.println("bound is 0");
            return 0;
        } else {
            while (true) {
                String input = scanner.next();
                if (input.matches("[0-9]+")) {
                    try {
                        if (Integer.parseInt(input) <= bound && Integer.parseInt(input) > 0)
                            return Integer.parseInt(input);
                        else {
                            System.out.println("❌ input not in bound\ntry again:");
                            scanner.nextLine();
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("❌ Mismatched input...\nThe input must be numeric:");
                    }
                } else
                    System.out.println("❌ Mismatched input...\nThe input must be numeric:");
            }
        }
    }

    public static int getAge() {
        while (true) {
            String input = scanner.next();
            if (input.matches("([1-9][0-9]+)")) {
                int number = Integer.parseInt(input);
                if (number >= 15)
                    return number;
                else {
                    System.out.println("You are too young to register on the site!");
                    break;
                }
            } else
                System.out.println("❌ Mismatched input...\njust numbers are allowed:");
        }
        return 0;
    }

    public static String getLetteringString() {
        while (true) {
            String input = scanner.next();
            if (input.matches("(([a-zA-Z ]+[a-zA-Z]+)+|[a-zA-Z]+)")) {
                try {
                    return input;
                } catch (InputMismatchException e) {
                    System.out.println("❌ Mismatched input...\njust alphabet are allowed:");
                }
            } else
                System.out.println("❌ Mismatched input...\njust alphabet are allowed:");
        }
    }

    public static String getUserNameString() {
        while (true) {
            String input = scanner.next();
            if (input.matches("[a-zA-z0-9._-]+")) {
                try {
                    return input;

                } catch (NumberFormatException e) {
                    System.out.println("❌ Mismatched input...\nenter a valid userName:");
                }
            } else
                System.out.println("❌ Mismatched input...\nenter a valid userName:");
        }
    }

    public static String getPasswordString() {
        while (true) {
            String input = scanner.next();
            if (input.matches("[a-zA-z0-9]{8}|[a-zA-z0-9]{9}|[a-zA-z0-9]{10}|[a-zA-z0-9]{11}|[a-zA-z0-9]{12}|[a-zA-z0-9]{13}|" +
                    "[a-zA-z0-9]{14}|[a-zA-z0-9]{15}|[a-zA-z0-9]{16}")) {
                try {
                    return input;
                } catch (NumberFormatException e) {
                    System.out.println("❌ Mismatched input...\nenter a valid password:");
                }
            } else
                System.out.println("❌ Mismatched input...\nenter a valid password:");
        }
    }

    public static String getEmailString() {
        while (true) {
            String input = scanner.next();
            if (input.matches("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$")) {
                try {
                    return input;
                } catch (InputMismatchException e) {
                    System.out.println("❌ Mismatched input...\nenter a valid EmailAddress:");
                }
            } else
                System.out.println("❌ Mismatched input...\nenter a valid EmailAddress:");
        }
    }

    public static String getMobileString() {
        while (true) {
            String input = scanner.next();
            if (input.matches("9((0[1-3]|5)|(1[0-9])|(2[0-2])|(3(1|[3-9]))|(9[0-1]))[0-9]{7}")) {
                try {
                    return input;

                } catch (NumberFormatException e) {
                    System.out.println("❌ Mismatched input...\nenter a valid Cell number:");
                }
            } else
                System.out.println("❌ Mismatched input...\nenter a valid Cell number:");
        }
    }

    public static int getInteger() {
        while (true) {
            String input = scanner.next();
            if (input.matches("[0-9]+"))
                return Integer.parseInt(input);
            else
                System.out.println("❌ Mismatched input...\nenter a number:");
        }
    }
}