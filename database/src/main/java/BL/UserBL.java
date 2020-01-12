package BL;

import DAO.UserDAO;
import Models.User;

import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class UserBL {

    public void InsertUserToDB(User user) throws SQLException {
        UserDAO userDao = new UserDAO();
        userDao.InsertToDB(user.getEmail(),user.getFirstName(),user.getLastName(),user.getPassword(), new Date(user.getBirthDate().getTime()),user.getGender(),user.getPhoneNumber());
    }

    public User GetUserDetails(String userId, String password) {
        return null;
    }
}
