package com.boardPrograms.web;


import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.object.SqlQuery;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.boardPrograms.web.board.common.Pagination;
import com.boardPrograms.web.board.dao.AccessDAO;
import com.boardPrograms.web.board.model.AccessVO;
import com.boardPrograms.web.board.model.BoardVO;
import com.boardPrograms.web.board.model.Params;
import com.boardPrograms.web.board.service.AccessService;
import com.boardPrograms.web.board.service.AccessServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ={"file:src/main/resources/spring/appServlet/servlet-context.xml", "file:src/main/resources/spring/dataSource-context.xml"})
//@ContextConfiguration(locations = {"classpath:spring/root-context.xml", "classpath:spring/dataSource-context.xml"})
public class getAccessServiceTest {
	
	private static final Logger logger = LoggerFactory.getLogger(getAccessServiceTest.class);
	
	//@Inject
	//AccessDAO access;
	
	//AccessDAO accessService;
	//@Inject
	@Autowired
	AccessService accessService;
	private SqlSession sqlSession;
	private static final String namespace = "com.boardPrograms.web.board.boarsMapper";
	
	@Test
	public void testGetEmpList() {
		Params params = new Params();
		/*
		params.setsWorkSect("workse");
		params.setsGrpVdn("adasd");
		params.setsCallListName("ada");
		params.setsPreNext("dada");
		params.setiSequence(1);
		params.setsFieldName("fds");
		params.setsAccount("Adad");
		params.setsText("asdas");
		params.setsFilterSect("Ada");
		*/
		
		//params.setAge(1);
		List<AccessVO> empList = accessService.getAccessList(params);
		
		//List<AccessVO> empList = accessService.listAccessList();
		
		//List<AccessVO> empList = sqlSession.selectList(namespace);
		
		System.out.println("list" + empList);
		
		int i = 0;
		AccessVO empVO = null;
		Iterator<AccessVO> iterator = empList.iterator();
		
		while( iterator.hasNext() ) {
			empVO = iterator.next();
			System.out.println( "[mirinae.maru] EmpVO["+i+"] : " + empVO.getiSequence() +"\t" + empVO.getsCallListName() );
			i++;
		}
		
	}
}

