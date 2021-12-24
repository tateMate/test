package edu.test.demo.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import edu.test.demo.vo.UserCharacterVO;
import edu.test.demo.vo.UserVO;


@Repository
@Mapper
public interface UserCharacterDAO {
	public UserCharacterVO selectUserCharacterByUserId(int user_id);	//���� ���̵�� Ư�� ���� 1���� �޾ƿ�
	public int modifyUserCharacter(UserCharacterVO vo);		//modify user character
}
