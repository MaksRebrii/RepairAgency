package com.my.repairagency;

import com.my.repairagency.web.utils.HashingPassword;

public class MAin {
    public static void main(String[] args) throws Exception {
        String password = "admin";
        System.out.println(HashingPassword.getSaltedHash(password));
        System.out.println(HashingPassword.check("admin", "d+sYTsy7/EPLsyaYCsD8QKxSfrJQhU4hk7N14rc2A7I=$pFeKrhPXvX8xyVpQ6hhw8ACiAgdooCGneWUx0Ivb1QY="));
    }
}
