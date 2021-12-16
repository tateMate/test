package edu.test.demo.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import edu.test.demo.vo.ComentVO;

@Repository
@Mapper
public interface ComentDAO {
	public ComentVO selectComentByComentId(int coment_id);
}
