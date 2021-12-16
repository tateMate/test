package edu.test.demo.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import edu.test.demo.vo.CocomentVO;

@Repository
@Mapper
public interface CocomentDAO {
	public CocomentVO selectCocomentByCocomentId(int cocoment_id);
}
