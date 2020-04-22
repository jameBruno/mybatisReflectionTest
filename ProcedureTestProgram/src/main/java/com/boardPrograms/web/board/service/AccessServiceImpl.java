package com.boardPrograms.web.board.service;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.Properties;

import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.object.SqlQuery;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.boardPrograms.web.board.dao.AccessDAO;
import com.boardPrograms.web.board.model.AccessVO;
import com.boardPrograms.web.board.model.DBtable;
import com.boardPrograms.web.board.model.Params;

@Component
@Transactional(isolation = Isolation.READ_UNCOMMITTED, propagation=Propagation.REQUIRED, rollbackFor=SQLException.class)
public class AccessServiceImpl implements AccessService {
	
	private static final String namespace = "com.boardPrograms.web.board.boardsMapper";
	
	@Autowired
	AccessDAO accessDAO;
	AccessVO accessVO;
	
	@Autowired
	private SqlSession sqlSession;
	
	@Autowired
	private DataSourceTransactionManager transactionManager;
	
	private Log log = LogFactory.getLog(getClass());
	
	public AccessServiceImpl(AccessDAO accessDAO, SqlSession sqlSession) {
		this.accessDAO = accessDAO;
		this.sqlSession = sqlSession;
	}
	
	@Transactional(isolation = Isolation.READ_UNCOMMITTED, propagation=Propagation.REQUIRED, rollbackFor=SQLException.class)
	public Cursor<Object> getAccessListCursor(Map<String, Object> param) {
		Cursor<Object> cursor = null;
		try {
			sqlSession.getConnection().setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			cursor = accessDAO.getAccessListCursor(param);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cursor;
	}
	
	/*
	 * @Override
	 * 
	 * @Transactional(isolation = Isolation.READ_UNCOMMITTED, propagation =
	 * Propagation.REQUIRED, rollbackFor = SQLException.class) public
	 * List<Map<String, String>> executeProcPostgreSQL(String queryId, Map<String,
	 * Object> param) {
	 * 
	 * List<Map<String, String>> params = null;
	 * 
	 * try { sqlSession.getConnection().setAutoCommit(false); } catch (Exception e)
	 * { e.printStackTrace(); }
	 * 
	 * try { params = accessDAO.executeProcPostgreSQL(queryId, param);
	 * System.out.println("param" + params); } catch (Exception e) {
	 * e.printStackTrace(); }
	 * 
	 * return params; }
	 */
	
	
	@Override
	@Transactional(isolation = Isolation.READ_UNCOMMITTED, propagation = Propagation.REQUIRED, rollbackFor = SQLException.class)
	public <T> List<T> executeProcPostgreSQL(String queryId, Map<String, Object> param, Class<T> clazz) {
		
		List<T> list = new ArrayList<T>();
		ResultSet rs = null;
		Object object = null;
		
		ArrayList<Params> lists = new ArrayList<Params>();
		//HashMap<String, Object> map = sqlSession
		//HashMap<String, Object> map = (HashMap<String, Object>) sqlSession.selectList(queryId, param);
		
		try {
			sqlSession.getConnection().setAutoCommit(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			Class className = clazz;
			Method m[] = className.getDeclaredMethods();
			
			Params params = new Params();
			
			Field fieldList[] = className.getDeclaredFields();
			
			for(int i = 0; i < fieldList.length; i++) {
				Field fid = fieldList[i];
				System.out.println("name" + fid.getName());
				String CampId = fid.getName().toString();
			}
			
			Method methods[] = clazz.getDeclaredMethods();
			
			methods = clazz.getClass().getMethods();
			System.out.println("method" + methods[0].toString());
			StringBuilder sb = new StringBuilder();
				
			for(Method method : methods) {
				sb.append(method.getName());
				
				Class<?>[] argTypes = method.getParameterTypes();
				sb.append("(");
				
				int size = argTypes.length;
				for(Class<?> argType : argTypes) {
					String argName = argType.getName();
					sb.append(argName + "val");
					System.out.println("arg" + argName);
					if(--size != 0) {
						sb.append(", ");
					}
				}
				sb.append(")");
			}
			
			for(int i=0; i< methods.length; i++) {
				Method me = methods[i];
				System.out.println("name" + me.getName().toString());
				
				System.out.println("declare" + me.getDeclaringClass().toString());
				System.out.println("class" + me.getReturnType());
				clazz = (Class<T>) me.getReturnType();
				System.out.println("class re" + clazz);
			}
			
			Field fields[] = clazz.getDeclaredFields();
			//System.out.println("field" + fields[0].toString());
			
			for(Field field : fields) {
				String fieldName = field.getName();
				System.out.println("field" + fieldName);
			}
			
			for(Field fieldName : className.getDeclaredFields()) {
				fieldName.setAccessible(true);
				
			}
			 
			list = accessDAO.executeProcPostgreSQL(queryId, param);
			System.out.println("list value" + list);
			
			HashMap<String, Object> mapVO = (HashMap<String, Object>) list.get(0);
			
			//Params mapVO = (Params) list.get(0);
			
			//List<Params> mapVO = (HashMap<String, Object>) list.get(0);
			
			System.out.println("map" + mapVO);
			
			rs = (ResultSet) mapVO.get("result");
			
			System.out.println("rs" + rs);
				
			ResultSetMetaData rsmd = rs.getMetaData(); 
			int nColumn = rsmd.getColumnCount();
			
			while(rs.next()) {
				T t = (T) clazz;
				object = t;
				Class<?> zclass = object.getClass();
				
				for (int _iterator = 0; _iterator < rsmd.getColumnCount(); _iterator++) {
					// getting the SQL column name
					String columnName = rsmd.getColumnName(_iterator + 1);
					// reading the value of the SQL column
					Object columnValue = rs.getObject(_iterator + 1);
					System.out.println(columnName + columnValue + "\t");
					
					for (Field field : fields) {
					// iterating over outputClass attributes to check if any attribute has 'Column' annotation with matching 'name' value
						//BeanUtils.setProperty(t, field.getName(), columnValue);
						System.out.println(columnName + columnValue + "\t");
					}
				}
				
				
				list.add(t);
				System.out.println("list" + t.getClass().getName());
				
				/*for(Field field : zclass.getDeclaredFields()) {
					field.setAccessible(true);
					DBtable column = field.getAnnotation(DBtable.class);
					Object value = rs.getObject(column.columnName());
					Class<?> type = field.getType();
					if (isPrimitive(type)) {// check primitive type(Point 5)
						Class<?> boxed = boxPrimitiveClass(type);// box if primitive(Point 6)
						value = boxed.cast(value);
					}
					
					field.set(object, value);
					*/
				}
				
			
			
			/*
			 * list = accessDAO.executeProcPostgreSQL(queryId, param);
			 * 
			 * System.out.println("list value" + list);
			 */
			
			//HashMap<String, Object> mapVO = (HashMap<String, Object>) list.get(0);
			
			//Params mapVO = (Params) list.get(0);
			
			//List<Params> mapVO = (HashMap<String, Object>) list.get(0);
			
			System.out.println("map" + mapVO);
			
			rs = (ResultSet) mapVO.get("result");
			
			System.out.println("rs" + rs);
			
			/*
			 * ResultSetMetaData rsmd = rs.getMetaData(); int nColumn =
			 * rsmd.getColumnCount();
			 */
			
			while (rs.next()) {	
				
				for(int i = 0; i < m.length; i++) {
					String findMethod = m[i].getName();
					
					if(findMethod.equals("setCampID")) {
						try {
							m[i].invoke(params, "CAMP_ID");
							System.out.println("result" + params.getCampID());
						} catch (Exception e) {
							e.printStackTrace();
						}
					} else if(findMethod.equals("setsWorkSect")) {
						try {
							m[i].invoke(params, "CAMP_STAT_CD");
						} catch (Exception e) {
							e.printStackTrace();
						}
					} else if(findMethod.equals("setsCallListName")) {
						try {
							m[i].invoke(params, "SCENARIO_NAME");
						} catch (Exception e) {
							e.printStackTrace();
						}
					} else if(findMethod.equals("setsPreNext")) {
						try {
							m[i].invoke(params, "CAMP_NAME");
						} catch (Exception e) {
							e.printStackTrace();
						}
					} else if(findMethod.equals("setiSequence")) {
						try {
							m[i].invoke(params, 1);
						} catch (Exception e) {
							e.printStackTrace();
						}
					} else if(findMethod.equals("setsFieldName")) {
						try {
							m[i].invoke(params, "GRP_VDN");
						} catch (Exception e) {
							e.printStackTrace();
						}
					} else if(findMethod.equals("setsAccount")) {
						try {
							m[i].invoke(params, "GRP_NAME");
						} catch (Exception e) {
							e.printStackTrace();
						}
					} else if(findMethod.equals("setsText")) {
						try {
							m[i].invoke(params, "TR_NAME");
						} catch (Exception e) {
							e.printStackTrace();
						}
					} else if(findMethod.equals("setsFilterSect")) {
						try {
							m[i].invoke(params, "CALLLIST_NAME");
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					
					System.out.println("me" + findMethod.getClass().getDeclaredFields());
					
					System.out.println("method value " + m[i].toString());
					Method metho = m[i];
				}	
				
				
				Params paramDto = new Params();
				
				Class<T> cls = (Class<T>) paramDto.getClass();
				
				
				paramDto.setCampID(rs.getString("CAMP_ID"));
				paramDto.setsWorkSect(rs.getString("CAMP_STAT_CD"));
				paramDto.setsCallListName(rs.getString("SCENARIO_NAME"));
				paramDto.setsPreNext(rs.getString("CAMP_NAME"));
				paramDto.setiSequence(rs.getInt("CAMP_COUNT"));
				paramDto.setsFieldName(rs.getString("GRP_VDN"));
				paramDto.setsAccount(rs.getString("GRP_NAME"));
				paramDto.setsText(rs.getString("TR_NAME"));
				paramDto.setsFilterSect(rs.getString("CALLLIST_NAME"));
				
				lists.add(paramDto);
				System.out.println("list value" + paramDto.getCampID());
			}
			
			list = (List<T>) lists;
			
			
			
			/*
			 * lists = (ArrayList<Params>) list.get(0);
			 * 
			 * System.out.println("list" + lists);
			 * 
			 * rs = (ResultSet) lists.get(0);
			 * 
			 * System.out.println("rs" + rs);
			 */
			
			/*
			 * rs = (ResultSet) map.get("result");
			 * 
			 * System.out.println("rs" + rs);
			 */
			
			/*
			 * rs = (ResultSet) accessDAO.executeProcPostgreSQL(queryId, param);
			 * 
			 * System.out.println("rs" + rs);
			 */
			
			
			/*
			rs = (ResultSet) list.get(0);
			
			System.out.println("rs" + rs);
			*/
			
			/*
			 * List<Params> paramVo = (List<Params>) list.get(0);
			 * 
			 * System.out.println("param" + paramVo);
			 * 
			 * rs = (ResultSet) paramVo.get(0);
			 * 
			 * ResultSetMetaData rsmd = rs.getMetaData(); int nColumn =
			 * rsmd.getColumnCount();
			 * 
			 * while (rs.next()) { for (int i = 0; i < nColumn; i++) {
			 * System.out.println(rsmd.getColumnName(i + 1) + rs.getString(i + 1) + "\t"); }
			 * System.out.println(); }
			 * 
			 * while(rs.next()) { Params paramDto = new Params();
			 * 
			 * paramDto.setCampID(rs.getString("CAMP_ID"));
			 * paramDto.setsWorkSect(rs.getString("CAMP_STAT_CD"));
			 * paramDto.setsCallListName(rs.getString("sCalllist_Name"));
			 * paramDto.setsPreNext(rs.getString("sPre_Next"));
			 * paramDto.setiSequence(rs.getInt("sPre_Next"));
			 * paramDto.setsFieldName(rs.getString("sFieldName"));
			 * paramDto.setsAccount(rs.getString("sAccount"));
			 * paramDto.setsText(rs.getString("sText"));
			 * paramDto.setsFilterSect(rs.getString("sFilterSect"));
			 * 
			 * lists.add(paramDto); System.out.println("list value" + paramDto.getCampID());
			 * }
			 */
			
			//list = accessDAO.executeProcPostgreSQL(queryId, param);
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	private static Class<?> boxPrimitiveClass(Class<?> type) {
		 if (type == int.class) {
		        return Integer.class;
		    } else if (type == long.class) {
		        return Long.class;
		    } else if (type == double.class) {
		        return Double.class;
		    } else if (type == float.class) {
		        return Float.class;
		    } else if (type == boolean.class) {
		        return Boolean.class;
		    } else if (type == byte.class) {
		        return Byte.class;
		    } else if (type == char.class) {
		        return Character.class;
		    } else if (type == short.class) {
		        return Short.class;
		    } else {
		        String string = "class '" + type.getName() + "' is not a primitive";
		        throw new IllegalArgumentException(string);
		    }
	}

	private static boolean isPrimitive(Class<?> type) {
		 return (type == int.class || type == long.class || type == double.class || type == float.class
		            || type == boolean.class || type == byte.class || type == char.class || type == short.class);
	}

	@Transactional(isolation = Isolation.READ_UNCOMMITTED, propagation=Propagation.REQUIRED, rollbackFor=SQLException.class)
	public Map<String, Object> getAccessList(Map<String, Object> param) {
		
		Map<String, Object> map = null;
		
		try {
			sqlSession.getConnection().setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			map = accessDAO.getAccessList(param);
			System.out.println("map" + map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return map;
		
		//return params.getRef_result();
		
		
		/*
		 * final AccessDAO accessDAO = sqlSession.getMapper(AccessDAO.class);
		 * System.out.println(params.toString()); accessDAO.getAccessList(params);
		 * return params.getRef_result();
		 */
		
		/*
		 * DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		 * def.setName("transaction");
		 * def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		 * def.setPropagationBehavior(TransactionDefinition.ISOLATION_READ_UNCOMMITTED);
		 * 
		 * TransactionStatus status = transactionManager.getTransaction(def);
		 */

		
		/*
		 * System.out.println(params.toString()); accessDAO.getAccessList(params);
		 */
		
		/*
		 * try { //sqlSession.getConnection().setAutoCommit(false);
		 * //accessDAO.setAutoCommit(false); //final AccessDAO accessDAO =
		 * sqlSession.getMapper(AccessDAO.class);
		 * 
		 * } catch (Exception e) { accessDAO.rollback(); } finally {
		 * accessDAO.setAutoCommit(true); }
		 */
		
	
		
		/*
		 * final AccessDAO accessDAO = sqlSession.getMapper(AccessDAO.class);
		 * accessDAO.getAccessList(params); System.out.println(params.toString());
		 */
		
		//transactionManager.commit(status);
		
	}
	
	public AccessDAO getAccessDAO() {
		return accessDAO;
	}
	
	public void setAccessDAO(AccessDAO accessDAO) {
		this.accessDAO = accessDAO;
	}

	
	/*
	@Override
	public Map<String, Object> getAccessList(Params params) {
		// TODO Auto-generated method stub
		Map<String, Object> list = accessDAO.getAccessList(params);
		
		return null;
	}
	*/
	
	/*
	@Override
	public List<AccessVO> listAccessList() {
		return sqlSession.selectList(namespace+".listAccessList");
	}
	*/
	
	/*
	public AccessServiceImpl(AccessDAO accessDAO, SqlSession sqlSession) {
		this.accessDAO = accessDAO;
		//this.sqlSession = sqlSession;
	}
	*/
	
	/*
	@Override
	public List<AccessVO> listAccessList() {
		return sqlSession.selectList(namespace+".listAccessList");
	}
	*/
	
	/*
	@Override
	public List<AccessVO> getAccessList(final Params params) {
		return accessDAO.getAccessList(params);
	}
	*/
	
}
