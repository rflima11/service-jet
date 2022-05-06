package br.com.businesstec.servicejet.service.impl;

import br.com.businesstec.servicejet.client.dto.ClienteDTO;
import br.com.businesstec.servicejet.client.dto.EntityDTO;
import br.com.businesstec.servicejet.client.dto.PedidoDTO;
import br.com.businesstec.servicejet.service.JmsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class RabbitServiceImpl implements JmsService {

    private final DirectExchange directExchange;
    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    public RabbitServiceImpl(DirectExchange directExchange, RabbitTemplate rabbitTemplate, ObjectMapper objectMapper) {
        this.directExchange = directExchange;
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public void send(EntityDTO message) {
        try {
            String routingBind = "";
            if (message.getClass() == PedidoDTO.class) {
                routingBind = "blabla";
            } else if (message.getClass() == ClienteDTO.class) {
                routingBind = "blibli";
            }
            rabbitTemplate.convertAndSend(directExchange.getName(), routingBind, objectMapper.writeValueAsString(message));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }
}
