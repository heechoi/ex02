package com.dgit.ex02;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dgit.domain.MessageVO;
import com.dgit.persistence.MessageDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class MessageDAOTest {
	
	@Autowired
	MessageDAO dao;
	
	@Test
	public void testCreate()throws Exception{
		MessageVO vo = new MessageVO();
		vo.setSender("user01");
		vo.setMessage("보내는 메시지");
		vo.setTargetid("user00");
		
		dao.create(vo);
	}
	
	//@Test
	public void testReadMessage()throws Exception{
		dao.readMessage(1);
	}
	
	//@Test
	public void testUpdateMessage()throws Exception{
		dao.updateState(1);
	}
}
