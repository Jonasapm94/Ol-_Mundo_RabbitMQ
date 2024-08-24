package com.example;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Produtor {
    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ConnectionFactory();

        connectionFactory.setHost("localhost");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("mqadmin");
        connectionFactory.setPassword("Admin123XX_");

        String NOME_FILA = "filaOla";

        try {
            Connection connection = connectionFactory.newConnection();

            Channel channel = connection.createChannel();
            channel.queueDeclare(NOME_FILA, false, false, false, null);
            String mensagem = "Nova mensagem para segundo printscreeen!!!!";

            channel.basicPublish("", NOME_FILA, null, mensagem.getBytes());
            System.out.println("Enviei mensagem: " + mensagem);
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
