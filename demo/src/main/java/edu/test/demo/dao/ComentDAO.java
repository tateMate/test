package edu.test.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import edu.test.demo.vo.CocomentVO;
import edu.test.demo.vo.ComentVO;

@Repository
@Mapper
public interface ComentDAO {
	public ComentVO selectComentByComentId(int coment_id);			//´ñ±Û ¾ÆÀÌµð·Î Æ¯Á¤ ´ñ±Û 1°³¸¦ ¹Þ¾Æ¿È
	public List<ComentVO> selectComentByUserId(int user_id);		//À¯Àú ¾ÆÀÌµð·Î ´ñ±Û ¸ñ·ÏÀ» °¡Á®¿È
	public List<ComentVO> selectComentByComentIdTo(int coment_id_to);//´ñ±ÛÀ» ¹Þ´Â »ç¶÷ ¾ÆÀÌµð·Î ´ñ±Û ¸ñ·ÏÀ» °¡Á®¿È
	public int insertComent(ComentVO comentVO);						//´ñ±Û ÀÔ·Â
}
