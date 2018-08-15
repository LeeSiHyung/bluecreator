package springbook2.learningtest.ioc;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.context.support.StaticApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations="./test-applicationContext.xml")
@ContextConfiguration(classes={TestAppContext.class})
public class IoCTest {
	
	@Test
	public void ioC(){
		/** ioC 컨테이너 생성 **/
		StaticApplicationContext ac = new StaticApplicationContext();
		
		/** 디폴트 메타정보 **/
		ac.registerSingleton("hello1", Hello.class);
		Hello hello1 = ac.getBean("hello1", Hello.class);
		assertThat(hello1, is(notNullValue()));
		
		/** BeanDefinition 타입의 설정 메타정보 **/
		BeanDefinition helloDef = new RootBeanDefinition(Hello.class);
		helloDef.getPropertyValues().addPropertyValue("name", "Spring");
		ac.registerBeanDefinition("hello2", helloDef);
		
		Hello hello2 = ac.getBean("hello2", Hello.class);
		assertThat(hello2.sayHello(), is("Hello Spring"));
		
		/** 처음 등록한 빈과 두 번째 등록한 빈이 모두 동일한 Hello 클래스지만 별개의 오브젝트로 생성됐다. **/
		assertThat(hello1, is(not(hello2)));
		
		assertThat(ac.getBeanFactory().getBeanDefinitionCount(), is(2));
		
	}
	
	
	@Test
	public void registerBeanWithDependency() {
		
		StaticApplicationContext ac = new StaticApplicationContext();
		ac.registerBeanDefinition("printer", new RootBeanDefinition(StringPrinter.class));
		
		BeanDefinition helloDef = new RootBeanDefinition(Hello.class);
		/** 단순 값을 갖는 프로퍼티 등록 **/
		helloDef.getPropertyValues().addPropertyValue("name", "Spring");
		/** 아이디가 printer인 빈에 대한 레퍼런스를 프로퍼티로 등록 **/
		helloDef.getPropertyValues().addPropertyValue("printer", new RuntimeBeanReference("printer"));
		ac.registerBeanDefinition("hello", helloDef);
		
		Hello hello = ac.getBean("hello", Hello.class);
		hello.print();
		
		assertThat(ac.getBean("printer").toString(), is("Hello Spring"));
		
	}
	
	@Test
	public void genericApplicationContext() {
		
		GenericApplicationContext ac = new GenericApplicationContext();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(ac);
		reader.loadBeanDefinitions(
				"springbook2/learningtest/ioc/genericApplicationContext.xml"
				);
		
		ac.refresh();
		
		Hello hello = ac.getBean("hello", Hello.class);
		hello.print();
		
		assertThat(ac.getBean("printer").toString(), is("Hello Spring"));
	}
	
	
	// 작동 안되는 현상 확인 필요.
	// @Test
	// public void genericApplicationContext2() {
	// 	
	// 	GenericApplicationContext ac = new GenericApplicationContext();
	// 	PropertiesBeanDefinitionReader reader = new PropertiesBeanDefinitionReader(ac);
	// 	reader.loadBeanDefinitions(
	// 			"springbook2/learningtest/ioc/appContext.properties"
	// 			);
	// 	
	// 	ac.refresh();
	// 	
	// 	Hello hello = ac.getBean("hello", Hello.class);
	// 	hello.print();
	// 	
	// 	assertThat(ac.getBean("printer").toString(), is("Hello Spring"));
	// 	
	// }
	
	
	@Test
	public void genericApplicationContext3() {
		
		GenericApplicationContext ac = new GenericXmlApplicationContext(
				"springbook2/learningtest/ioc/genericApplicationContext.xml");
		
		Hello hello = ac.getBean("hello", Hello.class);
		hello.print();
		
		assertThat(ac.getBean("printer").toString(), is("Hello Spring"));
		
	}
	

}
