package com.revature.database.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {

    public static Connection getConnection() throws SQLException {
        /* URL String breakdown:
         * jdbc:postgresql -> jdbc should handle this connection using a postgresql driver
         * localhost -> hostname or IP address
         * 5432 -> port number (5432 is the default for postgresql)
         * trng2499 -> data of the specific database that PostgreSQL should connect to
         */
        String url = "jdbc:postgresql://localhost:5432/trng2499";
        String user = "joe";        // this is the user created using DCL
        String pass = "password";   // this is the password created for that user
        return DriverManager.getConnection(url, user, pass);
    }

    /*
     * Although this example uses the above implementation in the code
     * It is recommended to use one of the bottom two approaches
     */
    public static Connection getConnection_prop() throws SQLException {
        try (FileInputStream file = new FileInputStream("src/main/resources/sqldb.properties")) {
            Properties props = new Properties();
            props.load(file);
            String url = props.getProperty("CONNECTION_URL");
            String user = props.getProperty("CONNECTION_USERNAME");
            String pass = props.getProperty("CONNECTION_PASSWORD");
            return DriverManager.getConnection(url, user, pass);
        } catch (IOException e) {
            System.out.println("Failed to read sqldb.properties file");
            e.printStackTrace();
            return null;
        }
    }

    public static Connection getConnection_env() throws SQLException {
        String url = System.getenv("CONN_URL");
        String user = System.getenv("CONN_NAME");
        String pass = System.getenv("CONN_PASS");
        return DriverManager.getConnection(url, user, pass);
    }
}
