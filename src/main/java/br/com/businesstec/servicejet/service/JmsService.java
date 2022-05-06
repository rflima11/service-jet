package br.com.businesstec.servicejet.service;

import br.com.businesstec.servicejet.client.dto.EntityDTO;

public interface JmsService {

    void send(EntityDTO message);
}
