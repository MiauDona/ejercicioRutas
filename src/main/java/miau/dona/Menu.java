package miau.dona;

import java.sql.SQLException;
import java.util.Scanner;

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

public class Menu {
    static Scanner scanner = new Scanner(System.in);

    static void showGeneralMenu() throws SQLException {
        String option;

        do {
            System.out.println("""
                    This is the Aucorsa application menu. What do you want to do?
                    1. Insert information
                    2. Update route
                    3. Delete route
                    4. Show information
                    0. Leave this menu
                    Select a number:""");

            option = scanner.nextLine();

            switch (option) {
                case "0" -> {
                    System.out.println("leaving");
                }
                case "1" -> {
                    showInsertMenu();
                }
                case "2" -> {
                    showUpdateRouteMenu();
                }

                case "3" -> {
                    showDeleteRouteMenu();
                }

                case "4" -> {
                    showShowInformationMenu();
                }

                default -> {
                    System.out.println("Entry not valid");
                }
            }
        } while (!option.equals("0"));
    }

    private static void showInsertMenu() throws SQLException {
        System.out.println("""
                ------- INSERT INFORMATION MENU
                1. Insert into Bus
                2. Insert into Driver
                3. Insert into Place
                4. Insert into Route (BDP)
                0. Cancel and go back
                """);

        String option = scanner.nextLine();
        insertMenu(option);
    }

    private static void insertMenu(String option) throws SQLException {
        switch (option) {
            case "0" -> {
                System.out.println("leaving");
            }
            case "1" -> {
                MenuActions.insertIntoBus();
            }
            case "2" -> {
                MenuActions.insertIntoDriver();
            }
            case "3" -> {
                MenuActions.insertIntoPlace();
            }
            case "4" -> {
                MenuActions.insertIntoRoute();
            }
            default -> {
                System.out.println("Entry not valid");
                System.out.println("Select another option: ");
                option = scanner.nextLine();
                insertMenu(option);
            }
        }
    }

    private static void showUpdateRouteMenu() throws SQLException {
        System.out.println("""
                ------- UPDATE INFORMATION MENU
                1. Update Route (BDP)
                0. Cancel and go back
                """);

        String option = scanner.nextLine();
        updateRouteMenu(option);
    }

    private static void updateRouteMenu(String option) throws SQLException {
        switch (option) {
            case "0" -> {
                System.out.println("leaving");
            }
            case "1" -> {
                MenuActions.updateRoute();
            }
            default -> {
                System.out.println("Entry not valid");
            }
        }
    }

    private static void showDeleteRouteMenu() throws SQLException {
        System.out.println("""
                ------- DELETE INFORMATION MENU
                1. Delete Route (BDP)
                0. Cancel and go back
                """);

        String option = scanner.nextLine();
        deleteRouteMenu(option);
    }

    private static void deleteRouteMenu(String option) throws SQLException {
        switch (option) {
            case "0" -> {
                System.out.println("leaving");
            }
            case "1" -> {
                MenuActions.deleteRoute();
            }
            default -> {
                System.out.println("Entry not valid");
            }
        }
    }

    private static void showShowInformationMenu() throws SQLException {
        System.out.println("""
                ------- SHOW INFORMATION MENU
                1. Bus
                2. Driver
                3. Place
                4. Route
                5. Driver data (by numdriver)
                6. Driver data (by register bus number)
                7. Route depending on which city it is
                0. Cancel and go back
                """);

        String option = scanner.nextLine();
        showInformationMenu(option);
    }
    private static void showInformationMenu(String option) throws SQLException {
        switch (option) {
            case "0" -> {
                System.out.println("Leaving");
            }
            case "1" -> {
                MenuActions.showTable("Bus");
            }
            case "2" -> {
                MenuActions.showTable("Driver");
            }
            case "3" -> {
                MenuActions.showTable("Place");
            }
            case "4" -> {
                MenuActions.showTable("BDP");
            }
            case "5" -> {
                MenuActions.showDriverDataNumdriver();
            }
            case "6" -> {
                MenuActions.registerBusCheckingDriverData();
            }
            case "7" -> {
                MenuActions.whatDayIsThisRouteToThisCity();
            }
            default -> {
                System.out.println("Entry not valid");
            }
        }
    }
}
