package springbook.dao;



import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.HSQL;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.oxm.Unmarshaller;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import springbook.service.sql.EmbeddedDbSqlRegistry;
import springbook.service.sql.OxmSqlService;
import springbook.service.sql.SqlMapConfig;
import springbook.service.sql.SqlRegistry;
import springbook.service.sql.SqlService;


@Configuration
public class SqlServiceContext {
	
	@Autowired
	SqlMapConfig sqlMapConfig;
	
	@Bean
	public SqlService sqlService(){
		OxmSqlService sqlService = new OxmSqlService();
		sqlService.setUnmarshaller(unmarshaller());
		sqlService.setSqlRegistry(sqlRegistry());
		//sqlService.setSqlmap(new ClassPathResource("sqlmap.xml", OxmSqlService.class));
		sqlService.setSqlmap(sqlMapConfig.getSqlMapResource());
		return sqlService;
	}
	
	@Bean
	public SqlRegistry sqlRegistry(){
		EmbeddedDbSqlRegistry sqlRegistry = new EmbeddedDbSqlRegistry();
		//sqlRegistry.setDataSource(embeddedDatabase);
		sqlRegistry.setDataSource(embeddedDatabase());
		return sqlRegistry;
	}
	
	@Bean
	public Unmarshaller unmarshaller(){
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setContextPath("springbook.jaxb");
		return marshaller;
	}
	
	@Bean
	public DataSource embeddedDatabase(){
		return new EmbeddedDatabaseBuilder()
		.setName("embeddedDatabase")
		.setType(HSQL)
		.addScript("classpath:springbook/service/sql/schema.sql")
		.build();
	}
	

}
