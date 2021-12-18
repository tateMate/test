package edu.test.demo.dao;

import java.util.*;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import edu.test.demo.vo.CocomentVO;

@Repository
@Mapper
public interface CocomentDAO {
	public CocomentVO selectCocomentByCocomentId(int cocoment_id);//대댓글 아이디로 특정 대댓글을 1개 받아옴
	public List<CocomentVO> selectCocomentByComentId(int coment_id);//댓글 아이디로 대댓글 목록을 받아옴
}
