package br.com.businesstec.servicejet.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    private static final String ROUTING_PEDIDOS = "routingPedidos";

    @Bean
    Queue queueRm() {
        return new Queue("queue.Pedidos", false);
    }

    @Bean
    DirectExchange directExchange(Queue queueRm) {
        return new DirectExchange("exchange.jet.direct");
    }

    @Bean
    Binding bindingJet(Queue queueJet, DirectExchange exchange) {
        return BindingBuilder.bind(queueJet).to(exchange).with(ROUTING_PEDIDOS);
    }



    @Bean
    RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        var rabbitTemplate = new RabbitTemplate(connectionFactory);
        return rabbitTemplate;

    }
}
