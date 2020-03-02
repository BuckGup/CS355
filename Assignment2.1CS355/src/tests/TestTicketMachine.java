package tests;//import code.TicketMachine;

import static org.junit.Assert.*;

import code.TicketMachine;
import org.junit.Test;

import java.util.PrimitiveIterator;

/*
 * assert methods
 *  assertEquals (message, expected, actual)
 *  assertEquals (expected, actual, delta)
 *  assertEquals (expected, actual)
 *  // many more versions of assertEquals(...)
 *  assertTrue (condition)
 *  assertFalse (condition)
 *  assertNotNull (object-variable)
 *  assertNull (object-variable)
 *  assertSame (object1, object2)
 *  assertNotSame (object1, object2)
 *  fail (message)
 */

public class TestTicketMachine {

    @Test
    public void testTicketMachine() {
        TicketMachine tm = new TicketMachine(100);
        assertNotNull(tm);
    }

    @Test
    public void testGetCost() {
        TicketMachine tm = new TicketMachine(100);
        assertEquals(100, tm.getCost());
    }

    //tests the total amount collected
    @Test
    public void testMoneyCounter() {
        TicketMachine tm = new TicketMachine(100);
        tm.insertMoney(30);
        assertEquals(30, tm.getInserted());
    }

    //tests count for the current ticket
    @Test
    public void testCurrentMoneyCounter() {
        TicketMachine tm = new TicketMachine(100);
        tm.insertMoney(30);
        tm.insertMoney(50);
        tm.insertMoney(20);
        assertEquals(100, tm.getInserted());
    }

    //tests just printing functionality
    @Test
    public void testPrintTicket() {
        TicketMachine tm = new TicketMachine(100);
        tm.insertMoney(101);
        assertNotNull(tm.printTicket());
    }

    @Test
    public void testMoneyCollected() {
        TicketMachine tm = new TicketMachine(100);
        tm.insertMoney(30);
        tm.insertMoney(50);
        tm.insertMoney(20);
        tm.printTicket();
        tm.insertMoney(25);
        assertEquals(125, tm.getTotalInserted());
    }

    //tests refunding before the ticket printed
    @Test
    public void testRefundBefore() {
        TicketMachine tm = new TicketMachine(100);
        tm.insertMoney(30);
        tm.insertMoney(50);
        tm.insertMoney(20);
        assertEquals(100, tm.getRefund());
        assertEquals(0, tm.getRefund());
        assertEquals(0, tm.getBalance());
    }

    @Test
    public void testRefundAfter() {
        TicketMachine tm = new TicketMachine(100);
        tm.insertMoney(30);
        tm.insertMoney(50);
        tm.insertMoney(50);
        tm.printTicket();
        assertEquals(30, tm.getRefund());
        assertEquals(0, tm.getRefund());
        assertEquals(0, tm.getBalance());
    }

    @Test
    public void testMoneyChecker() {
        TicketMachine tm = new TicketMachine(100);
        tm.insertMoney(30);
        tm.insertMoney(50);
        assertEquals("Insufficient fund error", "Insufficient funds, please insert 20 more.", tm.printTicket());
    }

    @Test
    public void testNegativeChecker() {
        TicketMachine tm = new TicketMachine(100);
        assertEquals(-1, tm.insertMoney(-900));
    }

    @Test
    public void testGetTicketCount() {
        TicketMachine tm = new TicketMachine(100);
        tm.insertMoney(30);
        tm.insertMoney(50);
        tm.insertMoney(20);
        tm.printTicket();
        tm.insertMoney(30);
        tm.insertMoney(50);
        tm.insertMoney(20);
        tm.insertMoney(30);
        tm.insertMoney(50);
        tm.insertMoney(20);
        tm.printFirstClass();
        tm.insertMoney(30);
        tm.insertMoney(50);
        tm.insertMoney(20);
        assertEquals("Final Report", "Total tickets sold 2, total amount collected 400.", tm.finalReport());
    }

    @Test
    public void testFirstClass() {
        TicketMachine tm = new TicketMachine(100);
        tm.insertMoney(30);
        tm.insertMoney(50);
        tm.insertMoney(20);
        tm.printTicket();
        tm.insertMoney(30);
        tm.insertMoney(50);
        tm.insertMoney(20);
        tm.insertMoney(30);
        tm.insertMoney(50);
        tm.insertMoney(20);
        tm.insertMoney(30);
        tm.insertMoney(50);
        tm.insertMoney(50);
        tm.insertMoney(50);
        tm.insertMoney(20);
        tm.printFirstClass();

        assertNotNull(tm.printFirstClass());
        assertEquals("Final report itemized", "Normal tickets sold 1, first class tickets sold 2," +
                " total amount collected 500, first class total, " + "200, " + "normal class total " + "100.", tm.finalReportItemized());
    }

}
