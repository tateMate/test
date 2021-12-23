package edu.test.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.test.demo.dao.CocommentDAO;
import edu.test.demo.dao.CommentDAO;
import edu.test.demo.vo.CocommentVO;
import edu.test.demo.vo.NicknamedCocommentVO;

@Service
public class CocommentService {
	@Autowired
	CocommentDAO cocommentDAO;
//���� ���̵�� Ư�� ���� 1���� �޾ƿ�
	public CocommentVO selectCocommentByCocommentId(int cocomment_id) {
		return cocommentDAO.selectCocommentByCocommentId(cocomment_id);
	}
//ver nickname
	public NicknamedCocommentVO selectNicknamedCocommentByCocommentId(int cocomment_id) {
		return cocommentDAO.selectNicknamedCocommentByCocommentId(cocomment_id);
	}

//��� ���̵�� ���� ����Ʈ�� �޾ƿ�
	public List<CocommentVO> selectCocommentByCommentId(int comment_id) {
		return cocommentDAO.selectCocommentByCommentId(comment_id);
	}
//ver nickname
	public List<NicknamedCocommentVO> selectNicknamedCocommentByCommentId(int comment_id) {
		return cocommentDAO.selectNicknamedCocommentByCommentId(comment_id);
	}

//���� �Է�
	public int insertCocomment(CocommentVO vo) {
		return cocommentDAO.insertCocomment(vo);
		
	}
}
