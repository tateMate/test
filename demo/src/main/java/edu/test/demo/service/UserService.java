package edu.test.demo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.test.demo.dao.UserDAO;
import edu.test.demo.vo.UserVO;

@Service
public class UserService {
	@Autowired
	UserDAO userDAO;

	public List<UserVO> selectUser() {
		return userDAO.selectUser();
	}

	public int insertUser(UserVO vo) {
		return userDAO.insertUser(vo);
	}

	public UserVO loginCheck(String user_email, String user_pw) {
		Map<String, String> id_pass = new HashMap<>();
		id_pass.put("user_email", user_email);
		id_pass.put("user_pw", user_pw);
		return userDAO.selectUserLogin(id_pass);
	}
	
}
