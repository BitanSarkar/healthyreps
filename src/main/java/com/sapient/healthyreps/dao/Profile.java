/*
 * @author GauravismPS
 * @date 10-05-2021 15:53
 * @version 1.0
 */

package com.sapient.healthyreps.dao;



import java.sql.*;
import java.util.*;
import javax.persistence.*;
import com.sapient.healthyreps.dbs.*;
import com.sapient.healthyreps.exceptions.*;


@Entity
@Table(name="profile")
public class Profile {
    Integer userId;
    Integer profileImageId;
    String firstName;
    String middleName;
    String lastName;
    String contactNo;
    Integer age;                       // In Years (Rounded).
    Integer weight;                    // In Kgs
    Integer height;                    // in centimeter
    Character gender;                  // M or F;
    Integer goalWeight;                // In Kgs
    Integer planDuration;              // In days
    String accessType;                 // User, Trainer, Admin etc.
    Float stars;                       // User Rating
    String plans;                      // Some like premium, gold etc.
    private Long profileId;

    public Profile() throws Exception {
        super();
    }

    public Profile(int _userId) throws Exception {
        super();
        userId = _userId;
    }

    public Profile(Map<String, Object> arg) throws Exception {
        super();
        for(Map.Entry<String, Object> entry : arg.entrySet()) {
            String key = entry.getKey();
            Object val = entry.getValue();

            switch (key) {
                case "userId" -> userId = (Integer) val;
                case "profileImageId" -> profileImageId = (Integer) val;
                case "firstName" -> firstName = (String) val;
                case "middleName" -> middleName = (String) val;
                case "lastName" -> lastName = (String) val;
                case "contactNo" -> contactNo = (String) val;
                case "age" -> age = (Integer) val;
                case "weight" -> weight = (Integer) val;
                case "height" -> height = (Integer) val;
                case "gender" -> gender = (Character) val;
                case "goalWeight" -> goalWeight = (Integer) val;
                case "planDuration" -> planDuration = (Integer) val;
                case "accessType" -> accessType = (String) val;
                case "stars" -> stars = (Float) val;
                case "plans" -> plans = (String) val;
            }
        }
    }

    public void setAccessType(String accessType) {
        this.accessType = accessType;
    }

    public String getAccessType() {
        return accessType;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setProfileImageId(Integer profileImageId) {
        this.profileImageId = profileImageId;
    }

    public Integer getProfileImageId() {
        return profileImageId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getAge() {
        return age;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getHeight() {
        return height;
    }

    public void setGender(Character gender) {
        this.gender = gender;
    }

    public Character getGender() {
        return gender;
    }

    public void setGoalWeight(Integer goalWeight) {
        this.goalWeight = goalWeight;
    }

    public Integer getGoalWeight() {
        return goalWeight;
    }

    public void setPlanDuration(Integer planDuration) {
        this.planDuration = planDuration;
    }

    public Integer getPlanDuration() {
        return planDuration;
    }

    public void setStars(Float stars) {
        this.stars = stars;
    }

    public Float getStars() {
        return stars;
    }

    public void setPlans(String plans) {
        this.plans = plans;
    }

    public String getPlans() {
        return plans;
    }

    public int deleteByUserId() throws Exception{
        Connection conn = ConnectionManager.getConnection();
        if(userId == -1) throw new InvalidUserIdException(userId + " is not a valid userId");
        PreparedStatement pst = conn.prepareStatement(
                "DELETE FROM PROFILE WHERE USERID=?"
        );
        pst.setInt(1, userId);
        int result = DatabaseManager.modify(pst);
        conn.close();
        return result;
    }

    public void setProfileId(Long profileId) {
        this.profileId = profileId;
    }

    @Id
    public Long getProfileId() {
        return profileId;
    }
}