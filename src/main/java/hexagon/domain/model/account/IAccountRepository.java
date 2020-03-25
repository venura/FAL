package hexagon.domain.model.account;


public interface IAccountRepository {
    Account add(Account account);

    Long nextId();

    Account find(AccountNumber accountNumber);
}
