package rabbit_mq;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

public class Receiver {
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("guest");
        factory.setPassword("guest");
        factory.setHost("localhost");
        factory.setPort(5672);
        factory.setVirtualHost("/");

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        DeliverCallback callback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            System.out.println("Received text: '" + message + "'");
            String result = "ping back: " + message;

            AMQP.BasicProperties basicProperties = new AMQP.BasicProperties().builder()
                    .correlationId(delivery.getProperties().getCorrelationId()).build();
            channel.basicPublish("", delivery.getProperties().getReplyTo(), basicProperties, result.getBytes());
        };
        channel.basicConsume("ot-channel", true, callback, consumerTag -> {
        });
    }
}
