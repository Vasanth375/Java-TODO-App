import java.sql.*;
import java.util.*;

class JDBC {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("enter FirstName");
        String fname = sc.next();
        System.out.println("enter LastName");
        String lname = sc.next();
        System.out.println("Enter Email");
        String email = sc.next();
        System.out.println("Enter Pass");
        String pass = sc.next();
        System.out.println("Enter mobile");
        long mobile = sc.nextLong();
        sc.nextLine();
        System.out.println("Enter Address");
        String address = sc.next();
        // the following driver class connects us oracle DB
        Class.forName("oracle.jdbc.driver.OracleDriver");
        // connection
        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "pat", "pat");
        Statement stmt = con.createStatement();
        // pk generation
        int regid = 0;
        ResultSet rs = stmt.executeQuery("select max(regid) from register");
        if (rs.next()) {
            regid = rs.getInt(1);
        }
        regid++;
        PreparedStatement pstmt = con.prepareStatement("Insert INTO register VALUES(?,?,?,?,?,?,?)");
        pstmt.setInt(1, regid);
        pstmt.setString(2, fname);
        pstmt.setString(3, lname);
        pstmt.setString(4, email);
        pstmt.setString(5, pass);
        pstmt.setLong(6, mobile);
        pstmt.setString(7, address);
        pstmt.executeUpdate();
        pstmt.close();
        rs.close();
        stmt.close();
        con.close();
        sc.close();
    }
}
