package edu.test.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.test.demo.dao.CocomentDAO;
import edu.test.demo.dao.ComentDAO;
import edu.test.demo.vo.CocomentVO;

@Service
public class CocomentService {
	@Autowired
	CocomentDAO cocomentDAO;
//대댓글 아이디로 특정 대댓글 1개를 받아옴
	public CocomentVO selectCocomentByCocomentId(int cocoment_id) {
		return cocomentDAO.selectCocomentByCocomentId(cocoment_id);
	}

//댓글 아이디로 대댓글 리스트를 받아옴
	public List<CocomentVO> selectCocomentByComentId(int coment_id) {
		return cocomentDAO.selectCocomentByComentId(coment_id);
	}
}
