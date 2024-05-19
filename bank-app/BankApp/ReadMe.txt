# Bank Application

This is a simple bank application implemented in Java, which uses Maven 396 The application allows users to create a bank, create and manage accounts, perform transactions, and check various statistics related to the bank's operations, their transactions and withdrawals/deposits

## Features

- Create a bank with a name, flat fee, and percent fee
- Create accounts with an ID, user name, and initial balance
- Deposit money into an account
- Withdraw money from an account
- Transfer money between accounts with either a flat fee or a percentage fee
- Check the balance of an account
- List all accounts in the bank
- List all transactions for a specific account
- Check the total transaction fees collected by the bank
- Check the total amount transferred by the bank

## Prerequisites

- Java Development Kit (JDK) 8 or higher
- Project uses Java version 21 (openjdk 2102)
- Maven 396 or higher


## Getting Started

1 **Clone the repository:**
   ```sh
   git clone https://githubcom/yourusername/bankappgit
  cd bank-app 
  cd BankApp

2 **Build the project using Maven:**

mvn clean install

mvn package

3 **Run the application:**

mvn exec:java -DexecmainClass="combankappMain"

or by Snapshot:  java -jar target/BankApp-10-SNAPSHOT-jar-with-dependenciesjar


### Instructions to Run the Application in Visual Studio Code

1 **Open the Project:**
   - Launch Visual Studio Code
   - Open the cloned project directory

2 **Install Extensions:**
   - Ensure you have the following extensions installed:
     - Java Extension Pack
     - Maven for Java

3 **Run the Application:**
   - Open the terminal in Visual Studio Code (View > Terminal)
   - Run the Maven commands to build and start the application:
     ```sh
     mvn clean install
     mvn exec:java -DexecmainClass="combankappMain"
     ```

4 **Configure Launch Configuration (Optional):**
   - You can create a launch configuration in `vscode/launchjson` to run the main class directly from the Run and Debug panel

This `READMEmd` file provides comprehensive instructions on how to set up, build, and run the bank application using Maven and Visual Studio Code, including the use of an in-memory H2 database and adherence to OOP principles

**Usage**
When you run the application, you will see a menu with several options You can perform the following actions:

-Create Bank:

Enter the bank name
Enter the transaction flat fee amount
Enter the transaction percent fee value

-Create Account:

Enter the account ID
Enter the user name
Enter the initial balance

-Deposit Money:

Enter the account ID
Enter the deposit amount

-Withdraw Money:

Enter the account ID
Enter the withdrawal amount

-Transfer Money:

Enter the originating account ID
Enter the resulting account ID
Enter the transfer amount
Specify if the transaction uses a flat fee (yes or no)

-Check Balance:

Enter the account ID to check the balance

-List Accounts:

Displays a list of all accounts in the bank

-List Transactions:

Enter the account ID to list all transactions for that account

-Check Total Fees:

Displays the total transaction fees collected by the bank

-Check Total Transfers:

Displays the total amount transferred by the bank

-Exit:

Exit the application

-Error Handling
The application includes error handling to manage cases where the bank has not been created, or account operations are attempted on non-existent accounts
Proper messages are displayed to guide the user in case of invalid input or actions

**Notes**
The application uses BigDecimal for monetary calculations to maintain precision and avoid floating-point arithmetic issues
The application uses an in-memory H2 database to store account and transaction data
Ensure you have the H2 database dependency in your pomxml


**Object-Oriented Programming**
The application follows object-oriented programming principles
Key classes include Bank, Account, and Transaction
Each class has specific responsibilities, encapsulating the related data and behaviors
Running the Application in Visual Studio Code
Open the project in Visual Studio Code
Ensure you have the Java and Maven extensions installed
Use the built-in terminal to run the Maven commands listed above

License
This project is licensed under the MIT License - see the LICENSE file for details

Author
Poema Besimi