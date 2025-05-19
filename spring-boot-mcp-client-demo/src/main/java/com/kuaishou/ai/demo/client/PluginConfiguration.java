package com.kuaishou.ai.demo.client;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.ai.moonshot.MoonshotChatModel;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PluginConfiguration {

    @Autowired
    private MoonshotChatModel moonshotChatModel;
    @Autowired
    private OllamaChatModel ollamaChatModel;


    /**
     * 有记忆能力的chatMemory,会将前文上下文信息都存在内存里，具体查看InMemoryChatMemory
     *
     * @return
     */
    @Bean
    public InMemoryChatMemory chatMemory() {
        return new InMemoryChatMemory();
    }


    /**
     * 创建通用chatClient
     *
     * @param chatMemory
     * @return
     */
    @Bean
    public ChatClient chatClient(ChatMemory chatMemory, ToolCallbackProvider provider) {
        return ChatClient.builder(moonshotChatModel)
                .defaultTools(provider)
                .defaultSystem("假设你是孙悟空，以孙悟空的口吻回答问题")
                .defaultAdvisors(new MessageChatMemoryAdvisor(chatMemory))
                .build();
    }
}
