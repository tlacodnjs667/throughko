package com.wooruk.repository;

import com.wooruk.datasource.DatasourceUtil;
import com.wooruk.domain.Category;
import com.wooruk.domain.Post;
import com.wooruk.dto.PostCreateDto;
import com.wooruk.dto.PostListItemDto;
import com.wooruk.dto.PostUpdateDto;
import com.wooruk.dto.UserForPostDto;
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

    public int like(Integer postId) {
        final String UPDATE_LIKE_SQL = """
                UPDATE POST
                SET LIKES = LIKES + 1
                WHERE POST_PK = ?
            """;

        Connection conn = null;
        PreparedStatement stmt = null;
        int result = 0;

        try {
            conn = dataSource.getConnection();
            stmt = conn.prepareStatement(UPDATE_LIKE_SQL);

            stmt.setInt(1, postId);

            result = stmt.executeUpdate();
        } catch (SQLException e) {
            log.error(e.toString());
        } finally {
            closeResources(conn, stmt);
        }
        return result;
    }

<<<<<<< HEAD
    public int updatePost (PostUpdateDto dto) {
        final String UPDATE_LIKE_SQL = """
            UPDATE POST
            SET POST_TITLE = ?, POST_CONTENT = ?
            WHERE POST_PK = ?
        """;
=======
    public int read(Integer postId) {
        final String UPDATE_HITS_SQL = """
                UPDATE POST
                SET HITS = HITS + 1
                WHERE POST_PK = ?
            """;
>>>>>>> c4265c3c1f75517f3a89b1f4cd21c08e0a4a1341

        Connection conn = null;
        PreparedStatement stmt = null;
        int result = 0;

        try {
            conn = dataSource.getConnection();
            stmt = conn.prepareStatement(UPDATE_LIKE_SQL);

            stmt.setString(1, dto.getTitle());
            stmt.setString(2, dto.getContent());
            stmt.setInt(3, dto.getPostId());

            result = stmt.executeUpdate();
        } catch (SQLException e) {
            log.error(e.toString());
        } finally {
            closeResources(conn, stmt);
        }
        return result;
    }

    public int read (Integer postId) {
        final String UPDATE_HITS_SQL = """
                UPDATE POST
                SET HITS = HITS + 1
                WHERE POST_PK = ?
            """;

        Connection conn = null;
        PreparedStatement stmt = null;
        int result = 0;

        try {
            conn = dataSource.getConnection();
            stmt = conn.prepareStatement(UPDATE_HITS_SQL);

            stmt.setInt(1, postId);

            result = stmt.executeUpdate();
        } catch (SQLException e) {
            log.error(e.toString());
        } finally {
            closeResources(conn, stmt);
        }
        return result;
    }

    public List<PostListItemDto> select(Integer categoryFk, String orderClause) {
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

        SQL_SELECT_POSTS_LIST += (" ORDER BY "+ orderClause + "POST_PK DESC");

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

    public Post selectPostById(Integer postId) {
        String SELECT_SQL_BY_ID = """
            SELECT
                POST_PK,
                POST_TITLE,
                POST_CONTENT,
                CATEGORY_FK,
                CATEGORY_TITLE,
                USER_FK,
                NICKNAME,
                CREATED_TIME,
                MODIFIED_TIME,
                LIKES,
                HITS
            FROM POST
            LEFT JOIN CATEGORY ON CATEGORY_FK = CATEGORY_PK
            LEFT JOIN USER ON POST.USER_FK = USER_PK
            WHERE POST_PK = ?
            """;

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Post result = null;
        try {

            conn = dataSource.getConnection();
            stmt = conn.prepareStatement(SELECT_SQL_BY_ID);

            stmt.setInt(1, postId);

            rs = stmt.executeQuery();

            if (rs.next()) {
                Integer postPk = rs.getInt("POST_PK");
                String postTitle = rs.getString("POST_TITLE");
                String postContent = rs.getString("POST_CONTENT");
                Timestamp createdTime = rs.getTimestamp("CREATED_TIME");
                Timestamp modifiedTime = rs.getTimestamp("MODIFIED_TIME");
                String nickname = rs.getString("NICKNAME");
                int userId = rs.getInt("USER_FK");
                int categoryId = rs.getInt("CATEGORY_FK");
                String categoryTitle = rs.getString("CATEGORY_TITLE");
                int likes = rs.getInt("LIKES");
                int hits = rs.getInt("HITS");

                Category category = new Category(categoryId, categoryTitle);
                UserForPostDto author = new UserForPostDto(userId, nickname);

                result = Post.getBuilder()
                    .post_pk(postPk).post_title(postTitle).post_content(postContent)
                    .created_time(createdTime).modified_time(modifiedTime)
                    .category(category).author(author).likes(likes).hits(hits).build();

            }


        } catch (SQLException e) {
            log.error(e.toString());
        } finally {
            closeResources(conn, stmt, rs);
            return result;
        }
    }

    public boolean selectPostByUserAndPost(Integer postId, Integer userId) {
        String SELECT_SQL_BY_ID = """
            SELECT
                POST_PK
            FROM POST
            WHERE POST_PK = ? AND USER_FK = ?
            """;

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean result = false;

        try {

            conn = dataSource.getConnection();
            stmt = conn.prepareStatement(SELECT_SQL_BY_ID);

            stmt.setInt(1, postId);
            stmt.setInt(2, userId);

            rs = stmt.executeQuery();

            result = rs.next();

        } catch (SQLException e) {
            log.error(e.toString());
        } finally {
            closeResources(conn, stmt, rs);
        }
        return result;
    }

    public boolean deletePost (Integer postId) {
        String DELETE_SQL_BY_ID = """
            DELETE FROM POST
            WHERE POST_PK = ?
            """;

        Connection conn = null;
        PreparedStatement stmt = null;

        boolean result = false;

        try {

            conn = dataSource.getConnection();
            stmt = conn.prepareStatement(DELETE_SQL_BY_ID);

            stmt.setInt(1, postId);

            result = stmt.executeUpdate() == 1;

        } catch (SQLException e) {
            log.error(e.toString());
        } finally {
            closeResources(conn, stmt);
        }
        return result;
    }


}
