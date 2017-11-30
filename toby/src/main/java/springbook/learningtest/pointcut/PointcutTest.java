package springbook.learningtest.pointcut;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;

public class PointcutTest {
	
	@Test
	public void methodSignaturePointcut() throws NoSuchMethodException, SecurityException{
		
		AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
		// Target 클래스 minus() 메소드 시그니처
		pointcut.setExpression("execution(public int springbook.learningtest.pointcut.Target.minus(int,int) throws java.lang.RuntimeException)");
		
		// Target.minus() 클래스 필터와 메소드 매처를 가져와 각각 비교한다.
		assertThat(
			pointcut.getClassFilter().matches(Target.class) && 
			pointcut.getMethodMatcher().matches(Target.class.getMethod("minus", int.class, int.class), null)
			, is(true)
		);
		
		// Target.plus()
		assertThat(
			pointcut.getClassFilter().matches(Target.class) &&
			pointcut.getMethodMatcher().matches(Target.class.getMethod("plus", int.class, int.class), null)
			, is(false)
		);
		
		// Bean.method()
		assertThat(
			pointcut.getClassFilter().matches(Bean.class) &&
			pointcut.getMethodMatcher().matches(Target.class.getMethod("method"), null)
			, is(false)
		);
		
	}
	
	public void pointcutMatches(String expression, Boolean expected, 
			Class<?> clazz, String methodName, Class<?>... args) throws Exception{
		
		AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
		pointcut.setExpression(expression);
		
		assertThat(
			pointcut.getClassFilter().matches(clazz) && 
			pointcut.getMethodMatcher().matches(clazz.getMethod(methodName, args), null)
			,is(expected)
		);
	}
	
	public void targetClassPointcutMatches(String expression, boolean... expected) throws Exception{
		pointcutMatches(expression, expected[0], Target.class, "hello");
		pointcutMatches(expression, expected[1], Target.class, "hello", String.class);
		pointcutMatches(expression, expected[2], Target.class, "plus", int.class, int.class);
	}
	
}
