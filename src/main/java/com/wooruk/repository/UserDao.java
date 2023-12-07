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
            SELECT USER_PK, NICKNAME, USER_ID FROM USER
            WHERE USER_ID = ? AND PASSWORD = ?  
        """;
    private static final String SQL_GET_USER_INFO_PROFILE = """
            SELECT
                USER_PK, USER_ID, EMAIL, NICKNAME
            FROM USER
            WHERE USER_PK = ?
        """;
    private static UserDao instance;
    private final Logger log = LoggerFactory.getLogger(UserDao.class);
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
                String userId = rs.getString("USER_ID");
                signedUser = new User(userPk, nickname, userId);
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

    public User getUserInfoToProfile(Integer userPk) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        User userInfo = null;
        try {
            conn = dataSource.getConnection();
            stmt = conn.prepareStatement(SQL_GET_USER_INFO_PROFILE);

            stmt.setInt(1, userPk);

            rs = stmt.executeQuery();
            if (rs.next()) {
                int user_pk = rs.getInt("USER_PK");
                String userId = rs.getString("USER_ID");
                String nickname = rs.getString("NICKNAME");
                String email = rs.getString("EMAIL");

                userInfo = new User(user_pk, userId, email, nickname);
                log.debug(userInfo.toString());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, rs);
        }
        return userInfo;
    }


    private static final String SQL_GET_USER_ID_BY_EMAIL = """
        SELECT USER_ID FROM USER WHERE EMAIL = ?
        """;
    public String getUserIdByEmail(String email) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        String userId = null;
        try {
            conn = dataSource.getConnection();
            stmt = conn.prepareStatement(SQL_GET_USER_ID_BY_EMAIL);

            stmt.setString(1, email);

            rs = stmt.executeQuery();
            if (rs.next()) {
                userId = rs.getString("USER_ID");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, rs);
        }
        return userId;
    }
    private static final String SQL_GET_EXIST_BY_EMAIL_AND_ID = """
        SELECT USER_PK FROM USER WHERE EMAIL = ? AND USER_ID = ?
        """;
    public boolean getUserIdByIdAndEmail(String email, String userId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        boolean isExist = false;
        try {
            conn = dataSource.getConnection();
            stmt = conn.prepareStatement(SQL_GET_EXIST_BY_EMAIL_AND_ID);

            stmt.setString(1, email);
            stmt.setString(2, userId);

            rs = stmt.executeQuery();
            if (rs.next()) {
               isExist = rs.getBoolean("USER_PK");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, rs);
        }
        return isExist;
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
