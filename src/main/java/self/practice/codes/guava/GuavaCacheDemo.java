package self.practice.codes.guava;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import self.practice.codes.Agent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class GuavaCacheDemo {

    static Map<Integer, Agent> agents = new HashMap<>();
    static {
        agents.put(1, new Agent(1, "java", "asdfasdf"));
        agents.put(2, new Agent(2, "java2", "asdfasdf2"));
        agents.put(3, new Agent(1, "java3", "asdfasdf3"));
    }

    public static void main(String[] args) throws ExecutionException {
        GuavaCacheDemo guavaCacheDemo = new GuavaCacheDemo();
        guavaCacheDemo.getFromGuava(3);
        System.out.println("dsafsadf");
        guavaCacheDemo.getFromGuava(3);
        guavaCacheDemo.update(3);
        guavaCacheDemo.getFromGuava(3);
        while (true) {

        }
    }

    public Agent getFromGuava(int agentId) throws ExecutionException {
        return agentLoader.get(agentId);
    }

    private static final Agent noAgent = new Agent();
    private LoadingCache<Integer, Agent> agentLoader = CacheBuilder.newBuilder()
            .concurrencyLevel(16)
            .expireAfterWrite(1000, TimeUnit.SECONDS)
            .initialCapacity(500)
            .maximumSize(10000).build(
                    new CacheLoader<Integer, Agent>() {
                        @Override
                        public Agent load(Integer agentId) {
                            Agent agentCache = getAgentCache(agentId);
                            System.out.println(agentId + ": from CacheLoader");
                            return agentCache == null ? noAgent : agentCache;
                        }
                    }
            );

    private Agent getAgentCache(Integer agentId) {
        return agents.get(agentId);
    }

    public void update(Integer agentId) {
        Agent agent = agents.get(agentId);
        agent.setAgentName("updateddddd");
        agents.put(agentId, agent);
    }
}
