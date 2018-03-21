package com.dgit.controller;

import java.awt.image.SampleModel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dgit.domain.SampleVO;

//class안의 모든 함수에 @ResponseBody를 자동으로 붙여줌, 하지만. jsp view화면으로 이동은 불가함. 그냥 json데이터만 전송 가능 별도로 구현 해야함
@RestController
@RequestMapping("/json/*")
public class JSONController {
	private static final Logger logger = LoggerFactory.getLogger(JSONController.class);
	
	@RequestMapping("/hello")
	public @ResponseBody String sayHello(){
		return "hello";
	}
	
	@RequestMapping("/map")
	public @ResponseBody HashMap<String, String> testMap(){
		HashMap<String, String> map = new HashMap<>();
		map.put("key1", "값1");
		map.put("key2", "값2");
		map.put("key3", "값3");
		return map;
	}
	
	@RequestMapping("/sendVO")
	public SampleVO sendVO(){
		SampleVO vo = new SampleVO();
		vo.setFirstName("길동");
		vo.setLastName("홍");
		vo.setMno(111);
		return vo;
	}
	
	@RequestMapping("/sendList")
	public List<SampleVO> sendList(){
		ArrayList<SampleVO> list = new ArrayList<>();
		for(int i=1;i<11;i++){
			SampleVO vo = new SampleVO();
			vo.setFirstName("길동"+i);
			vo.setLastName("홍");
			vo.setMno(i);
			list.add(vo);
		}
		
		return list;
	}
	
	@RequestMapping("/sendListAuth")
	public ResponseEntity<Void> sendListAuth(){
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);//400error error와 값을 같이 보낼때 ResponseEntity
	}
	
	@RequestMapping("/sendVOAuth")
	public ResponseEntity<SampleVO> sendVOAuth(){
		SampleVO vo = new SampleVO();
		vo.setFirstName("길동");
		vo.setLastName("홍");
		vo.setMno(111);
		
		return new ResponseEntity<SampleVO>(vo,HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping("/sendMapAuth")
	public ResponseEntity<Map<String,String>> sendMapAuth(String test,int test2){
		//매개변수를 받을때 int는 공백이면 error지만 String null이여도 오류 나지 않는다(int의 경우 parseint를 하게 되는데 공백은 오류를 유발하고, String은 null이여도 상관없다.
		HashMap<String, String> map = new HashMap<>();
		map.put("key1", test);
		map.put("key2", test2+" string");
		
		return new ResponseEntity<Map<String,String>>(map,HttpStatus.OK);
	}
}
