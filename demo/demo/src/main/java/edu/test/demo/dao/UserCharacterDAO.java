package edu.test.demo.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import edu.test.demo.vo.UserCharacterVO;


@Repository
@Mapper
public interface UserCharacterDAO {
	public UserCharacterVO selectUserCharacterByUserId(int user_id);
}
