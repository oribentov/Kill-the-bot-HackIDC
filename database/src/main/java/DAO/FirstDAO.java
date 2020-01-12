package DAO;

import DBConnector.DBConnection;
import java.sql.SQLException;

public class FirstDAO {

    private DBConnection connector;

    public FirstDAO(){
        this.connector = new DBConnection();
    }

    public void InsertToDB() throws SQLException {
        String sql = "INSERT INTO luckydeal.users(\n" +
                "\tuser_id, user_first_name, user_last_name, user_mail, user_password, user_birth_date, user_gender, user_payment_method, user_rank)\n" +
                "\tVALUES (3, 'lidor', 'ben itzhak', 'lidor@gmail.com', '123', now(), 'male', '{ \"customer\": \"John Doe\", \"items\": {\"product\": \"Beer\",\"qty\": 6}}', '5');";
        connector.GetDSL().fetch(sql);
    }
}
