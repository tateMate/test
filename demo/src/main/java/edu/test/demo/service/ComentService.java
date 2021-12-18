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
//��� ���̵�� Ư�� ��� 1���� �޾ƿ�	
	public ComentVO selectComentByComentId(int coment_id) {
		return comentDAO.selectComentByComentId(coment_id);
	}
//���� ���̵�� ��� ����� ������	
	public List<ComentVO> selectComentByUserId(int user_id){
		return comentDAO.selectComentByUserId(user_id);
	}
	
}
