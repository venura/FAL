package hexagon.application;

import hexagon.domain.model.transaction.AccountingTransaction;

public interface IPostTransactionCommand {
    AccountingTransaction execute(PostTransactionModel model);
}
