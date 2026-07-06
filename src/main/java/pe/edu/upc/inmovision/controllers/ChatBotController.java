package pe.edu.upc.inmovision.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.inmovision.dtos.ChatMessageRequestDTO;
import pe.edu.upc.inmovision.dtos.ChatMessageResponseDTO;
import pe.edu.upc.inmovision.serviceinterfaces.IChatBotService;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/chatbot")
public class ChatBotController {

    @Autowired
    private IChatBotService chatBotService;

    @PostMapping("/message")
    public ChatMessageResponseDTO sendMessage(@RequestBody ChatMessageRequestDTO request) {

        String response = chatBotService.generateResponse(request.getMessage());

        return new ChatMessageResponseDTO(
                response,
                LocalDateTime.now()
        );
    }
}