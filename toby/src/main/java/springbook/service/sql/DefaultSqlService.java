package springbook.service.sql;

public class DefaultSqlService extends BaseSqlService{
	
	public DefaultSqlService(){
		setSqlReader(new JaxbXmlSqlReader());
		setSqlRegistry(new HashMapSqlRegitry());
	}
}
