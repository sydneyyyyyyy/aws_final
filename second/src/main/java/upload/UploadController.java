package upload;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadController {
	//1. uploadForm 보여주는 메서드 필요
	@GetMapping("/fileUpload")
	public String uploadform() {
		return "upload/uploadForm";
	}
	
	
	//2. 폼에서 선택된 파일 받아서 c:upload에 저장하는 처리를 하는 업로드 메서드 필요
	@PostMapping("fileUpload")
	//여러가지 타입이 존재하면 multipart 쓸 수 없어서 dto 사용해야함.
	public String uploadProcess(@ModelAttribute("dto") UploadDTO dto) throws IOException {
		//UUID 랜덤으로 긴 길이 중복 문자열 생성
		//클라이언트 a.txt 였다면, a(UUID랜덤).txt로 바꾸기
		
		
		//c:/upload 폴더에 사용자가 업로드 했던 이름 그대로 저장
		//저장한 결과 객체를 뷰로 전달 ->uploadProcess.jsp
		String savePath = "c:/upload/";//업로드된 파일 저장할 경로
		MultipartFile mf1 = dto.getFile1();
		
		//파일명 추출
		String fileName1 = mf1.getOriginalFilename();
		
		//파일이름.확장자 분리
		String beforeExt1 = fileName1.substring(0,fileName1.lastIndexOf('.'));//title
		String ext1 = fileName1.substring(fileName1.lastIndexOf('.'));//확장자
		//UUID.randomUUID();
		String newFileName1 = beforeExt1 + "(" + UUID.randomUUID().toString() + ")" + ext1;
		
		//파일 내용 추출해서 c:/upload/filename1 저장
		File serverFile1 = new File(savePath + newFileName1);
		mf1.transferTo(serverFile1);
		
		
		//파일2
		MultipartFile mf2 = dto.getFile2();
		String fileName2 = mf2.getOriginalFilename();
		
		//파일이름.확장자 분리
		String beforeExt2 = fileName2.substring(0,fileName2.lastIndexOf('.'));//title
		String ext2 = fileName2.substring(fileName2.lastIndexOf('.'));//확장자
		String newFileName2 = beforeExt2 + "(" + UUID.randomUUID().toString() + ")" + ext2;
		
		
		File serverFile2 = new File(savePath + newFileName2);
		mf2.transferTo(serverFile2);
		
		//mapping type이 String이면 view이름전달
		//@ModelAttribute("view이름")
		return "upload/uploadProcess";
	}
	
}
