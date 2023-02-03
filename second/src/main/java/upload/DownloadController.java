package upload;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class DownloadController {
	//c:upload 폴더의 list 출력
	@RequestMapping("/fileList")
	public ModelAndView downloadList() {
		ModelAndView mv = new ModelAndView();
		
		File f = new File("c:/upload");//폴더이름/파일이름 가능.
		String[] fileArr = f.list();//존재하는 폴더, 파일이름 출력
		
		mv.addObject("fileList",fileArr);//c:/upload의 파일리스트들 넣기
		mv.setViewName("upload/fileList");
		
		return mv;
	}
	
	@RequestMapping("/fileDownload")
	public void fileDownload(String oneFile, HttpServletResponse response) throws IOException {
		//oneFile이라는 이름의 파일을 c:/upload에서 찾는다
		File f = new File("c:/upload/" + oneFile);
		int f_length = (int)f.length();//byte단위
		
		//응답할 다운로드 파일
		//servlet의 response
		response.setContentType("application/download;;charset=utf-8");//선택한 파일에 대해 다운로드, ;charset=utf-8 => 한글설정
		response.setContentLength(f_length);//언제까지 다운로드 해야할지 알려줌(길이)
		response.setHeader("Content-Disposition", "attachment;fileName=\"" + oneFile + "\"");//파일 다운로드 할 때 고정적으로 사용
		
		//서버는 출력, 클라이언트는 입력
		OutputStream out = response.getOutputStream();
		FileInputStream f_in = new FileInputStream(f);//c의 oneFile에 있는 경로 찾음
		FileCopyUtils.copy(f_in, out);//다운로드 일어나는 곳
		f_in.close();
		out.close();
		
		//REST 방식으로 입력하고 REST 방식으로 처리
		//SPA 와 MPA
		
	}
}
