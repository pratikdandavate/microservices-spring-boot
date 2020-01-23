/**
 * 
 */
package com.airamatrix.airadhi.cache.redis;

import java.time.Duration;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.ReactiveHashOperations;
import org.springframework.data.redis.core.ReactiveListOperations;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.ReactiveValueOperations;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Jaikishan Gurav
 *
 */
@Configuration
public class ReactiveRedisClient<T> {
    private ReactiveRedisTemplate<String, T> redisTemplate;
    private ReactiveHashOperations<String, Object, T> hashOperation;
    private ReactiveListOperations<String, T> listOperation;
    private ReactiveValueOperations<String, T> valueOperations;

    @Autowired
    ReactiveRedisClient(ReactiveRedisTemplate<String, T> redisTemplate) {
	this.redisTemplate = redisTemplate;
	this.hashOperation = redisTemplate.opsForHash();
	this.listOperation = redisTemplate.opsForList();
	this.valueOperations = redisTemplate.opsForValue();
    }

    public void putMap(String redisKey, Object key, T data) {
	hashOperation.put(redisKey, key, data);
    }

    public Mono<T> getMapAsSingleEntry(String redisKey, Object key) {
	return hashOperation.get(redisKey, key);
    }

    public Flux<Entry<Object, T>> getMapAsAll(String redisKey) {
	return hashOperation.entries(redisKey);
    }

    public void putValue(String key, T value) {
	valueOperations.set(key, value);
    }

    public void putValueWithExpireTime(String key, T value, Duration timeout) {
	valueOperations.set(key, value, timeout);
    }

    public Mono<T> getValue(String key) {
	return valueOperations.get(key);
    }

    public void setExpire(String key, Duration timeout) {
	redisTemplate.expire(key, timeout);
    }
}
