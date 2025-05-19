package com.kuaishou.ai.demo.client;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PluginConfiguration {

    @Autowired
    private OllamaChatModel ollamaChatModel;


    /**
     * 创建通用chatClient
     *
     * @param chatMemory
     * @return
     */
    @Bean
    public ChatClient chatClient(ChatMemory chatMemory, ToolCallbackProvider provider) {
        return ChatClient.builder(ollamaChatModel)
                .defaultTools(provider)
                .defaultSystem("假设你是孙悟空，以孙悟空的口吻回答问题")
                .defaultAdvisors(MessageChatMemoryAdvisor.builder(chatMemory).build())
                .build();
    }
}
