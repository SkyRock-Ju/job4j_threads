package cash;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Optional;

@ThreadSafe
public class AccountStorage {
    @GuardedBy("this")
    private final HashMap<Integer, Account> accounts = new HashMap<>();

    public synchronized boolean add(Account account) {
        if (accounts.containsKey(account.getId()))
            throw new NoSuchElementException("Account not found");
        return account.equals(
                accounts.putIfAbsent(account.getId(), account));
    }

    public synchronized boolean update(Account account) {
        if (!accounts.containsKey(account.getId()))
            throw new NoSuchElementException("Account not found");
        return account.equals(
                accounts.computeIfPresent(account.getId(), (id, acc) -> acc = account));
    }

    public synchronized boolean delete(int id) {
        if (!accounts.containsKey(id))
            throw new NoSuchElementException("Account not found");
        return id == accounts.remove(id).getId();
    }

    public synchronized Optional<Account> getById(int id) {
        if (!accounts.containsKey(id))
            throw new NoSuchElementException("Account not found");
        return Optional.ofNullable(accounts.get(id));
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        if (!accounts.containsKey(fromId) || !accounts.containsKey(toId))
            throw new NoSuchElementException("Account not found");
        if (accounts.get(fromId).getAmount() < amount) {
            throw new IllegalArgumentException("Insufficient funds");
        }
        var fromAccount = accounts.get(fromId);
        var toAccount = accounts.get(toId);
        return
                accounts.replace(fromId, fromAccount, new Account(fromId, fromAccount.getAmount() - amount))
                        &&
                        accounts.replace(toId, toAccount, new Account(toId, toAccount.getAmount() + amount));
    }
}