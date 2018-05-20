﻿# NHN WAS 구현
 
 스펙 1. 구현
 - 실행가능한 jar 파일 실행 후 localhost, a.com, b.com 실행 (HOST 설정 필요)
 
 스펙 2. 구현
 - CONF 디렉토리 conf.json 로 설정 (PORT, HOST 등)
 
 스펙 3. 구현
 - 403 : local/a.exe 실행시 발생
 - 404 : local/B 실행시  발생
 - 501 : 500 대신 501로 구현 GET 파라미터 외에 HTTP 메소드로 보내면 발생
 
 스펙 4. 구현
 - 403 : local/a.exe 실행시 발생
 
 스펙 5. 구현
 - JUNIT에 테스트 존재
 - conf/logback.xml 설정 정보
 
 스펙 6. 구현
 - 내부 소스 bin/servelet에 구현
 - conf.json에 매핑 url, 클래스 정보 등록 필요.
 - 테스트는 http://localhost/Hello
 - 확장을 위해 conf.json의 host 별 urlMappings 설정과 bin/mapping 디렉토리의 자바를 바라보도록 설정했습니다.
 
 스펙 7. 구현
 - 위와 동일하게 테스트는 http://localhost/Hello 로 할 수 있도록 했습니다.

 스펙 8. 구현
 - 소스 경로는 src/test/java/com/nhn/simplewas/TestApplication으로 junit으로 실행하면 됩니다.
 - maven package 실행시 Junit을 실행하도록 했습니다.
 - jar 파일은 target/simplewas-0.0.1-jar-with-dependencies.jar 입니다.
 
