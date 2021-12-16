
package edu.test.demo.controller;
/*
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.test.demo.dao.ComentDAO;
import edu.test.demo.service.CocomentService;
import edu.test.demo.service.ComentService;
import edu.test.demo.service.ImageService;
import edu.test.demo.service.UserCharacterService;
import edu.test.demo.service.UserService;
import edu.test.demo.vo.ComentVO;
import edu.test.demo.vo.UserVO;

@RestController
public class MainController {
	@Autowired
	UserService userService;
	ImageService imageService;
	UserCharacterService userCharacterService;
	ComentService comentService;
	CocomentService cocomentService;
	
	@RequestMapping(value = "/user", method=RequestMethod.GET)
	public List<UserVO> getUserVO(){
		UserVO user1=userService.selectUser(0); 
		List<UserVO> userList = new ArrayList<UserVO>();
		userList.add(user1);
		return userList;
	}
	
	@RequestMapping(value = "/user", method=RequestMethod.GET)
	public List<ComentVO> getComentVO(int user_id){
		List<ComentVO> comentList = new ArrayList<ComentVO>();
		ComentVO coment=comentService.selectComentByComentId(user_id);
		comentList.add(coment);
		return comentList;
	}
	
	
	
}
*/