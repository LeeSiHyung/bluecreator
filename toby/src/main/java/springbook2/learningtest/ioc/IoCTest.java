package springbook2.learningtest.ioc;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertThat;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.context.support.StaticApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import springbook2.learningtest.ioc.bean.AnnotatedHello;
import springbook2.learningtest.ioc.config.AnnotatedHelloConfig;
import springbook2.learningtest.ioc.config.ServiceConfig;


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
	
	
	@Test
	public void 부모_자식_컨텍스트_테스트() {
		
		String basePath = StringUtils.cleanPath(ClassUtils.classPackageAsResourcePath(getClass()) + "/");
		
		ApplicationContext parent = new GenericXmlApplicationContext(basePath + "parentContext.xml");
		GenericApplicationContext child = new GenericApplicationContext(parent);
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(child);
		reader.loadBeanDefinitions(basePath + "childContext.xml");
		child.refresh();
		
		Printer printer = child.getBean("printer", Printer.class);
		assertThat(printer, is(notNullValue()));
		
		Hello hello = child.getBean("hello", Hello.class);
		assertThat(hello, is(notNullValue()));
		
		hello.print();
		assertThat(printer.toString(), is("Hello Child"));
		
	}
	
	
	@Test
	public void simpleBeanScanning() {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(
				"springbook2.learningtest.ioc.bean");
		
		AnnotatedHello hello  = ctx.getBean("myAnnotatedHello", AnnotatedHello.class);
		
		assertThat(hello, is(notNullValue()));
		
	}
	
	@Test
	public void simpleBeanFactory() {
		ApplicationContext ctx = new AnnotationConfigApplicationContext("springbook2.learningtest.ioc");
		
		// 스테레오타입 애노테이션과 빈 스캐너를 이용한 빈 등록
		AnnotatedHello hello  = ctx.getBean("myAnnotatedHello", AnnotatedHello.class);
		assertThat(hello, is(notNullValue()));
		
	}
	
	
	@Test
	public void simpleBeanFactory2() {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(AnnotatedHelloConfig.class);
		
		// 스테레오타입 애노테이션과 빈 스캐너를 이용한 빈 등록
		AnnotatedHello hello  = ctx.getBean("annotatedHello", AnnotatedHello.class);
		assertThat(hello, is(notNullValue()));
		
		
		// 자바 코드에 의한 빈 등록
		AnnotatedHelloConfig config = ctx.getBean("annotatedHelloConfig", AnnotatedHelloConfig.class);
		assertThat(config, is(notNullValue()));
		
		// 자바 코드에 의한 빈 안에서 생성된 빈과 위의 스테레오타입 애노테이션과 빈 스캐너를 이용한 빈과 같은 빈인지 비교
		assertThat(config.annotatedHello(), is(sameInstance(hello)));
		
	}
	
	
	@Test
	public void simpleBeanFactory3() {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(ServiceConfig.class);
		DataSource dataSource  = ctx.getBean("dataSource", DataSource.class);
		assertThat(dataSource, is(notNullValue()));
	}
	
	
	@Test
	public void simpleAtAutowired() {
		AbstractApplicationContext ac = new AnnotationConfigApplicationContext(BeanA.class, BeanB.class);
		BeanA beanA = ac.getBean(BeanA.class);
		assertThat(beanA.beanB, is(notNullValue()));
	}
	
	
	private static class BeanA{
		@Autowired BeanB beanB;
	}
	private static class BeanB{
		
	}
	
}
