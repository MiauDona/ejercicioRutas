package miau.dona;

import java.sql.SQLException;
import java.sql.Statement;

public class CreateDBSchema {
    public static void createTables(Statement statement) throws SQLException {
        tableBus(statement);
        tableDriver(statement);
        tablePlace(statement);
        tableBusDriverPlace(statement);
    }

    private static void tableBus(Statement statement) throws SQLException {
        String table = """
                CREATE TABLE IF NOT EXISTS Bus (
                    register TEXT PRIMARY KEY,
                    type TEXT,
                    license TEXT
                );""";

        statement.executeUpdate(table);
    }

    private static void tableDriver(Statement statement) throws SQLException {
        String table = """
                CREATE TABLE IF NOT EXISTS Driver (
                    numdriver INTEGER PRIMARY KEY,
                    name TEXT,
                    surname TEXT
                );""";

        statement.executeUpdate(table);
    }

    private static void tablePlace(Statement statement) throws SQLException {
        String table = """
                CREATE TABLE IF NOT EXISTS Place (
                    idplace INTEGER PRIMARY KEY,
                    cp TEXT,
                    city TEXT,
                    site TEXT
                );""";

        statement.executeUpdate(table);
    }

    private static void tableBusDriverPlace(Statement statement) throws SQLException {
        String table = """
                CREATE TABLE IF NOT EXISTS BDP (
                    register TEXT,
                    numdriver INTEGER,
                    idplace INTEGER,
                    day_of_week TEXT,
                    PRIMARY KEY (register, numdriver, idplace, day_of_week),
                    FOREIGN KEY (register) REFERENCES Bus(register),
                    FOREIGN KEY (numdriver) REFERENCES Driver(numdriver),
                    FOREIGN KEY (idplace) REFERENCES Place(idplace)
                );
                """;

        statement.executeUpdate(table);
    }
}
