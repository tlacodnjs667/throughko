package com.wooruk.repository;

import com.wooruk.datasource.DatasourceUtil;
import com.wooruk.domain.Category;
import com.wooruk.dto.PostCreateDto;
import com.wooruk.dto.PostListItemDto;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PostDao {

    private static final String SQL_SELECT_CATEGORIES = "SELECT * FROM CATEGORY";

    private static final String CREATE_POST_SQL = """
        INSERT INTO POST (
            POST_TITLE,
            POST_CONTENT,
            USER_FK,
            CATEGORY_FK
        ) VALUES (
            ?, ?, ?, ?
        )
        """;
    private static PostDao instance;
    private final Logger log = LoggerFactory.getLogger(PostDao.class);
    private final HikariDataSource dataSource;

    private PostDao() {
        dataSource = DatasourceUtil.getInstance().getDs();
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
            conn = dataSource.getConnection();
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

    private void closeResources(Connection conn, PreparedStatement stmt) {
        closeResources(conn, stmt, null);
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

    public int createPost(PostCreateDto dto) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int result = 0;

        try {
            conn = dataSource.getConnection();
            stmt = conn.prepareStatement(CREATE_POST_SQL);

            stmt.setString(1, dto.getPostTitle());
            stmt.setString(2, dto.getPostContent());
            stmt.setInt(3, dto.getUserFk());
            stmt.setInt(4, dto.getCategoryFk());

            result = stmt.executeUpdate();
        } catch (SQLException e) {
            log.error(e.toString());
        } finally {
            closeResources(conn, stmt);
        }
        return result;
    }

    public List<PostListItemDto> select(Integer categoryFk) {
        String DEFAULT_SELECT_SQL = """
            SELECT
                POST_PK,
                POST_TITLE,
                CATEGORY_TITLE,
                NICKNAME,
                CREATED_TIME
            FROM POST
            LEFT JOIN CATEGORY ON CATEGORY_FK = CATEGORY_PK
            LEFT JOIN USER ON USER_FK = USER_PK
            """;

        String SQL_SELECT_POSTS_LIST = null;

        if (categoryFk != null) {
            SQL_SELECT_POSTS_LIST = DEFAULT_SELECT_SQL + " WHERE CATEGORY_FK = ?";
        } else {
            SQL_SELECT_POSTS_LIST = DEFAULT_SELECT_SQL;
        }

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<PostListItemDto> postList = new ArrayList<>();

        try {
            conn = dataSource.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_POSTS_LIST);

            if (categoryFk != null) {
                stmt.setInt(1, categoryFk);
            }

            rs = stmt.executeQuery();

            while (rs.next()) {
                Integer postId = rs.getInt("POST_PK");
                String postTitle = rs.getString("POST_TITLE");
                String nickname = rs.getString("NICKNAME");
                String category = rs.getString("CATEGORY_TITLE");
                Timestamp createdTime = rs.getTimestamp("CREATED_TIME");

                PostListItemDto post = new PostListItemDto(postId, postTitle, nickname, category,
                    createdTime);
                postList.add(post);
            }

        } catch (SQLException e) {
            log.error(e.toString());
        } finally {
            closeResources(conn, stmt, rs);
        }

        return postList;
    }

}
