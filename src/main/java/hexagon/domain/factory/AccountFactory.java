package hexagon.domain.factory;

import hexagon.domain.model.account.Account;
import hexagon.domain.model.account.AccountId;
import hexagon.domain.model.account.AccountNumber;
import hexagon.domain.model.account.Money;

/**
 * AccountFactory is only for demo purpose we can use Account#of method instead since createAccount
 * doesn't involve any other aggregate roots
 */
public class AccountFactory implements IAccountFactory{
    public Account createAccount(Long accountId, String title, String description, Money initialBalance, Account.Type category) {
        AccountNumber accountNumber = AccountNumber.generateNumber(category, accountId);
        return new Account(new AccountId(accountId), accountNumber, title, description, initialBalance, category);
    }
}
