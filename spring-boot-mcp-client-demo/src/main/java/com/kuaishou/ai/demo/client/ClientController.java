package com.kuaishou.ai.demo.client;

import org.springframework.ai.anthropic.AnthropicChatModel;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientController {
    @Autowired
    private AnthropicChatModel chatClient;

    @RequestMapping("/chat")
    public String chat(@RequestParam(value = "msg", defaultValue = "今天天气如何？") String msg) {
        return chatClient.call(msg);
    }

}
