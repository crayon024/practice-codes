package self.practice.codes.concurrent.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 利用读写锁实现的简单缓存方案 - 缓存按需加载
 * 访问缓存没有数据的时候，去存储层加载数据，并放到缓存中
 *
 * 1. 缓存的初始化 -> 按需加载，预热加载
 * 2. 缓存和存储的一致性。存储中的数据改变，需不需要及时同步到缓存的问题。
 * @param <K>
 * @param <V>
 */
public class Cache<K,V> {
    final Map<K, V> hashmap = new HashMap<>(100_000);
    final ReadWriteLock rwl = new ReentrantReadWriteLock();
    final Lock r = rwl.readLock();
    final Lock w = rwl.writeLock();

    V get(K key) {
        V v = null;
        r.lock();
        try {
            // get 之后马上释放读锁，再去判断有没有从缓存取到值
            v = hashmap.get(key);
        } finally {
            r.unlock();
        }
        if (v == null) {
            w.lock();
            try {
                /*
                 * 从 hashmap 访问
                 */
                v = hashmap.get(key);
                if (v == null) {
                    /*
                     * v = load from storage
                     * 加载数据到缓存。比如访问数据库得到数据 v
                     */
                    if (v == null) throw new NoSuchElementException("查无此人");
                    return put(key, v);
                }
            } finally {
                w.unlock();
            }
        }
        return v;
    }

    V put(K key, V value) {
        w.lock();
        try {
            return hashmap.put(key, value);
        } finally {
            w.unlock();
        }
    }
}
