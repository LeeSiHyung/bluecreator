package springbook.service.tx;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.util.PatternMatchUtils;

public class NameMatchClassMethodPointcut extends NameMatchMethodPointcut{
	
	public void setMappedClassName(String mappedClassName){
		this.setClassFilter(new SimpleClassFilter(mappedClassName));
	}
	
	static class SimpleClassFilter implements ClassFilter{
		
		String mappedName;

		public SimpleClassFilter(String mappedName) {
			this.mappedName = mappedName;
		}

		@Override
		public boolean matches(Class<?> clazz) {
			// 와일드카드(*)가 들어간 문자열 비교를 지원하는 스프링의 유틸리티 메소드다.
			// *name,name*,*name*
			return PatternMatchUtils.simpleMatch(mappedName, clazz.getSimpleName());
		}
		
	}
}
