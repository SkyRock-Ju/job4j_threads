package cash;

import java.util.Objects;

public class Account {
    private final int id;
    private final int amount;

    public Account(int id, int amount) {
        this.id = id;
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Account account = (Account) o;
        return id == account.id && amount == account.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount);
    }
}

