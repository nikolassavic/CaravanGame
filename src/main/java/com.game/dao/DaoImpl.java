package com.game.dao;

import com.game.entity.Caravan;
import com.game.entity.Member;
import com.game.entity.User;
import org.springframework.stereotype.Component;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component(value = "dao")
public class DaoImpl implements Dao {

    private static final String NEW_USER = "INSERT INTO users(displayName, password, email) VALUES(?,?,?)";
    private static final String GET_USER = "SELECT * FROM users WHERE email=? AND password=?";
    private static final String VALIDATE_USER = "UPDATE users set isValid=1 WHERE users.id=?";
    private static final String SAVE_CARAVAN = "UPDATE caravans SET toGoal=?, money=?, food=?, medicine=?, ammo=?, ox=?, canCarry=?, lastSaved=now() WHERE userId=?";
    private static final String SAVE_MEMBER = "UPDATE members SET isAliveFirst=?, sickLevelFirst=?, isAliveSecond=?, sickLevelSecond=?, isAliveThird=?, sickLevelThird=?, isAliveFourth=?, sickLevelFourth=?, isAliveFifth=?, sickLevelFifth=? WHERE caravanId=?";
    private static final String START_CARAVAN = "INSERT INTO caravans(userId) VALUES(?)";
    private static final String START_MEMBER = "INSERT INTO members(caravanId) VALUES(?)";
    private static final String GET_CARAVAN = "SELECT * FROM caravans WHERE userId=?";
    private static final String GET_MEMBER = "SELECT * FROM members WHERE caravanId=?";

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
            e.printStackTrace();
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
            if (user.getId() != -1) {
                if (user.isValid()) {
                    result = 1;
                } else if (!user.isValid()) {
                    result = 2;
                }
            }

        } catch (SQLException e) {
            result = -1;
            //e.printStackTrace();
        } finally {
            ConnectionManager.closeStatement(callableStatement);
            ConnectionManager.closeConnection(connection);
        }
        return result;
    }

    @Override
    public int validateUser(User user) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        int result;

        try {
            connection = ConnectionManager.getConnection();
            callableStatement = connection.prepareCall(VALIDATE_USER);
            callableStatement.setInt(1,user.getId());
            callableStatement.execute();
            ConnectionManager.commit(connection);
            result = 1;
        } catch (SQLException e) {
            result = -1;
            e.printStackTrace();
        } finally {
            ConnectionManager.closeStatement(callableStatement);
            ConnectionManager.closeConnection(connection);
        }
        return result;
    }

    @Override
    public int saveGame(User user, Caravan caravan, Member member) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        int result;

        try {
            connection = ConnectionManager.getConnection();
            callableStatement = connection.prepareCall(SAVE_CARAVAN);
            callableStatement.setInt(1, caravan.getToGoal());
            callableStatement.setInt(2, caravan.getMoney());
            callableStatement.setInt(3, caravan.getFood());
            callableStatement.setInt(4, caravan.getMedicine());
            callableStatement.setInt(5, caravan.getAmmo());
            callableStatement.setInt(6, caravan.getOx());
            callableStatement.setInt(7, caravan.getCanCarry());
            callableStatement.setInt(8, user.getId());
            callableStatement.execute();
            ConnectionManager.commit(connection);

            callableStatement = connection.prepareCall(SAVE_MEMBER);
            callableStatement.setBoolean(1, member.isAliveFirst());
            callableStatement.setInt(2, member.getSickLevelFirst());
            callableStatement.setBoolean(3, member.isAliveSecond());
            callableStatement.setInt(4, member.getSickLevelSecond());
            callableStatement.setBoolean(5, member.isAliveThird());
            callableStatement.setInt(6, member.getSickLevelThird());
            callableStatement.setBoolean(7, member.isAliveFourth());
            callableStatement.setInt(8, member.getSickLevelFourth());
            callableStatement.setBoolean(9, member.isAliveFifth());
            callableStatement.setInt(10, member.getSickLevelFifth());
            callableStatement.setInt(11, caravan.getId());
            callableStatement.execute();
            ConnectionManager.commit(connection);

            result = 1;
        } catch (SQLException e) {
            result = -1;
            e.printStackTrace();
        } finally {
            ConnectionManager.closeStatement(callableStatement);
            ConnectionManager.closeConnection(connection);
        }
        return result;
    }

    @Override
    public List<Object> startNewGame(User user) {
        Caravan caravan = new Caravan();
        Member member = new Member();
        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionManager.getConnection();
            //caravan
            callableStatement = connection.prepareCall(START_CARAVAN);
            callableStatement.setInt(1, user.getId());
            callableStatement.execute();
            ConnectionManager.commit(connection);

            callableStatement = connection.prepareCall(GET_CARAVAN);
            callableStatement.setInt(1, user.getId());
            callableStatement.execute();
            resultSet = callableStatement.getResultSet();
            setCaravan(caravan, resultSet);

            //members
            callableStatement = connection.prepareCall(START_MEMBER);
            callableStatement.setInt(1, caravan.getId());
            callableStatement.execute();
            ConnectionManager.commit(connection);

            callableStatement = connection.prepareCall(GET_MEMBER);
            callableStatement.setInt(1, caravan.getId());
            callableStatement.execute();
            resultSet = callableStatement.getResultSet();
            setMember(member, resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeStatement(callableStatement);
            ConnectionManager.closeConnection(connection);
        }

        List<Object> gameObjects = new ArrayList<Object>();
        gameObjects.add(user);
        gameObjects.add(caravan);
        gameObjects.add(member);
        return gameObjects;
    }

    @Override
    public List<Object> startSavedGame(User user) {
        Caravan caravan = new Caravan();
        Member member = new Member();
        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionManager.getConnection();
            //caravan
            callableStatement = connection.prepareCall(GET_CARAVAN);
            callableStatement.setInt(1, user.getId());
            callableStatement.execute();
            resultSet = callableStatement.getResultSet();
            setCaravan(caravan, resultSet);

            //members
            callableStatement = connection.prepareCall(GET_MEMBER);
            callableStatement.setInt(1, caravan.getId());
            callableStatement.execute();
            resultSet = callableStatement.getResultSet();
            setMember(member, resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeStatement(callableStatement);
            ConnectionManager.closeConnection(connection);
        }

        List<Object> gameObjects = new ArrayList<Object>();
        gameObjects.add(user);
        gameObjects.add(caravan);
        gameObjects.add(member);
        return gameObjects;
    }

    private static void setCaravan(Caravan caravan, ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            caravan.setId(resultSet.getInt("id"));
            caravan.setUserId(resultSet.getInt("userId"));
            caravan.setToGoal(resultSet.getInt("toGoal"));
            caravan.setToGoal(resultSet.getInt("money"));
            caravan.setFood(resultSet.getInt("food"));
            caravan.setMedicine(resultSet.getInt("medicine"));
            caravan.setAmmo(resultSet.getInt("ammo"));
            caravan.setOx(resultSet.getInt("ox"));
            caravan.setCanCarry(resultSet.getInt("canCarry"));
            caravan.setLastSaved(resultSet.getTimestamp("lastSaved"));
        }
        resultSet.close();
    }

    private static void setMember(Member member, ResultSet resultSet) throws SQLException{
        while (resultSet.next()) {
            member.setId(resultSet.getInt("id"));
            member.setCaravanId(resultSet.getInt("caravanId"));
            member.setMemberFirst(resultSet.getString("memberFirst"));
            member.setAliveFirst(resultSet.getBoolean("isAliveFirst"));
            member.setSickLevelFirst(resultSet.getInt("sickLevelFirst"));
            member.setMemberSecond(resultSet.getString("memberSecond"));
            member.setAliveSecond(resultSet.getBoolean("isAliveSecond"));
            member.setSickLevelSecond(resultSet.getInt("sickLevelSecond"));
            member.setMemberThird(resultSet.getString("memberThird"));
            member.setAliveThird(resultSet.getBoolean("isAliveThird"));
            member.setSickLevelThird(resultSet.getInt("sickLevelThird"));
            member.setMemberFourth(resultSet.getString("memberFourth"));
            member.setAliveFourth(resultSet.getBoolean("isAliveFourth"));
            member.setSickLevelFourth(resultSet.getInt("sickLevelFourth"));
            member.setMemberFifth(resultSet.getString("memberFifth"));
            member.setAliveFifth(resultSet.getBoolean("isAliveFifth"));
            member.setSickLevelFifth(resultSet.getInt("sickLevelFifth"));
        }
        resultSet.close();
    }
}