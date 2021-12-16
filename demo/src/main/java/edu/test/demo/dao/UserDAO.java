package edu.test.demo.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import edu.test.demo.vo.UserVO;

@Repository
@Mapper
public interface UserDAO {
	public List<UserVO> selectUser();

	public int insertUser(UserVO vo);

	public UserVO selectUserLogin(Map<String, String> id_pass);
}
