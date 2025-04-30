package miau.dona;

import java.sql.*;
import java.util.Scanner;

public class AucorsaDB {
    static Connection connection;
    static Statement statement;

    public AucorsaDB(Connection connection, Statement statement) throws SQLException {
        CreateDBSchema.createTables(statement);
        AucorsaDB.connection = connection;
        AucorsaDB.statement = statement;
    }

    public void mostrarDatosTabla(String table) throws SQLException {
        String sql = "SELECT * FROM " + table;

        ResultSet rs = statement.executeQuery(sql);

        while (rs.next()) {
            for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                System.out.print(rs.getString(i) + " ");
            }
            System.out.println();
        }
    }

    public void cerrarStatement() throws SQLException {
        this.statement.close();
    }

    public void insertBus(String register, String type, String license) throws SQLException {
        String sql = dataInsertion("Bus", "register, type, license", "?, ?, ?");
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, register);
        preparedStatement.setString(2, type);
        preparedStatement.setString(3, license);

        preparedStatement.executeUpdate();
    }

    public void insertDriver(int numDriver, String name, String surname) throws SQLException {
        String sql = dataInsertion("Driver", "numdriver, name, surname", "?, ?, ?");

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, numDriver);
        preparedStatement.setString(2, name);
        preparedStatement.setString(3, surname);

        preparedStatement.executeUpdate();
    }

    public void insertPlace(int idPlace, String site, String city, int cp) throws SQLException {
        String sql = dataInsertion("Place", "idplace, site, city, cp", "?, ?, ?, ?");
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, idPlace);
        preparedStatement.setString(2, site);
        preparedStatement.setString(3, city);
        preparedStatement.setInt(4, cp);

        preparedStatement.executeUpdate();
    }

    // Es la tabla BDP
    public void insertRoute(String register, int numdriver, int idplace, String dayWeek) throws SQLException {
        String sql = dataInsertion("BDP", "register, numdriver, idplace, day_of_week", "?, ?, ?, ?");
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, register);
        preparedStatement.setInt(2, numdriver);
        preparedStatement.setInt(3, idplace);
        preparedStatement.setString(4, dayWeek);

        preparedStatement.executeUpdate();
    }

    public void updateRoutePerDay() {

    }

    public boolean askUserDeleteRoute() throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("You are going to delete a route \nEnter numdriver");
        int numdriver = Integer.parseInt(scanner.nextLine());

        System.out.println("Enter idplace");
        int idplace = Integer.parseInt(scanner.nextLine());

        System.out.println("Enter register");
        int register = Integer.parseInt(scanner.nextLine());

        String sql = "DELETE FROM BDP WHERE numdriver = ? AND idplace = ? AND register = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setInt(1, numdriver);
        preparedStatement.setInt(2, idplace);
        preparedStatement.setInt(3, register);

        int filas = preparedStatement.executeUpdate();

        if (filas > 0) {
            System.out.println("Deleted successfully");
            return true;
        } else {
            System.out.println("Route not found");
            return false;
        }
    }

    private void showDriverData(int numdriver) throws SQLException {
        String sql = "SELECT * FROM Driver WHERE numdriver = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setInt(1, numdriver);
        ResultSet rs = preparedStatement.executeQuery();

        System.out.println("Their data:");

        if (rs.next()) {
            System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3));
        } else {
            System.out.println("No driver data found");
        }
    }

    public void askDriverData() throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter numdriver");
        int numdriver = scanner.nextInt();

        showDriverData(numdriver);
    }

    public void whatDayIsThisRouteToThisCity() throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Which route?");
        System.out.println("Enter idplace");
        int idplace = Integer.parseInt(scanner.nextLine());

        System.out.println("Enter numdriver");
        int numdriver = Integer.parseInt(scanner.nextLine());

        System.out.println("Enter register");
        int register = Integer.parseInt(scanner.nextLine());

        showRouteDay(idplace, numdriver, register);
    }

    private void showRouteDay(int idplace, int numdriver, int register) throws SQLException {
        String sql = "SELECT day_of_week FROM BDP WHERE idplace = ? AND numdriver = ? AND register = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, idplace);
        preparedStatement.setInt(2, numdriver);
        preparedStatement.setInt(3, register);

        ResultSet rs = preparedStatement.executeQuery(sql);

        if (rs.next()) {
            System.out.println(rs.getString(1));
        } else {
            System.out.println("No route found");
        }
    }

    private String dataInsertion(String table, String columns, String values) {
        return "INSERT INTO " + table + "(" + columns + ") VALUES(" + values + ")";
    }
}
