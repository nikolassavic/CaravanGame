package com.game.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

    private static final String dbName = "caravan";
    private static final String username = "root";
    private static final String password = "bazepodataka";

    static final String MYSQL_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String MYSQL_CONNECTION = "jdbc:mysql://localhost/" + dbName +
            "?user=" + username + "&password=" + password + "&useSSL=false";

    private static ConnectionManager instance = null;

    private ConnectionManager() {

    }

    public static ConnectionManager getInstance() {

        if (instance == null) {
            instance = new ConnectionManager();
        }
        return instance;
    }

    public static synchronized Connection getConnection() throws SQLException {

        Connection newCon = null;

        try {
            Class.forName(MYSQL_DRIVER).newInstance();
            newCon = DriverManager.getConnection(MYSQL_CONNECTION);
        } catch (Exception e) {
            e.printStackTrace();
        }
        newCon.setAutoCommit(false);
        return newCon;
    }

    public static synchronized void closeConnection(Connection conn) {
        try {
            if (conn != null || !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {

        }
    }

    public static synchronized void closeStatement(CallableStatement cstmt) {
        try {
            if (cstmt != null || !cstmt.isClosed()) {
                cstmt.close();
            }
        } catch (SQLException e) {

        }
    }

    public static synchronized void commit(Connection conn) {
        try {
            if (conn != null || !conn.isClosed()) {
                conn.commit();
            }
        } catch (SQLException e) {

        }
    }

    public static synchronized void rollback(Connection conn) {
        try {
            if (conn != null || !conn.isClosed()) {
                conn.rollback();
            }
        } catch (SQLException e) {

        }
    }
}