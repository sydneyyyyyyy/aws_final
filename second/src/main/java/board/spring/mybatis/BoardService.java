package board.spring.mybatis;

import java.util.List;

public interface BoardService {
	//게시물 리스트를 보여주기 위한 서비스 : 명세서
	int getTotalBoard(); //public
	List<BoardDTO> getBoardlist(int limit);
	
	//상세 게시물 조회
	//void updateViewCount(int seq);
	//서비스는 기능의 단위이므로 sql 단위로 나누지 않고 하나의 메서드에서 두개의 sql 실행할 수 있도록 작성
	BoardDTO getOneBoard(int seq);
	
	void insertBoard(BoardDTO dto);
	
}
