package edu.test.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.test.demo.dao.UserCharacterDAO;
import edu.test.demo.vo.UserCharacterVO;

@Service
public class UserCharcterService {
	@Autowired
	UserCharacterDAO userCharacterDAO;
	
	public UserCharacterVO selectUserCharacterByUserId(int user_id) {
		return userCharacterDAO.selectUserCharacterByUserId(user_id);
	}
}
