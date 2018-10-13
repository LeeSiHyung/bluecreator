package springbook.service.tx;

import java.lang.reflect.Proxy;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

public class TxProxyFactoryBean implements FactoryBean<Object>{
	
	Object target;
	PlatformTransactionManager transactionManager;
	String pattern;
	Class<?> serviceInterface;
	

	@Override
	public Object getObject() throws Exception {
		// TransactionHandler 클래스는 InvocationHandler 구현해서 프록시의 부가기능을 제공해준다.
		TransactionHandler txHandler = new TransactionHandler();
		txHandler.setTarget(target);
		txHandler.setTransactionManager(transactionManager);
		txHandler.setPattern(pattern);
		
		// 객체를 생성할 수 없는 프록시를 빈으로 설정하기 위해서 FactoryBean를 구현해서 getObject를 통해 Bean을 등록한다.
		return Proxy.newProxyInstance(
				getClass().getClassLoader(), 
				new Class[]{serviceInterface}, 
				txHandler);
	}

	@Override
	public Class<?> getObjectType() {
		return serviceInterface;
	}

	@Override
	public boolean isSingleton() {
		return false;
	}

	public void setTarget(Object target) {
		this.target = target;
	}

	public void setTransactionManager(PlatformTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	public void setServiceInterface(Class<?> serviceInterface) {
		this.serviceInterface = serviceInterface;
	}

}
