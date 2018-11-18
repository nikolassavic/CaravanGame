package com.game.dao;

import com.game.entity.Caravan;
import com.game.entity.Member;
import com.game.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component(value = "dao")
public class DaoImpl implements Dao {

    private static final String NEW_USER = "INSERT INTO users(displayName, password, email) VALUES(?,?,?)";
    private static final String GET_USER = "SELECT * FROM users WHERE email=? AND password=?";


    @Override
    public int newUser(User newUser) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        int result;

        try {
            connection = ConnectionManager.getConnection();
            callableStatement = connection.prepareCall(NEW_USER);
            callableStatement.setString(1, newUser.getDisplayName());
            callableStatement.setString(2, newUser.getPassword());
            callableStatement.setString(3, newUser.getEmail());
            callableStatement.execute();
            ConnectionManager.commit(connection);
            result = 1;
        } catch (SQLException e) {
            result = 0;
            //e.printStackTrace();
        } finally {
            ConnectionManager.closeStatement(callableStatement);
            ConnectionManager.closeConnection(connection);
        }
        return result;
    }

    @Override
    public int loggedUser(User user) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;
        int result = 0;

        try {
            connection = ConnectionManager.getConnection();
            callableStatement = connection.prepareCall(GET_USER);
            callableStatement.setString(1, user.getEmail());
            callableStatement.setString(2, user.getPassword());
            callableStatement.execute();
            resultSet = callableStatement.getResultSet();
            while (resultSet.next()) {
                user.setId(resultSet.getInt("id"));
                user.setDisplayName(resultSet.getString("displayName"));
                user.setValid(resultSet.getBoolean("isValid"));
            }
            if (user.isValid()) {
                result = 1;
            } else if(!user.isValid()) {
                result = 2;
            }
            if (user.getId() == -1){
                result = 0;
            }

        } catch (SQLException e) {
            result = 0;
            //e.printStackTrace();
        } finally {
            ConnectionManager.closeStatement(callableStatement);
            ConnectionManager.closeConnection(connection);
        }
        return result;
    }

    @Override
    public int startNewGame(User user, Caravan caravan, Member member) {

        return 0;
    }

    @Override
    public int saveGame(Caravan caravan, Member member) {
        return 0;
    }

    @Override
    public int startSavedGame(User user, Caravan caravan, Member member) {
        return 0;
    }
}