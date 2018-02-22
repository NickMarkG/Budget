package com.company;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        DatabaseManager db = new DatabaseManager("test.db");
        //db.createNewTable("Categories", "Category", "text",
        //"weighted_percent", "text");
        //db.buildCategories();
        //db.insertCategories("Savings", 0.50, 0.0);
        db.updateCategory("Savings", 0.0, 5.0);
        db.updateCategory(1, 0.3, 0.50);
        db.deleteCategory( "Savings" );
        db.selectCategories();

    }

}
