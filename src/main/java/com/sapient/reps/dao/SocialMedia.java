/*
 * @author GauravismPS
 * 
 * @date 10-05-2021 15:53
 * 
 * @version 1.0
 */

package com.sapient.reps.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Map;
import com.sapient.reps.dbs.ConnectionManager;
import com.sapient.reps.dbs.DatabaseManager;
import com.sapient.reps.exceptions.InvalidSocialMediaException;
import com.sapient.reps.exceptions.InvalidUserIdException;

public class SocialMedia {
    int userId = -1;
    String type = ""; // like twitter, insta, etc.
    String url = ""; // url to that Media;
    Connection conn = ConnectionManager.getConnection();
    List<String> medium = List.of("Twitter", "Facebook", "Twitch", "Instagram");

    public SocialMedia() throws Exception {
        super();
    }

    public SocialMedia(int id) throws Exception {
        super();
        userId = id;
    }

    public SocialMedia(int id, String _type, String _url) throws Exception {
        super();
        userId = id;
        type = _type;
        url = _url;
    }

    public SocialMedia(int id, String _type) throws Exception {
        super();
        userId = id;
        type = _type;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public List<Map<String, Object>> getUrlById() throws Exception {
        if (userId == -1) {
            throw new InvalidUserIdException("UserId is not valid or not set");
        }
        PreparedStatement pst =
                conn.prepareStatement("SELECT (type, url) from socialmedia where userid=?");
        pst.setInt(1, userId);
        return DatabaseManager.getQuery(pst);
    }

    public List<Map<String, Object>> getUrlByIdMedium() throws Exception {
        if (userId == -1) {
            throw new InvalidUserIdException("UserId is not valid or not set");
        }
        if (!medium.contains(type)) {
            throw new InvalidSocialMediaException(type + " is not allowed");
        }
        PreparedStatement pst = conn
                .prepareStatement("SELECT (type, url) from SOCIALMEDIA where userid=? and type=?");
        pst.setInt(1, userId);
        pst.setString(2, type);
        return DatabaseManager.getQuery(pst);
    }

    public int upsert() throws Exception {
        if (getUrlByIdMedium().size() == 0) {
            PreparedStatement pst = conn.prepareStatement(
                    "INSERT INTO SOCIALMEDIA (userid, type, url) values (?, ?, ?)");
            pst.setInt(1, userId);
            pst.setString(2, type);
            pst.setString(3, url);
            return DatabaseManager.modify(pst);
        }
        PreparedStatement pst =
                conn.prepareStatement("UPDATE SOCIALMEDIA SET URL=? WHERE USERID=? and TYPE=?");

        pst.setString(1, url);
        pst.setInt(2, userId);
        pst.setString(3, type);
        return DatabaseManager.modify(pst);
    }


}
