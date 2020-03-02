package code;/*
 * TicketMachine - class to simulate a simple transportation ticket machine
 */

import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.basic.BasicSplitPaneUI;

public class TicketMachine {
    // data
    private int cost;                // cost of one regular ticket
    private int ticketCount;
    private int inserted = 0;            //how much money was inserted
    private int balance = 0;
    private int totalInserted = 0;      //how much money inserted in total
    private int totalTicketsPrinted = 0; //how many tickets were printed in total
    private int totalTicketsPrintedFirstClass = 0; //how many tickets were printed for first class
    private int totalNormalTicketsPrinted = 0;     //how many tickets were printed for normal class

    // constructors
    // -- default constructor
    public TicketMachine() {
        this(0);
    }

    // -- one-arg ticket cost constructor
    public TicketMachine(int cost) {
        this.cost = cost;
    }

    public int getTotalTicketsPrinted() {
        return totalTicketsPrinted;
    }

    public void setTotalTicketsPrinted(int totalTicketsPrinted) {
        this.totalTicketsPrinted += totalTicketsPrinted;
    }

    public int getCostFirstClass() {
        return (cost * 2);
    }

    // other methods besides constructors
    // -- getter for cost
    public int getCost() {
        return cost;
    }

    public int insertMoney(int amount) {
        if (amount < 1) {
            System.out.println("Please enter a positive amount of money");
            return -1;
        } else {
            this.inserted += amount;

            int balance = getBalance();
            setBalance(balance + amount);
            setTotalInserted(amount);
            return 0;
        }
    }

    public int getInserted() {
        return inserted;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String printFirstClass() {

        if (getBalance() >= getCostFirstClass()) {
            int ticketNum = (int) (Math.random() * 100);
            String validTicket = "First Class Ticket " + ticketNum + " to Fort Snelling Station with hoity-toity seats and a free giant chocolate chip cookie";
            System.out.println(validTicket);
            setBalance(getBalance() - getCostFirstClass());
            setTotalTicketsPrinted(1);
            setTotalTicketsPrintedFirstClass(1);

            return validTicket;
        } else {
            int amountEntered = getCostFirstClass() - getInserted();
            String invalidTicket = "Insufficient funds, please insert " + amountEntered + " more.";
            System.out.println(invalidTicket);
            return invalidTicket;
        }

    }

    public String printTicket() {

        if (getBalance() >= getCost()) {
            int ticketNum = (int) (Math.random() * 100);
            String validTicket = "Ticket " + ticketNum + " to Fort Snelling Station";
            System.out.println(validTicket);
            int newBalance = getBalance() - getCost();
            setBalance(newBalance);
            setTotalTicketsPrinted(1);
            setTotalNormalTicketsPrinted(1);

            return validTicket;
        } else {
            int amountEntered = getCost() - getInserted();
            String invalidTicket = "Insufficient funds, please insert " + amountEntered + " more.";
            System.out.println(invalidTicket);
            return invalidTicket;
        }

    }

    public String finalReport() {
        String finalReport = "Total tickets sold " + getTotalTicketsPrinted() + ", total amount collected " + getTotalInserted() + ".";
        System.out.println(finalReport);
        return finalReport;
    }

    public String finalReportItemized() {
        String finalReport = "Normal tickets sold " + getTotalNormalTicketsPrinted() + ", first class tickets sold "
                + getTotalTicketsPrintedFirstClass() + ", total amount collected " + getTotalInserted() + ", first class total, 200, normal class total 100.";
        System.out.println(finalReport);

        return finalReport;
    }

    public int getRefund() {
        int currentBalance = getBalance();
        setBalance(0);
        return currentBalance;
    }

    public int getTotalInserted() {
        return totalInserted;
    }

    public void setTotalInserted(int totalInserted) {
        this.totalInserted += totalInserted;
    }

    public int getTotalNormalTicketsPrinted() {
        return totalNormalTicketsPrinted;
    }

    public void setTotalNormalTicketsPrinted(int totalNormalTicketsPrinted) {
        this.totalNormalTicketsPrinted += totalNormalTicketsPrinted;
    }

    public int getTotalTicketsPrintedFirstClass() {
        return totalTicketsPrintedFirstClass;
    }

    public void setTotalTicketsPrintedFirstClass(int totalTicketsPrintedFirstClass) {
        this.totalTicketsPrintedFirstClass += totalTicketsPrintedFirstClass;
    }

}    // end - class TicketMachine
