package com.maoshen.echo.test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms.Bucket;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONObject;
import com.maoshen.component.elasticsearch.BaseElasticSearchClientHandle;

import junit.framework.TestCase;

@ContextConfiguration(locations = { "classpath*:/applicationContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class EsTest extends TestCase {
	@Test
	public void testEsInsert2() throws Exception {
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < 8; i++) {
			JdxTest jdxTest = new JdxTest(System.currentTimeMillis(), UUID.randomUUID().toString(),
					UUID.randomUUID().toString());
			JdxTestNew jdxTestNew = new JdxTestNew();
			jdxTestNew.setNewId(3031689464994L);
			jdxTestNew.setNewName(UUID.randomUUID().toString() + UUID.randomUUID().toString());
			jdxTestNew.setJdxTest(jdxTest);
			list.add(JSONObject.toJSONString(jdxTestNew));
			Thread.sleep(10);
		}

		Client client = BaseElasticSearchClientHandle.getClient();
		for (int i = 0; i < list.size(); i++) {
			client.prepareIndex("jdxindexnew", "jdxtypenew").setSource(list.get(i)).get();
		}
		System.out.println();
	}

	@Test
	public void testSelect2() throws Exception {
		QueryBuilder qb = QueryBuilders.multiMatchQuery("3031689464994", "newId");
		SearchRequestBuilder searchRequestBuilder = BaseElasticSearchClientHandle.getClient()
				.prepareSearch("jdxindexnew");
		searchRequestBuilder.addAggregation(AggregationBuilders.terms("jdxTest").field("jdxTest.id").size(10000))
				.addSort("jdxTest.id", SortOrder.ASC).setFrom(0).setSize(0);
		SearchResponse response = searchRequestBuilder.setTypes("jdxtypenew").setQuery(qb).execute().actionGet();

		Terms jdxTest = response.getAggregations().get("jdxTest");
		
		List<Bucket> getBucketsList = jdxTest.getBuckets();
		for(Bucket bucket:getBucketsList){
			System.out.println(bucket.getKey().toString());
		};

		SearchHits hit = response.getHits();
		if (hit.totalHits() > 0) {
			for (SearchHit h : hit) {
				System.out.println(JSONObject.toJSONString(h.getSource()));
			}
		}
		System.out.println();
	}

	@Test
	public void testSelect() throws Exception {
		QueryBuilder qb = QueryBuilders.multiMatchQuery("1515842423454", "id");
		SearchRequestBuilder searchRequestBuilder = BaseElasticSearchClientHandle.getClient().prepareSearch("jdxindex");
		SearchResponse response = searchRequestBuilder.setTypes("jdxtype").setQuery(qb).execute().actionGet();
		SearchHits hit = response.getHits();
		if (hit.totalHits() > 0) {
			for (SearchHit h : hit) {
				System.out.println(JSONObject.toJSONString(h.getSource()));
			}
		}
		System.out.println();
	}

	@Test
	public void testEsInsert() throws Exception {
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < 3; i++) {
			JdxTest jdxTest = new JdxTest(System.currentTimeMillis(), UUID.randomUUID().toString(),
					UUID.randomUUID().toString());
			list.add(JSONObject.toJSONString(jdxTest));
		}

		Client client = BaseElasticSearchClientHandle.getClient();
		for (int i = 0; i < list.size(); i++) {
			client.prepareIndex("jdxindex", "jdxtype").setSource(list.get(i)).get();
		}
		System.out.println();
	}

	public class JdxTest {
		private Long id;
		private String name;
		private String desc;

		public JdxTest(Long id, String name, String desc) {
			super();
			this.id = id;
			this.name = name;
			this.desc = desc;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getDesc() {
			return desc;
		}

		public void setDesc(String desc) {
			this.desc = desc;
		}
	}

	public class JdxTestNew {
		private Long newId;

		private String newName;

		private JdxTest jdxTest;

		public Long getNewId() {
			return newId;
		}

		public void setNewId(Long newId) {
			this.newId = newId;
		}

		public String getNewName() {
			return newName;
		}

		public void setNewName(String newName) {
			this.newName = newName;
		}

		public JdxTest getJdxTest() {
			return jdxTest;
		}

		public void setJdxTest(JdxTest jdxTest) {
			this.jdxTest = jdxTest;
		}
	}
}
