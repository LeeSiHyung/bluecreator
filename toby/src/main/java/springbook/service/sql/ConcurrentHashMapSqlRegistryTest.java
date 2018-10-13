package springbook.service.sql;



public class ConcurrentHashMapSqlRegistryTest extends AbstractUpdatableSqlRegistryTest{
	
	@Override
	protected UpdatableSqlRegistry createUpdatableSqlRegistry() {
		return new ConcurrentHashMapSqlRegistry();
	}

}
