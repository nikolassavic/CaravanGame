package com.game.dao;

import com.game.entity.Caravan;
import com.game.entity.Member;
import com.game.entity.User;
import org.springframework.stereotype.Component;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component(value = "dao")
public class DaoImpl implements Dao {
    public static final String SAVE_GAME = "update into caravans (toGoal, money, food, medicine, ammo, ox, canCarry, lastSaved) values (?, ?, ?, ?, ?, ?, ?, now())"; //+
//            "insert into members (memberFirst, memberSecond, memberThird, memberFourth, memberFifth, isAliveFirst, isAliveSecond, isAliveThird, isAliveFourth, isAliveFifth, sickLevelFirst, sickLevelSecond, sickLevelThird, sickLevelFourth, sickLevelFifth) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String SHOW_ALL = "select * from users join caravans c on users.id = c.userId join members m on c.id = m.caravanId";

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
        /*zapamtiti poslednje stanje elemenata igre i upisati ih u bazu za
        *tog usera i za taj caravan, tj. za tu sesiju*/
        System.out.println("yuhu brate....");
        Connection connection = null;
        CallableStatement callableStatement = null;
        int result;

        try {
            connection = ConnectionManager.getConnection();
            callableStatement = connection.prepareCall(SAVE_GAME);
//            callableStatement.setInt(1, caravan.getToGoal());
            callableStatement.setInt(1, 500);
//            callableStatement.setInt(2, caravan.getMoney());
            callableStatement.setInt(2, 500);
//            callableStatement.setInt(3, caravan.getFood());
            callableStatement.setInt(3, 500);
//            callableStatement.setInt(4, caravan.getMedicine());
            callableStatement.setInt(4, 500);
//            callableStatement.setInt(5, caravan.getAmmo());
            callableStatement.setInt(5, 500);
//            callableStatement.setInt(6, caravan.getOx());
            callableStatement.setInt(6, 500);
//            callableStatement.setInt(7, caravan.getCanCarry());
            callableStatement.setInt(7, 500);
//            callableStatement.setDate(8, (Date) caravan.getLastSaved());
            callableStatement.execute();
            ConnectionManager.commit(connection);
            result = 1;
        } catch (Exception e){
            result = 0;
            e.printStackTrace();
        } finally {
            ConnectionManager.closeStatement(callableStatement);
            ConnectionManager.closeConnection(connection);
        }
        return result;
    }

    @Override
    public int startSavedGame(User user, Caravan caravan, Member member) {
        return 0;
    }
}