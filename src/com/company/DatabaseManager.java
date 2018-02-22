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

    // Creates the Categories table in the database
    public void buildCategories(){
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS Categories (\n"
                + "	id integer PRIMARY KEY,\n"
                + "category text NOT NULL,\n"
                + "percentage real, \n"
                + "value real, \n"
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

    // Inserts three values into the Categories table
    public void insertCategories(String name, Double percentage, Double value ){
        String sql = "INSERT INTO Categories (category,percentage, value) VALUES(?,?,?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setDouble(2, percentage);
            pstmt.setDouble(3, value);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteCategory(String category){
        String sql = "DELETE FROM Categories WHERE category = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setString(1, category);
            // execute the delete statement
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteCategory(int id){
        String sql = "DELETE FROM Categories WHERE id = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setInt(1, id);
            // execute the delete statement
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateCategory(int id, double percentage, double value){
            String sql = "UPDATE Categories SET percentage = ? , "
                    + "value = ? "
                    + "WHERE id = ?";

            try (Connection conn = this.connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                // set the corresponding param
                pstmt.setDouble(1, percentage);
                pstmt.setDouble(2, value);
                pstmt.setInt(3, id);
                // update
                pstmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
    }

    public void updateCategory(String category, double percentage, double value){
        String sql = "UPDATE Categories SET percentage = ? , "
                + "value = ? "
                + "WHERE category = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setDouble(1, percentage);
            pstmt.setDouble(2, value);
            pstmt.setString(3, category);
            // update
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void selectCategories(){
        String sql = "SELECT id, category, percentage, value FROM Categories";

        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getInt("id") +  "\t" +
                        rs.getString("category") + "\t" +
                        rs.getDouble("percentage") + "\t" +
                        rs.getDouble("value"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /*
    public void selectTable(String tableName){
        String sql = "SELECT * FROM " + tableName;

        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            // loop through the result set
            while (rs.next()) {
                int i = 0;
                while() {
                    System.out.println(rs.getObject(i));
                    //System.out.println(rs.getInt("id") +  "\t" +
                    //        rs.getString("name") + "\t" +
                    //        rs.getDouble("capacity"));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    */

    public void selectTable(String tableName, String conditionOne, String conditionTwo ){

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
