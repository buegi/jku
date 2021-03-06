package swe1.ws17.ue04;

import swe1.input.Input;

public class Ticketing {
    public static void main(String[] args) {
        int menuId = 0;
        int tickets = 10;
        double sellPrice = 9.9;
        double earned = 0;
        int buyTickets = 0;
        do {
            printMenu();
            System.out.print("Enter Menu Function: ");
            menuId = Input.readInt();
            switch (menuId) {
                case 1:
                    if (tickets <= 0) {
                        System.out.println("No tickets left for sale");
                        break;
                    }
                    do {
                        System.out.println("Enter amount of tickets to be bought: ");
                        buyTickets = Input.readInt();
                        if (buyTickets > 0) {
                            if (tickets - buyTickets >= 0) {
                                double price = Math.round(sellPrice * buyTickets * 100) / 100d;
                                earned += price;
                                System.out.printf("Sell Price: %.2f\n", price);
                                tickets -= buyTickets;
                            } else {
                                System.out.println("Not enough tickets left");
                            }
                        }
                    } while (buyTickets > 0 && tickets > 0);
                    break;
                case 2:
                    printTicketsLeft(tickets);
                    break;
                case 3:
                    printShowBalance(earned);
                    break;
                case 0:
                    printOnExit(tickets, earned);
                    break;
                default:
                    printUnknownFunctionHelpText();
                    break;
            }
        } while (menuId != 0);
    }

    private static void printMenu() {
        System.out.println("Main Menu: ");
        System.out.println("  1) Sell Tickets");
        System.out.println("  2) Show Tickets Left");
        System.out.println("  3) Show Balance");
        System.out.println("  0) Exit");
    }

    private static void printUnknownFunctionHelpText() {
        System.out.println("Unknown Menu Function");
    }

    private static void printOnExit(int tickets, double earned) {
        System.out.printf("%d Tickets left\n", tickets);
        System.out.printf("The Cash Register contains %.2f �\n", Math.round(earned * 100) / 100d);
        System.out.println("Bye!");
    }

    private static void printShowBalance(double earned) {
        System.out.printf("The Cash Register contains %.2f �\n", Math.round(earned * 100) / 100d);
    }

    private static void printTicketsLeft(int tickets) {
        System.out.printf("%d Tickets left\n", tickets);
    }
}