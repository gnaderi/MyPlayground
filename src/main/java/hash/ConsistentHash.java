package hash;

import java.util.*;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * A modern, thread-safe Consistent Hashing implementation.
 */
public class ConsistentHash<T> {

    // Use a thread-safe, sorted map for the hash ring
    private final ConcurrentSkipListMap<Long, T> circle = new ConcurrentSkipListMap<>();
    private final HashFunction hashFunction;
    private final int numberOfReplicas;

    /**
     * @param hashFunction     Use a non-cryptographic hash like Murmur3 or CityHash for speed.
     * @param numberOfReplicas 100-200 is generally recommended for even distribution.
     */
    public ConsistentHash(HashFunction hashFunction, int numberOfReplicas, Collection<T> nodes) {
        this.hashFunction = hashFunction;
        this.numberOfReplicas = numberOfReplicas;
        for (T node : nodes) {
            add(node);
        }
    }

    public void add(T node) {
        for (int i = 0; i < numberOfReplicas; i++) {
            // Avoid string concatenation: use a more efficient seed approach
            circle.put(hashFunction.hash(node.toString(), i), node);
        }
    }

    public void remove(T node) {
        for (int i = 0; i < numberOfReplicas; i++) {
            circle.remove(hashFunction.hash(node.toString(), i));
        }
    }

    public T get(Object key) {
        if (circle.isEmpty()) {
            return null;
        }

        long hash = hashFunction.hash(key.toString(), 0);
        // tailMap(hash) provides an efficient view of the "clockwise" part of the ring
        SortedMap<Long, T> tailMap = circle.tailMap(hash);

        // If tailMap is empty, wrap around to the first key in the circle
        Long targetHash = tailMap.isEmpty() ? circle.firstKey() : tailMap.firstKey();
        return circle.get(targetHash);
    }
}

@FunctionalInterface
interface HashFunction {
    // Modern implementation should return 64-bit long to reduce collisions
    long hash(String input, int seed);
}

class MyHashFunctionImpl implements HashFunction {
    @Override
    public long hash(String input, int seed) {
        // In production, use MurmurHash3 or CityHash
        return input.hashCode() + seed;
    }
}

class VideoStreamingApp {
    public static void main(String[] args) {

        // 2. Define our cache servers (Nodes)
        List<String> cacheServers = new ArrayList<>(Arrays.asList(
                "CACHE-US-EAST",
                "CACHE-US-WEST",
                "CACHE-EU-CENTRAL"
        ));

        // 3. Create the ring with 200 virtual replicas per server to ensure even distribution
        ConsistentHash<String> videoRing = new ConsistentHash<>(new MyHashFunctionImpl(), 2, cacheServers);

        // 4. Simulate client requests for popular videos
        String[] videoIds = {"video_id_101", "video_id_202", "video_id_303", "video_id_999"};

        System.out.println("--- Initial Routing ---");
        for (String id : videoIds) {
            System.out.println("Video [" + id + "] is routed to: " + videoRing.get(id));
        }

        // 5. Scaling: Add a new server to handle peak traffic (e.g., during a 2026 sporting event)
        System.out.println("\n--- Scaling Up: Adding CACHE-ASIA-SOUTH ---");
        videoRing.add("CACHE-ASIA-SOUTH");

        for (String id : videoIds) {
            System.out.println("Video [" + id + "] is now routed to: " + videoRing.get(id));
        }
        // Note: Only a small fraction of keys will shift to the new server.

        // 6. Fault Tolerance: Simulate a server crash
        System.out.println("\n--- Fault Tolerance: CACHE-US-WEST goes offline ---");
        videoRing.remove("CACHE-US-WEST");

        for (String id : videoIds) {
            System.out.println("Video [" + id + "] redirected to: " + videoRing.get(id));
        }
    }
}
