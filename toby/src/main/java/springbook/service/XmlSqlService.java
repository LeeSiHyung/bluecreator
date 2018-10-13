package springbook.service;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import springbook.exception.SqlNotFoundException;
import springbook.exception.SqlRetrievalFailureException;
import springbook.jaxb.SqlType;
import springbook.jaxb.Sqlmap;
import springbook.service.sql.SqlReader;
import springbook.service.sql.SqlRegistry;
import springbook.service.sql.SqlService;

public class XmlSqlService implements SqlService, SqlRegistry, SqlReader{
	
	private Map<String, String> sqlMap = new HashMap<String, String>();
	
	private SqlReader sqlReader;
	private SqlRegistry sqlRegistry;
	
	private String sqlmapFile;
	

	@Override
	public String findSql(String key) throws SqlNotFoundException {
		String sql = sqlMap.get(key);
		if(sql == null) 
			throw new SqlNotFoundException(key + "를 이용해서 SQL을 찾을 수 없습니다.");
		else
			return sql;
	}

	@Override
	public void registerSql(String key, String sql) {
		sqlMap.put(key, sql);
	}
	
	
	@Override
	public void read(SqlRegistry sqlRegistry) {
		String contextPath = Sqlmap.class.getPackage().getName();
		try {
			JAXBContext context = JAXBContext.newInstance(contextPath);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			InputStream is = getClass().getResourceAsStream(sqlmapFile);
			Sqlmap sqlmap = (Sqlmap) unmarshaller.unmarshal(is);
			for(SqlType sql : sqlmap.getSql()){
				sqlRegistry.registerSql(sql.getKey(), sql.getValue());
			}
			
		} catch (JAXBException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	@PostConstruct
	public void loadSql() {
		this.sqlReader.read(this.sqlRegistry);
	}


	@Override
	public String getSql(String key) throws SqlRetrievalFailureException {
		try{
			return this.sqlRegistry.findSql(key);
		}
		catch(SqlNotFoundException e){
			throw new SqlRetrievalFailureException(e);
		}
	}


	public void setSqlmapFile(String sqlmapFile) {
		this.sqlmapFile = sqlmapFile;
	}


	public void setSqlReader(SqlReader sqlReader) {
		this.sqlReader = sqlReader;
	}


	public void setSqlRegistry(SqlRegistry sqlRegistry) {
		this.sqlRegistry = sqlRegistry;
	}



}
