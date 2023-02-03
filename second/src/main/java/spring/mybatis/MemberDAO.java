package spring.mybatis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository("memberdao")
public interface MemberDAO {

	//mapping 되어진 sql에서 같은 이름 찾아 실행
	public List<MemberDTO> memberlist() ;
	
	public int membercount() ;
	
	public MemberDTO onemember(String id);
	
	public List<MemberDTO> paginglist(int[] limit);
	
	public int insertmember(MemberDTO dto) ;
	
	public int updatemember(MemberDTO dto);
	
	public int deletemember(String id);
	
	public List<MemberDTO> searchmember(HashMap map);
	
	public List<MemberDTO> addressSearch(ArrayList<String> addressList);
	
	public List<MemberDTO> combination(MemberDTO dto);
	
	public List<HashMap<String,String>> memberBoard(String writer);
	
	public int updateMember2(MemberDTO dto); 
	
}