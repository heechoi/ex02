package com.dgit.ex02;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dgit.domain.MessageVO;
import com.dgit.service.MessageService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class MessageServiceTest {
	
	@Autowired
	MessageService service;
	
	@Test
	public void insertMessageTest()throws Exception{
		MessageVO vo = new MessageVO();
		vo.setSender("user01");
		vo.setMessage("보내는 메시지");
		vo.setTargetid("user00");
		service.addMessage(vo);
	}
	
	//@Test
	public void readMessageTest()throws Exception{
		service.readMessage("user00", 1);
	}
}
