package com.depromeet.external.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

@Configuration
//@EnableCaching
public class RedisConfig {

	@Value("${spring.data.redis.host}")
	private String host;

	@Value("${spring.data.redis.port}")
	private int port;

	@Value("${spring.data.redis.timeout}")
	private int timeout;

	@Bean
	public RedisConnectionFactory redisConnectionFactory() {
		return new LettuceConnectionFactory(host, port);
	}

	@Bean
	public RedisTemplate<String, Object> redisTemplate() {
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(redisConnectionFactory());
		redisTemplate.afterPropertiesSet();;
		return redisTemplate;
	}

	@Bean(name = "redisCacheManager")
	public RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory) {
		RedisCacheConfiguration configuration = RedisCacheConfiguration
				.defaultCacheConfig()
				.disableCachingNullValues()
				.entryTtl(Duration.ofMinutes(timeout))
				.serializeKeysWith(
						RedisSerializationContext
								.SerializationPair
								.fromSerializer(new StringRedisSerializer()))
				.serializeValuesWith(
						RedisSerializationContext
								.SerializationPair
								.fromSerializer(new GenericJackson2JsonRedisSerializer()));

		return RedisCacheManager.RedisCacheManagerBuilder
				.fromConnectionFactory(connectionFactory)
				.cacheDefaults(configuration)
				.build();
	}

}
