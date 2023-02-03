package board.spring.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface BoardDAO {
	//게시물 리스트 출력
	int getTotalBoard();
	List <BoardDTO> getBoardList(int limit);
	
	//하나의 상세 게시물 출력
	void updateViewCount(int seq);
	BoardDTO getOneBoard(int seq);
	void insertBoard(BoardDTO dto);
	

}
