package org.jimador.notes.app.config;

import com.google.common.flogger.FluentLogger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.messaging.handler.annotation.Payload;
import reactor.core.publisher.Flux;

/**
 * Configures Spring Cloud Stream support.
 * <p>
 * This works out-of-the-box if you use the Docker Compose configuration at "src/main/docker/kafka.yml".
 * <p>
 * See http://docs.spring.io/spring-cloud-stream/docs/current/reference/htmlsingle/
 * for the official Spring Cloud Stream documentation.
 */
@EnableBinding(value = {Sink.class})
public class MessagingConfiguration {

    private static final FluentLogger log = FluentLogger.forEnclosingClass();

    @Value("${spring.application.name:JhipsterService}")
    private String applicationName;

    @StreamListener(Sink.INPUT)
    public void process(@Payload String input) {
        log.atInfo().log(applicationName + " received message: " + input);
    }
}
