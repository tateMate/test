package edu.test.demo.service;

import javax.mail.*;
import javax.mail.internet.*;


import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.*;

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
	
	
	public void sendEmail(String user_email) throws UnsupportedEncodingException, MessagingException {
		final String TO=user_email;
		final String FROM="";
		final String FROMNAME="TATEMATE";
		final String SMTP_USERNAME="";
		final String SMTP_PASSWORD="";
		final String HOST="smtp.gmail.com";
		final int PORT=587;
		final String SUBJECT="****TEST(TITLE)****";
		final String BODY="test용 메일입니다.";
		
		Properties props=System.getProperties();
		props.put("mail.smtp.ssl.protocols", "TLSv1.2");
		props.put("mail.transport.protocol","smtp");
		props.put("mail.smtp.port",PORT);
		props.put("mail.smtp.starttls.enable","true");
		props.put("mail.smtp.auth","true");
		
		Authenticator auth=new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(SMTP_USERNAME,SMTP_PASSWORD);
			}
		};
		
		Session session=Session.getInstance(props,auth);
		MimeMessage msg=new MimeMessage(session);
		msg.setFrom(new InternetAddress(FROM,FROMNAME));
		msg.setRecipient(Message.RecipientType.TO, new InternetAddress(TO));
		msg.setSubject(SUBJECT);
		msg.setContent(BODY,"text/html;charset=utf-8");
		
		Transport transport=session.getTransport();
		try {
			System.out.println("Sending...");
			transport.connect(HOST, SMTP_USERNAME,SMTP_PASSWORD);
			transport.sendMessage(msg, msg.getAllRecipients());
			System.out.println("Email sent!");
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			transport.close();
		}
		
		
	}
	
	
//for a test
	public List<UserVO> selectUser() {
		return userDAO.selectUser();
	}
	
//by user id
	public UserVO selectUserByUserId(int user_id) {
		return userDAO.selectUserByUserId(user_id);
	}
	
//insert user(join)
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
//false==사용불가 email, true==사용 가능한 email
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
	
//login Check
	public UserVO loginCheck(String user_email, String user_pw) {
		Map<String, String> id_pass = new HashMap<>();
		id_pass.put("user_email", user_email);
		id_pass.put("user_pw", shalize(user_email+user_pw));
		return userDAO.selectUserLogin(id_pass);
	}
//Recommend User by UserId
	public List<UserVO> selectRcmdUserByUserId(int user_id) {
		return userDAO.selectRcmdUserByUserId(user_id);
	}

//	recommend user mulgae version
	public List<UserVO> rcmd(int user_id){		
		List<UserVO> rcmdUserList = new ArrayList<>();		//	최종 추천 리스트		
		UserVO user = userDAO.selectUserByUserId(user_id);
		String userLocation = user.getUser_location();	//	user 지역
//		8개 미만이면 범위 확대?('%xx구%')
		String location =userLocation.substring(userLocation.lastIndexOf(" ", userLocation.lastIndexOf(" ")-1));	//	xx구 xx동 or xx면 xx리(밑에서 두번째 행정구역부터)
		location = "%"+location+"%";	//	like를 위한 '%' 추가.
		List<UserVO> nearbyUserList = userDAO.selectUsersByLocation(location);	//	location이 해당 구역인 user list 받아오기
		if(nearbyUserList.size()<8) {												//	너무 적을 경우 최하위 행정구역 제외하고 찾기
			location = userLocation.substring(userLocation.lastIndexOf(" ", userLocation.lastIndexOf(" ")-1), userLocation.lastIndexOf(" "));	//	xx구
			location = "%"+location+"%";	//	like를 위한 '%' 추가.
			nearbyUserList.clear();
			nearbyUserList.addAll(userDAO.selectUsersByLocation(location));
		}
		System.out.println(nearbyUserList);
		Map<Integer, List<UserVO>> differentMap = new HashMap<Integer, List<UserVO>>(); // 성격 차이가 적은 순으로 정렬하기 위한 맵.
		//	나중에 user_ideal 생기면 캐릭터 찾는 부분만 변경
		UserCharacterVO userCharacter = userCharacterDAO.selectUserCharacterByUserId(user_id);
		int userCleanLiness = userCharacter.getCleanliness();
		int userWakeupTime = userCharacter.getWakeup_time();
		int userSleepTime =  userCharacter.getSleep_time();
		int userCookingFrequency = userCharacter.getCooking_frequency();
		int userChatter = userCharacter.getChatter();
		int userSnoring = userCharacter.getSnoring();
		for (UserVO nearbyUser : nearbyUserList) {
			UserCharacterVO nearbyUserCharacter = userCharacterDAO.selectUserCharacterByUserId(nearbyUser.getUser_id());
			if(nearbyUserCharacter == null || nearbyUser.getUser_id() == user_id) continue;
			int nearbyUserCleanLiness = nearbyUserCharacter.getCleanliness();
			int nearbyUserWakeupTime = nearbyUserCharacter.getWakeup_time();
			int nearbyUserSleepTime =  nearbyUserCharacter.getSleep_time();
			int nearbyUserCookingFrequency = nearbyUserCharacter.getCooking_frequency();
			int nearbyUserChatter = nearbyUserCharacter.getChatter();
			int nearbyUserSnoring = nearbyUserCharacter.getSnoring();
			int different = 0;													//	성향 차이
			different += Math.abs(userCleanLiness-nearbyUserCleanLiness);
			different += Math.abs(userWakeupTime-nearbyUserWakeupTime);
			different += Math.abs(userSleepTime-nearbyUserSleepTime);
			different += Math.abs(userCookingFrequency-nearbyUserCookingFrequency);
			different += Math.abs(userChatter-nearbyUserChatter);
			different += Math.abs(userSnoring-nearbyUserSnoring);
			List<UserVO> tmpUserList = differentMap.getOrDefault(different, new ArrayList<UserVO>());	//ex:성향차이가 4라면 key가 4인 List에 저장. 
			tmpUserList.add(nearbyUser);
			differentMap.put(different, tmpUserList);
		}
		List<Integer> keyList = new ArrayList<>();
		keyList.addAll(differentMap.keySet());		//	key리스트에 맵의 keySet 저장
		Collections.sort(keyList);					//	key를 오름차순으로 sort.
		for (Integer key : keyList) {
			rcmdUserList.addAll(differentMap.get(key));	//	정렬된 key에 해당하는 userList를 받아서 추천 리스트에 저장.
		}
		System.out.println(rcmdUserList);
		return rcmdUserList;
	}
	
// pw change & return tmpPw (int user_id)
	public String forgotPw(int user_id) {
		UserVO user = selectUserByUserId(user_id);
		String email = user.getUser_email();
		String pw = user.getUser_pw();
		String time = LocalDateTime.now().toString();
		String tmpPw = shalize(email+pw+time);
		Map<String, String> emailPass = new HashMap<>();
		emailPass.put("user_email", email);
		emailPass.put("pw", shalize("tateMate"+tmpPw));
		userDAO.modifyPw(emailPass);
		return tmpPw;
	}
// userVO by pw
	public UserVO selectUserByUserPw(String user_pw) {
		String pw = shalize("tateMate"+user_pw);
		return userDAO.selectUserByUserPw(pw);
	}
	
//modify password
	public int modifyPw(String user_email, String user_pw) {
		String pw = shalize(user_email+user_pw);
		Map<String, String> emailPass = new HashMap<>();
		emailPass.put("user_email", user_email);
		emailPass.put("pw", pw);
		return userDAO.modifyPw(emailPass);
	}
//shalize(SHA256�̿�)
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
