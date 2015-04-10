package db_hw4;



import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;
import java.util.Date;

class DB_HW4{
    public static void main(String[] args) throws SQLException  {
        Connection con = null;
        Statement stmt = null;
        ArrayList<String> userInfo = new ArrayList();
        Scanner mainScanner = new Scanner(System.in);
        String id, command;
        int balance = 0;


        try {
        // Create an connection with bank server
        Class.forName("oracle.jdbc.driver.OracleDriver");
        con = DriverManager.getConnection( "jdbc:oracle:thin:@dbclick.kaist.ac.kr:1521:orcl", "s20130380", "s20130380");

            System.out.println("CS 360  Simple Bank");
            System.out.println("Please type 'new' to create an account.");

            stmt = con.createStatement();

            // We'll handle commits manually
            con.setAutoCommit(false);
            userInfo = Login(con);

            // If login is not established because of invalid connection attempts, quit.
            if (userInfo.size() < 1) return;

            id = userInfo.get(0);

            // Successfully logged in. The user can now interact with bank server.
            // Before user types 'q', it goes on and on and on and...
            while(true) {

                // After each execution of the command, update the balance of the user.
                ResultSet rs =
                                stmt.executeQuery("SELECT usermoney FROM users WHERE UserID = '" + id + "'");
                if (rs.next()) balance = rs.getInt(1);

                System.out.println("---------------------------------------------------------------------------------------");
                System.out.println(id + "'s current balance = " + balance);
                System.out.println("---------------------------------------------------------------------------------------");

                System.out.print("Please type a command: ");
                command = mainScanner.next();

                // Receive a command, get it done
                switch (command) {
                        case "q" :
                                System.out.println("Thank you for using Simple Bank system.");
                                return;
                        case "l" :
                                PrintLog(con, id);
                                continue;
                        case "d" :
                                Deposit(con, id);
                                continue;
                        case "w" :
                                Withdraw(con, id);
                                continue;
                        case "s" :
                                Send(con, id);
                                continue;
                        default :
                                System.out.println("Please choose a valid command: 'q'uit, 'l'og, 'd'eposit, 'w'ithdraw, or 's'end");
                                continue;
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
            con.rollback();
        } finally {
            try {
                // Close a statement and a connection
                if (stmt != null) stmt.close();
                if (con != null) con.close();
            } catch (Exception e) { }
        }
    }

    // Login Function
    public static ArrayList<String> Login(Connection con) throws Exception {
        Scanner loginScanner = new Scanner(System.in);
        String newID, newPasswd, id, passwd;
        int attemptCount = 0;
        ArrayList<String> resultArray = new ArrayList<String>();


        Statement stmt = con.createStatement();

        while (true)    {
                // Initialize resultArray
                resultArray = new ArrayList<String>();

                // If three invalid login attempts were made, return a single-element list to quit the bank system.
                if (attemptCount == 3) {
                        System.out.println("Sorry, you can't log-in to Simple Bank after 3 attempts.");
                        resultArray.add("Invalid Connection");
                        break;
                }

                // Receives user name
                System.out.print("User name: ");
                id = loginScanner.next();


                // If input string is 'new', creates a new id
                if (id.equals("new"))   {
                        while (true) {
                                System.out.print("ID: ");
                                newID = loginScanner.next();

                                // Check if given ID already exists or not
                                ResultSet rs = stmt.executeQuery("SELECT * FROM users WHERE UserID = '" + newID + "'");
                                if (rs.next()) System.out.println("Same ID exists.");
                                else break;
                        }

                        System.out.print("Password: ");
                        newPasswd = loginScanner.next();

                        // Insert new account into users table with zero balance
                        PreparedStatement newStmt =
                                con.prepareStatement("INSERT INTO users values(?, ?, ?)");
                                con.commit();
                                newStmt.setString(1, newID);
                                newStmt.setString(2, newPasswd);
                                newStmt.setInt(3, 0);
                                newStmt.executeUpdate();

                                System.out.println("New account " + newID + " is created.");
                        if (newStmt != null) newStmt.close();

                        }

                // Elsewhere, try to connect the bank server with given id and password.
                else {
                        System.out.print("Password: ");
                        passwd = loginScanner.next();

                        ResultSet rs =
                                stmt.executeQuery("SELECT usermoney FROM users WHERE UserID = '" + id + "' and passwd = " + "'" + passwd + "'");

                        //  If valid, return an ArrayList of id and password. Successfully logged in.
                        if (rs.next())  {
                                resultArray.add(id);
                                resultArray.add(passwd);
                                break;
                        }

                        // If invalid, print error message and increase attempt count. Retry login.
                        else {
                                System.out.println("Invalid Username / Password");
                                attemptCount++;
                                continue;
                        }
                }
            }

        // Close a statement
        if (stmt != null) stmt.close();
        return resultArray;
    }

    // Log Printing Function
    public static void PrintLog(Connection con, String id) throws Exception {
        String date, mode, from, to, money;

        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM logs WHERE UserID = '" + id + "' ORDER BY LogDate");

        System.out.println("CS360       Simple Bank Log");
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println("Date                  Mode       From                 To                   Money");
        System.out.println("---------------------------------------------------------------------------------------");

        // Print every log stored in logs table with UserID
        while (rs.next()) {
                mode = rs.getString(3);

                // If deposit, 'from' field and 'to' field are stored in reverse order
                if (mode.equals("deposit")) {
                        from = rs.getString(4);
                        to = rs.getString(2);
                }
                else {
                        from = rs.getString(2);
                        to = rs.getString(4);
                }

                date = rs.getString(1);
                money = String.valueOf(rs.getInt(5) - rs.getInt(6));
                System.out.printf("%s %-10s %-20s %-20s %s\n", date, mode, from, to, money);

        }

        // Close a statement
        if (stmt != null) stmt.close();
        return;
    }

    // Deposit Function
    public static void Deposit(Connection con, String id) throws Exception{
        int depositAmount;
        Scanner depositScanner = new Scanner(System.in);

                java.util.Date now = new java.util.Date();
                java.sql.Date date = new java.sql.Date(now.getTime());

                System.out.print("Deposit money: ");

                // Check if given input is valid
                if ((depositAmount = depositScanner.nextInt()) <= 0)    {
                        System.out.println("Please input a valid amount of money.");
                        return;
                }


        // Update a balance
        PreparedStatement depositStmt =
                        con.prepareStatement("UPDATE users SET usermoney = usermoney + ? WHERE userID = ?");
                        depositStmt.setInt(1, depositAmount);
                        depositStmt.setString(2, id);
                        depositStmt.executeUpdate();
                        con.commit();

        // Add a deposit log
                PreparedStatement logStmt =
                        con.prepareStatement("INSERT INTO logs values(?, ?, ?, ?, ?, ?)");
                                logStmt.setTimestamp(1, new Timestamp(date.getTime()));
                        logStmt.setString(2, id);
                        logStmt.setString(3, "deposit");
                        logStmt.setString(4, "");
                        logStmt.setInt(5, depositAmount);
                        logStmt.setInt(6, 0);
                        logStmt.executeUpdate();
                        con.commit();

        // Close statements
        if (depositStmt != null) depositStmt.close();
        if (logStmt != null) logStmt.close();
        return;
    }

    // Withdraw Function
    public static void Withdraw(Connection con, String id) throws Exception{
        int withdrawAmount, currentBalance = 0;
        Scanner withdrawScanner = new Scanner(System.in);

                java.util.Date now = new java.util.Date();
                java.sql.Date date = new java.sql.Date(now.getTime());

                Statement balanceStmt = con.createStatement();

                ResultSet rs =
                        balanceStmt.executeQuery("SELECT usermoney FROM users WHERE UserID = '" + id + "'");


                System.out.print("Withdraw money: ");


                // Check if given amount of money could be withdrawn
        if (rs.next()) currentBalance = rs.getInt(1);
                if ((withdrawAmount = withdrawScanner.nextInt()) <= 0 || withdrawAmount > currentBalance)       {
                        System.out.println("Please input a valid amount of money.");
                        return;
                }


        // Update a balance
        PreparedStatement withdrawStmt =
                        con.prepareStatement("UPDATE users SET usermoney = usermoney - ? WHERE userID = ?");
                        withdrawStmt.setInt(1, withdrawAmount);
                        withdrawStmt.setString(2, id);
                        withdrawStmt.executeUpdate();
                        con.commit();

        // Add a withdraw log
                PreparedStatement logStmt =
                        con.prepareStatement("INSERT INTO logs values(?, ?, ?, ?, ?, ?)");
                                logStmt.setTimestamp(1, new Timestamp(date.getTime()));
                        logStmt.setString(2, id);
                        logStmt.setString(3, "withdraw");
                        logStmt.setString(4, "");
                        logStmt.setInt(5, 0);
                        logStmt.setInt(6, withdrawAmount);
                        logStmt.executeUpdate();
                        con.commit();

        // Close statements
        if (withdrawStmt != null) withdrawStmt.close();
        if (logStmt != null) logStmt.close();
        return;
    }

    // Send Function
    public static void Send(Connection con, String id) throws Exception {
        String destID;
        int sendAmount, currentBalance=0;

        Scanner destScanner = new Scanner(System.in);
        Scanner sendScanner = new Scanner(System.in);
        Statement destStmt = con.createStatement();

                java.util.Date now = new java.util.Date();
                java.sql.Date date = new java.sql.Date(now.getTime());

                Statement balanceStmt = con.createStatement();

                System.out.print("Send money to: ");
                destID = destScanner.next();

                // Check if the receiver exists.
                ResultSet rs = destStmt.executeQuery("SELECT userid FROM users WHERE UserID = '" + destID + "'");
                if (!rs.next()) {
                        System.out.println("Invalid receiver name.");
                        return;
                }

                System.out.print("How much money? ");

                rs = balanceStmt.executeQuery("SELECT usermoney FROM users WHERE UserID = '" + id + "'");

        if (rs.next()) currentBalance = rs.getInt(1);

                // Check if given amount of money could be sent
                if ((sendAmount = sendScanner.nextInt()) <= 0 || sendAmount > currentBalance) {
                        System.out.println("Invalid send amount...");
                        return;
                }

                // - - - - - - - - - - - - - Sender Side - - - - - - - - - - - - - - -

                // Update a balance
        PreparedStatement sendStmt =
                        con.prepareStatement("UPDATE users SET usermoney = usermoney - ? WHERE userID = ?");
                        sendStmt.setInt(1, sendAmount);
                        sendStmt.setString(2, id);
                        sendStmt.executeUpdate();
                        con.commit();

        // Add a send log
                PreparedStatement logStmt_s =
                        con.prepareStatement("INSERT INTO logs values(?, ?, ?, ?, ?, ?)");
                                logStmt_s.setTimestamp(1, new Timestamp(date.getTime()));
                        logStmt_s.setString(2, id);
                        logStmt_s.setString(3, "send");
                        logStmt_s.setString(4, destID);
                        logStmt_s.setInt(5, 0);
                        logStmt_s.setInt(6, sendAmount);
                        logStmt_s.executeUpdate();
                        con.commit();

        // - - - - - - - - - - - - - Receiver Side - - - - - - - - - - - - - - -

                // Update a balance
        PreparedStatement receiveStmt =
                        con.prepareStatement("UPDATE users SET usermoney = usermoney + ? WHERE userID = ?");
                        receiveStmt.setInt(1, sendAmount);
                        receiveStmt.setString(2, destID);
                        receiveStmt.executeUpdate();
                        con.commit();

        // Add a receive log
                PreparedStatement logStmt_r =
                        con.prepareStatement("INSERT INTO logs values(?, ?, ?, ?, ?, ?)");
                                logStmt_r.setTimestamp(1, new Timestamp(date.getTime()));
                        logStmt_r.setString(2, id);
                        logStmt_r.setString(3, "receive");
                        logStmt_r.setString(4, destID);
                        logStmt_r.setInt(5, sendAmount);
                        logStmt_r.setInt(6, 0);
                        logStmt_r.executeUpdate();
                        con.commit();

        // Close statements
                if (sendStmt != null) sendStmt.close();
        if (receiveStmt != null) receiveStmt.close();
        if (logStmt_s != null) logStmt_s.close();
        if (logStmt_r != null) logStmt_r.close();

        return;
    }
}
