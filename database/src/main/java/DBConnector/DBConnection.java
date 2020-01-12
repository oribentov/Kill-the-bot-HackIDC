package DBConnector;

import org.apache.commons.dbcp.BasicDataSource;
import org.jooq.Configuration;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.jooq.impl.DefaultConfiguration;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    final String USER_NAME = "postgres";
    final String PASSWORD = "postgres";
    final String URL = "jdbc:postgresql://localhost:5432/luckydeal";

    Configuration configurationion;
    BasicDataSource source;

    public DBConnection() {

        this.source = new BasicDataSource();
        this.source.setUrl(URL);
        this.source.setUsername(USER_NAME);
        this.source.setPassword(PASSWORD);

        configurationion = new DefaultConfiguration().set(SQLDialect.POSTGRES_10).set(source);
    }

    private Connection GetDBConnection () throws SQLException {
        return this.source.getConnection();
    }

    public DSLContext GetDSL() throws SQLException {
        return DSL.using(this.GetDBConnection(), SQLDialect.POSTGRES_10);
    }
}
