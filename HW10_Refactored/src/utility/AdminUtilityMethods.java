package utility;

import jdbc.dao.AdminDao;
import jdbc.dto.admin.Admin;

import java.util.Objects;

public class AdminUtilityMethods {

    public Admin registerAdmin() {
        Admin admin = new Admin();
        System.out.println("registering a new admin:\nEnter userName:");
        admin.setName(CommonUtilityMethods.getUserNameString());
        System.out.println("Enter password:");
        admin.setPassword(CommonUtilityMethods.getPasswordString());
        return admin;
    }

    public Admin adminSignIn() {
        AdminDao adminDao = new AdminDao();
        System.out.println("userName:");
        String userName = CommonUtilityMethods.getUserNameString();
        System.out.println("password:");
        String password = CommonUtilityMethods.getPasswordString();
        Admin admin = adminDao.getManager(userName, password);
        if (!Objects.equals(admin.getName(), null)) {
            System.out.println("welcome " + userName);
            return admin;
        } else {
            System.out.println("InCorrect UserName Or Password");
        }
        return null;
    }

    public Admin adminSignUp() {
        AdminDao adminDao = new AdminDao();
        Admin admin = registerAdmin();
        adminDao.addAdmin(admin);
        System.out.println("Welcome " + admin.getName()+"\n--------------------------");
        return admin;
    }
}
