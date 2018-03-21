package com.dgit.persistence;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PointDAOImpl implements PointDAO {
	private static final String namespace = "com.dgit.mapper.PointMapper";
	
	@Autowired
	private SqlSession session;
	
	@Override
	public void updatePoint(String uid, int point)throws Exception {
		HashMap<String,Object> map = new HashMap<>();
		map.put("uid",uid);
		map.put("point",point);
		session.update(namespace+".updatePoint",map);
	}

}
