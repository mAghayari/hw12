package view;

import dto.admin.Admin;
import services.AdminServices;

import java.util.Objects;

public class AdminView {

    public Admin registerAdmin() {
        Admin admin = new Admin();
        System.out.println("registering a new admin:\nEnter userName:");
        admin.setName(GetUserInputs.getUserNameString());
        System.out.println("Enter password:");
        admin.setPassword(GetUserInputs.getPasswordString());
        return admin;
    }

    public Admin getSignInInfo() {
        Admin admin = new Admin();
        System.out.println("userName:");
        admin.setName(GetUserInputs.getUserNameString());
        System.out.println("password:");
        admin.setPassword(GetUserInputs.getPasswordString());
        return admin;
    }

    public Admin adminSignIn() {
        AdminServices adminServices = new AdminServices();
        Admin admin = getSignInInfo();
        admin = adminServices.findAdmin(admin);
        if (!Objects.equals(admin.getUserName(), null)) {
            System.out.println("Welcome " + admin.getUserName() + "\n--------------------------");
        } else {
            System.out.println("InCorrect UserName Or Password");
        }
        return admin;
    }

    public Admin adminSignUp() {
        AdminServices adminServices = new AdminServices();
        Admin admin = registerAdmin();
        if (!Objects.equals(admin, null)) {
            admin = adminServices.signUp(admin);
            System.out.println("Welcome " + admin.getUserName() + "\n--------------------------");
        }
        return admin;
    }

    public void adminMenu() {
        CustomerView customerView = new CustomerView();
        adminMenu:
        while (true) {
            System.out.println("1)Reporting\n2)SignOut\n3)Exit");
            int adminMenuItem = GetUserInputs.getInBoundDigitalInput(3);
            switch (adminMenuItem) {
                case 1:
                    customerView.printReport();
                    break;
                case 2:
                    break adminMenu;
                case 3:
                    System.exit(0);
            }
        }
    }
}