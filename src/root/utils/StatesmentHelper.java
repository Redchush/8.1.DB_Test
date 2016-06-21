package root.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

import static root.utils.ResourceManager.PARTS;
import static root.utils.ResourceManager.QUERIES;


public class StatesmentHelper {

    public static PreparedStatement getUsersByFavoriteTag(Map<String, PreparedStatement> result, Connection
                                            connection, String tag1, String tag2) throws SQLException {

        String queryString =QUERIES.getString("select.usersByFavoriteTag");
        PreparedStatement statement = connection.prepareStatement(queryString);
        statement.setString(1, tag1);
        statement.setString(2, tag2);
        result.put(queryString, statement);
        return statement;
    }

    public static PreparedStatement getPostAndCategoryByTagConstraint( Map<String, PreparedStatement> result,
                                                           Connection connection, int tagId)
            throws SQLException {

        String queryString =QUERIES.getString("select.postAndCategoryByTagConstraint");
        PreparedStatement statement = connection.prepareStatement(queryString);
        statement.setInt(1, tagId);
        statement.setInt(2, tagId);
        result.put(queryString, statement);
        return statement;
    }

    public static PreparedStatement getRecentByAnswerAuthor( Map<String, PreparedStatement> result,
                                                 Connection connection, int period,  int userId)
            throws SQLException {

        String queryString =QUERIES.getString("select.recent.commentsByAnswerAuthor");
        PreparedStatement statement = connection.prepareStatement(queryString);

        statement.setInt(1, period);
        statement.setInt(2, userId);
        result.put(queryString, statement);
        return statement;
    }

    public static PreparedStatement getTagsByUserId(Map<String, PreparedStatement> result, Connection connection, int testedUserId)
            throws SQLException {

        String queryString = PARTS.getString("select.full.favoriteTags") + PARTS.getString("append.byAuthor");
        PreparedStatement statement = connection.prepareStatement(queryString);
        statement.setInt(1, testedUserId);
        result.put(queryString, statement);
        return statement;
    }

    public static PreparedStatement getPostsByTagId(Map<String, PreparedStatement> result, Connection connection, int testedTagId)
            throws SQLException {
        String queryString = PARTS.getString("select.full.postAllTags") + PARTS.getString("append.byTagId");
        PreparedStatement statement = connection.prepareStatement(queryString);
        statement.setInt(1, testedTagId);
        result.put(queryString, statement);
        return statement;

    }

    public static PreparedStatement getCategoriesByTagId(Map<String, PreparedStatement> result, Connection connection,
                                            int testedTagId) throws SQLException {
        String queryString = PARTS.getString("select.full.categoryAllTags") + PARTS.getString("append.byTagId");
        PreparedStatement statement = connection.prepareStatement(queryString);
        statement.setInt(1, testedTagId);
        result.put(queryString, statement);
        return statement;

    }
}
