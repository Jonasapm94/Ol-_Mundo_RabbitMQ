package com.example;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class Consumidor {
    public static void main(String[] args) throws IOException, TimeoutException {
        System.out.println("Consumidor");

        String NOME_FILA = "filaOla";

        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("mqadmin");
        connectionFactory.setPassword("Admin123XX_");

        Connection connection = connectionFactory.newConnection();

        Channel channel = connection.createChannel();
        channel.queueDeclare(NOME_FILA, false, false, false, null);

        DeliverCallback callback = (consumerTag, delivery) -> {
            String mensagem = new String(delivery.getBody());
            System.out.println("Recebi a mensagem: " + mensagem);
        };

        channel.basicConsume(NOME_FILA, true,  callback, consumerTag -> {});

        System.out.println("Continuarei executando outras atividades enquanto não chega mensagem...");
    }
}
