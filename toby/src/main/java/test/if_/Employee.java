package test.if_;

public class Employee implements Person, Identified{

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	
	// Person, Identified 인터페이스에 모두 default getId() 메소드가 존재한다면, 둘 중 어느 슈퍼타입을 원하는지 명시해야 함.
	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return Identified.super.getId();
	}
	
}
