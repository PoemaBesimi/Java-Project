package com.bankapp;

import java.util.Scanner;

public class Main {
    private static Bank bank;

    public static void main(String[] args) {
        DatabaseManager.initializeDatabase();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Create Bank");
            System.out.println("2. Create Account");
            System.out.println("3. Deposit Money");
            System.out.println("4. Withdraw Money");
            System.out.println("5. Transfer Money");
            System.out.println("6. Check Balance");
            System.out.println("7. List Accounts");
            System.out.println("8. List Transactions");
            System.out.println("9. Check Total Fees");
            System.out.println("10. Check Total Transfers");
            System.out.println("11. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (choice) {
                    case 1:
                        createBank(scanner);
                        break;
                    case 2:
                        createAccount(scanner);
                        break;
                    case 3:
                        depositMoney(scanner);
                        break;
                    case 4:
                        withdrawMoney(scanner);
                        break;
                    case 5:
                        transferMoney(scanner);
                        break;
                    case 6:
                        checkBalance(scanner);
                        break;
                    case 7:
                        listAccounts();
                        break;
                    case 8:
                        listTransactions(scanner);
                        break;
                    case 9:
                        checkTotalFees();
                        break;
                    case 10:
                        checkTotalTransfers();
                        break;
                    case 11:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void createBank(Scanner scanner) {
       
        System.out.println("Enter bank name:");
        String bankName = scanner.nextLine();

        System.out.println("Enter transaction flat fee amount:");
        double flatFee = scanner.nextDouble();

        System.out.println("Enter transaction percent fee value:");
        double percentFee = scanner.nextDouble();

        bank = new Bank(bankName, flatFee, percentFee);
        System.out.println("Bank created successfully.");
    }

    private static void createAccount(Scanner scanner) {
        if (bank == null) {
            System.out.println("No bank exists. Please create a bank first.");
            return;
        }
          System.out.println("Enter account ID:");
        String accountId = scanner.nextLine();

        System.out.println("Enter user name:");
        String userName = scanner.nextLine();

        System.out.println("Enter initial balance:");
        double balance = scanner.nextDouble();
        scanner.nextLine();

        Account account = new Account(accountId, userName, balance);
        bank.addAccount(account);
        System.out.println("Account created successfully.");
    }

    private static void depositMoney(Scanner scanner) {
        if (bank == null) {
            System.out.println("No bank exists. Please create a bank first.");
            return;
        }
        System.out.println("Enter account ID:");
        String accountId = scanner.nextLine();

        System.out.println("Enter deposit amount:");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        Account account = bank.getAccount(accountId);
        account.deposit(amount);
        System.out.println("Deposit successful.");
    }

    private static void withdrawMoney(Scanner scanner) {
        if (bank == null) {
            System.out.println("No bank exists. Please create a bank first.");
            return;
        }
        System.out.println("Enter account ID:");
        String accountId = scanner.nextLine();

        System.out.println("Enter withdrawal amount:");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        Account account = bank.getAccount(accountId);
        account.withdraw(amount);
        System.out.println("Withdrawal successful.");
    }

    private static void transferMoney(Scanner scanner) {
        if (bank == null) {
            System.out.println("No bank exists. Please create a bank first.");
            return;
        }
        System.out.println("Enter originating account ID:");
        String fromAccountId = scanner.nextLine();

        System.out.println("Enter resulting account ID:");
        String toAccountId = scanner.nextLine();

        System.out.println("Enter transfer amount:");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("Is this a flat fee transaction? (yes/no):");
        String isFlatFee = scanner.nextLine();

        bank.transfer(fromAccountId, toAccountId, amount, isFlatFee.equalsIgnoreCase("yes"));
        System.out.println("Transfer successful.");
    }

    private static void checkBalance(Scanner scanner) {
        if (bank == null) {
            System.out.println("No bank exists. Please create a bank first.");
            return;
        }
        System.out.println("Enter account ID:");
        String accountId = scanner.nextLine();

        Account account = bank.getAccount(accountId);
        System.out.println("Account Balance: " + account.getFormattedBalance());
    }

    private static void listAccounts() {
        for (Account account : bank.getAccounts()) {
            System.out.println("Account ID: " + account.getAccountId() + ", User Name: " + account.getUserName() + ", Balance: " + account.getFormattedBalance());
        }
    }

    private static void listTransactions(Scanner scanner) {
        if (bank == null) {
            System.out.println("No bank exists. Please create a bank first.");
            return;
        }
        System.out.println("Enter account ID:");
        String accountId = scanner.nextLine();

        Account account = bank.getAccount(accountId);
        for (Transaction transaction : account.getTransactions()) {
            System.out.println("Transaction: " + transaction.getReason() + ", Amount: " + transaction.getFormattedAmount() + ", From: " + transaction.getOriginatingAccountId() + ", To: " + transaction.getResultingAccountId());
        }
    }

    private static void checkTotalFees() {
        if (bank == null) {
            System.out.println("No bank exists. Please create a bank first.");
            return;
        }
        System.out.println("Total Transaction Fees: " + bank.getFormattedTotalTransactionFeeAmount());
    }

    private static void checkTotalTransfers() {
        if (bank == null) {
            System.out.println("No bank exists. Please create a bank first.");
            return;
        }
        System.out.println("Total Transfer Amount: " + bank.getFormattedTotalTransferAmount());
    }
}
