package com.dgit.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dgit.domain.MemberVO;
import com.dgit.service.MemberService;
import com.mysql.fabric.xmlrpc.base.Member;

@RestController
@RequestMapping("/member")
public class MemberController {
	
	@Autowired
	private MemberService service;
	
	@RequestMapping(value = "/",method=RequestMethod.GET)
	public ResponseEntity<List<MemberVO>> listAll(){
		ResponseEntity<List<MemberVO>> entity = null;
		try {
			List<MemberVO> list = service.listMember();
			entity = new ResponseEntity<List<MemberVO>>(list,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<List<MemberVO>>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	@RequestMapping(value="/",method=RequestMethod.POST)
	public ResponseEntity<String> insertMembr(@RequestBody MemberVO vo){
		ResponseEntity<String> entity = null;
		try {
			service.addMember(vo);
			entity = new ResponseEntity<String>("success",HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>("fail",HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	@RequestMapping(value="/{userid}",method=RequestMethod.DELETE)
	public ResponseEntity<String> deleteMember(@PathVariable("userid") String userid){
		ResponseEntity<String> entity = null;
		try {
			service.removeMember(userid);
			entity = new ResponseEntity<String>("success",HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>("fail",HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	
	
	@RequestMapping(value="/{userid}",method={RequestMethod.PUT})
	public ResponseEntity<MemberVO> readMember(@PathVariable("userid") String userid){
		ResponseEntity<MemberVO> entity = null;
		try {
			MemberVO vo = service.readMember(userid);
			entity = new ResponseEntity<MemberVO>(vo,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<MemberVO>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}

	@RequestMapping(value="/{userid}",method={RequestMethod.PATCH})
	public ResponseEntity<String> updateMember(@PathVariable("userid") String userid,@RequestBody MemberVO vo){
		
		ResponseEntity<String> entity = null;
		try {
			service.modifyMember(vo);
			entity = new ResponseEntity<String>("success",HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>("fail",HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
}
