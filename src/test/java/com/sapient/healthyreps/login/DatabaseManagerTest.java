package com.sapient.reps.login;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.sapient.reps.dbs.DatabaseManager;

import java.util.List;
import java.util.Map;

public class DatabaseManagerTest {

    @Test
    public void qTest() throws  Exception {
        String query = "select userid, username, emailid from userregister";
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


