package hexagon.application;

import hexagon.domain.model.account.*;

public class CreateAccountCommand implements ICreateAccountCommand {
    private final IAccountRepository accountRepository;
    private final ITransactionSupport<Account> transactionSupport;

    public CreateAccountCommand(
            IAccountRepository accountRepository,
            ITransactionSupport transactionSupport) {
        this.accountRepository = accountRepository;
        this.transactionSupport = transactionSupport;
    }

    @Override
    public Account execute(CreateAccountModel model) {

        return transactionSupport.commit(() -> {
            Long id = accountRepository.nextId();
            AccountNumber accountNumber = AccountNumber.generateNumber(model.getType(), id);
            Account account = new Account(
                    new AccountId(id),
                    accountNumber,
                    model.getTitle(),
                    model.getDescription(),
                    Money.inCents(
                            model.getInitialBalanceInCents(),
                            model.getCurrency()),
                    model.getType());

            return accountRepository.add(account);

        });
    }
}
