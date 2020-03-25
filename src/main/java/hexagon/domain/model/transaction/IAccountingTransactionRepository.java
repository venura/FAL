package hexagon.domain.model.transaction;

public interface IAccountingTransactionRepository {
    Long nextId();
    AccountingTransaction add(AccountingTransaction accountingTransaction);
}
