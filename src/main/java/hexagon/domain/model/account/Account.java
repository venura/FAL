package hexagon.domain.model.account;

import hexagon.domain.model.Entity;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;
import java.util.Collection;

/**
 * No setters should added to avoid anemic domain model.Always try to change internal attributes through behavioural methods
 * You add any complex behaviour/business logic here
 */
@Getter
public class Account extends Entity {
    public enum AccountCategory {
        Asset,Liability
    }
    public enum Type {
        Merchant,Card
    }
    private AccountId accountId;
    private AccountNumber accountNo;
    private String title;
    private String description;
    private Money initialBalance;
    private Type type;

    public Account(AccountId accountId, AccountNumber accountNo, String title, String description, Money initialBalance, Type type) {
        this.accountId = accountId;
        this.accountNo = accountNo;
        this.title = title;
        this.description = description;
        this.initialBalance = initialBalance;
        this.type = type;
    }
}
