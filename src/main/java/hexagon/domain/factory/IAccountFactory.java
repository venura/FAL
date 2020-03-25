package hexagon.domain.factory;

import hexagon.domain.model.account.Account;
import hexagon.domain.model.account.Money;

public interface IAccountFactory {
    Account createAccount(Long accountId, String title, String description, Money initialBalance, Account.Type category);
}
