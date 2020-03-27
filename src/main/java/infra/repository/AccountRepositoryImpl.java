package infra.repository;

import hexagon.domain.model.account.Account;
import hexagon.domain.model.account.AccountNumber;
import hexagon.domain.model.account.IAccountRepository;

import java.util.Optional;

@Repository/@Component
public class AccountRepositoryImpl implements IAccountRepository {
    @Override
    public Account add(Account account) {
        return null;
    }

    @Override
    public Long nextId() {
        return null;
    }

    @Override
    public Optional<Account> find(AccountNumber accountNumber) {
        return Optional.empty();
    }
}
