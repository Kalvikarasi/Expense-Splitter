import java.util.Scanner;

public class Bill {
    private double totalExpense;
    private int numberOfPersons;
    private double[] splitAmounts;

    public Bill(double totalExpense, int numberOfPersons) {
        this.totalExpense = totalExpense;
        this.numberOfPersons = numberOfPersons;
        this.splitAmounts = new double[numberOfPersons];
    }

    public void splitEvenly() {
        double splitAmount = totalExpense / numberOfPersons;
        for (int i = 0; i < numberOfPersons; i++) {
            splitAmounts[i] = splitAmount;
        }
        printSplits();
    }

    public void splitByMoney(double[] amounts) {
        if (amounts.length != numberOfPersons) {
            System.out.println("Error: Number of amounts provided does not match the number of persons.");
            return;
        }
        double sum = 0;
        for (double amount : amounts) {
            sum += amount;
        }
        if (sum != totalExpense) {
            System.out.println("Error: Sum of amounts provided does not equal the total expense.");
            return;
        }
        for (int i = 0; i < numberOfPersons; i++) {
            splitAmounts[i] = amounts[i];
        }
        printSplits();
    }

    public void splitByShares(int[] shares) {
        if (shares.length != numberOfPersons) {
            System.out.println("Error: Number of shares provided does not match the number of persons.");
            return;
        }
        int totalShares = 0;
        for (int share : shares) {
            totalShares += share;
        }
        for (int i = 0; i < numberOfPersons; i++) {
            splitAmounts[i] = (totalExpense * shares[i]) / totalShares;
        }
        printSplits();
    }

    public void splitByPercentage(double[] percentages) {
        if (percentages.length != numberOfPersons) {
            System.out.println("Error: Number of percentages provided does not match the number of persons.");
            return;
        }
        double totalPercentage = 0;
        for (double percentage : percentages) {
            totalPercentage += percentage;
        }
        if (totalPercentage != 100) {
            System.out.println("Error: Total of percentages provided does not equal 100%.");
            return;
        }
        for (int i = 0; i < numberOfPersons; i++) {
            splitAmounts[i] = (totalExpense * percentages[i]) / 100;
        }
        printSplits();
    }

    private void printSplits() {
        System.out.println("Expense splits:");
        for (int i = 0; i < numberOfPersons; i++) {
            System.out.printf("Person %d: %.2f%n", (i + 1), splitAmounts[i]);
        }
    }
    

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter total expense: ");
        double totalExpense = scanner.nextDouble();

        System.out.print("Enter number of persons: ");
        int numberOfPersons = scanner.nextInt();

        Bill bill = new Bill(totalExpense, numberOfPersons);

        System.out.println("Select split method:");
        System.out.println("1. Split evenly");
        System.out.println("2. Split by money");
        System.out.println("3. Split by shares");
        System.out.println("4. Split by percentage");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                bill.splitEvenly();
                break;
            case 2:
                double[] amounts = new double[numberOfPersons];
                System.out.println("Enter the amounts for each person:");
                for (int i = 0; i < numberOfPersons; i++) {
                    System.out.print("Person " + (i + 1) + ": ");
                    amounts[i] = scanner.nextDouble();
                }
                bill.splitByMoney(amounts);
                break;
            case 3:
                int[] shares = new int[numberOfPersons];
                System.out.println("Enter the shares for each person:");
                for (int i = 0; i < numberOfPersons; i++) {
                    System.out.print("Person " + (i + 1) + ": ");
                    shares[i] = scanner.nextInt();
                }
                bill.splitByShares(shares);
                break;
            case 4:
                double[] percentages = new double[numberOfPersons];
                System.out.println("Enter the percentages for each person:");
                for (int i = 0; i < numberOfPersons; i++) {
                    System.out.print("Person " + (i + 1) + ": ");
                    percentages[i] = scanner.nextDouble();
                }
                bill.splitByPercentage(percentages);
                break;
            default:
                System.out.println("Invalid choice");
        }

        scanner.close();
    }
}
