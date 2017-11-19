package springbook.learningtest.jdk;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.junit.Test;
import org.springframework.aop.framework.ProxyFactoryBean;

public class ReflectionTest {
	
	@Test
	public void invokeMethod() throws Exception{
		
		String name = "Spring";
		
		// length()
		assertThat(name.length(), is(6));
		Method lengthMethod = String.class.getMethod("length");
		assertThat((Integer)lengthMethod.invoke(name), is(6));
		
		// charAt()
		assertThat(name.charAt(0), is('S'));
		Method charAtMethod = String.class.getMethod("charAt", int.class);
		assertThat((Character)charAtMethod.invoke(name, 0), is('S'));
		
	}
	
	
	@Test
	public void simpleProxy(){
		
		Hello hello = new HelloTarget();
		assertThat(hello.sayHello("Toby"), is("Hello Toby"));
		assertThat(hello.sayHi("Toby"), is("Hi Toby"));
		assertThat(hello.sayThankYou("Toby"), is("Thank You Toby"));
		
		Hello proxiedHello = (Hello)Proxy.newProxyInstance(
				getClass().getClassLoader(), 
				new Class[] {Hello.class}, 
				new UppercaseHandler(new HelloTarget())
				);
		
		assertThat(proxiedHello.sayHello("Toby"), is("HELLO TOBY"));
		assertThat(proxiedHello.sayHi("Toby"), is("HI TOBY"));
		assertThat(proxiedHello.sayThankYou("Toby"), is("THANK YOU TOBY"));
		
	}

	static class UppercaseAdvice implements MethodInterceptor{
		
		// MethodInterceptor는 여러 프록시가 공유해서 사용할 수 있다. 
		// 그러기 위해서  MethodInterceptor 오브젝트는 타깃 정보를 갖고 있지 않도록 만들었다.
		// 그 덕분에 MethodInterceptor를 스프링의 싱글톤 빈으로 등록할 수 있었다.
		
		@Override
		public Object invoke(MethodInvocation invocation) throws Throwable {
			// 리플렉션의 Method와 달리 메소드 실행시 타킷오브젝트를 전달할 필요가 없다. 
			// MethodInvocation은 메소드 정보와 함께 타깃 오브젝트를 알고 있기 때문이다.
			String ret = (String) invocation.proceed();
			return ret.toUpperCase(); // 부가 기능 적용
		}
	}
	
	static interface Hello{
		String sayHello(String name);
		String sayHi(String name);
		String sayThankYou(String name);
	}
	
	static class HelloTarget implements Hello{
		@Override
		public String sayHello(String name) {
			return "Hello " + name;
		}

		@Override
		public String sayHi(String name) {
			return "Hi " + name;
		}

		@Override
		public String sayThankYou(String name) {
			return "Thank You " + name;
		}
	}
	
	
	@Test
	public void proxyFactoryBean(){
		
		ProxyFactoryBean pfBean = new ProxyFactoryBean();
		pfBean.setTarget(new HelloTarget());
		// 부가기능을 담은 어드바이스를 추가한다. 여러개를 추가할 수 있다.
		pfBean.addAdvice(new UppercaseAdvice()); // 
		
		Hello proxiedHello = (Hello) pfBean.getObject();
		assertThat(proxiedHello.sayHello("Toby"), is("HELLO TOBY"));
		assertThat(proxiedHello.sayHi("Toby"), is("HI TOBY"));
		assertThat(proxiedHello.sayThankYou("Toby"), is("THANK YOU TOBY"));
		
	}

}
