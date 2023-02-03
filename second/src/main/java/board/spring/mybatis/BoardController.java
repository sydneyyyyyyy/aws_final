package board.spring.mybatis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BoardController {
	@Autowired
	@Qualifier("service_board")
	BoardService service;
	
	@RequestMapping("/boardList")
	public ModelAndView boardList(@RequestParam(value="page", required = false, defaultValue = "1") int page) {
		//몇 페이지 구성 선택 가능 보여주는 링크
		/*
		1. select count(*) from board > int 리턴> model 저장(parameter 필요x)
		2. select * from borad limit (page-1)*3,3 > list > model 저장
		3. board/list 뷰
		*/
		int totalBoard = service.getTotalBoard();
		
		int limit = (page-1) *3;
		List<BoardDTO> list = service.getBoardlist(limit);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("totalCount",totalBoard);
		mv.addObject("list",list);
		mv.setViewName("board/list");
		return mv;
	}
	
	@RequestMapping("/oneBoard")
	public ModelAndView oneBoard(int seq) {
		ModelAndView mv = new ModelAndView();
		
		BoardDTO dto = service.getOneBoard(seq);//조회수 증가된 상태의 dto
		
		mv.addObject("oneBoard",dto);
		mv.setViewName("board/oneBoard");
		return mv;
	}
	
	@GetMapping("/insertBoard")
	public String insertBoardForm() {//login 세션 전달. 따로 세션 파라미터 받을 필요x
		
		/*
		 1.로그인 상태인지 확인
		 	1-1. 로그인 상태 아니면 로그인 화면
		 	1-2. 로그인 상태면, board.insertForm,jsp 보여주기
		 		작성자폼 : 로그인 id 미리 보여주기, 제목, 내용은 입력 받기
		 		게시물 번호 자동 증가, 작성자, 작성시간 자동 입력, 조회수는 0
		 */
		
		return "board/insertForm"; 
	}
	
	@PostMapping("/insertBoard")
	public ModelAndView insertBoardProcess(BoardDTO dto) {
		ModelAndView mv = new ModelAndView();
		service.insertBoard(dto);// 제목 내용 (세션 id - 작성자)

		/*mv.addObject("list", 1페이지3개게시물);
		mv.addObject("totalboard", 전체게시물수);	
		mv.setViewName("board/list"); //board/list.jsp 뷰 
		*/
		
		mv.setViewName("redirect:/boardList");//mapping url의 boardList 메서드 호출. 
		return mv;
	}
	/*
	 1.글쓰기 정보를 board 테이블에 저장하고 board/list.jsp 보여주기
	 
	 */

}
