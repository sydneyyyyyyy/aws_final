package websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration //설정파일
@EnableWebSocket
public class ChatWebSocketConfig implements WebSocketConfigurer {
	// /ws url 요청시 ChatWebSocketHandler mapping
	private final WebSocketHandler handler;

	public ChatWebSocketConfig(WebSocketHandler handler) {
		super();
		this.handler = handler;
	}

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(handler, "/ws").setAllowedOrigins("*");
	}
	
	

}
