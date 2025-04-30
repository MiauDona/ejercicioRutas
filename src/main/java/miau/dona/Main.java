package miau.dona;

import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String connection = "jdbc:sqlite:aucorsa.db";

        try {
            Connection con = DriverManager.getConnection(connection);
            Statement statement = con.createStatement();

            AucorsaDB aucorsaDB = new AucorsaDB(con, statement);

            Menu.showGeneralMenu();

            con.close();
            statement.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}