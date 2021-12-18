package edu.test.demo.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import edu.test.demo.vo.UserCharacterVO;


@Repository
@Mapper
public interface UserCharacterDAO {
	public UserCharacterVO selectUserCharacterByUserId(int user_id);	//유저 아이디로 특정 성향 1개를 받아옴
}
