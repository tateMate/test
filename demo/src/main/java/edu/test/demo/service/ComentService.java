package edu.test.demo.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.test.demo.dao.ComentDAO;
import edu.test.demo.vo.CocomentVO;
import edu.test.demo.vo.ComentVO;

@Service
public class ComentService {
	@Autowired
	ComentDAO comentDAO;
//´ñ±Û ¾ÆÀÌµð·Î Æ¯Á¤ ´ñ±Û 1°³¸¦ ¹Þ¾Æ¿È	
	public ComentVO selectComentByComentId(int coment_id) {
		return comentDAO.selectComentByComentId(coment_id);
	}
//´ñ±ÛÀ» ¹Þ´Â »ç¶÷ ¾ÆÀÌµð·Î ´ñ±Û ¸ñ·ÏÀ» °¡Á®¿È
	public List<ComentVO> selectComentByComentIdTo(int coment_id_to){
		return comentDAO.selectComentByComentIdTo(coment_id_to);
	}
//´ñ±Û ÀÔ·Â
	public int insertComent(ComentVO vo) {
		//vo.setComent_time(Timestamp.valueOf(LocalDateTime.now()));
		return comentDAO.insertComent(vo);
	}

	
}
