package websocket;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ChatController {
//view를 보여줄 수 있게 하는 controller

	@RequestMapping("/chat")
	public String chat(String id) {
		return "webSocket/webSocket";
	}
}
