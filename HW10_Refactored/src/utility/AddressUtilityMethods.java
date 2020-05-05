package utility;

import jdbc.dto.customer.Address;

import java.util.Scanner;

public class AddressUtilityMethods {
    public static String getZipCodeString() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.next();
            if (input.matches("\\b(?!(\\d)\\1{3})[13-9]{4}[1346-9][013-9]{5}\\b")) {
                try {
                    return input;

                } catch (NumberFormatException e) {
                    System.out.println("❌ Mismatched input...\nenter a valid ZipCode:");
                }
            } else
                System.out.println("❌ Mismatched input...\nenter a valid ZipCode:");
        }
    }

    public static Address getAddress() {
        Address address = new Address();
        System.out.println("Getting Address\nEnter Province:");
        address.setProvince(CommonUtilityMethods.getLetteringString());
        System.out.println("Enter City:");
        address.setCity(CommonUtilityMethods.getLetteringString());
        System.out.println("Enter Street:");
        address.setStreet(CommonUtilityMethods.getLetteringString());
        System.out.println("Enter ZipCode(a real ZipCode):");
        address.setZipCode(getZipCodeString());
        return address;
    }
}