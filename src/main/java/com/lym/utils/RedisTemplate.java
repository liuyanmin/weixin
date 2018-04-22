package com.lym.utils;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPipeline;
import redis.clients.jedis.ShardedJedisPool;
import redis.clients.jedis.SortingParams;
import redis.clients.jedis.Tuple;
import redis.clients.jedis.BinaryClient.LIST_POSITION;

/**
 * Created by LIUYANMIN on 2018/4/10.
 */
public class RedisTemplate {
    private static final Logger LOG = LoggerFactory.getLogger(RedisTemplate.class);
    private ShardedJedisPool shardedJedisPool;

    public RedisTemplate() {
    }

    public ShardedJedis getRedisClient() {
        try {
            ShardedJedis shardJedis = this.shardedJedisPool.getResource();
            return shardJedis;
        } catch (Exception var2) {
            var2.printStackTrace();
            LOG.error("getRedisClent error", var2);
            return null;
        }
    }

    public void returnResource(ShardedJedis shardedJedis) {
        this.shardedJedisPool.returnResource(shardedJedis);
    }

    public void returnResource(ShardedJedis shardedJedis, boolean broken) {
        if(broken) {
            this.shardedJedisPool.returnBrokenResource(shardedJedis);
        } else {
            this.shardedJedisPool.returnResource(shardedJedis);
        }

    }

    public ShardedJedisPool getShardedJedisPool() {
        return this.shardedJedisPool;
    }

    public void setShardedJedisPool(ShardedJedisPool shardedJedisPool) {
        this.shardedJedisPool = shardedJedisPool;
    }

    public void disconnect() {
        ShardedJedis shardedJedis = this.getRedisClient();
        shardedJedis.disconnect();
    }

