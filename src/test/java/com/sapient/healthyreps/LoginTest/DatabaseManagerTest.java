package com.sapient.healthyreps.LoginTest;

import static org.junit.jupiter.api.Assertions.*;
import com.sapient.healthyreps.dbs.DatabaseManager;

import java.util.List;
import java.util.Map;

public class DatabaseManagerTest {

    // @Test
    public void qTest() throws  Exception {
        String query = "select userid, username, emailid from User";
        List<Map<String, Object>> result = DatabaseManager.getQuery(query);
        StringBuilder res = new StringBuilder();
        for (Map<String, Object> row : result) {
            res.append(row.get("username"));
            res.append(" ");
        }

        String actual = "Himanshu Shubham anshul ";
        assertEquals(actual, res.toString());
        
    }
}


