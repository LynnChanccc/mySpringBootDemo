package com.woniu.springboot.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import javax.annotation.Resource;

/**
 * @author cl
 * @Date 2020/3/8 13:55
 * redis配置类
 */
//定义配置类与@Bean组合使用。可替换xml配置
@Configuration
//使配置生效
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisConfig.class);

    /**
     * 注入Jedis连接对象
     */
    @Resource
    private JedisConnectionFactory jedisConnectionFactory;

    @Bean
    @Override
    public KeyGenerator keyGenerator() {
        //  设置自动key的生成规则，配置spring boot的注解，进行方法级别的缓存
        // 使用：进行分割，可以很多显示出层级关系
        return (target, method, params) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(target.getClass().getName());
            sb.append(":");
            sb.append(method.getName());
            for (Object obj : params) {
                sb.append(":" + String.valueOf(obj));
            }
            String rsToUse = String.valueOf(sb);
            LOGGER.info("自动生成Redis Key -> [{}]", rsToUse);
            return rsToUse;
        };
    }

    @Bean
    @Override
    public CacheManager cacheManager() {
        // 初始化缓存管理器，在这里我们可以缓存的整体过期时间什么的，这里默认没有配置
        LOGGER.info("初始化 -> [{}]", "cacheManager RedisCacheManager start");
        RedisCacheManager.RedisCacheManagerBuilder builder = RedisCacheManager
                .RedisCacheManagerBuilder
                .fromConnectionFactory(jedisConnectionFactory);
        return builder.build();
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(JedisConnectionFactory jedisConnectionFactory) {
        //设置序列化
        Jackson2JsonRedisSerializer<Object> jsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jsonRedisSerializer.setObjectMapper(objectMapper);
        //配置redisTemplate
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory);
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        //key序列化
        redisTemplate.setKeySerializer(stringRedisSerializer);
        //value序列化
        redisTemplate.setValueSerializer(jsonRedisSerializer);
        //hash key序列化
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        //hash value序列化
        redisTemplate.setHashValueSerializer(jsonRedisSerializer);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    @Bean
    @Override
    public CacheErrorHandler errorHandler() {
        //异常处理，当Redis发生异常时，打印日志，但是程序正常走
        LOGGER.info("初始化 -> [{}]", "Redis CacheErrorHandler");
        CacheErrorHandler cacheErrorHandler = new CacheErrorHandler() {

            @Override
            public void handleCacheGetError(RuntimeException e, Cache cache, Object o) {
                LOGGER.error("Redis occur handleCacheGetError:key -> [{}]", o, e);
            }

            @Override
            public void handleCachePutError(RuntimeException e, Cache cache, Object o, Object o1) {
                LOGGER.error("Redis occur handleCachePutError:key -> [{}]", o, e);
            }

            @Override
            public void handleCacheEvictError(RuntimeException e, Cache cache, Object o) {
                LOGGER.error("Redis occur handleCacheEvictError:key -> [{}]", o, e);
            }

            @Override
            public void handleCacheClearError(RuntimeException e, Cache cache) {
                LOGGER.error("Redis occur handleCacheClearError:", e);
            }
        };
        return cacheErrorHandler;
    }

    /**
     * 此内部类就是把yml的配置数据，进行读取，创建JedisConnectionFactory和JedisPool，
     * 以供外部类初始化缓存管理器使用,不了解的同学可以去看@ConfigurationProperties和@Value的作用
     */
    @ConfigurationProperties
    class DataJedisProperties {
        @Value("${spring.redis.host}")
        private String host;
        @Value("${spring.redis.password}")
        private String password;
        @Value("${spring.redis.port}")
        private int port;
        @Value("${spring.redis.timeout}")
        private int timeout;
        @Value("${spring.redis.jedis.pool.max-idle}")
        private int maxIdle;
        @Value("${spring.redis.jedis.pool.max-wait}")
        private long maxWaitMillis;

        @Bean
        public JedisConnectionFactory jedisConnectionFactory() {
            LOGGER.info("Create jedisConnectionFactory successful");
            JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
            jedisConnectionFactory.setHostName(host);
            jedisConnectionFactory.setPassword(password);
            jedisConnectionFactory.setPort(port);
            jedisConnectionFactory.setTimeout(timeout);
            return jedisConnectionFactory;
        }

        @Bean
        public JedisPool jedisPool() {
            LOGGER.info("JedisPool init successful,host -> [{}];port -> [{}]",host,port);
            JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
            jedisPoolConfig.setMaxIdle(maxIdle);
            jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
            JedisPool jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout, password);
            return jedisPool;
        }
    }
}
