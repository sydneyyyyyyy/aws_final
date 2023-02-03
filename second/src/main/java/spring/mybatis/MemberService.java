package spring.mybatis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface MemberService {
	
	public List<MemberDTO> memberlist();
	public int membercount();
	public MemberDTO onemember(String id);
	public List<MemberDTO> paginglist(int[] limit);//<foreach collection="array|list"
	public int insertmember(MemberDTO dto);
	public int updatemember(MemberDTO dto);
	public int deletemember(String id);//dto.setId();
	public List<MemberDTO> searchmember(HashMap map);
	public List<MemberDTO> addressSearch(ArrayList<String> addressList);
	public List<MemberDTO> combination(MemberDTO dto);
	public List<HashMap<String, String>> memberBoard(String writer);
	public int updateMember2(MemberDTO dto);
}