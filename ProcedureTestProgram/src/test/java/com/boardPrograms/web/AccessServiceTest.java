package com.boardPrograms.web;

import com.boardPrograms.web.board.dao.AccessDAO;
import com.boardPrograms.web.board.model.AccessVO;
import com.boardPrograms.web.board.model.Params;
import com.boardPrograms.web.board.service.AccessService;

import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/root-context.xml", "classpath:spring/dataSource-context.xml", "classpath:spring/appServlet/servlet-context.xml"})
public class AccessServiceTest {
	
	private static final Logger logger = LoggerFactory.getLogger(AccessServiceTest.class);
	private static final String namespace = "com.boardPrograms.web.board.boarsMapper";
	
	@Autowired
	private AccessService accessService;
	
	@Autowired
	private DataSourceTransactionManager transactionManager;
	
	private static final String queryId = "com.boardPrograms.web.board.boarsMapper";
	
	@Autowired
	SqlSession sqlSession;
	
	ResultSet rs = null;
	
	@Test
	public void testGetEmpList() {
		
		Map<String, Object> params = new HashMap<String, Object>();
		
		ArrayList<Params> lists = new ArrayList<Params>();
		
		params.put("CAMP_ID", "C");
		params.put("CAMP_STAT_CD", "중지");
		params.put("SCENARIO_NAME", "NO");
		params.put("CAMP_NAME", "테스트-통합테스트용");
		params.put("CAMP_COUNT", 1);
		params.put("GRP_VDN", "6001");
		params.put("GRP_NAME", "가입자아웃바운드");
		params.put("TR_NAME", "GH_TEST");
		params.put("CALLLIST_NAME", "U00120090904CL");
		
		Params paramVO = null;
		
		List<Object> resultList = accessService.executeProcPostgreSQL("com.boardPrograms.web.board.boardsMapper.getAccessList", params, Object.class);
		
		//logger.info("list" + resultList.get(0));
		
		//logger.info("list" + resultList.get(0).getClass().getName() getCampID().toString());
		
		try {
			//if(!resultList.isEmpty()) {
				
				/*for(int i = 0; i < resultList.size(); i++) {
					logger.info("result " + resultList.get(i).getCampID());
					logger.info("result " + resultList.get(i).getsAccount());
					logger.info("result " + resultList.get(i).getsCallListName());
					logger.info("result " + resultList.get(i).getsFieldName());
					logger.info("result " + resultList.get(i).getsFilterSect());
					logger.info("result " + resultList.get(i).getsPreNext());
					logger.info("result " + resultList.get(i).getsText());
					logger.info("result " + resultList.get(i).getsWorkSect());
					System.out.println("result " + resultList.get(i).getiSequence());
				}*/
				
				
				
				/*
				HashMap<String, Object> mapVO = (HashMap<String, Object>) resultList.get(0);
				
				//Params mapVO = (Params) list.get(0);
				
				//List<Params> mapVO = (HashMap<String, Object>) list.get(0);
				
				System.out.println("map" + mapVO);
				
				rs = (ResultSet) mapVO.get("result");

				System.out.println("rs" + rs);
				
				ResultSetMetaData rsmd = rs.getMetaData();
				int nColumn = rsmd.getColumnCount();
				
				while (rs.next()) {
					Params paramDto = new Params();
					
					paramDto.setCampID(rs.getString("CAMP_ID"));
					paramDto.setsWorkSect(rs.getString("CAMP_STAT_CD"));
					paramDto.setsCallListName(rs.getString("SCENARIO_NAME"));
					paramDto.setsPreNext(rs.getString("CAMP_NAME"));
					paramDto.setiSequence(rs.getInt("CAMP_COUNT"));
					paramDto.setsFieldName(rs.getString("GRP_VDN"));
					paramDto.setsAccount(rs.getString("SCENARIO_NAME"));
					paramDto.setsText(rs.getString("TR_NAME"));
					paramDto.setsFilterSect(rs.getString("CALLLIST_NAME"));
					
					lists.add(paramDto);
					System.out.println("list value" + paramDto.getCampID());
				}
				*/
				
				
				
				
				
				
				
				/*
				Params mapVO = resultList.get(0);
				
				for(int i = 0; i < resultList.size(); i++) {
					
					Params pa = resultList.get(i);
					
					logger.info(resultList.get(i).getCampID());
					
					
					Params paramVO = (Params) resultList.get(i);
					logger.info(paramVO.getCampID());	
				}
				*/
				
				
				/*
				 * paramVO = (Params) resultList.get(0);
				 * 
				 * logger.info("paramVO" + paramVO);
				 */
				
				
				/*
				List<Params> paramVo = (List<Params>) resultList.get(0);
				
				System.out.println("param" + paramVo);
				
				rs = (ResultSet) paramVo.get(0);
				
				ResultSetMetaData rsmd = rs.getMetaData();
				int nColumn = rsmd.getColumnCount();
				
				while (rs.next()) {
					for (int i = 0; i < nColumn; i++) {
						System.out.println(rsmd.getColumnName(i + 1) + rs.getString(i + 1) + "\t");
					}
					System.out.println();
				}
				
				while (rs.next()) {
					Params paramDto = new Params();

					paramDto.setCampID(rs.getString("CAMP_ID"));
					paramDto.setsWorkSect(rs.getString("CAMP_STAT_CD"));
					paramDto.setsCallListName(rs.getString("sCalllist_Name"));
					paramDto.setsPreNext(rs.getString("sPre_Next"));
					paramDto.setiSequence(rs.getInt("sPre_Next"));
					paramDto.setsFieldName(rs.getString("sFieldName"));
					paramDto.setsAccount(rs.getString("sAccount"));
					paramDto.setsText(rs.getString("sText"));
					paramDto.setsFilterSect(rs.getString("sFilterSect"));
					
					lists.add(paramDto);
					System.out.println("list value" + paramDto.getCampID());
				}
				*/
				
				
				/*
				for (int i = 0; i < resultList.size(); i++) {
					Params testpVo = resultList.get(i);
				}
				
				List<Params> map = (List<Params>) resultList.get(0);
				logger.info("map" + map);
				*/
				
				
				
				/*
				 * ResultSet rs = (ResultSet) map.get(0);
				 * 
				 * ResultSetMetaData rsmd = rs.getMetaData(); int nColumn =
				 * rsmd.getColumnCount();
				 * 
				 * for (int i = 0; i < nColumn; i++) { System.out.println(rsmd.getColumnName(i +
				 * 1) + "\t"); }
				 * 
				 * while(rs.next()) { for(int i = 0; i < nColumn; i++) {
				 * System.out.println(rs.getString(i + 1) + "\t"); } System.out.println(); }
				 */
				
				//Map<String, Object> map = (Map<String, Object>) resultList.get(0);
				
				
				/*
				 * List<Params> paramvalue = (List<Params>) resultList.get(0);
				 * 
				 * logger.info("list" + paramvalue);
				 * 
				 * //ResultSet rsd = (ResultSet) map.get(0);
				 * 
				 * //Iterator<AccessVO> paramValue = resultList.get(0);
				 * 
				 * Iterator<Params> paramValue = resultList.iterator(); logger.info("map" +
				 * paramValue);
				 * 
				 * for(int i =0; i < resultList.size(); i++) {
				 * 
				 * }
				 * 
				 * while(paramValue.hasNext()) { access = paramValue.next();
				 * System.out.println("data" + access.getCampID()); }
				 * 
				 * //paramVo.setRef_result(resultList.get(0));
				 * 
				 * logger.info("map" + map);
				 */
				/*
				 * ResultSet rs = (ResultSet) map.get("result");
				 * 
				 * //rs = (ResultSet) paramValue.getRef_result("re");
				 * 
				 * ResultSetMetaData rsmd = rs.getMetaData(); int nColumn =
				 * rsmd.getColumnCount();
				 */
				
				/*
				for (int i = 0; i < nColumn; i++) {
					Params pa = resultList.get(i);
					System.out.println(pa.getCampID());
					System.out.println(rsmd.getColumnName(i + 1));
				}
				
				while (rs.next()) {
					for (int i = 0; i < nColumn; i++) {
						paramValue.setCampID(rsmd.getColumnName(i + 1));
						System.out.println(rsmd.getColumnName(i + 1) + rs.getString(i + 1));
					}
					System.out.println();
				}
				*/
				 
				
				//ResultSet rs = (ResultSet) resultList.get(0);
				
				//Iterator<Params> iterator = resultList.iterator();
				
				
				/*
				ResultSetMetaData rsmd = rs.getMetaData();
				int nColumn = rsmd.getColumnCount();
				                    
				for (int i = 0; i < nColumn; i++) {
					System.out.println(rsmd.getColumnName(i + 1) + "\t");
				}
				
				while (rs.next()) {
					for (int i = 0; i < nColumn; i++) {
						System.out.println(rs.getString(i + 1) + "\t");
					}
					System.out.println();
				}
				 
				
				//
				 * 
				 *  
				 * 
				 * 
				 * ResultSet rs = map.get("SP_ACS_CAMP_INFO")
				 */
			//}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		/*
		ResultSet rs = (ResultSet) resultList.get(1);
		
	
		Iterator<Map<String, String>> iterator = resultList.iterator();
		
		
		
		
		
				 * 
				 * ResultSetMetaData rsmd = rs.getMetaData(); int nColumns =
				 * rsmd.getColumnCount();
				 * 
				 * for (int i = 0; i < nColumns; i++) { System.out.print("result" +
				 * rsmd.getColumnName(i + 1) + "\t"); }
				 * 
				 * while (rs.next()) { for (int i = 0; i < nColumns; i++) {
				 * System.out.print("result" + rs.getString(i + 1) + "\t"); }
				 * System.out.println(); } }
				 */
		
		/*	
			  while( iterator.hasNext() ) { empVO = iterator.next(); System.out.println(
			  "[mirinae.maru] EmpVO["+i+"] : " + empVO.getsFieldName() +"\t" +
			  empVO.getsWorkSect() + "\t" + empVO.getiSequence() + "\t" +
			  empVO.getsAccount() + "\t" + empVO.getsCallListName() + "\t" +
			  empVO.getsFilterSect() + "\t" + empVO.getsPreNext() + "\t" +
			  empVO.getsText());
			  
			  logger.info(empVO.getsCallListName() + empVO.getsFieldName() +
			  empVO.getsAccount()); i++; }*/
		
		
		/*
		  logger.info("list" + ref_result);
		  
		  for (String k : ref_result.keySet()) { logger.info(k + ":" +
		  ref_result.get(k)); }
		  
		  ResultSet rs = (ResultSet) ref_result.get("result"); logger.info("rs" + rs);
		 
		try {
			ResultSetMetaData rsmd = rs.getMetaData();
			int nColumns = rsmd.getColumnCount();
			
			for (int i = 0; i < nColumns; i++) {
				System.out.print(rsmd.getColumnName(i + 1) + "\t");
			}
			
			while (rs.next()) {
				for (int i = 0; i < nColumns; i++) {
					System.out.print(rs.getString(i + 1) + "\t");
				}
				System.out.println();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	
		
		
		/*
		 * Params params = new Params();
		 * 
		 * params.setCampID("C"); params.setsWorkSect("중지");
		 * params.setsCallListName("NO"); params.setsPreNext("테스트-통합테스트용");
		 * params.setiSequence(3069); params.setsFieldName("6001");
		 * params.setsAccount("가입자아웃바운드"); params.setsText("GH_TEST");
		 * params.setsFilterSect("U00120090904CL");
		 */
	
		
		//Cursor<AccessVO> accessList = (Cursor<AccessVO>) accessService.getAccessList(params); 
		
		//System.out.println("list" + ref_result.toString());
		
		//Map<String, Object> access = (Map<String, Object>) ref_result.get(0);
		//logger.info("access" + access);
		
		
			
			/*
			 * if (!ref_result.isEmpty()) { ResultSet rs = (ResultSet)
			 * ref_result.get("ref_result"); logger.info("rs" + rs);
			 * 
			 * // int i = 0; //AccessVO empVO = null; //Iterator<AccessVO> iterator =
			 * ref_result.iterator();
			 * 
			 * ResultSetMetaData rsmd = rs.getMetaData(); int nColumns =
			 * rsmd.getColumnCount();
			 * 
			 * for (int i = 0; i < nColumns; i++) { System.out.print("result" +
			 * rsmd.getColumnName(i + 1) + "\t"); }
			 * 
			 * while (rs.next()) { for (int i = 0; i < nColumns; i++) {
			 * System.out.print("result" + rs.getString(i + 1) + "\t"); }
			 * System.out.println(); } }
			 */
	
		/*
		 * while( iterator.hasNext() ) { empVO = iterator.next(); System.out.println(
		 * "[mirinae.maru] EmpVO["+i+"] : " + empVO.getsFieldName() +"\t" +
		 * empVO.getsWorkSect() + "\t" + empVO.getiSequence() + "\t" +
		 * empVO.getsAccount() + "\t" + empVO.getsCallListName() + "\t" +
		 * empVO.getsFilterSect() + "\t" + empVO.getsPreNext() + "\t" +
		 * empVO.getsText());
		 * 
		 * logger.info(empVO.getsCallListName() + empVO.getsFieldName() +
		 * empVO.getsAccount()); i++; }
		 */
	} 	
}
			