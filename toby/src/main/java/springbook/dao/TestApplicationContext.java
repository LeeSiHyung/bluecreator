package springbook.dao;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;


@Configuration
// @ImportResource 애노테이션을 이용하면 DI 설정정보에서 XML의 설정정보를 가져오게 만들 수 있다.
@ImportResource("/test-applicationContext.xml")
public class TestApplicationContext {

}
