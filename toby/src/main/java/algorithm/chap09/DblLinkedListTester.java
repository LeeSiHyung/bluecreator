package algorithm.chap09;

import java.util.Comparator;
import java.util.Scanner;

public class DblLinkedListTester {
	
	static Scanner stdIn = new Scanner(System.in);
	
	static class Data{
		
		static final int NO = 1;
		static final int NAME = 2;
		
		private Integer no;
		private String name;
		
		
		public String toString() {
			return "(" + no + ") " + name;
		}
		
		void scanData(String guide, int sw) {
			System.out.println(guide + "할 데이터를 입력하세요.");
			
			if((sw & NO) == NO) {
				System.out.print("번호 : ");
				no = stdIn.nextInt();
			}
			
			if((sw & NAME) == NAME) {
				System.out.print("이름 : ");
				name = stdIn.next();
			}
		}
		
		public static final Comparator<Data> NO_ORDER = new NoOrderComparator();
		
		private static class NoOrderComparator implements Comparator<Data>{
			@Override
			public int compare(Data o1, Data o2) {
				return (o1.no > o2.no) ? 1 : (o1.no < o2.no) ? -1 : 0;
			}
		}
		
		public static final Comparator<Data> NAME_ORDER = new NameOrderComparator();
		
		private static class NameOrderComparator implements Comparator<Data>{
			@Override
			public int compare(Data o1, Data o2) {
				return (o1.name.compareTo(o2.name));
			}
		}
	}
	
	enum Menu{
		
		ADD_FIRST("머리에 노드를 삽입"),
		ADD_LAST("꼬리에 노드를 삽입"),
		ADD("선택 노드의 바로 뒤에 삽입"),
		RMV_FIRST("머리 노드를 삭제"),
		RMV_LAST("꼬리 노드를 삭제"),
		RMV_CNRT("선택 노드를 삭제"),
		CLEAR("모든 노드를 삭제"),
		SEARCH_NO("번호로 검색"),
		SEARCH_NAME("이름으로 검색"),
		NEXT("선택 노드를 뒤쪽으로"),
		PREV("선택 노드를 앞쪽으로"),
		PRINT_CRNT("선택 노드를 출력"),
		DUMP("모든 노드를 출력"),
		TERMINATE("종료")
		;
		
		private final String message;
	
		Menu(String string){
			this.message = string;
		}
		
		public String getMessage() {
			return message;
		}
		
		static Menu MenuAt(int idx) {
			for(Menu m : Menu.values()) {
				if(m.ordinal() == idx)
					return m;
			}
			return null;
		}

	}
	
	static Menu SelectMenu() {
		int key;
		do {
			
			for(Menu m : Menu.values()) {
				System.out.printf("(%2d) %s          	", m.ordinal(), m.getMessage());
				if(m.ordinal() % 3 == 2 && m.ordinal() != Menu.TERMINATE.ordinal())
					System.out.println();
			}
			
			System.out.print(" : ");
			key = stdIn.nextInt();
		}
		while(key < Menu.ADD_FIRST.ordinal() || key > Menu.TERMINATE.ordinal());
		return Menu.MenuAt(key);
	}
	
	
	public static void main(String[] args) {
		Menu menu;
		Data data;
		Data ptr;
		Data temp = new Data(); // 하나의 객체만을 만들어서 사용한다.
		
		DblLinkedList<Data> list = new DblLinkedList<Data>();
		
		
		do {
			switch(menu = SelectMenu()) {
				case ADD_FIRST:
					data = new Data();
					data.scanData("머리에 삽입", Data.NO | data.NAME);
					list.addFirst(data);
					break;
					
				case ADD_LAST:
					data = new Data();
					data.scanData("꼬리에 삽입", Data.NO | data.NAME);
					list.addLast(data);
					break;
					
				case ADD:
					data = new Data();
					data.scanData("선택 노드의 바로 뒤에 삽입",  Data.NO | data.NAME);
					list.add(data);
					break;
					
				case RMV_FIRST:
					list.removeFirst();
					break;
					
				case RMV_LAST:
					list.removeLast();
					break;
					
				case RMV_CNRT:
					list.removeCurrentNode();
					break;
					
				case SEARCH_NO:
					temp.scanData("검색", Data.NO);
					ptr = list.search(temp, Data.NO_ORDER);
					if(ptr == null)
						System.out.println("그 번호의 데이터가 없습니다.");
					else
						System.out.println("검색 성공 : " + ptr);
					break;
					
				case SEARCH_NAME:
					temp.scanData("검색", Data.NAME);
					ptr = list.search(temp, Data.NAME_ORDER);
					if(ptr == null)
						System.out.println("그 이름의 데이터가 없습니다.");
					else
						System.out.println("검색 성공 : " + ptr);
					
				case NEXT:
					list.next();
					break;
					
				case PREV:
					list.prev();
					break;
					
				case PRINT_CRNT:
					list.printCurrentNode();
					break;
					
				case DUMP:
					list.dump();
					break;
					
				case CLEAR:
					list.clear();
					break;
					
			}
		}
		while(menu.ordinal() != Menu.TERMINATE.ordinal());
		
	}

}