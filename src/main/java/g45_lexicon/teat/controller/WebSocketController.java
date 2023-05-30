package g45_lexicon.teat.controller;

import g45_lexicon.teat.model.dto.MessageDto;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public MessageDto handleChatMessage(MessageDto messageDto) {
        // Process the incoming message and broadcast it to subscribers
        return messageDto;
    }
}

