package spring.mybatis;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import ch.qos.logback.core.model.Model;
import jakarta.servlet.http.HttpSession;

@Controller
public class MemberController {
	@Autowired
	@Qualifier("service")
	MemberService service;
	
	//context root url
	// http://ip:port/spring
	@RequestMapping("/")
	public String start() {
		return "mybatis/start";
	}
	
	@GetMapping("/login")
	public String login() {
		return "mybatis/loginForm";
	}
	
	@PostMapping("/login")
	public String login2(String id, String pw, HttpSession session) {//HttpSession api 객체를 자동 생성해서 주입해줌.
		MemberDTO dto = service.onemember(id);
		String view = "";
		if(dto == null) {
			//회원가입화면을 보여주는 controller의 url 호출해서 view 보여줄 수 있도록 함.
			//view = "mybatis/memberInsert.jsp"; 하려면 memberInsert 호출
			
			//회원가입화면이 view 가 될수 있게 함.
			view = "mybatis/memberInsert";
			
		} else {
			if(pw.equals(dto.getPw())) {
				//정상로그인
				//HttpSession session = request.getSession();
				session.setAttribute("loginId", id);
				view = "mybatis/start";
			}
			else {
				//비밀번호 틀린사람
				view = "mybatis/loginForm";
			}
		}
		return view;
		
	}
	
	@RequestMapping("/mybatisMemberList")
	public ModelAndView memberList() {
		List<MemberDTO> memberList = service.memberlist();
		ModelAndView mv = new ModelAndView();
		mv.addObject("memberList", memberList);
		mv.setViewName("mybatis/memberList");
		return mv;
	}
	
	//view만 보여줌
	@GetMapping("/memberInsert")
	public String memberInsert() {
		return "mybatis/memberInsert";
	}
	
	@PostMapping("/memberInsert")
	public ModelAndView memberInsert(MemberDTO dto) throws IOException{
		//dto.setxxx :xxx파라미터 이름이 같아서 자동 저장
		
		//파일업로드 c:upload 저장처리
		//dto image 변수에 c:upload 저장 파일명 세팅
		String savePath = "c:/upload/";//업로드된 파일 저장할 경로
		MultipartFile imageFile = dto.getImageFile();
		
		//파일명 추출
		String fileName1 = imageFile.getOriginalFilename();
		
		//파일이름.확장자 분리
		String beforeExt1 = fileName1.substring(0,fileName1.lastIndexOf('.'));//title
		String ext1 = fileName1.substring(fileName1.lastIndexOf('.'));//확장자
		//UUID.randomUUID();
		String newFileName1 = beforeExt1 + "(" + UUID.randomUUID().toString() + ")" + ext1;
		
		//파일 내용 추출해서 c:/upload/filename1 저장
		File serverFile1 = new File(savePath + newFileName1);
		imageFile.transferTo(serverFile1);
		dto.setImage(newFileName1);
		
		//아이디 중복 방지
		//해당되는 아이디의 회원정보 리턴
		MemberDTO db_dto = service.onemember(dto.getId());
		String insertResult = "";
		
		if(db_dto == null) {
			int row = service.insertmember(dto);//indate 없다
			if(row == 1) {
				insertResult = "정상 회원가입 처리";
			} else {
				insertResult = "회원가입 처리 오류 발생";				
			}
		}
		else {
			insertResult = "이미 사용중인 아이디입니다.";
		}
		
		//dto 객체에 저장되어진 값들.(폼에서 입력한 사용자 값) -> memberTable에 저장
		//indate(가입일 입력x)
		//저장한 결과 "정상회원가입처리"
		//저장한 결과 보여주고 싶으면 가져옴(int return -> 1이면 정상회원가입처리 return) - model 로 저장
		//mybatis/memberInsert2 view 에서 보여줌?
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("insertResult", insertResult);
		mv.setViewName("mybatis/memberInsert2");
		
		return mv;
	}
	
