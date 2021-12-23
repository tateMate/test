package edu.test.demo.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.test.demo.dao.CommentDAO;
import edu.test.demo.vo.CocommentVO;
import edu.test.demo.vo.CommentVO;
import edu.test.demo.vo.NicknamedCommentVO;

@Service
public class CommentService {
	@Autowired
	CommentDAO commentDAO;
//��� ���̵�� Ư�� ��� 1���� �޾ƿ�	
	public CommentVO selectCommentByCommentId(int comment_id) {
		return commentDAO.selectCommentByCommentId(comment_id);
	}
//ver nickname
	public NicknamedCommentVO selectNicknamedCommentByCommentId(int comment_id) {
		return commentDAO.selectNicknamedCommentByCommentId(comment_id);
	}
//����� �޴� ��� ���̵�� ��� ����� ������
	public List<CommentVO> selectCommentByCommentIdTo(int comment_id_to){
		return commentDAO.selectCommentByCommentIdTo(comment_id_to);
	}
//ver nickname
	public List<NicknamedCommentVO> selectNicknamedCommentByCommentIdTo(int comment_id_to){
		return commentDAO.selectNicknamedCommentByCommentIdTo(comment_id_to);
	}
//��� �Է�
	public int insertComment(CommentVO vo) {
		//vo.setComment_time(Timestamp.valueOf(LocalDateTime.now()));
		return commentDAO.insertComment(vo);
	}

	
}
