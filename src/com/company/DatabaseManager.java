package com.company;
/* Created by InteliJ Idea

 *User: Nicholas Gaultney
 *Date: 2/17/2018
 *Time: 9:02 PM
 *Contact: nmgaultney@gmail.com
 */

import java.sql.*;

public class DatabaseManager {
    private static String url = "jdbc:sqlite:C:/sqlite/db/";
    private static String fileName;

    public DatabaseManager(String fileName){
        this.fileName = fileName;
        url += fileName;
        createNewDatabase(fileName);
    }

    // Connects to fileName database and returns the Connection object
    private Connection connect() {
        // SQLite connection string
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    // Creates a new database in the sqlite directory
    private static void createNewDatabase(String fileName){

        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Create table with one column
    public static void createNewTable(String tableName, String colOneName, String colOneType) {

        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS " + tableName + " (\n"
                + "	id integer PRIMARY KEY,\n"
                + colOneName + " " + colOneType + " NOT NULL,\n"
                + "	capacity real\n"
                + ");";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Create table with two columns
    public static void createNewTable(String tableName, String colOneName,
                                      String colOneType, String colTwoName,
                                      String colTwoType) {

        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS " + tableName + " (\n"
                + "	id integer PRIMARY KEY,\n"
                + colOneName + " " + colOneType + " NOT NULL,\n"
                + colTwoName + " " + colTwoType + "NOT NULL, \n"
                + "	capacity real\n"
                + ");";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Create table with three columns
    public static void createNewTable(String tableName, String colOneName,
                                      String colOneType, String colTwoName,
                                      String colTwoType, String colThreeName,
                                      String colThreeType) {

        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS " + tableName + " (\n"
                + "	id integer PRIMARY KEY,\n"
                + colOneName + " " + colOneType + " NOT NULL,\n"
                + colTwoName + " " + colTwoType + "NOT NULL, \n"
                + colThreeName + " " + colThreeType + "NOT NULL, \n"
                + "	capacity real\n"
                + ");";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Inserts one value into desired table
    public void insert(String tableName, String colOneName, String name,
                       String colTwoName, String value2 ) {
        String sql = "INSERT INTO " + tableName + " (name,value1) VALUES(?,?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, value2);             //FIXME: use actual column names
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Inserts two values into desired table
    public void insert(String tableName, String colOneName, String name,
                       String colTwoName, String value2, String colThreeName,
                       String value3 ) {
        String sql = "INSERT INTO " + tableName + " (name,value1,value2) VALUES(?,?,?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, value2);
            pstmt.setString(3, value3);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
