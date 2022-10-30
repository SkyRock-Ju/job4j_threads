package cash;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AccountStorageTest {

    @Test
    void whenAdd()  {
        var account = new Account(1, 100);
        var storage = new AccountStorage();
        storage.add(account);
        var firstAccount = storage.getById(1)
                .orElseThrow(() -> new IllegalStateException("Not found account by id = 1"));
        assertThat(firstAccount).isEqualTo(account);
    }

    @Test
    void whenUpdate() {
        var account = new Account(1, 200);
        var storage = new AccountStorage();
        storage.add(new Account(1, 100));
        storage.update(account);
        var firstAccount = storage.getById(1)
                .orElseThrow(() -> new IllegalStateException("Not found account by id = 2"));
        assertThat(firstAccount).isEqualTo(account);
    }

    @Test
    void whenDelete() {
        var storage = new AccountStorage();
        storage.add(new Account(1, 100));
        assertThat(storage.delete(1)).isEqualTo(true);
        assertThrows(NoSuchElementException.class, () -> storage.getById(1));
    }

    @Test
    void whenTransfer() {
        var storage = new AccountStorage();
        storage.add(new Account(1, 100));
        storage.add(new Account(2, 100));
        boolean check = storage.transfer(1, 2, 100);
        var firstAccount = storage.getById(1)
                .orElseThrow(NoSuchElementException::new);
        var secondAccount = storage.getById(2)
                .orElseThrow(NoSuchElementException::new);
        assertThat(firstAccount.getAmount()).isEqualTo(0);
        assertThat(secondAccount.getAmount()).isEqualTo(200);
        assertThat(check).isTrue();
    }

    @Test
    void shouldThrowExceptionIfAmountIsNotEnough() {
        var storage = new AccountStorage();
        storage.add(new Account(1, 100));
        storage.add(new Account(2, 100));
        assertThrows(IllegalArgumentException.class, () -> storage.transfer(1, 2, 200));
    }
}