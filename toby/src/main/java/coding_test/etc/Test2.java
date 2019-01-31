package coding_test.etc;

import java.util.*;

public class Test2 {
	public static void main(String[] args) {
		String S = "photo.jpg, Warsaw, 2013-09-05 14:08:15\njohn.png, London, 2015-06-20 15:13:22\nmyFriends.png, Warsaw, 2013-09-05 14:07:13\nEiffel.jpg, Paris, 2015-07-23 08:03:02\npisatower.jpg, Paris, 2015-07-22 23:59:59\nBOB.jpg, London, 2015-08-05 00:02:03\nnotredame.png, Paris, 2015-09-01 12:00:00\nme.jpg, Warsaw, 2013-09-06 15:40:22\na.png, Warsaw, 2016-02-13 13:33:50\nb.jpg, Warsaw, 2016-01-02 15:12:22\nc.jpg, Warsaw, 2016-01-02 14:34:30\nd.jpg, Warsaw, 2016-01-02 15:15:01\ne.png, Warsaw, 2016-01-02 09:49:09\nf.png, Warsaw, 2016-01-02 10:55:32\ng.jpg, Warsaw, 2016-02-29 22:13:11";
		//solution(S);
		System.out.println(solution(S));
	}

	static public String solution(String S) {
		// write your code in Java SE 8
		
		StringBuffer sb = new StringBuffer();
		List<PhotoInfo> infos = new ArrayList<PhotoInfo>();
		Map<String, ArrayList<PhotoInfo>> cityMap = new HashMap();
		
		String[] list = S.split("\\n");
		for(int i=0;i<list.length;i++) {
			String[] data = list[i].split(", ");
			//System.out.print(getExt(data[0]) + " ");
			
			PhotoInfo info = new PhotoInfo(data[0], data[1], data[2]);
			infos.add(info);
		}
		
		for (PhotoInfo p : infos) {
			String c = p.city;
			
			if (cityMap.get(c) != null) {
				cityMap.get(c).add(p);

            } else {
            	ArrayList<PhotoInfo> arr = new ArrayList<PhotoInfo>();
    			arr.add(p);
    			cityMap.put(c, arr);
            }
			
			
        }
		
		
		for (PhotoInfo p : infos) {
            ArrayList<PhotoInfo> myLisSort = cityMap.get(p.city);
            Collections.sort(myLisSort);
            int counter = myLisSort.indexOf(p) + 1;
            
            sb.append(p.city +  getCityCount(p.city, counter) + getExt(p.filename) + "\n");
            
        }
		
		return sb.toString();
	}
	
	static class PhotoInfo implements Comparable<PhotoInfo>{
		String filename;
		String city;
		String date;
		
		public PhotoInfo(String filename, String city, String date) {
			this.filename = filename;
			this.city = city;
			this.date = date;
		}

		@Override
		public int compareTo(PhotoInfo o) {
			return this.date.compareTo(o.date);
		}
		
	}
	
	// 확장자 가져오기
	static String getExt(String value) {
		int pos = value.lastIndexOf( "." ) - 1;
		return value.substring( pos + 1 );
	}
	
	static String getCityCount(String city, int count) {
		if("Warsaw".equals(city)) {
			return String.format("%02d", count);
		}
		return String.valueOf(count);
	}
	
}
