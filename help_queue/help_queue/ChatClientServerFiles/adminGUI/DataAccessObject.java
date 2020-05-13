package adminGUI;

import java.sql.*;

public class DataAccessObject {

    private Connection daoConn = null;                // JDBC connection
    private ResultSet daoRset = null;                // result set for queries
    private int returnValue;                        // return value for all other commands
    private boolean transactionStarted = false;        // flag for whether currently in a transaction
    private boolean exceptionOccurred = false;        // flag for whether a problem occurred within a transaction

    // --- connect() - connect to the Oracle database
    public void connect() {
        // --- set the username and password
        String user = "STRECKSH8883";                    // NOTE: you must set your UWEC Oracle username here
        String pass = "AMUJPG9P";                    // NOTE: you must set your UWEC Oracle pw here

        // --- 1) get the Class object for the driver
        try {
            Class.forName("oracle.jdbc.OracleDriver");
        } catch (ClassNotFoundException e) {
            System.err.println("Could not get class object for Driver");
        }

        // --- 2) connect to database
        try {
            daoConn = DriverManager.getConnection(
                    "jdbc:oracle:thin:@alfred.cs.uwec.edu:1521:csdev", user, pass);
            transactionStarted = false;
            exceptionOccurred = false;
        } catch (SQLException sqle) {
            System.err.println("DAO, connect() - Could not make connection to database");
            System.err.println(sqle.getMessage());
        }
    }    // end - method connect

    // --- startTransaction - signal the beginning of the transaction
    public void startTransaction() {
        try {
            if (!daoConn.getAutoCommit()) {                    // if autoCommit setting is off, we control transactions
                transactionStarted = true;
                exceptionOccurred = false;
            } else {                                        // if autoCommit setting is on, each statement is its own transaction, we don't control
                System.err.println("DAO, startTransaction() - Cannot start a transaction if autoCommit is set");
            }
        } catch (SQLException sqle) {
            System.err.println("DAO, startTransaction() - Could not start transaction due to failure to get autoCommit status");
            System.err.println(sqle.getMessage());
        }
    }

    // --- executeSQLQuery() - execute an SQL query
    public void executeSQLQuery(String sqlQuery) {
        // --- 3a) execute SQL query
        Statement stmt = null;        // SQL statement object
        daoRset = null;                // initialize result set

        try {
            stmt = daoConn.createStatement();
            daoRset = stmt.executeQuery(sqlQuery);
        } catch (SQLException sqle) {
            System.err.println("DAO, executeSQLQuery() - SQL Exception, cannot execute SQL statement: >" + sqlQuery + "<");
            System.err.println(sqle.getMessage());
            exceptionOccurred = true;
        } catch (Exception e) {
            System.err.println("DAO, executeSQLQuery() - General Exception, cannot execute SQL statement: >" + sqlQuery + "<");
            System.err.println(e.getMessage());
            exceptionOccurred = true;
        }
    }    // end - method executeSQLQuery

    // --- executeSQLNonQuery() - execute an SQL command that is not a query
    public int executeSQLNonQuery(String sqlCommand) {
        // --- 3b) execute SQL non-query command
        Statement stmt = null;        // SQL statement object
        returnValue = -1;            // initialize return value
        try {
            stmt = daoConn.createStatement();
            returnValue = stmt.executeUpdate(sqlCommand);
        } catch (SQLException sqle) {
            System.err.println("DAO, executeSQLNonQuery - SQL Exception, cannot execute SQL command: >" + sqlCommand + "<");
            System.err.println("Return value: " + returnValue);
            System.err.println(sqle.getMessage());
            exceptionOccurred = true;
        } catch (Exception e) {
            System.err.println("DAO, executeSQLNonQuery - General Exception, cannot execute SQL command: >" + sqlCommand + "<");
            System.err.println("Return value: " + returnValue);
            System.err.println(e.getMessage());
            exceptionOccurred = true;
        }
        return returnValue;
    }    // end - method executeSQLNonQuery

    // --- processResultSet() - process the result set
    public String processResultSet() {
        // --- 4) process result set, only applicable if executing an SQL SELECT statement
        ResultSetMetaData rsmd = null;        // result set metadata object
        int columnCount = -1;                // column count
        String resultString = "";            // result string

        try {
            rsmd = daoRset.getMetaData();

            // get number of columns from result set metadata
            columnCount = rsmd.getColumnCount();

            // row processing of result set
            while (daoRset.next()) {
                for (int index = 1; index <= columnCount; index++) {
                    resultString += daoRset.getString(index) + "  ";
                }
                resultString += "\n";
            }
        } catch (SQLException sqle) {
            System.err.println("DAO, processResultSet() - Error in processing result set");
            System.err.println(sqle.getMessage());
        } catch (NullPointerException npe) {
            System.err.println("DAO, processResultSet() - No result set generated");
            System.err.println(npe.getMessage());
        }
        return resultString;
    }    // end - method processResultSet

    // --- setAutoCommit(flag) - set autocommit on or off based on flag
    public void setAutoCommit(boolean flag) {
        try {
            daoConn.setAutoCommit(flag);
        } catch (SQLException sqle) {
            System.err.println("DAO, setAutoCommit() - error in setting autoCommit");
            System.err.println(sqle.getMessage());
        }
    }    // end - method setAutoCommit()

    // --- commit() - commit current transaction on connection
    public boolean commit() {
        boolean result;                                                // whether commit was successful
        try {
            if (!transactionStarted) {                                // not a transaction, error, notify user
                System.err.println("DAO, commit() - No transaction started - cannot commit");
                result = false;
            } else if (!exceptionOccurred) {                        // transaction - no problems, so commit
                daoConn.commit();
                transactionStarted = false;
                exceptionOccurred = false;
                result = true;
            } else {                                                // transaction - problem, so rollback
                daoConn.rollback();
                result = false;
            }
        } catch (SQLException sqle) {
            System.err.println("DAO, commit() - SQL error in commit");
            System.err.println(sqle.getMessage());
            result = false;
        }
        return result;
    }    // end - method commit()

    // --- rollback() - rollback current transaction on connection
    public void rollback() {
        try {
            daoConn.rollback();
            transactionStarted = false;
            exceptionOccurred = false;
        } catch (SQLException sqle) {
            System.err.println("DAO, rollback() - SQL error in rollback");
            System.err.println(sqle.getMessage());
        }
    }    // end - method rollback()

    // --- disconnect() - disconnect from the Oracle database
    public void disconnect() {
        // --- 5) disconnect from database
        try {
            if (daoConn != null) {
                daoConn.close();
            }
            if (daoRset != null) {
                daoRset = null;
            }
        } catch (SQLException sqle) {
            System.err.println("DAO, disconnect() - SQL error in closing database connection");
            System.err.println(sqle.getMessage());
        } finally {
            if (daoConn != null) {
                try {
                    daoConn.close();
                } catch (SQLException sqlerb) {
                    daoConn = null;
                }
            }
            if (daoRset != null) {
                try {
                    daoRset = null;
                } catch (Exception e) {
                    daoRset = null;
                }
            }
        }
    }    // end - method disconnect

}    // end - class adminGUI.DataAccessObject
