package edu.test.demo.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import edu.test.demo.vo.ComentVO;
import edu.test.demo.vo.UserVO;

@Repository
@Mapper
public interface UserDAO {
	public List<UserVO> selectUser();					//for a test
	
	public UserVO selectUserByUserId(int user_id);		//user id로 user선택
	public int insertUser(UserVO vo);					//회원가입
	public UserVO selectUserLogin(Map<String, String> id_pass);//로긴
}
