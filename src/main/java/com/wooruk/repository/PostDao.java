package com.wooruk.repository;

import com.wooruk.datasource.DatasourceUtil;
import com.wooruk.domain.Category;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PostDao {

    private static final String SQL_SELECT_CATEGORIES = "SELECT * FROM CATEGORY";
    private static PostDao instance;
    private final HikariDataSource ds;
    private final Logger log = LoggerFactory.getLogger(PostDao.class);

    private PostDao() {
        ds = DatasourceUtil.getInstance().getDs();
    }

    public static PostDao getInstance() {
        if (instance == null) {
            instance = new PostDao();
        }
        return instance;
    }

    public List<Category> selectCategory() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Category> result = new ArrayList<>();

        try {
            conn = ds.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_CATEGORIES);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Category category = makeCategoryInstance(rs);
                result.add(category);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, rs);
        }
        return result;
    }

    private void closeResources(Connection conn, PreparedStatement stmt, ResultSet rs) {
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
            log.error(e.toString());
        }
    }

    private Category makeCategoryInstance(ResultSet rs) throws SQLException {
        int category_pk = rs.getInt("CATEGORY_PK");
        String title = rs.getString("CATEGORY_TITLE");

        return new Category(category_pk, title);
    }
}
