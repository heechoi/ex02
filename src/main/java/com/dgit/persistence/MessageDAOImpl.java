package com.dgit.persistence;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dgit.domain.MessageVO;

@Repository
public class MessageDAOImpl implements MessageDAO {
	
	private static final String namespace = "com.dgit.mapper.MessageMapper";
	@Autowired
	SqlSession session;
	
	@Override
	public void create(MessageVO vo) throws Exception {
		session.insert(namespace+".create",vo);
	}

	@Override
	public MessageVO readMessage(int mno) throws Exception {
		return session.selectOne(namespace+".readMessage",mno);
	}

	@Override
	public void updateState(int mno) throws Exception {
		session.update(namespace+".updateState",mno);
	}

}
