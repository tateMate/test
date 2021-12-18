package edu.test.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.test.demo.dao.CocomentDAO;
import edu.test.demo.vo.CocomentVO;

@Service
public class CocomentService {
	@Autowired
	CocomentDAO cocomentDAO;
	
	public CocomentVO selectCocomentByCocomentId(int cocoment_id) {
		return cocomentDAO.selectCocomentByCocomentId(cocoment_id);
	}
}
