/*
 * @author GauravismPS
 * 
 * @date 10-05-2021 15:53
 * 
 * @version 1.0
 */

package com.sapient.healthyreps.dao;

import java.sql.*;
import java.util.*;
import javax.persistence.*;
import com.sapient.healthyreps.dbs.*;
import com.sapient.healthyreps.exceptions.InvalidUserIdException;
import com.sapient.healthyreps.exceptions.InvalidSocialMediaException;



@Entity
@Table(name="social_media")
public class SocialMedia {
    int userId = -1;
    @Column(name = "type", nullable = false)
    String name = ""; // like twitter, insta, etc.
    @Column(name = "url", nullable = false)
    String url = ""; // url to that Media;
    private Long id;
    @Transient
    private List<String> medium = Arrays.asList("facebook", "twitter", "instagram");

    public SocialMedia() throws Exception {
        super();
    }

    public SocialMedia(int id) throws Exception {
        super();
        userId = id;
    }

    public SocialMedia(int id, String _name, String _url) throws Exception {
        super();
        userId = id;
        name = _name;
        url = _url;
    }

    public SocialMedia(int id, String _name) throws Exception {
        super();
        userId = id;
        name = _name;
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

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Map<String, Object>> queryUrlById() throws Exception {
        Connection conn = ConnectionManager.getConnection();
        if (userId == -1) {
            throw new InvalidUserIdException("UserId is not valid or not set");
        }
        PreparedStatement pst =
                conn.prepareStatement("SELECT (name, url) from socialmedia where userid=?");
        pst.setInt(1, userId);
        List<Map<String, Object>> result = DatabaseManager.getQuery(pst);
        conn.close();
        return result;
    }

    public List<Map<String, Object>> queryUrlByIdMedium() throws Exception {
        Connection conn = ConnectionManager.getConnection();
        if (userId == -1) {
            throw new InvalidUserIdException("UserId is not valid or not set");
        }
        if (!medium.contains(name)) {
            throw new InvalidSocialMediaException(name + " is not allowed");
        }
        PreparedStatement pst = conn
                .prepareStatement("SELECT (name, url) from SOCIALMEDIA where userid=? and name=?");
        pst.setInt(1, userId);
        pst.setString(2, name);
        List<Map<String, Object>> result = DatabaseManager.getQuery(pst);
        conn.close();
        return result;
    }

    public int upsert() throws Exception {
        Connection conn = ConnectionManager.getConnection();
        if (queryUrlByIdMedium().size() == 0) {
            PreparedStatement pst = conn.prepareStatement(
                    "INSERT INTO SOCIALMEDIA (userid, name, url) values (?, ?, ?)");
            pst.setInt(1, userId);
            pst.setString(2, name);
            pst.setString(3, url);
            return DatabaseManager.modify(pst);
        }
        PreparedStatement pst =
                conn.prepareStatement("UPDATE SOCIALMEDIA SET URL=? WHERE USERID=? and name=?");

        pst.setString(1, url);
        pst.setInt(2, userId);
        pst.setString(3, name);
        int result = DatabaseManager.modify(pst);
        conn.close();
        return result;
    }


    public void setId(Long id) {
        this.id = id;
    }

    @Id
    public Long getId() {
        return id;
    }
}
