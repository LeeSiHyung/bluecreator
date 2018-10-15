package springbook2.learningtest.ioc;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class HelloService {
	
	Printer printer;
	public void setPrinter(Printer printer) {
		this.printer = printer;
	}

	@Bean
	public Hello hello() {
		Hello hello = new Hello();
		//hello.setPrinter(printer());
		hello.setPrinter(this.printer);
		return hello;
	}
	
	@Bean
	public Hello hello2() {
		Hello hello = new Hello();
		//hello.setPrinter(printer());
		hello.setPrinter(this.printer);
		return hello;
	}

	@Bean
	public Printer printer() {
		return new StringPrinter();
	}

	@Test
	public void simpleBeanFactory3() {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(HelloService.class);
		HelloService helloService  = ctx.getBean("helloService", HelloService.class);
		helloService.setPrinter(helloService.printer());
		
		assertThat(helloService.hello().getPrinter(), is(notNullValue()));
		assertThat(helloService.hello2().getPrinter(), is(notNullValue()));
		
		assertThat(helloService.hello().getPrinter(), is(sameInstance(helloService.hello2().getPrinter())));
	}


}
