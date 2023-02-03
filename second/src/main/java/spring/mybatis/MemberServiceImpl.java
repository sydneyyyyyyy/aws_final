package spring.mybatis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("service")
public class MemberServiceImpl implements MemberService {

	// DAO에게 직접 일을 시키는 것은 Service가 되어야 함!
	// service는 하나의 기능을 구현하기 위해 여러개의 sql문을 실행(DAO사용)
	@Autowired
	MemberDAO dao;
	

	@Override
	public List<MemberDTO> memberlist() {
		
		// dao 메서드 1개 - sql 1개 실행 단위
		// 서비스 기능 메서드 1개 - 여러개 실행 단위
		
		// main에게 리턴
		return dao.memberlist();
		
	}

	@Override
	public int membercount() {
		return dao.membercount();
	}

	@Override
	public MemberDTO onemember(String id) {
		return dao.onemember(id);
	}

	@Override
	public List<MemberDTO> paginglist(int[] limit) {
		return dao.paginglist(limit);
	}

	@Override
	public int insertmember(MemberDTO dto) {
		MemberDTO resultDTO = dao.onemember(dto.getId());
		if(resultDTO == null){
			return dao.insertmember(dto);
		}
		return 0;
	}

	@Override
	public int updatemember(MemberDTO dto) {
		return dao.updatemember(dto);
	}

	@Override
	public int deletemember(String id) {
		return dao.deletemember(id);
	}

	@Override
	public List<MemberDTO> searchmember(HashMap map) {
		return dao.searchmember(map);
	}

	@Override
	public List<MemberDTO> addressSearch(ArrayList<String> addressList) {
		return dao.addressSearch(addressList);
	}

	@Override
	public List<MemberDTO> combination(MemberDTO dto) {
		return dao.combination(dto);
	}

	@Override
	public List<HashMap<String, String>> memberBoard(String writer) {
		return dao.memberBoard(writer);
	}

	@Override
	public int updateMember2(MemberDTO dto) {
		return dao.updateMember2(dto);
	}




	
	
}

// 과정
// main 설정 파일 읽음 - service에게 일을 시킴 - service는 dao에게 일을 시킴(sql) - return - return - 메인 출력