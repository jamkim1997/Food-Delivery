package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DB {

    // Benz's DB URL --- jdbc:mysql://localhost:3306, db, root, root,
    // com.mysql.cj.jdbc.Driver
    protected String URL = "jdbc:mysql://fdssql.mysql.database.azure.com:3306/";
    protected String db = "db?useSSL=true";
    protected String dbuser = "g10";
    protected String dbpass = "ro0T!ro0T!";
    protected String driver = "com.mysql.cj.jdbc.Driver";
    protected Connection conn;
}
