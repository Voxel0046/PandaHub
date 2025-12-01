package dev.panda.hub.cache;

import com.google.common.collect.Maps;
import lombok.experimental.UtilityClass;

import java.util.Map;
import java.util.UUID;

@UtilityClass
public class CommunicationCache {

    public Map<UUID, UUID> cacheMap = Maps.newHashMap();

    public void add(UUID sender, UUID receiver) {
        cacheMap.put(sender, receiver);
    }

    public void remove(UUID sender) {
        cacheMap.remove(sender);
    }

    public UUID get(UUID sender) {
        return cacheMap.get(sender);
    }

    public boolean isCached(UUID sender) {
        return cacheMap.containsKey(sender);
    }
}
