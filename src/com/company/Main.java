package com.company;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        DatabaseManager db = new DatabaseManager("test.db");
        db.createNewTable("Categories", "Category", "text",
                "weighted_percent", "text");
        db.insert("Categories", "Savings", "50");
    }

}
