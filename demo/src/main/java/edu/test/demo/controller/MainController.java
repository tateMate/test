
package edu.test.demo.controller;
/*
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.test.demo.dao.CommentDAO;
import edu.test.demo.service.CocommentService;
import edu.test.demo.service.CommentService;
import edu.test.demo.service.ImageService;
import edu.test.demo.service.UserCharacterService;
import edu.test.demo.service.UserService;
import edu.test.demo.vo.CommentVO;
import edu.test.demo.vo.UserVO;

@RestController
public class MainController {
	@Autowired
	UserService userService;
	ImageService imageService;
	UserCharacterService userCharacterService;
	CommentService commentService;
	CocommentService cocommentService;
	
	@RequestMapping(value = "/user", method=RequestMethod.GET)
	public List<UserVO> getUserVO(){
		UserVO user1=userService.selectUser(0); 
		List<UserVO> userList = new ArrayList<UserVO>();
		userList.add(user1);
		return userList;
	}
	
	@RequestMapping(value = "/user", method=RequestMethod.GET)
	public List<CommentVO> getCommentVO(int user_id){
		List<CommentVO> commentList = new ArrayList<CommentVO>();
		CommentVO comment=commentService.selectCommentByCommentId(user_id);
		commentList.add(comment);
		return commentList;
	}
	
	
	
}
*/