    public String set(String key, String value) {
        String result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.set(key, value);
            } catch (Exception var10) {
                LOG.error(var10.getMessage(), var10);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public String get(String key) {
        String result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.get(key);
            } catch (Exception var9) {
                LOG.error(var9.getMessage(), var9);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public Boolean exists(String key) {
        Boolean result = Boolean.valueOf(false);
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.exists(key);
            } catch (Exception var9) {
                LOG.error(var9.getMessage(), var9);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public String type(String key) {
        String result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.type(key);
            } catch (Exception var9) {
                LOG.error(var9.getMessage(), var9);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public Long expire(String key, int seconds) {
        Long result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.expire(key, seconds);
            } catch (Exception var10) {
                LOG.error(var10.getMessage(), var10);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public Long expireAt(String key, long unixTime) {
        Long result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.expireAt(key, unixTime);
            } catch (Exception var11) {
                LOG.error(var11.getMessage(), var11);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public Long ttl(String key) {
        Long result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.ttl(key);
            } catch (Exception var9) {
                LOG.error(var9.getMessage(), var9);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public boolean setbit(String key, long offset, boolean value) {
        ShardedJedis shardedJedis = this.getRedisClient();
        boolean result = false;
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.setbit(key, offset, value).booleanValue();
            } catch (Exception var12) {
                LOG.error(var12.getMessage(), var12);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public boolean getbit(String key, long offset) {
        ShardedJedis shardedJedis = this.getRedisClient();
        boolean result = false;
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.getbit(key, offset).booleanValue();
            } catch (Exception var11) {
                LOG.error(var11.getMessage(), var11);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public long setrange(String key, long offset, String value) {
        ShardedJedis shardedJedis = this.getRedisClient();
        long result = 0L;
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.setrange(key, offset, value).longValue();
            } catch (Exception var13) {
                LOG.error(var13.getMessage(), var13);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public String getrange(String key, long startOffset, long endOffset) {
        ShardedJedis shardedJedis = this.getRedisClient();
        String result = null;
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.getrange(key, startOffset, endOffset);
            } catch (Exception var13) {
                LOG.error(var13.getMessage(), var13);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public String getSet(String key, String value) {
        String result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.getSet(key, value);
            } catch (Exception var10) {
                LOG.error(var10.getMessage(), var10);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public Long setnx(String key, String value) {
        Long result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.setnx(key, value);
            } catch (Exception var10) {
                LOG.error(var10.getMessage(), var10);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public String setex(String key, int seconds, String value) {
        String result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.setex(key, seconds, value);
            } catch (Exception var11) {
                LOG.error(var11.getMessage(), var11);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public Long decrBy(String key, long integer) {
        Long result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.decrBy(key, integer);
            } catch (Exception var11) {
                LOG.error(var11.getMessage(), var11);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public Long decr(String key) {
        Long result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.decr(key);
            } catch (Exception var9) {
                LOG.error(var9.getMessage(), var9);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public Long incrBy(String key, long integer) {
        Long result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.incrBy(key, integer);
            } catch (Exception var11) {
                LOG.error(var11.getMessage(), var11);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public Long incr(String key) {
        Long result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.incr(key);
            } catch (Exception var9) {
                LOG.error(var9.getMessage(), var9);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public Long append(String key, String value) {
        Long result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.append(key, value);
            } catch (Exception var10) {
                LOG.error(var10.getMessage(), var10);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public String substr(String key, int start, int end) {
        String result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.substr(key, start, end);
            } catch (Exception var11) {
                LOG.error(var11.getMessage(), var11);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public Long hset(String key, String field, String value) {
        Long result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.hset(key, field, value);
            } catch (Exception var11) {
                LOG.error(var11.getMessage(), var11);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public String hget(String key, String field) {
        String result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.hget(key, field);
            } catch (Exception var10) {
                LOG.error(var10.getMessage(), var10);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public Long hsetnx(String key, String field, String value) {
        Long result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.hsetnx(key, field, value);
            } catch (Exception var11) {
                LOG.error(var11.getMessage(), var11);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public String hmset(String key, Map<String, String> hash) {
        String result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.hmset(key, hash);
            } catch (Exception var10) {
                LOG.error(var10.getMessage(), var10);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public List<String> hmget(String key, String... fields) {
        List<String> result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.hmget(key, fields);
            } catch (Exception var10) {
                LOG.error(var10.getMessage(), var10);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public Long hincrBy(String key, String field, long value) {
        Long result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.hincrBy(key, field, value);
            } catch (Exception var12) {
                LOG.error(var12.getMessage(), var12);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public Boolean hexists(String key, String field) {
        Boolean result = Boolean.valueOf(false);
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.hexists(key, field);
            } catch (Exception var10) {
                LOG.error(var10.getMessage(), var10);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public Long del(String key) {
        Long result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.del(key);
            } catch (Exception var9) {
                LOG.error(var9.getMessage(), var9);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public Long hdel(String key, String field) {
        Long result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.hdel(key, new String[]{field});
            } catch (Exception var10) {
                LOG.error(var10.getMessage(), var10);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public Long hlen(String key) {
        Long result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.hlen(key);
            } catch (Exception var9) {
                LOG.error(var9.getMessage(), var9);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public Set<String> hkeys(String key) {
        Set<String> result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.hkeys(key);
            } catch (Exception var9) {
                LOG.error(var9.getMessage(), var9);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public List<String> hvals(String key) {
        List<String> result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.hvals(key);
            } catch (Exception var9) {
                LOG.error(var9.getMessage(), var9);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public Map<String, String> hgetAll(String key) {
        Map<String, String> result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.hgetAll(key);
            } catch (Exception var9) {
                LOG.error(var9.getMessage(), var9);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public Long rpush(String key, String string) {
        Long result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.rpush(key, new String[]{string});
            } catch (Exception var10) {
                LOG.error(var10.getMessage(), var10);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public Long lpush(String key, String string) {
        Long result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.lpush(key, new String[]{string});
            } catch (Exception var10) {
                LOG.error(var10.getMessage(), var10);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public Long llen(String key) {
        Long result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.llen(key);
            } catch (Exception var9) {
                LOG.error(var9.getMessage(), var9);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public List<String> lrange(String key, long start, long end) {
        List<String> result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.lrange(key, start, end);
            } catch (Exception var13) {
                LOG.error(var13.getMessage(), var13);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public String ltrim(String key, long start, long end) {
        String result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.ltrim(key, start, end);
            } catch (Exception var13) {
                LOG.error(var13.getMessage(), var13);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public String lindex(String key, long index) {
        String result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.lindex(key, index);
            } catch (Exception var11) {
                LOG.error(var11.getMessage(), var11);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public String lset(String key, long index, String value) {
        String result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.lset(key, index, value);
            } catch (Exception var12) {
                LOG.error(var12.getMessage(), var12);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public Long lrem(String key, long count, String value) {
        Long result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.lrem(key, count, value);
            } catch (Exception var12) {
                LOG.error(var12.getMessage(), var12);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public String lpop(String key) {
        String result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.lpop(key);
            } catch (Exception var9) {
                LOG.error(var9.getMessage(), var9);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public String rpop(String key) {
        String result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.rpop(key);
            } catch (Exception var9) {
                LOG.error(var9.getMessage(), var9);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public Long sadd(String key, String member) {
        Long result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.sadd(key, new String[]{member});
            } catch (Exception var10) {
                LOG.error(var10.getMessage(), var10);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public Set<String> smembers(String key) {
        Set<String> result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.smembers(key);
            } catch (Exception var9) {
                LOG.error(var9.getMessage(), var9);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public Long srem(String key, String member) {
        ShardedJedis shardedJedis = this.getRedisClient();
        Long result = null;
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.srem(key, new String[]{member});
            } catch (Exception var10) {
                LOG.error(var10.getMessage(), var10);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public String spop(String key) {
        ShardedJedis shardedJedis = this.getRedisClient();
        String result = null;
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.spop(key);
            } catch (Exception var9) {
                LOG.error(var9.getMessage(), var9);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public Long scard(String key) {
        ShardedJedis shardedJedis = this.getRedisClient();
        Long result = null;
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.scard(key);
            } catch (Exception var9) {
                LOG.error(var9.getMessage(), var9);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public Boolean sismember(String key, String member) {
        ShardedJedis shardedJedis = this.getRedisClient();
        Boolean result = null;
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.sismember(key, member);
            } catch (Exception var10) {
                LOG.error(var10.getMessage(), var10);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public String srandmember(String key) {
        ShardedJedis shardedJedis = this.getRedisClient();
        String result = null;
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.srandmember(key);
            } catch (Exception var9) {
                LOG.error(var9.getMessage(), var9);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public Long zadd(String key, double score, String member) {
        Long result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.zadd(key, score, member);
            } catch (Exception var12) {
                LOG.error(var12.getMessage(), var12);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public Set<String> zrange(String key, int start, int end) {
        Set<String> result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.zrange(key, (long)start, (long)end);
            } catch (Exception var11) {
                LOG.error(var11.getMessage(), var11);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public Long zrem(String key, String member) {
        Long result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.zrem(key, new String[]{member});
            } catch (Exception var10) {
                LOG.error(var10.getMessage(), var10);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public Double zincrby(String key, double score, String member) {
        Double result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.zincrby(key, score, member);
            } catch (Exception var12) {
                LOG.error(var12.getMessage(), var12);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public Long zrank(String key, String member) {
        Long result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.zrank(key, member);
            } catch (Exception var10) {
                LOG.error(var10.getMessage(), var10);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public Long zrevrank(String key, String member) {
        Long result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.zrevrank(key, member);
            } catch (Exception var10) {
                LOG.error(var10.getMessage(), var10);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public Set<String> zrevrange(String key, int start, int end) {
        Set<String> result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.zrevrange(key, (long)start, (long)end);
            } catch (Exception var11) {
                LOG.error(var11.getMessage(), var11);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public Set<Tuple> zrangeWithScores(String key, int start, int end) {
        Set<Tuple> result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.zrangeWithScores(key, (long)start, (long)end);
            } catch (Exception var11) {
                LOG.error(var11.getMessage(), var11);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public Set<Tuple> zrevrangeWithScores(String key, int start, int end) {
        Set<Tuple> result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.zrevrangeWithScores(key, (long)start, (long)end);
            } catch (Exception var11) {
                LOG.error(var11.getMessage(), var11);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public Long zcard(String key) {
        Long result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.zcard(key);
            } catch (Exception var9) {
                LOG.error(var9.getMessage(), var9);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public Double zscore(String key, String member) {
        Double result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.zscore(key, member);
            } catch (Exception var10) {
                LOG.error(var10.getMessage(), var10);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public List<String> sort(String key) {
        List<String> result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.sort(key);
            } catch (Exception var9) {
                LOG.error(var9.getMessage(), var9);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public List<String> sort(String key, SortingParams sortingParameters) {
        List<String> result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.sort(key, sortingParameters);
            } catch (Exception var10) {
                LOG.error(var10.getMessage(), var10);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public Long zcount(String key, double min, double max) {
        Long result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.zcount(key, min, max);
            } catch (Exception var13) {
                LOG.error(var13.getMessage(), var13);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public Set<String> zrangeByScore(String key, double min, double max) {
        Set<String> result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.zrangeByScore(key, min, max);
            } catch (Exception var13) {
                LOG.error(var13.getMessage(), var13);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public Set<String> zrevrangeByScore(String key, double max, double min) {
        Set<String> result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.zrevrangeByScore(key, max, min);
            } catch (Exception var13) {
                LOG.error(var13.getMessage(), var13);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public Set<String> zrangeByScore(String key, double min, double max, int offset, int count) {
        Set<String> result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.zrangeByScore(key, min, max, offset, count);
            } catch (Exception var15) {
                LOG.error(var15.getMessage(), var15);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public Set<String> zrevrangeByScore(String key, double max, double min, int offset, int count) {
        Set<String> result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.zrevrangeByScore(key, max, min, offset, count);
            } catch (Exception var15) {
                LOG.error(var15.getMessage(), var15);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public Set<Tuple> zrangeByScoreWithScores(String key, double min, double max) {
        Set<Tuple> result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.zrangeByScoreWithScores(key, min, max);
            } catch (Exception var13) {
                LOG.error(var13.getMessage(), var13);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public Set<Tuple> zrevrangeByScoreWithScores(String key, double max, double min) {
        Set<Tuple> result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.zrevrangeByScoreWithScores(key, max, min);
            } catch (Exception var13) {
                LOG.error(var13.getMessage(), var13);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public Set<Tuple> zrangeByScoreWithScores(String key, double min, double max, int offset, int count) {
        Set<Tuple> result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.zrangeByScoreWithScores(key, min, max, offset, count);
            } catch (Exception var15) {
                LOG.error(var15.getMessage(), var15);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public Set<Tuple> zrevrangeByScoreWithScores(String key, double max, double min, int offset, int count) {
        Set<Tuple> result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.zrevrangeByScoreWithScores(key, max, min, offset, count);
            } catch (Exception var15) {
                LOG.error(var15.getMessage(), var15);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public Long zremrangeByRank(String key, int start, int end) {
        Long result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.zremrangeByRank(key, (long)start, (long)end);
            } catch (Exception var11) {
                LOG.error(var11.getMessage(), var11);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public Long zremrangeByScore(String key, double start, double end) {
        Long result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.zremrangeByScore(key, start, end);
            } catch (Exception var13) {
                LOG.error(var13.getMessage(), var13);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public Long linsert(String key, LIST_POSITION where, String pivot, String value) {
        Long result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.linsert(key, where, pivot, value);
            } catch (Exception var12) {
                LOG.error(var12.getMessage(), var12);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public String set(byte[] key, byte[] value) {
        String result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.set(key, value);
            } catch (Exception var10) {
                LOG.error(var10.getMessage(), var10);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public byte[] get(byte[] key) {
        byte[] result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.get(key);
            } catch (Exception var9) {
                LOG.error(var9.getMessage(), var9);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public Boolean exists(byte[] key) {
        Boolean result = Boolean.valueOf(false);
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.exists(key);
            } catch (Exception var9) {
                LOG.error(var9.getMessage(), var9);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public String type(byte[] key) {
        String result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.type(key);
            } catch (Exception var9) {
                LOG.error(var9.getMessage(), var9);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public Long expire(byte[] key, int seconds) {
        Long result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.expire(key, seconds);
            } catch (Exception var10) {
                LOG.error(var10.getMessage(), var10);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public Long expireAt(byte[] key, long unixTime) {
        Long result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.expireAt(key, unixTime);
            } catch (Exception var11) {
                LOG.error(var11.getMessage(), var11);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public Long ttl(byte[] key) {
        Long result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.ttl(key);
            } catch (Exception var9) {
                LOG.error(var9.getMessage(), var9);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public byte[] getSet(byte[] key, byte[] value) {
        byte[] result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.getSet(key, value);
            } catch (Exception var10) {
                LOG.error(var10.getMessage(), var10);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public Long setnx(byte[] key, byte[] value) {
        Long result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.setnx(key, value);
            } catch (Exception var10) {
                LOG.error(var10.getMessage(), var10);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public String setex(byte[] key, int seconds, byte[] value) {
        String result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.setex(key, seconds, value);
            } catch (Exception var11) {
                LOG.error(var11.getMessage(), var11);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public Long decrBy(byte[] key, long integer) {
        Long result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.decrBy(key, integer);
            } catch (Exception var11) {
                LOG.error(var11.getMessage(), var11);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public Long decr(byte[] key) {
        Long result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.decr(key);
            } catch (Exception var9) {
                LOG.error(var9.getMessage(), var9);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public Long incrBy(byte[] key, long integer) {
        Long result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.incrBy(key, integer);
            } catch (Exception var11) {
                LOG.error(var11.getMessage(), var11);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public Long incr(byte[] key) {
        Long result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.incr(key);
            } catch (Exception var9) {
                LOG.error(var9.getMessage(), var9);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public Long append(byte[] key, byte[] value) {
        Long result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.append(key, value);
            } catch (Exception var10) {
                LOG.error(var10.getMessage(), var10);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public byte[] substr(byte[] key, int start, int end) {
        byte[] result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.substr(key, start, end);
            } catch (Exception var11) {
                LOG.error(var11.getMessage(), var11);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public Long hset(byte[] key, byte[] field, byte[] value) {
        Long result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.hset(key, field, value);
            } catch (Exception var11) {
                LOG.error(var11.getMessage(), var11);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public byte[] hget(byte[] key, byte[] field) {
        byte[] result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.hget(key, field);
            } catch (Exception var10) {
                LOG.error(var10.getMessage(), var10);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public Long hsetnx(byte[] key, byte[] field, byte[] value) {
        Long result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.hsetnx(key, field, value);
            } catch (Exception var11) {
                LOG.error(var11.getMessage(), var11);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public String hmset(byte[] key, Map<byte[], byte[]> hash) {
        String result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.hmset(key, hash);
            } catch (Exception var10) {
                LOG.error(var10.getMessage(), var10);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public List<byte[]> hmget(byte[] key, byte[]... fields) {
        List<byte[]> result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.hmget(key, fields);
            } catch (Exception var10) {
                LOG.error(var10.getMessage(), var10);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public Long hincrBy(byte[] key, byte[] field, long value) {
        Long result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.hincrBy(key, field, value);
            } catch (Exception var12) {
                LOG.error(var12.getMessage(), var12);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public Boolean hexists(byte[] key, byte[] field) {
        Boolean result = Boolean.valueOf(false);
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.hexists(key, field);
            } catch (Exception var10) {
                LOG.error(var10.getMessage(), var10);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public Long hdel(byte[] key, byte[] field) {
        Long result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.hdel(key, new byte[][]{field});
            } catch (Exception var10) {
                LOG.error(var10.getMessage(), var10);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public Long hlen(byte[] key) {
        Long result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.hlen(key);
            } catch (Exception var9) {
                LOG.error(var9.getMessage(), var9);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public Set<byte[]> hkeys(byte[] key) {
        Set<byte[]> result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.hkeys(key);
            } catch (Exception var9) {
                LOG.error(var9.getMessage(), var9);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public Collection<byte[]> hvals(byte[] key) {
        Collection<byte[]> result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.hvals(key);
            } catch (Exception var9) {
                LOG.error(var9.getMessage(), var9);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public Map<byte[], byte[]> hgetAll(byte[] key) {
        Map<byte[], byte[]> result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.hgetAll(key);
            } catch (Exception var9) {
                LOG.error(var9.getMessage(), var9);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public Long rpush(byte[] key, byte[] string) {
        Long result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.rpush(key, new byte[][]{string});
            } catch (Exception var10) {
                LOG.error(var10.getMessage(), var10);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public Long lpush(byte[] key, byte[] string) {
        Long result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.lpush(key, new byte[][]{string});
            } catch (Exception var10) {
                LOG.error(var10.getMessage(), var10);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public Long llen(byte[] key) {
        Long result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.llen(key);
            } catch (Exception var9) {
                LOG.error(var9.getMessage(), var9);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public List<byte[]> lrange(byte[] key, int start, int end) {
        List<byte[]> result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.lrange(key, (long)start, (long)end);
            } catch (Exception var11) {
                LOG.error(var11.getMessage(), var11);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public String ltrim(byte[] key, int start, int end) {
        String result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.ltrim(key, (long)start, (long)end);
            } catch (Exception var11) {
                LOG.error(var11.getMessage(), var11);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public byte[] lindex(byte[] key, int index) {
        byte[] result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.lindex(key, (long)index);
            } catch (Exception var10) {
                LOG.error(var10.getMessage(), var10);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public String lset(byte[] key, int index, byte[] value) {
        String result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.lset(key, (long)index, value);
            } catch (Exception var11) {
                LOG.error(var11.getMessage(), var11);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public Long lrem(byte[] key, int count, byte[] value) {
        Long result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.lrem(key, (long)count, value);
            } catch (Exception var11) {
                LOG.error(var11.getMessage(), var11);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public byte[] lpop(byte[] key) {
        byte[] result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.lpop(key);
            } catch (Exception var9) {
                LOG.error(var9.getMessage(), var9);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public byte[] rpop(byte[] key) {
        byte[] result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.rpop(key);
            } catch (Exception var9) {
                LOG.error(var9.getMessage(), var9);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public Long sadd(byte[] key, byte[] member) {
        Long result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.sadd(key, new byte[][]{member});
            } catch (Exception var10) {
                LOG.error(var10.getMessage(), var10);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public Set<byte[]> smembers(byte[] key) {
        Set<byte[]> result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.smembers(key);
            } catch (Exception var9) {
                LOG.error(var9.getMessage(), var9);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public Long srem(byte[] key, byte[] member) {
        Long result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.srem(key, new byte[][]{member});
            } catch (Exception var10) {
                LOG.error(var10.getMessage(), var10);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public byte[] spop(byte[] key) {
        byte[] result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.spop(key);
            } catch (Exception var9) {
                LOG.error(var9.getMessage(), var9);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public Long scard(byte[] key) {
        Long result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.scard(key);
            } catch (Exception var9) {
                LOG.error(var9.getMessage(), var9);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public Boolean sismember(byte[] key, byte[] member) {
        Boolean result = Boolean.valueOf(false);
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.sismember(key, member);
            } catch (Exception var10) {
                LOG.error(var10.getMessage(), var10);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public byte[] srandmember(byte[] key) {
        byte[] result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.srandmember(key);
            } catch (Exception var9) {
                LOG.error(var9.getMessage(), var9);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public Long zadd(byte[] key, double score, byte[] member) {
        Long result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.zadd(key, score, member);
            } catch (Exception var12) {
                LOG.error(var12.getMessage(), var12);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public Set<byte[]> zrange(byte[] key, int start, int end) {
        Set<byte[]> result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.zrange(key, (long)start, (long)end);
            } catch (Exception var11) {
                LOG.error(var11.getMessage(), var11);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public Long zrem(byte[] key, byte[] member) {
        Long result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.zrem(key, new byte[][]{member});
            } catch (Exception var10) {
                LOG.error(var10.getMessage(), var10);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public Double zincrby(byte[] key, double score, byte[] member) {
        Double result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.zincrby(key, score, member);
            } catch (Exception var12) {
                LOG.error(var12.getMessage(), var12);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public Long zrank(byte[] key, byte[] member) {
        Long result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.zrank(key, member);
            } catch (Exception var10) {
                LOG.error(var10.getMessage(), var10);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public Long zrevrank(byte[] key, byte[] member) {
        Long result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.zrevrank(key, member);
            } catch (Exception var10) {
                LOG.error(var10.getMessage(), var10);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public Set<byte[]> zrevrange(byte[] key, int start, int end) {
        Set<byte[]> result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.zrevrange(key, (long)start, (long)end);
            } catch (Exception var11) {
                LOG.error(var11.getMessage(), var11);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public Set<Tuple> zrangeWithScores(byte[] key, int start, int end) {
        Set<Tuple> result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.zrangeWithScores(key, (long)start, (long)end);
            } catch (Exception var11) {
                LOG.error(var11.getMessage(), var11);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public Set<Tuple> zrevrangeWithScores(byte[] key, int start, int end) {
        Set<Tuple> result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.zrevrangeWithScores(key, (long)start, (long)end);
            } catch (Exception var11) {
                LOG.error(var11.getMessage(), var11);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public Long zcard(byte[] key) {
        Long result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.zcard(key);
            } catch (Exception var9) {
                LOG.error(var9.getMessage(), var9);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public Double zscore(byte[] key, byte[] member) {
        Double result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.zscore(key, member);
            } catch (Exception var10) {
                LOG.error(var10.getMessage(), var10);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public List<byte[]> sort(byte[] key) {
        List<byte[]> result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.sort(key);
            } catch (Exception var9) {
                LOG.error(var9.getMessage(), var9);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public List<byte[]> sort(byte[] key, SortingParams sortingParameters) {
        List<byte[]> result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.sort(key, sortingParameters);
            } catch (Exception var10) {
                LOG.error(var10.getMessage(), var10);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public Long zcount(byte[] key, double min, double max) {
        Long result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.zcount(key, min, max);
            } catch (Exception var13) {
                LOG.error(var13.getMessage(), var13);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public Set<byte[]> zrangeByScore(byte[] key, double min, double max) {
        Set<byte[]> result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.zrangeByScore(key, min, max);
            } catch (Exception var13) {
                LOG.error(var13.getMessage(), var13);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public Set<byte[]> zrangeByScore(byte[] key, double min, double max, int offset, int count) {
        Set<byte[]> result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.zrangeByScore(key, min, max, offset, count);
            } catch (Exception var15) {
                LOG.error(var15.getMessage(), var15);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public Set<Tuple> zrangeByScoreWithScores(byte[] key, double min, double max) {
        Set<Tuple> result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.zrangeByScoreWithScores(key, min, max);
            } catch (Exception var13) {
                LOG.error(var13.getMessage(), var13);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public Set<Tuple> zrangeByScoreWithScores(byte[] key, double min, double max, int offset, int count) {
        Set<Tuple> result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.zrangeByScoreWithScores(key, min, max, offset, count);
            } catch (Exception var15) {
                LOG.error(var15.getMessage(), var15);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public Set<byte[]> zrevrangeByScore(byte[] key, double max, double min) {
        Set<byte[]> result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.zrevrangeByScore(key, max, min);
            } catch (Exception var13) {
                LOG.error(var13.getMessage(), var13);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public Set<byte[]> zrevrangeByScore(byte[] key, double max, double min, int offset, int count) {
        Set<byte[]> result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.zrevrangeByScore(key, max, min, offset, count);
            } catch (Exception var15) {
                LOG.error(var15.getMessage(), var15);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public Set<Tuple> zrevrangeByScoreWithScores(byte[] key, double max, double min) {
        Set<Tuple> result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.zrevrangeByScoreWithScores(key, max, min);
            } catch (Exception var13) {
                LOG.error(var13.getMessage(), var13);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public Set<Tuple> zrevrangeByScoreWithScores(byte[] key, double max, double min, int offset, int count) {
        Set<Tuple> result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.zrevrangeByScoreWithScores(key, max, min, offset, count);
            } catch (Exception var15) {
                LOG.error(var15.getMessage(), var15);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public Long zremrangeByRank(byte[] key, int start, int end) {
        Long result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.zremrangeByRank(key, (long)start, (long)end);
            } catch (Exception var11) {
                LOG.error(var11.getMessage(), var11);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public Long zremrangeByScore(byte[] key, double start, double end) {
        Long result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.zremrangeByScore(key, start, end);
            } catch (Exception var13) {
                LOG.error(var13.getMessage(), var13);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public Long linsert(byte[] key, LIST_POSITION where, byte[] pivot, byte[] value) {
        Long result = null;
        ShardedJedis shardedJedis = this.getRedisClient();
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.linsert(key, where, pivot, value);
            } catch (Exception var12) {
                LOG.error(var12.getMessage(), var12);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public List<Object> pipelined(ShardedJedisPipeline shardedJedisPipeline) {
        ShardedJedis shardedJedis = this.getRedisClient();
        List<Object> result = null;
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.pipelined(shardedJedisPipeline);
            } catch (Exception var9) {
                LOG.error(var9.getMessage(), var9);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public Jedis getShard(byte[] key) {
        ShardedJedis shardedJedis = this.getRedisClient();
        Jedis result = null;
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = (Jedis)shardedJedis.getShard(key);
            } catch (Exception var9) {
                LOG.error(var9.getMessage(), var9);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public Jedis getShard(String key) {
        ShardedJedis shardedJedis = this.getRedisClient();
        Jedis result = null;
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = (Jedis)shardedJedis.getShard(key);
            } catch (Exception var9) {
                LOG.error(var9.getMessage(), var9);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public JedisShardInfo getShardInfo(byte[] key) {
        ShardedJedis shardedJedis = this.getRedisClient();
        JedisShardInfo result = null;
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = (JedisShardInfo)shardedJedis.getShardInfo(key);
            } catch (Exception var9) {
                LOG.error(var9.getMessage(), var9);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public JedisShardInfo getShardInfo(String key) {
        ShardedJedis shardedJedis = this.getRedisClient();
        JedisShardInfo result = null;
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = (JedisShardInfo)shardedJedis.getShardInfo(key);
            } catch (Exception var9) {
                LOG.error(var9.getMessage(), var9);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public String getKeyTag(String key) {
        ShardedJedis shardedJedis = this.getRedisClient();
        String result = null;
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.getKeyTag(key);
            } catch (Exception var9) {
                LOG.error(var9.getMessage(), var9);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public Collection<JedisShardInfo> getAllShardInfo() {
        ShardedJedis shardedJedis = this.getRedisClient();
        Collection<JedisShardInfo> result = null;
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.getAllShardInfo();
            } catch (Exception var8) {
                LOG.error(var8.getMessage(), var8);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }

    public Collection<Jedis> getAllShards() {
        ShardedJedis shardedJedis = this.getRedisClient();
        Collection<Jedis> result = null;
        if(shardedJedis == null) {
            return result;
        } else {
            boolean broken = false;

            try {
                result = shardedJedis.getAllShards();
            } catch (Exception var8) {
                LOG.error(var8.getMessage(), var8);
                broken = true;
            } finally {
                this.returnResource(shardedJedis, broken);
            }

            return result;
        }
    }
}
