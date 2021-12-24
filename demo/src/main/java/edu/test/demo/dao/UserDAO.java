package edu.test.demo.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import edu.test.demo.vo.UserVO;

@Repository
@Mapper
public interface UserDAO {
	public List<UserVO> selectUser();					//for a test
	
	public UserVO selectUserByUserId(int user_id);		//user id�� user����
	public int insertUser(UserVO vo);					//ȸ������
	public UserVO selectUserLogin(Map<String, String> id_pass);//�α�
<<<<<<< HEAD
=======
	public int modifyUser(UserVO vo);		//modify user
	public UserVO emailCheck(String email);			//	email check
	public UserVO nicknameCheck(String nickname);	//	nickname check
>>>>>>> 479869e... 211224 session userCharacter, modify file check, comment/cocomment user check
}
