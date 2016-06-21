package root.launcher;

import root.utils.ConnectorDB;
import root.utils.ConsoleHelper;
import root.utils.StatesmentHelper;

import java.sql.*;
import java.util.*;

import static root.utils.ResourceManager.QUERIES;

public class Main {
    private static final String KEY_FROM_BUNDLE = "Key : ";
    private static final String QUERY = "Query: ";
    private static final String RESULT = "Result: ";
    private static final String DELIMITER = "_________________________________";


    public static void main(String[] args) {

        Connection connection = null;
        PreparedStatement statement = null;
        Map<String, PreparedStatement> statementsMap = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectorDB.getConnection();
            Set<String> set3 = QUERIES.keySet();
            for (String key : set3){
                if (key.contains(".byId.")) {
                    int testedUserId =  1;
                    String statementString = QUERIES.getString(key);
                    ConsoleHelper.writeMessage(QUERY);
                    ConsoleHelper.writeMessage(statementString);
                    statement = connection.prepareStatement(statementString);
                    statement.setInt(1, testedUserId);
                    resultSet = statement.executeQuery();
                    ConsoleHelper.writeMessage(RESULT);
                    ConsoleHelper.writeMessage(resultSet);
                    ConsoleHelper.writeMessage(DELIMITER);

                }
            }
            // another queries example //
            statementsMap = getPrepareStatements(connection);
            for(Map.Entry<String, PreparedStatement> entry : statementsMap.entrySet()){
                String key = entry.getKey();
                PreparedStatement value = entry.getValue();
                resultSet = value.executeQuery();
                ConsoleHelper.writeMessage(QUERY + key);
                ConsoleHelper.writeMessage(RESULT);
                ConsoleHelper.writeMessage(resultSet);
                ConsoleHelper.writeMessage(DELIMITER);
            }
            //combined queries example //
            statementsMap = getCombinedStatsments(connection);
            for(Map.Entry<String, PreparedStatement> entry : statementsMap.entrySet()){
                String key = entry.getKey();
                PreparedStatement value = entry.getValue();
                resultSet = value.executeQuery();
                ConsoleHelper.writeMessage(QUERY + key);
                ConsoleHelper.writeMessage(RESULT);
                ConsoleHelper.writeMessage(resultSet);
                ConsoleHelper.writeMessage(DELIMITER);


            }

        } catch (ClassNotFoundException e) {
            root.utils.ConsoleHelper.writeMessage("Can't downloas the jdbc driber. Reason : " + e);
        } catch (SQLException e) {
            root.utils.ConsoleHelper.writeMessage("Unexpexted SQLException : " + e);
        } finally {
            closeResultSet(resultSet);
            closeStatesmentQuetly(statement);
            closeStatesmentsQuetly(statementsMap);
            closeConnectionQuetly(connection);
        }
    }



    private static Map<String, PreparedStatement> getPrepareStatements(Connection connection) throws SQLException {
        Map<String, PreparedStatement> result = new HashMap<>();
        int testedUserId = 1;
        int testedTagId = 1;
        int testedPeriod = 1;
        String testedTagName1 = "java";
        String testedTagName2 = "sql";

        StatesmentHelper.getUsersByFavoriteTag(result, connection, testedTagName1, testedTagName2);
        StatesmentHelper.getPostAndCategoryByTagConstraint(result, connection, testedTagId);
        StatesmentHelper.getRecentByAnswerAuthor(result, connection, testedPeriod, testedUserId);
        return result;
    }

    private static Map<String,PreparedStatement> getCombinedStatsments(Connection connection) throws SQLException {
        Map<String, PreparedStatement> result = new HashMap<>();
        int testedUserId = 1;
        int testedTagId = 1;
        StatesmentHelper.getTagsByUserId(result, connection, testedUserId);
        StatesmentHelper.getPostsByTagId (result, connection, testedTagId);
        StatesmentHelper.getCategoriesByTagId(result, connection, testedTagId);
        return result;
    }

    private static void closeConnectionQuetly(Connection connection){
        if (connection != null){
            try{
                connection.close();;
            } catch (SQLException e) {
                root.utils.ConsoleHelper.writeMessage("Connection close error " + e);
            }
        }
    }

    private static void closeStatesmentsQuetly(Map<String, PreparedStatement> statementMap){
        if (statementMap != null){
            for (PreparedStatement statement : statementMap.values()){
                closeStatesmentQuetly(statement);
            }
        }
    }

    private static void closeStatesmentQuetly(Statement statement){
        if (statement != null){
            try{
                statement.close();;
            } catch (SQLException e) {
                root.utils.ConsoleHelper.writeMessage("Statement close error " + e);
            }
        }
    }

    private static void closeResultSet(ResultSet resultSet)  {
        if (resultSet != null){
            try{
                resultSet.close();;
            } catch (SQLException e) {
                root.utils.ConsoleHelper.writeMessage("ResultSet close error " + e);
            }
        }
    }
}



