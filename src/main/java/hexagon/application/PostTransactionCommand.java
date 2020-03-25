package hexagon.application;

import hexagon.domain.model.Timestamp;
import hexagon.domain.model.account.AccountNumber;
import hexagon.domain.model.account.IAccountRepository;
import hexagon.domain.model.account.Money;
import hexagon.domain.model.transaction.AccountingTransaction;
import hexagon.domain.model.transaction.IAccountingTransactionRepository;
import hexagon.domain.model.transaction.LedgerEntry;

import java.time.Instant;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class PostTransactionCommand implements IPostTransactionCommand {

    private final IAccountRepository accountRepository;
    private final IAccountingTransactionRepository accountingTransactionRepository;
    private final ITransactionSupport transactionSupport;

    public PostTransactionCommand(IAccountRepository accountRepository, IAccountingTransactionRepository accountingTransactionRepository, ITransactionSupport transactionSupport) {
        this.accountRepository = accountRepository;
        this.accountingTransactionRepository = accountingTransactionRepository;
        this.transactionSupport = transactionSupport;
    }

    @Override
    public AccountingTransaction execute(PostTransactionModel model) {
        Set<LedgerEntry> ledgerEntries = Arrays.stream(model.getEntries()).map(
                entry -> new LedgerEntry(
                        null,
                        accountRepository.find(new AccountNumber(entry.getAccountNumber())).getAccountId(),
                        new Timestamp(Instant.now().toEpochMilli()),
                        Money.inCents(entry.getAmount(), entry.getCurrency())
                )

        ).collect(Collectors.toSet());
        AccountingTransaction accountingTransaction = new AccountingTransaction(
                new Timestamp(model.getTimestamp()),
                model.getDescription(),
                ledgerEntries);
        accountingTransaction = accountingTransactionRepository.add(accountingTransaction);
        return accountingTransaction;
    }
}
