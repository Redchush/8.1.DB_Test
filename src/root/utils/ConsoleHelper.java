package root.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class ConsoleHelper {

    public static void writeMessage(String message){
        if (message != null) {
            System.out.println(message);
        }
    }

    public static void writeMessage (ResultSet set) throws SQLException {
        ResultSetMetaData meta = set.getMetaData();
        while (set.next()){
            int length = meta.getColumnCount();
            for (int i = 1; i <= length; i++) {
                System.out.print(set.getObject(i) + " ");
            }

            System.out.println();
        }
    }
}
