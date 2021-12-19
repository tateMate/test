package edu.test.demo.service;

import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import edu.test.demo.dao.UserDAO;
import edu.test.demo.vo.UserVO;

@Service
public class UserService {
	@Autowired
	UserDAO userDAO;
	
//for a test
	public List<UserVO> selectUser() {
		return userDAO.selectUser();
	}
	
//user id로 user 선택
	public UserVO selectUserByUserId(int user_id) {
		return userDAO.selectUserByUserId(user_id);
	}
	
//회원가입(user 집어넣기)	
	public int insertUser(UserVO vo, HttpServletRequest request, MultipartFile file) throws IllegalStateException, IOException {
		String fileURL=request.getServletContext().getRealPath("IMG");
		String uploadFileName=vo.getUser_email().replace(".","d").replace("@","at")+"."+file.getContentType().substring(file.getContentType().lastIndexOf("/")+1);
		System.out.println(uploadFileName);
		File destinationFile=new File(fileURL, uploadFileName);
		file.transferTo(destinationFile);//upload
		vo.setUser_profile(uploadFileName);//upload된 경로를 vo에 setting
		vo.setUser_pw(shalize(vo.getUser_email()+vo.getUser_pw()));
		return userDAO.insertUser(vo);
	}

//로그인
	public UserVO loginCheck(String user_email, String user_pw) {
		Map<String, String> id_pass = new HashMap<>();
		id_pass.put("user_email", user_email);
		id_pass.put("user_pw", shalize(user_email+user_pw));
		return userDAO.selectUserLogin(id_pass);
	}
	
//pw 암호화(SHA256이용)
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
