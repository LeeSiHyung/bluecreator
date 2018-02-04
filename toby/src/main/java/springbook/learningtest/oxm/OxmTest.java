package springbook.learningtest.oxm;

import java.io.IOException;
import java.util.List;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.Unmarshaller;
import org.springframework.oxm.XmlMappingException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import oracle.net.aso.l;
import springbook.jaxb.SqlType;
import springbook.jaxb.Sqlmap;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
// 클래스 이름 + "-context.xml" 파일을 사용하는 애플리케이션 컨텍스트로 만들어서 테스트 할 수 있게 해준다. (Path 지정이 안되었으면 자동으로 같은 클래스의 클래스 이름과 똑같은 Context를 맵핑)
@ContextConfiguration
public class OxmTest {
	
	@Autowired
	// 스프링 테스트가 테스트용 애플리케이션 컨텍스트에서 Unmarshaller 인터페이스 타입의 빈을 찾아서 테스트가 시작되기 전에 이 변수에 넣어준다.
	Unmarshaller unmarshaller;
	
	@Test
	public void unmarshallerSqlMap() throws XmlMappingException, IOException{
		
		// InputStream을 이용하는 Source타입의 StreamSource를 만든다.
		Source xmlSource = new StreamSource(getClass().getResourceAsStream("sqlmap.xml"));
		
		// 어떤 OXM 기술이든 어마샬은 이 한줄이면 끝이다.
		Sqlmap sqlmap = (Sqlmap) this.unmarshaller.unmarshal(xmlSource);
		
		List<SqlType> sqlList = sqlmap.getSql();
		assertThat(sqlList.size(), is(3));
		
		assertThat(sqlList.get(0).getKey(), is("add"));
		assertThat(sqlList.get(0).getValue(), is("insert"));
		
		assertThat(sqlList.get(1).getKey(), is("get"));
		assertThat(sqlList.get(1).getValue(), is("select"));
		
		assertThat(sqlList.get(2).getKey(), is("delete"));
		assertThat(sqlList.get(2).getValue(), is("delete"));
	}

}
