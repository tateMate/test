package edu.test.demo.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import edu.test.demo.vo.UserVO;

@Repository
@Mapper
public interface UserDAO {
	public UserVO selectUser(int user_id);
}
