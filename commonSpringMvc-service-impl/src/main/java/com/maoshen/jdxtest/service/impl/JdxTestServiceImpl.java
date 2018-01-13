package com.maoshen.jdxtest.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.maoshen.component.mongo.MongoBeanUtil;
import com.maoshen.jdxtest.mongo.JdxTest;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

@Service("jdxTestServiceImpl")
public class JdxTestServiceImpl {
	@Autowired
	private MongoTemplate mongoTemplate;

	private final static String COLLECTION_NAME = "jdxTest";

	public List<JdxTest> getItemInfo(String id) throws Exception {
		List<JdxTest> list = new ArrayList<JdxTest>();
		// 判断查询的json中传递过来的参数
		DBObject query = new BasicDBObject();
		query.put("id", id);
		DBCursor results = mongoTemplate.getCollection(COLLECTION_NAME).find(query);
		if (null != results) {
			Iterator<DBObject> iterator = results.iterator();
			while (iterator.hasNext()) {
				BasicDBObject obj = (BasicDBObject) iterator.next();
				JdxTest itemInfo = new JdxTest();
				itemInfo = MongoBeanUtil.dbObject2Bean(obj, itemInfo);
				list.add(itemInfo);
			}
		}
		return list;
	}

}
