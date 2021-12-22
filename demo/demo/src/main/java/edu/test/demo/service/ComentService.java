package edu.test.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.test.demo.dao.ComentDAO;
import edu.test.demo.vo.ComentVO;

@Service
public class ComentService {
	@Autowired
	ComentDAO comentDAO;
	
	public ComentVO selectComentByComentId(int coment_id) {
		return comentDAO.selectComentByComentId(coment_id);
	}

}
