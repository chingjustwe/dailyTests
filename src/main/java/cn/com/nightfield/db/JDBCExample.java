package cn.com.nightfield.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: zhochi
 * @create: 2020/12/9
 **/
public class JDBCExample {
    // JDBC driver name and database URL / credentials
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/EMP";
    static final String USER = "username";
    static final String PASS = "password";

    public static void main(String[] args) {
        JDBCExample jdbcExample = new JDBCExample();
        jdbcExample.queryUsers();
    }

    public List<User> queryUsers() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        List<User> users = new ArrayList<>();
        try{
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);
            // STEP 2: Open a connection
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            // STEP 3: Execute a query
            stmt = conn.createStatement();
            String sql = "SELECT ID, NAME, AGE FROM USER";
            rs = stmt.executeQuery(sql);

            // STEP 4: Extract data from result set
            while(rs.next()){
                User user = new User();
                user.setId(rs.getInt("ID"));
                user.setAge(rs.getInt("AGE"));
                user.setName(rs.getString("NAME"));
                users.add(user);
            }
            return users;
        }catch(SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }finally{
            // STEP 5: Clean-up environment
            try{
                if (rs != null) {
                    rs.close();
                }
                if(stmt != null)
                    stmt.close();
                if(conn != null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        return users;
    }

    static class User {
        private int id;
        private int age;
        private String name;
        public int getId() {
            return id;
        }
        public void setId(int id) {
            this.id = id;
        }
        public int getAge() {
            return age;
        }
        public void setAge(int age) {
            this.age = age;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }
}
