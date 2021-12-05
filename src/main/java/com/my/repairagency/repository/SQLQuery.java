package com.my.repairagency.repository;

public class SQLQuery {
    static class UserRequest {
        public static final String GET_USER_ID_BY_EMAIL = "SELECT user_id FROM users WHERE user_email = ?";
        public static final String SELECT_USER_WITH_ROLE_BY_LOGIN = "SELECT * FROM users JOIN user_role USING (user_role_id) WHERE user_email = ?";
        public static final String SELECT_USER_BY_ROLE = "SELECT * FROM users JOIN user_role USING (user_role_id) WHERE user_role_title = ?";
        public static final String GET_USER_WITH_ROLE_BY_ID = "SELECT * FROM users JOIN user_role USING (user_role_id) WHERE user_id =?";
        //public static final String GET_USER_BY_SURNAME = "SELECT * FROM  users JOIN user_role USING (user_role_id) WHERE ";
        public static final String ADD_NEW_USER = "INSERT INTO users (user_role_id, user_name, user_surname, user_email, user_password) VALUES (?, ?, ?, ?, ?)";
        public static final String GET_ALL_MASTERS = "SELECT * FROM users JOIN user_role USING (user_role_id) WHERE user_role_title = 'MASTER'";
        public static final String GET_ALL_USERS = "SELECT * FROM users JOIN user_role USING (user_role_id)";
        public static final String UPDATE_ACCOUNT = "UPDATE users SET account=? WHERE user_id=?";
        public static final String GET_ALL_WHERE_TEMPLATE = "SELECT * FROM users JOIN user_role USING (user_role_id) WHERE ";
    }

    static class RoleRequest {
        public static final String GET_ROLE_BY_ID = "SElECT user_role_title FROM user_role WHERE user_role_id = ?";
    }

    static class ApplicationRequest {
        public static final String INSERT_NEW_APPLICATION = "INSERT INTO  application (client_id, date, application_description) VALUES (?, ?, ?)";
        public static final String GET_ALL_APPLICATIONS = "SELECT * FROM application ORDER BY application_id";
        public static final String GET_ALL_CUSTOMER_APPLICATIONS = "SELECT * FROM application WHERE client_id = ? ORDER BY application_id";
        public static final String GET_ALL_MASTER_APPLICATIONS = "SELECT * FROM application WHERE master_id = ? ORDER BY application_id";
        public static final String SET_PRICE = "UPDATE application SET application_price=? WHERE application_id=?";
        public static final String CHANGE_COMPLETION_STATUS = "UPDATE application SET completion_status=? WHERE application_id=?";
        public static final String CHANGE_PAYMENT_STATUS = "UPDATE application SET payment_status=? WHERE application_id=?";
        public static final String CANCEL = "UPDATE application SET payment_status='CANCELED' WHERE application_id=?";
        public static final String EDIT = "UPDATE application SET application_description=? WHERE application_id=?";
        public static final String SET_MASTER = "UPDATE application SET master_id=? WHERE application_id=?";
        public static final String GET_ALL_WHERE_TEMPLATE = "SELECT * FROM application WHERE ";
        public static final String GET_ALL_BY_PRICE_RANGE = "SELECT * FROM application WHERE application_price BETWEEN ? AND ?";
        public static final String GET_ALL_BY_DATE_RANGE = "SELECT * FROM application WHERE date BETWEEN ? AND ?";
        public static final String GET_ALL_BY_MASTER_MASK = "SELECT * FROM application JOIN users u on u.user_id = application.master_id WHERE user_surname LIKE ?";
    }
}
