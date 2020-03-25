package hexagon.application;

import hexagon.domain.model.account.Money;
import lombok.Getter;

@Getter
public class TransactionEntryModel {
    private String accountNumber;
    private long amount;
    private Money.Currency currency;

    public TransactionEntryModel(String accountNumber, long amount, Money.Currency currency) {
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.currency = currency;
    }
}
