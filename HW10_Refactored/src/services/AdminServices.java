package services;

import dao.AdminDao;
import dto.admin.Admin;

public class AdminServices {

    public Admin signUp(Admin admin) {
        AdminDao adminDao = new AdminDao();
        adminDao.addAdmin(admin);
        return admin;
    }

    public Admin findAdmin(Admin admin) {
        AdminDao adminDao = new AdminDao();
        admin = adminDao.fetchAdmin(admin);
        return admin;
    }
}