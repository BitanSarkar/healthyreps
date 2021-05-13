/*
 * @author GauravismPS
 * @date 10-05-2021 15:53
 * @version 1.0
 */

package com.sapient.healthyreps.dao;




import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Map;
import com.sapient.healthyreps.dbs.ConnectionManager;
import com.sapient.healthyreps.dbs.DatabaseManager;
import com.sapient.healthyreps.exceptions.InvalidImageIdException;
import com.sapient.healthyreps.exceptions.InvalidImageUrlException;
import com.sapient.healthyreps.exceptions.InvalidUserIdException;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "gallery")
public class Images {
    private Long imageId= (long) -1;
    int userId=-1;
    String imageUrl="";
    Connection conn = ConnectionManager.getConnection();

    public Images() throws Exception {
        super();
    }

    public Images(int _userId) throws Exception {
        super();
        userId = _userId;
    }

    public Images(int _userId, String _imageUrl) throws Exception {
        super();
        imageUrl = _imageUrl;
        userId = _userId;
    }




    public List<Map<String, Object>> queryByUserId() throws Exception {
        if(userId == -1) throw new InvalidUserIdException(userId + " is invalid");
        PreparedStatement pst = conn.prepareStatement(
                "SELECT (imageId, imageUrl) from Images where UserId=?"
        );
        pst.setInt(1, userId);
        return DatabaseManager.getQuery(pst);
    }

    public int deleteByImageId() throws Exception {
        if(imageId == -1) throw new InvalidImageIdException(imageId + " is not a valid Image Id");
        PreparedStatement pst = conn.prepareStatement(
                "DELETE FROM IMAGES WHERE IMAGEID=?"
        );
        pst.setLong(1, imageId);
        return DatabaseManager.modify(pst);
    }

    public int deleteByUserId() throws Exception {
        if(userId == -1) throw new InvalidUserIdException(userId + " is invalid");
        PreparedStatement pst = conn.prepareStatement(
                "DELETE FROM IMAGES WHERE USERID=?"
        );
        pst.setInt(1, userId);
        return DatabaseManager.modify(pst);
    }

    public int insertImage() throws Exception {
        if(userId == -1) throw new InvalidUserIdException(userId + " is invalid");
        if(imageUrl.equals("")) throw new InvalidImageUrlException(imageUrl + " is invalid");
        PreparedStatement pst = conn.prepareStatement(
                "INSERT INTO (userId, imageUrl) values(?, ?)"
        );
        pst.setInt(1, userId);
        pst.setString(2, imageUrl);
        return DatabaseManager.modify(pst);
    }


    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public void setImageUrl(String url) {
        this.imageUrl = url;
    }
    public int getUserId() { return userId; }
    public void setImageId(Long imageId) { this.imageId = imageId; }
    @Id
    public Long getImageId() { return imageId; }
}