package com.sapient.healthyreps.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.healthyreps.dao.UserDAO;
import com.sapient.healthyreps.entity.RequirementForChangePassword;
import com.sapient.healthyreps.entity.User;

/*public class RequirementForChangePassword{
	private String email;
	private String 
}*/

@RestController
public class ChangePasswordController {

	@Autowired
	UserDAO dao = new UserDAO();

	@PostMapping("/api/changePassword")
	public String userPasswordReset(@RequestBody RequirementForChangePassword passwordChangeRequest) {
		/*
		 * try { UserDAO.passwordCheck(passwordChangeRequest.getNewPassword()); } catch
		 * (PasswordTooSmall e) { e.printStackTrace(); return "PasswordTooSmall"; }
		 * catch (PasswordIsWeak e) { e.printStackTrace(); return "PasswordIsWeak"; }
		 */
		List<User> user = dao.getUserId(passwordChangeRequest.getEmail());
		if (user.isEmpty())
			return "Email is wrong";
		return dao.updatePassword(user.get(0).getUserId(), passwordChangeRequest.getNewPassword())
				? "Password Changed Successfully"
				: "Email is wrong";
	}
}
