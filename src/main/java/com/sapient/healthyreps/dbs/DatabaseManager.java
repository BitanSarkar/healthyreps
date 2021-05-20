
/*
 * @author GauravismPS
 * @date 10-05-2021 15:53
 * @version 1.0
 */
package com.sapient.healthyreps.dbs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseManager {

    public static List<Map<String, Object>> getQuery(String query) throws Exception {


        try (Connection conn = ConnectionManager.getConnection(); Statement smt = conn.createStatement()) {
            ResultSet rst = smt.executeQuery(query);
            return convertResultSetToList(rst);


        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
        return null;
    }

    public static List<Map<String, Object>> convertResultSetToList(ResultSet rst) throws Exception {
        List<Map<String, Object>> resultList = new ArrayList<>();
        Map<String, Object> row;
        ResultSetMetaData metaData = rst.getMetaData();
        int columnCount = metaData.getColumnCount();
        while (rst.next()) {
            row = new HashMap<>();
            for (int i = 1; i <= columnCount; i++) {
                row.put(metaData.getColumnName(i), rst.getObject(i));
            }
            resultList.add(row);
        }
        return resultList;
    }

    public static List<Map<String, Object>> getQuery(PreparedStatement query) throws Exception {


        try  {
            ResultSet rst = query.executeQuery();
            return convertResultSetToList(rst);


        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }

        return null;
    }


    public static int modify(String query) throws Exception {
        Connection conn = ConnectionManager.getConnection();
        int count=-1;
        try (Statement smt = conn.createStatement()) {
            count = smt.executeUpdate(query);
            System.out.println("Number of records modified :" + count);
            return count;
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
        finally {
            conn.close();
        }
        return count;
    }


    public static int modify(PreparedStatement query) throws Exception {
        try{
            return query.executeUpdate();
        } 
        finally {
            try {
                if(query != null) {
                    query.close();
                }
            }
            catch (SQLException e) {
                System.out.println("SQLException: " + e.getMessage());
                System.out.println("SQLState: " + e.getSQLState());
                System.out.println("VendorError: " + e.getErrorCode());
            }
        }
    }

}


