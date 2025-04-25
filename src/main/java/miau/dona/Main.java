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

            //aucorsaDB.mostrarDatosTabla("Driver");
            //aucorsaDB.insertDriver();
            //aucorsaDB.insertBus();
            //aucorsaDB.insertPlace();
            //aucorsaDB.insertRoute();
            //TODO aucorsaDB.updateRoutePerDay();
            //TODO aucorsaDB.registerBusCheckingDriverData();
            //aucorsaDB.askUserDeleteRoute();
            //aucorsaDB.askDriverData();
            //aucorsaDB.whatDayIsThisRouteToThisCity();
            showGeneralMenu();

            con.close();
            statement.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void showGeneralMenu() {
        String option;
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("""
                    This is the Aucorsa application menu. What do you want to do?
                    1. Insert information
                    2. Update information
                    3. Delete information
                    4. Show information
                    0. Leave this menu
                    Select a number:""");

            option = scanner.nextLine();

            switch (option) {
                case "0" -> {
                    System.out.println("leaving");
                }
                case "1" -> {
                    showMenu1(scanner);
                }
                case "2" -> {
                    showMenu2(scanner);
                }

                case "3" -> {
                    showMenu3(scanner);
                }

                case "4" -> {
                    showMenu4(scanner);
                }

                default -> {
                    System.out.println("Entry not valid");
                }
            }
        } while (option.equals("0"));
    }
    
    private static void showMenu1(Scanner scanner) {
        System.out.println("""
                ------- INSERT INFORMATION MENU
                1. Insert into Bus
                2. Insert into Driver
                3. Insert into Place
                4. Insert into Route (BDP)
                0. Cancel and go back
                """);
        
        String option = scanner.nextLine();
        actionMenu1(option, scanner);
    }

    private static void actionMenu1(String option, Scanner scanner) {
        switch (option) {
            case "0" -> {
                System.out.println("leaving");
            }
            case "1" -> {
                System.out.println("Insert into bus");
            }
            case "2" -> {
                System.out.println("Insert into driver");
            }
            case "3" -> {
                System.out.println("Insert into place");
            }
            case "4" -> {
                System.out.println("Insert into route");
            }
            default -> {
                System.out.println("Entry not valid");
                System.out.println("Select another option: ");
                option = scanner.nextLine();
                actionMenu1(option, scanner);
            }
        }
    }

    private static void showMenu2(Scanner scanner) {
        System.out.println("""
                ------- UPDATE INFORMATION MENU
                1. Update Bus
                2. Update Driver
                3. Update Place
                4. Update Route (BDP)
                0. Cancel and go back
                """);

        String option = scanner.nextLine();
        actionMenu2(option, scanner);
    }
    
    private static void actionMenu2(String option, Scanner scanner) {
        switch (option) {
            case "0" -> {
                
            }
            case "1" -> {
                
            }
            case "2" -> {
                
            }
            case "3" -> {
                
            }
            case "4" -> {
                
            }
            default -> {
                System.out.println("Entry not valid");
            }
        }
    }
    
    private static void showMenu3(Scanner scanner) {
        System.out.println("""
                ------- DELETE INFORMATION MENU
                1. Delete Bus
                2. Delete Driver
                3. Delete Place
                4. Delete Route (BDP)
                0. Cancel and go back
                """);

        String option = scanner.nextLine();
        actionMenu2(option, scanner);
    }
    
    private static void actionMenu3(String option, Scanner scanner) {
        switch (option) {
            case "0" -> {

            }
            case "1" -> {

            }
            case "2" -> {

            }
            case "3" -> {

            }
            case "4" -> {

            }
            default -> {
                System.out.println("Entry not valid");
            }
        }
    }
    
    private static void showMenu4(Scanner scanner) {
        
    }
    private static void actionMenu4(String option, Scanner scanner) {
        switch (option) {
            case "0" -> {

            }
            case "1" -> {

            }
            case "2" -> {

            }
            case "3" -> {

            }
            case "4" -> {

            }
            default -> {
                System.out.println("Entry not valid");
            }
        }
    }
}