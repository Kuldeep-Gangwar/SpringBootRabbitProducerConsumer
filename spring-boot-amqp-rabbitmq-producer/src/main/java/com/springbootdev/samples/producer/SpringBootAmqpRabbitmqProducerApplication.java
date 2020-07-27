package com.springbootdev.samples.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.rabbitmq.client.Channel;

@SpringBootApplication
@EnableScheduling
public class SpringBootAmqpRabbitmqProducerApplication implements CommandLineRunner {
	@Autowired
	RabbitTemplate rabbitTemplate;
	private static final Logger LOGGER = LoggerFactory.getLogger(SpringBootAmqpRabbitmqProducerApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringBootAmqpRabbitmqProducerApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
		LOGGER.info("application is up and running");
		Channel channel = rabbitTemplate.getConnectionFactory().createConnection().createChannel(false);
		
		channel.queueDeclare("orders_queue", true, false, false, null);
		channel.queueBind("orders_queue", "sales_data_exchange", "customer.order");
	}
}
