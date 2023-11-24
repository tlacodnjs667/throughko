package com.wooruk.repository;

import com.wooruk.datasource.DatasourceUtil;
import com.wooruk.dto.UserSignUpDto;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
    private static UserDao instance;
    private HikariDataSource dataSource;

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
            return result;
        }
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
