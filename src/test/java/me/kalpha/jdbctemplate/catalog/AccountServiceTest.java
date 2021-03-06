package me.kalpha.jdbctemplate.catalog;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class AccountServiceTest {
    @Autowired
    AccountService accountService;

    @Test
    public void findById() {
        String userId = "hong";
        Account account = accountService.getAccount(userId);

        assertTrue(account.getUserId().equals(userId));
    }

    @DisplayName("NoSuchElementException.class 발생 테스트")
    @Test
    public void findById_notfound() {
        String userId = "hong1";

        assertThrows(NoSuchElementException.class, () -> {
            accountService.getAccount(userId);
        });
    }
}