	@RequestMapping("/memberInform")
	public ModelAndView memberInform(HttpSession session) {
		/*
		1. HttpSession 객체에 저장된 loginId 값을 가져와서
		2. service.onemember() 호출
		3. 2번 값 모델로 저장
		4. view - mybatis/memberInform
		5. view 에서 form 태그 내부 input 태그에 미리 정보 출력, 내정보수정 submit 버튼
		 */
		ModelAndView mv = new ModelAndView();
		String id = (String)session.getAttribute("loginId");
		
		if(session.getAttribute("loginId") !=null) {
			MemberDTO dto = service.onemember(id);
			mv.addObject("memberInfo",dto);
			mv.setViewName("mybatis/memberInform");
		}else {
			//로그인 안한 경우 
			mv.setViewName("mybatis/loginForm");
		}
		return mv;
		}
	

	//memberUpdate를 post 방식으로 처리
	/*
	1. 내정보 수정시 입력정보 모두 가져온다 
	2. updatemember2 라는 id 가진 sql 실행
	=>서비스 내부에 기능 추가 필요 - serive + serviceimpl 둘다 수정 -> dao 도 메서드 추가 필요
	3. "a" : "회원정보 수정 완료" 를 모델로 저장
	4. start.jsp => a라는 정보 읽는 부분 없으니, 필요하다면 추가하도록 수정
	 */
	@PostMapping("/memberUpdate")
	public ModelAndView memberUpdate(MemberDTO dto) {
		int result = service.updateMember2(dto);
		String updateResult = "수정 처리 되지 않음";
		if(result == 1) {
			updateResult = "회원정보 수정 완료";
		}
		ModelAndView mv = new ModelAndView();
		mv.addObject("updateResult",updateResult);
		mv.setViewName("mybatis/start");
		return mv;
	}
	
	/*
	 로그아웃
	 1.세션에서 loginId 속성값 읽는다
	 2. 읽었는데 값이 있다면 remove, db 작업은 필요x
	 3. start.jsp 로 이동
	 */
	//a태그는 get방식만 사용가능
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		if(session.getAttribute("loginId") != null) {
			session.removeAttribute("loginId");
		}
		return "mybatis/start";
	}
	
	/*
	 회원탈퇴
	 1. 세션에서 loginId 속성값 읽어온다
	 2. service.deletemember(loginId)
	 3. 2번의 결과가 1(성공적으로 delete)라면 "회원 탈퇴 정상 처리" 모델로 저장
	 4. model의 이름을 updateResult로 함.
	 5. start.jsp로 이동
	 */
	@GetMapping("/memberDelete")
	public ModelAndView memberDelete(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		if(session.getAttribute("loginId") != null) {
			int row = service.deletemember((String)session.getAttribute("loginId"));
			if(row == 1) {
				mv.addObject("updateResult","회원탈퇴 정상 처리");
			} else {
				mv.addObject("updateResult","회원탈퇴 오류 발생");
			}
		}
		mv.setViewName("mybatis/start");
		return mv;
	}
	
	@ResponseBody
	@GetMapping("/otherMemberInform")
	public MemberDTO otherMemberInform(String id, HttpSession session){
		/*
		 1. 로그인 되었는지 확인
		 2-1. 로그인 안 되어 있으면 start.jsp
		 2-2. 로그인 아이디가 "admin" 인지 확인
		 	a. 아래코드 수행
		 	b. alert("회원정보 접근 권한이 없습니다.")
		  */
		String model = null;
		//MemberDTO dto = new MemberDTO();
		MemberDTO dto = null;
		
		if(session.getAttribute("loginId") !=null) {
			String loginId = (String)session.getAttribute("loginId");
			if(!loginId.equalsIgnoreCase("admin")) {
				model = "회원 정보 접근 권한이 없습니다.";
				dto.setId(model);
			} else {
				dto = service.onemember(id);
			}
			
		}else {
			//로그인 안한 경우 
			model = "로그인 전 입니다.";
			dto.setId(model);
		}
		return dto;
	}

}
