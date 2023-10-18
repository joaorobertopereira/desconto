package br.com.helpcsistemas.api.v1.config;

import br.com.helpcsistemas.api.v1.model.SimulacaoRecebivel;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import io.lettuce.core.ClientOptions;
import io.lettuce.core.ClientOptions.Builder;
import io.lettuce.core.TimeoutOptions;
import io.lettuce.core.resource.ClientResources;
import io.lettuce.core.resource.DefaultClientResources;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

@Configuration
public class RedisConfig {

    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName("helpc-cluster-redis.dh3efv.ng.0001.use1.cache.amazonaws.com");
        redisStandaloneConfiguration.setPort(6379); // Use the correct port

        ClientResources clientResources = DefaultClientResources.builder().build();

        LettuceClientConfiguration clientConfiguration = LettuceClientConfiguration.builder()
                .clientResources(clientResources)
                .clientOptions(ClientOptions.builder()
                        .timeoutOptions(TimeoutOptions.enabled()) // Define o timeout
                        .build())
                .build();

        return new LettuceConnectionFactory(redisStandaloneConfiguration, clientConfiguration);
    }

    @Bean
    public RedisTemplate<String, SimulacaoRecebivel> redisSimulacaoRecebivelTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, SimulacaoRecebivel> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);

        // Configurando um serializer JSON com a biblioteca Jackson
        Jackson2JsonRedisSerializer<SimulacaoRecebivel> jsonSerializer = new Jackson2JsonRedisSerializer<>(SimulacaoRecebivel.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        jsonSerializer.setObjectMapper(objectMapper);

        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(jsonSerializer);
        template.afterPropertiesSet();

        return template;
    }
}



