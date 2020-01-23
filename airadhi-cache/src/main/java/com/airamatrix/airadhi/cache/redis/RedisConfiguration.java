package com.airamatrix.airadhi.cache.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author Jaikishan Gurav
 *
 */
public class RedisConfiguration {

    @Value("${spring.redis.host}")
    private String REDIS_HOSTNAME;

    @Value("${spring.redis.port}")
    private int REDIS_PORT;

    @Bean
    public ReactiveRedisConnectionFactory reactiveRedisConnectionFactory() {
	return new LettuceConnectionFactory(REDIS_HOSTNAME, REDIS_PORT);
    }

    @Bean
    public ReactiveRedisTemplate<String, Object> reactiveRedisTemplate(
	    ReactiveRedisConnectionFactory connectionFactory) {
	Jackson2JsonRedisSerializer<Object> serializer = new Jackson2JsonRedisSerializer<>(Object.class);
	RedisSerializationContext.RedisSerializationContextBuilder<String, Object> builder = RedisSerializationContext
		.newSerializationContext(new StringRedisSerializer());
	RedisSerializationContext<String, Object> context = builder.value(serializer).build();
	return new ReactiveRedisTemplate<>(connectionFactory, context);
    }

    @Bean
    protected JedisConnectionFactory jedisConnectionFactory() {
	RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration(REDIS_HOSTNAME, REDIS_PORT);
	JedisClientConfiguration jedisClientConfiguration = JedisClientConfiguration.builder().usePooling().build();
	JedisConnectionFactory factory = new JedisConnectionFactory(configuration, jedisClientConfiguration);
	factory.afterPropertiesSet();
	return factory;
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
	final RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
	redisTemplate.setKeySerializer(new StringRedisSerializer());
	redisTemplate.setHashKeySerializer(new GenericToStringSerializer<Object>(Object.class));
	redisTemplate.setHashValueSerializer(new JdkSerializationRedisSerializer());
	redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
	redisTemplate.setConnectionFactory(jedisConnectionFactory());
	return redisTemplate;
    }

}
