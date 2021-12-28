package edu.test.demo.service;

import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import edu.test.demo.dao.UserCharacterDAO;
import edu.test.demo.dao.UserDAO;
import edu.test.demo.vo.UserCharacterVO;
import edu.test.demo.vo.UserVO;

@Service
public class UserService {
	@Autowired
	UserDAO userDAO;
	@Autowired
	UserCharacterDAO userCharacterDAO;
	
//for a test
	public List<UserVO> selectUser() {
		return userDAO.selectUser();
	}
	
//user id�� user ����
	public UserVO selectUserByUserId(int user_id) {
		return userDAO.selectUserByUserId(user_id);
	}
	
//ȸ������(user ����ֱ�)	
	public int insertUser(UserVO vo, HttpServletRequest request, MultipartFile file) throws IllegalStateException, IOException {
		if(file.getContentType().contains("octet-stream")) {
			vo.setUser_profile(null);
		}else {
			String fileURL=request.getServletContext().getRealPath("IMG");
			String uploadFileName=vo.getUser_email().replace(".","d").replace("@","at")+"."+file.getContentType().substring(file.getContentType().lastIndexOf("/")+1);
			System.out.println(uploadFileName);
			File destinationFile=new File(fileURL, uploadFileName);
			file.transferTo(destinationFile);//upload
			vo.setUser_profile(uploadFileName);//upload�� ��θ� vo�� setting
		}
		vo.setUser_pw(shalize(vo.getUser_email()+vo.getUser_pw()));
		return userDAO.insertUser(vo);
	}
	
//modify user info
	public int modifyUser(UserVO userVO, UserCharacterVO userCharacterVO, HttpSession session, HttpServletRequest request, MultipartFile file) throws IllegalStateException, IOException {
		
		userCharacterDAO.modifyUserCharacter(userCharacterVO);
		if(file.getContentType().contains("octet-stream")) {
			userVO.setUser_profile(((UserVO)session.getAttribute("user")).getUser_profile());
		}
		else {
			String fileURL=request.getServletContext().getRealPath("IMG");
			String uploadFileName=((UserVO)session.getAttribute("user")).getUser_email().replace(".","d").replace("@","at")+"."+file.getContentType().substring(file.getContentType().lastIndexOf("/")+1);
			System.out.println(uploadFileName);
			File destinationFile=new File(fileURL, uploadFileName);
			file.transferTo(destinationFile);//upload
			userVO.setUser_profile(uploadFileName);//upload�� ��θ� vo�� setting
		}
		int rst = userDAO.modifyUser(userVO);
		session.setAttribute("user", userDAO.selectUserByUserId(userVO.getUser_id()));
		session.setAttribute("userCharacter", userCharacterVO);
		return rst;
	}
	
	//email check
	public boolean emailCheck(String user_email) {
		UserVO user = userDAO.emailCheck(user_email);
		boolean rst = false;			
		//the email has existed
		if(user==null) {
			rst = true;
		}
		return rst;
	}

	//nickname check
	public boolean nicknameCheck(String user_nickname) {
		UserVO user = userDAO.nicknameCheck(user_nickname);
		boolean rst = false;			
		//the nickname has existed
		if(user==null) {
			rst = true;
		}
		return rst;
	}
	
	//password check
	public boolean pwCheck(String user_pw, HttpSession session) {
		Map<String, String> id_pass = new HashMap<String, String>();
		String user_email = ((UserVO)session.getAttribute("user")).getUser_email();
		id_pass.put("user_email", user_email);
		id_pass.put("user_pw", shalize(user_email+user_pw));
		UserVO user = userDAO.selectUserLogin(id_pass);
		boolean rst = false;			
		//the nickname has existed
		if(user!=null) {
			rst = true;
		}
		return rst;
	}
	
//�α���
	public UserVO loginCheck(String user_email, String user_pw) {
		Map<String, String> id_pass = new HashMap<>();
		id_pass.put("user_email", user_email);
		id_pass.put("user_pw", shalize(user_email+user_pw));
		return userDAO.selectUserLogin(id_pass);
	}
	
//pw ��ȣȭ(SHA256�̿�)
	private String shalize(String pw) {
		String sha = null;
		try {
			MessageDigest sh = MessageDigest.getInstance("SHA-256");
			sh.update(pw.getBytes());
			byte[] byteData = sh.digest();
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < byteData.length; i++) {
				sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
			
			}
			sha = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return sha;

	}
}
