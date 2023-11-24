package com.wooruk.repository;

import com.wooruk.datasource.DatasourceUtil;
import com.wooruk.domain.User;
import com.wooruk.dto.UserSignInDto;
import com.wooruk.dto.UserSignUpDto;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserDao {

    private static final String SQL_TO_SIGNUP = """
        INSERT INTO USER (
           USER_ID,
           PASSWORD,
           EMAIL,
           NICKNAME          
        ) VALUES (
            ?, ?, ?, ?
        )
        """;
    private static final String SQL_SIGNIN = """
            SELECT USER_PK, NICKNAME FROM USER
            WHERE USER_ID = ? AND PASSWORD = ?  
        """;
    private final Logger log = LoggerFactory.getLogger(UserDao.class);
    private static UserDao instance;
    private final HikariDataSource dataSource;

    private UserDao() {
        dataSource = DatasourceUtil.getInstance().getDs();
    }

    public static UserDao getInstance() {
        if (instance == null) {
            instance = new UserDao();
        }
        return instance;
    }

    public int signup(UserSignUpDto userToSignUp) {
        Connection conn = null;
        PreparedStatement stmt = null;

        int result = 0;

        try {
            conn = dataSource.getConnection();
            stmt = conn.prepareStatement(SQL_TO_SIGNUP);

            stmt.setString(1, userToSignUp.getUserId());
            stmt.setString(2, userToSignUp.getPassword());
            stmt.setString(3, userToSignUp.getEmail());
            stmt.setString(4, userToSignUp.getNickname());

            result = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt);
        }
        return result;
    }

    public User signin(UserSignInDto dto) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        User signedUser = null;
        try {
            conn = dataSource.getConnection();
            stmt = conn.prepareStatement(SQL_SIGNIN);

            stmt.setString(1, dto.getUserId());
            stmt.setString(2, dto.getPassword());

            rs = stmt.executeQuery();
            if (rs.next()) {
                int userPk = rs.getInt("USER_PK");
                String nickname = rs.getString("NICKNAME");
                signedUser = new User(userPk, nickname);
                log.debug(signedUser.toString());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, rs);
        }
        return signedUser;
    }


    void closeResources(Connection conn, PreparedStatement statement) {
        closeResources(conn, statement, null);
    }

    void closeResources(Connection conn, PreparedStatement statement, ResultSet rs) {

        try {
            if (rs != null) {
                rs.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
