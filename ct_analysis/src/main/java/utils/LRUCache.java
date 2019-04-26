package utils;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @description:
 * @time:2019/4/26
 */
public class LRUCache  extends LinkedHashMap<String, Integer> {

    private static final long serivalVersionUID = 1L;
    protected int maxElements;

    public LRUCache(int maxSize){
        super(maxSize, 0.75F, true);
        this.maxElements = maxSize;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<String, Integer> eldest) {
        return super.removeEldestEntry(eldest);
    }
}
