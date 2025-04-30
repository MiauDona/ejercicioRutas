package miau.dona;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class MenuActions {
    static Scanner scanner = new Scanner(System.in);
    static Connection connection = AucorsaDB.connection;
    static Statement statement = AucorsaDB.statement;

    private static String[] askForInfo(String[] questions) {
        String[] infoArray = new String[questions.length];

        for (int i = 0; i < questions.length; i++) {
            System.out.println(questions[i]);
            infoArray[i] = scanner.nextLine();
        }

        return infoArray;
    }

    public static void insertIntoBus() throws SQLException {
        String[] questions = new String[]{
                "What is the register?",
                "What is the type?",
                "What is the license?"
        };

        String[] infoArray = askForInfo(questions);
        String sql = "INSERT INTO Bus(register, type, license) VALUES('" + infoArray[0] + "', '" + infoArray[1] + "', '" + infoArray[2] + "')";

        int rows = statement.executeUpdate(sql);
        if (rows > 0) {
            System.out.println("Inserted successfully");
        } else {
            System.out.println("Something went wrong");
        }

    }

    public static void insertIntoDriver() throws SQLException {
        String[] questions = new String[]{
                "What is the numdriver?",
                "What is the name?",
                "What is the surname?"
        };

        String[] infoArray = askForInfo(questions);
        String sql = "INSERT INTO Driver(numdriver, name, surname) VALUES(" + infoArray[0] + ", '" + infoArray[1] + "', '" + infoArray[2] + "')";

        int rows = statement.executeUpdate(sql);
        if (rows > 0) {
            System.out.println("Driver registered successfully");
        } else {
            System.out.println("Driver registration failed");
        }
    }

    public static void insertIntoPlace() throws SQLException {
        String[] questions = new String[]{
                "What is the idplace?",
                "What is the cp?",
                "What is the city?",
                "What is the site?"
        };
        String[] infoArray = askForInfo(questions);

        String sql = "INSERT INTO Place(idplace, cp, city, site) VALUES(" + infoArray[0] + ", '" + infoArray[1] + "', '" + infoArray[2] + "', '" + infoArray[3] + "')";
        int rows = statement.executeUpdate(sql);
        if (rows > 0) {
            System.out.println("Place added successfully");
        } else {
            System.out.println("Place not added");
        }
    }

    public static void insertIntoRoute() throws SQLException {
        String[] questions = new String[]{
                "What is the register?",
                "What is the numdriver?",
                "What is the idplace?",
                "What is the day of the week that the route is?"
        };
        String[] infoArray = askForInfo(questions);

        String sql = "INSERT INTO BDP(register, numdriver, idplace, day_of_week) VALUES(" + infoArray[0] + ", " + infoArray[1] + ", " + infoArray[2] + ", '" + infoArray[3] + "')";
        int rows = statement.executeUpdate(sql);
        if (rows > 0) {
            System.out.println("Route added successfully");
        } else {
            System.out.println("Route could not be added");
        }
    }

    public static void deleteRoute() throws SQLException {
        String[] questions = new String[]{
                "What is the register?",
                "What is the idplace?",
                "What is the numdriver?"
        };
        String[] infoArray = askForInfo(questions);
        String sql = "DELETE FROM BDP WHERE register = " + infoArray[0] + " AND idplace = " + infoArray[1] + " AND numdriver = " + infoArray[2];
        int rows = statement.executeUpdate(sql);
        if (rows > 0) {
            System.out.println("Route deleted");
        } else {
            System.out.println("Route not deleted");
        }
    }

    public static void updateRoute() throws SQLException {
        String[] questions = new String[]{
                "What is the register?",
                "What is the idplace?",
                "What is the numdriver?"
        };

        String[] infoArray = askForInfo(questions);

        String[] updateInfo = new String[]{
                "What is the new register?",
                "What is the new idplace?",
                "What is the new numdriver?",
                "What is the new day of the week?"
        };

        String[] updateInfoArray = askForInfo(updateInfo);

        String sql = "UPDATE BDP SET register = '" + updateInfoArray[0] + "' AND idplace = " + updateInfoArray[1] + " AND numdriver = " + updateInfoArray[2] + " AND day_of_week = '" + updateInfoArray[3] + "' WHERE register = " + infoArray[0] + " AND idplace = " + infoArray[1] + " AND numdriver = " + infoArray[2];

        int rows = statement.executeUpdate(sql);

        if (rows > 0) {
            System.out.println("Update successful");
        } else {
            System.out.println("Update failed");
        }
    }

    public static void showTable(String table) throws SQLException {
        String sql = "SELECT * FROM " + table;
        ResultSet rs = statement.executeQuery(sql);

        for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
            System.out.print(rs.getMetaData().getColumnLabel(i + 1) + " ");
        }

        System.out.println();

        while (rs.next()) {
            for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                System.out.print(rs.getString(i) + " ");
            }
            System.out.println();
        }
    }

    public static void showDriverDataNumdriver() throws SQLException {
        System.out.println("Enter the driver number to see their information");
        String numDriver = scanner.nextLine();
        String sql = "SELECT * FROM Driver WHERE numdriver = " + numDriver + "";
        ResultSet rs = statement.executeQuery(sql);

        if (rs.next()) {
            System.out.println("Driver Information");
            System.out.println("Number " + rs.getString("numdriver"));
            System.out.println("Name " + rs.getString("name"));
            System.out.println("Surname " + rs.getString("surname"));
        } else {
            System.out.println("No driver found with the number " + numDriver);
        }
    }

    public static void whatDayIsThisRouteToThisCity() throws SQLException {
        System.out.println("Enter the destination city");
        String city = scanner.nextLine();
        String sql = "SELECT B.day_of_week FROM BDP B JOIN Place P ON B.idplace = P.idplace WHERE P.city = '" + city + "'";
        ResultSet rs = statement.executeQuery(sql);

        if (rs.next()) {
            System.out.println("Routes to " + city + " are available on");
            do {
                System.out.println(" - " + rs.getString("day_of_week"));
            } while (rs.next());
        } else {
            System.out.println("No routes found to " + city);
        }
    }

    public static void registerBusCheckingDriverData() throws SQLException {
        System.out.println("Enter the register number of the bus");
        String register = scanner.nextLine();
        String sql = "SELECT D.* FROM Driver D JOIN BDP B ON D.numdriver = B.numdriver WHERE B.register = '" + register + "'";
        ResultSet rs = statement.executeQuery(sql);

        if (rs.next()) {
            System.out.println("Drivers who drive bus with register " + register);
            do {
                System.out.println("- Number: " + rs.getString("numdriver") + ", Name: " + rs.getString("name") + " " + rs.getString("surname"));
            } while (rs.next());
        } else {
            System.out.println("No drivers found for the bus with register " + register);
        }
    }
}
