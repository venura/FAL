package hexagon.domain.model.transaction;

import hexagon.domain.model.Entity;
import hexagon.domain.model.Timestamp;
import hexagon.domain.model.account.AccountId;
import hexagon.domain.model.account.Money;
import hexagon.domain.model.transaction.AccountingTransactionId;
import lombok.Builder;
import lombok.Getter;

@Getter
public class LedgerEntry extends Entity {
    private LedgerEntryId ledgerEntryId;
    private final AccountingTransactionId transactionId;
    private final AccountId accountId;
    private final Timestamp date;
    private final Money amount;

    public LedgerEntry( AccountingTransactionId transactionId, AccountId accountId, Timestamp date, Money amount) {
        this.transactionId = transactionId;
        this.accountId = accountId;
        this.date = date;
        this.amount = amount;
    }
}
