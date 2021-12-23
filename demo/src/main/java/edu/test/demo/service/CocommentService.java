package edu.test.demo.service;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.test.demo.dao.CocommentDAO;
import edu.test.demo.dao.CommentDAO;
import edu.test.demo.vo.CocommentVO;
import edu.test.demo.vo.NicknamedCocommentVO;
import edu.test.demo.vo.NicknamedCommentVO;

@Service
public class CocommentService {
	@Autowired
	CocommentDAO cocommentDAO;
	@Autowired
	CommentDAO commentDAO;
	
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
	
//JSONArray받기 ([{"comment":comment, "cocomment":[cocomment]}])
	public JSONArray selectCocommentByCommentIdTo(int user_id) {
		JSONArray ja = new JSONArray();
		List<NicknamedCommentVO> coList = commentDAO.selectNicknamedCommentByCommentIdTo(user_id);
		for(NicknamedCommentVO comment:coList) {
			List<NicknamedCocommentVO> cocoList = cocommentDAO.selectNicknamedCocommentByCommentId(comment.getComment_id());
			JSONObject jo = new JSONObject();
			jo.put("comment", comment);
			jo.put("cocomment", cocoList);
			ja.add(jo);
		}
		return ja;
	}
	
	
	
	
	
}
