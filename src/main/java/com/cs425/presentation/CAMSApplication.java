package com.cs425.presentation;

import java.util.Scanner;

import com.cs425.services.CustomerAccountService;

public class CAMSApplication {

    private final CustomerAccountService service;

    public CAMSApplication() {
        service = CustomerAccountService.getInstance();
    }

    public void start() {

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {

            printMenu();

            System.out.print("Select option: ");
            String choice = scanner.nextLine();

            switch (choice) {

                case "1":
                    displayAllAccounts();
                    break;

                case "2":
                    displayPlatinumAccounts();
                    break;

                case "3":
                    displayLiquidityPosition();
                    break;

                case "0":
                    running = false;
                    System.out.println("Goodbye.");
                    break;

                default:
                    System.out.println("Invalid option.");
            }

            System.out.println();
        }

        scanner.close();
    }

    private void printMenu() {

        System.out.println("========================================");
        System.out.println(" CS425 Banking Corporation - CAMS");
        System.out.println("========================================");
        System.out.println("1. Display All Accounts (JSON)");
        System.out.println("2. Display Platinum Accounts (JSON)");
        System.out.println("3. Display Liquidity Position");
        System.out.println("0. Exit");
        System.out.println("========================================");
    }

    private void displayAllAccounts() {

        service.printAccountsAsJSON(
                service.getAllAccountsSortedByBalanceDesc());

        System.out.printf(
                "%nLiquidity Position: $%,.2f%n",
                service.getLiquidityPosition());
    }

    private void displayPlatinumAccounts() {
        service.printPlatinumAccountsAsJSON();
    }

    private void displayLiquidityPosition() {

        System.out.printf(
                "Liquidity Position: $%,.2f%n",
                service.getLiquidityPosition());
    }
}