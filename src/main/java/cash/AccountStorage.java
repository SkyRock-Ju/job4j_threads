package cash;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.Optional;

@ThreadSafe
public class AccountStorage {
    @GuardedBy("this")
    private final HashMap<Integer, Account> accounts = new HashMap<>();

    public synchronized void add(Account account) {
        if (accounts.containsKey(account.id))
            throw new ConflictException("Account not found");
        accounts.putIfAbsent(account.id, account);
    }

    public synchronized void update(Account account) {
        if (!accounts.containsKey(account.id))
            throw new ConflictException("Account not found");
        accounts.computeIfPresent(account.id, (id, acc) -> acc = account);
    }

    public synchronized void delete(int id) {
        if (!accounts.containsKey(id))
            throw new ConflictException("Account not found");
        accounts.remove(id);
    }

    public synchronized Account getById(int id) {
        if (!accounts.containsKey(id))
            throw new ConflictException("Account not found");
        return accounts.get(id);
    }

    public synchronized void transfer(int fromId, int toId, int amount) {
        if (!accounts.containsKey(fromId) || !accounts.containsKey(toId))
            throw new ConflictException("Account not found");
        if (accounts.get(fromId).amount < amount) {
            throw new ConflictException("Insufficient funds");
        }
        accounts.computeIfPresent(fromId,
                (id, account) -> account = new Account(id, account.amount - amount));
        accounts.computeIfPresent(toId,
                (id, account) -> account = new Account(id, account.amount + amount));
    }
}