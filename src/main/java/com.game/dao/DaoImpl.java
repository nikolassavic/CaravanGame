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

    private static final String NEWUSER = "insert into users(displayName, password, email) values(?,?,?)";


    @Override
    public int newUser(User newUser) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        int result;

        try {
            connection = ConnectionManager.getConnection();
            callableStatement = connection.prepareCall(NEWUSER);
            callableStatement.setString(1, newUser.getDisplayName());
            callableStatement.setString(2,newUser.getPassword());
            callableStatement.setString(3,newUser.getEmail());
            callableStatement.execute();
            ConnectionManager.commit(connection);
            result = 1;
        } catch (SQLException e) {
            result = 0;
            //e.printStackTrace();
        }
        finally {
            ConnectionManager.closeStatement(callableStatement);
            ConnectionManager.closeConnection(connection);
        }
        return result;
    }

    @Override
    public int loggedUser(User user) {
        return 0;
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