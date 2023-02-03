package spring.mybatis;

import java.io.IOException;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringMain {

	public static void main(String[] args) throws IOException {
		
		
		ApplicationContext factory = new ClassPathXmlApplicationContext("spring/mybatis/spring-mybatis.xml");
		String names[] = factory.getBeanDefinitionNames();
		System.out.println("==============names==============");
		for(String n:names) {
			System.out.println(n);
		}
		System.out.println("=============names===============");

		//AutoWired로 객체만들고 주입해줌
		MemberService service = (MemberService)factory.getBean("service");
		
		////////////////////////////////////////////////////////////////////
		// test1 - 리스트 조회

		List<MemberDTO> list = service.memberlist();
		for (MemberDTO m : list) {
			System.out.println(m.getId() + " : " + m.getPw() + " : " + m.getName());
		}

		
		// test2 - 조회 int
		//System.out.println("전체 회원수  = " + service.membercount());
		
		// test3 - 조회(입력파라미터 존재, 결과 MemberDTO)
//		MemberDTO m = service.onemember("maria123");
//		if (m != null) { // 예외처리
//			System.out.println(m.getId() + " : " + m.getPw() + " : " + m.getName());
//		}
		
		
		// test4 - 페이징 처리된 리스트 조회
//		int[] limit = {0, 3}; // {시작페이지, 페이지수}
//		List<MemberDTO> list = service.paginglist(limit);
//		for (MemberDTO m : list) {
//			System.out.println(m.getId() + " : " + m.getPw() + " : " + m.getName());
//		}
		
		//test5
//		MemberDTO insertdto = new MemberDTO();
//		insertdto.setId("mysql");
//		insertdto.setPw("mysql");
//		insertdto.setName("mysql");
//		insertdto.setPhone("01034992309");
//		insertdto.setEmail("mysql@m.com");
//		insertdto.setAddress("서울시 동대문구");
//		service.insertmember(insertdto);
		
		//test6
		//id=mybatis, name="박한국" phone=01087654321 email=mybatis@b.com 으로 수정하기
		//updatemember sql 실행
//		MemberDTO updatedto = new MemberDTO();
//		updatedto.setId("mybatis");
//		updatedto.setName("박한국");
//		updatedto.setPhone("01087654321");
//		updatedto.setEmail("mybatis@b.com ");
//		service.updatemember(updatedto);//factory.openSession(true)
//		
//		int[] limit = {0, 3}; // {시작페이지, 페이지수}
//		List<MemberDTO> list = service.paginglist(limit);
//		for (MemberDTO m : list) {
//			System.out.println(m.getId() + " : " + m.getPw() + " : " + m.getName());
//		}
		
		
		//test7 삭제
//		int row = service.deletemember("mybatis");
//		System.out.println(row);
		
		
		
		//test 8 map
//		HashMap<String, String> map = new HashMap();
//		map.put("colname", "indate");
//		map.put("colvalue", "%2023%");
//		//select * from member where ${colname} like #{colvalue}
//		//비교해야할 값은 #, 컬럼명은 $
//		List<MemberDTO> searchList = service.searchmember(map);
//		for(MemberDTO m:searchList) {
//			System.out.println(
//					m.getId() + " : " + m.getPw() + " : "  + m.getName() + " : "+ m.getEmail()
//					+ " : "+ m.getPhone() + " : "+ m.getIndate());
//		}
		
		//select from member where address in ('서울시 용산구','제주시 용산구',...바뀌는 값 반복하는법);
		/*
		 select from member where address in
		 <foreach collection="list" item="addr" open="(" separator="," close=")">
		 #{addr}
		 </foreach>
		 */
		
		//test9
//		ArrayList<String> addressList = new ArrayList();
//		addressList.add("서울시 용산구");
//		addressList.add("서울시 동작구");
//		addressList.add("서울시 강남구");
//		List<MemberDTO> resultList = service.addressSearch(addressList);
//		for(MemberDTO m:resultList) {
//		System.out.println(
//				m.getId() + " : " + m.getPw() + " : "  + m.getName() + " : "+ m.getEmail()
//				+ " : "+ m.getPhone() + " : "+ m.getIndate());
//		}
		
		//test10 - sql조합 + 동적인 조건(where)절
//		MemberDTO dto = new MemberDTO();
//		dto.setName("박한국");
//		dto.setEmail("mybatis@b.com");
//		List<MemberDTO> combinationList = service.combination(dto);
//		for(MemberDTO m:combinationList) {
//		System.out.println(
//				m.getId() + " : " + m.getPw() + " : "  + m.getName() + " : "+ m.getEmail()
//				+ " : "+ m.getPhone() + " : "+ m.getIndate());
//		}
		
		//test11 - join시 resultMap 도 사용가능하다(다른 방법도 있음)
//		List<HashMap<String, String>> boardList = service.memberBoard("maria");
//		for(HashMap map:boardList) {
//			Set<String> keys = map.keySet();
//			for(String s:keys) {
//				System.out.println(s + ": " + map.get(s));
//			}
//		}
		
		//test12 과제 - 작성글 먼저 삭제 후, 회원정보삭제
		/*
		1. id 회원이 작성한 글이 있는지 검사
		2-1. 글이 없는 경우 바로 삭제처리
		2-2. 글이 있으면 사용자에게 ask -> 탈퇴전에 글도 삭제할건지(keyboard 입력으로 y/n)
			3-1. yes, 글삭제 후 사용자 탈퇴
			3-2. no, 사용자 탈퇴 취소
			
		 * */
//		
//		
//		
//		//글이 있으면,
//		Scanner sc = new Scanner(System.in);
//		String answer = sc.next();
//		if(answer.equals("y")) {
//			
//			
//			
//		} else if(answer.equals("n")) {
//			
//		} else {
//			
//			
//		}
		
		
		
	}

}
