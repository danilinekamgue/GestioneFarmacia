package Service;

import config.DbConfig;
import config.DbInfo;

import java.sql.*;

public class SqlConn {
    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private String query;

    public SqlConn(String query) {
        this.query = query;
    }


    public ResultSet execute() {
            try {
                DbInfo db = DbConfig.getDbConfig();
                Class.forName("com.mysql.cj.jdbc.Driver");
                this.conn = DriverManager.getConnection(db.getUrl(), db.getUser(), db.getPassword());
                this.stmt = conn.createStatement();
                this.rs = stmt.executeQuery(this.query);
                return rs;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return null;
            }
    }

    public  void freeSql(){
        try {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("freeSql::"+ e.getMessage());
        }
    }
    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public Statement getStmt() {
        return stmt;
    }

    public void setStmt(Statement stmt) {
        this.stmt = stmt;
    }

    public ResultSet getRs() {
        return rs;
    }

    public void setRs(ResultSet rs) {
        this.rs = rs;
    }
}
