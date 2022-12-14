package cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Cache {
    private final Map<Integer, Base> memory = new ConcurrentHashMap<>();

    public boolean add(Base model) {
        return memory.putIfAbsent(model.getId(), model) == null;
    }

    public boolean update(Base model) {
        return memory.computeIfPresent(
                model.getId(), (id, base) -> {
                    if (checkVersions(model.getVersion(), base.getVersion())) {
                        String name = base.getName();
                        base =  new Base(id, model.getVersion() + 1);
                        base.setName(name);
                    }
                    return base;
                }
        ) != null;
    }

    public void delete(Base model) {
        memory.remove(model.getId());
    }

    private boolean checkVersions(int firstValue, int secondValue) {
        if (firstValue != secondValue) {
            throw new OptimisticException("Versions are not equal");
        }
        return true;
    }
}