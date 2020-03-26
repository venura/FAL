package hexagon.domain.model.transaction;


import hexagon.domain.model.Entity;
import hexagon.domain.model.Timestamp;

import java.util.Set;


public class AccountingTransaction extends Entity {
    private AccountingTransactionId accountingTransactionId;
    private final Timestamp transactionTimestamp;
    private final String description;
    private final Set<LedgerEntry> ledgerEntries;

    public AccountingTransaction(AccountingTransactionId accountingTransactionId, Timestamp transactionTimestamp, String description, Set<LedgerEntry> ledgerEntries) {
        this(transactionTimestamp, description, ledgerEntries);
        this.accountingTransactionId = accountingTransactionId;
    }

    public AccountingTransaction(Timestamp transactionTimestamp, String description, Set<LedgerEntry> ledgerEntries) {
        this.transactionTimestamp = transactionTimestamp;
        this.description = description;
        this.ledgerEntries = ledgerEntries;
        if (!isEntriesSumToZero(ledgerEntries)) {
            throw new UnableToPostException();
        }
    }

    private boolean isEntriesSumToZero(Set<LedgerEntry> ledgerEntries) {
        return ledgerEntries.stream()
                .map(a -> a.getAmount())
                .reduce((a, b) -> a.add(b))
                .map(a -> a.isZero())
                .orElse(false);
    }

    private void post() {
        ledgerEntries.stream().map(a -> a.getAmount()).reduce((a, b) -> a.add(b));
    }
}
