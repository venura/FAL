package hexagon;


import hexagon.application.*;
import hexagon.domain.model.account.Account;
import hexagon.domain.model.account.AccountNumber;
import hexagon.domain.model.account.IAccountRepository;
import hexagon.domain.model.account.Money;
import hexagon.domain.model.transaction.AccountingTransaction;
import hexagon.domain.model.transaction.IAccountingTransactionRepository;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.function.Supplier;

import static org.junit.Assert.assertEquals;


public class AcceptanceTest {

    @Test
    public void should_create_a_new_account() {
        //Given
        //1.Mock right side adaptors
        Long accountId = 11L;
        Account.Type type = Account.Type.Card;
        IAccountRepository accountRepository = Mockito.mock(IAccountRepository.class);
        Mockito.when(accountRepository.nextId()).thenReturn(accountId);

        Account accountMock = Mockito.mock(Account.class);
        Mockito.when(
                accountMock.getAccountNo())
                .thenReturn(
                        AccountNumber.generateNumber(type, accountId));

        ITransactionSupport<Account> transactionSupport = supplier -> (Account) supplier.get();
        Mockito.when((transactionSupport.commit(Mockito.any(Supplier.class)))).thenReturn(accountMock);

        //2.Instantiate hexagon
        ICreateAccountCommand accountCreatorCommand = new CreateAccountCommand(accountRepository, transactionSupport);
        CreateAccountModel model = new CreateAccountModel(
                "Card no:1234",
                "CoJ card",
                5000,
                Money.Currency.USD,
                type);
        //When
        Account account = accountCreatorCommand.execute(model);
        //Then
        assertEquals(new AccountNumber("20011"), account.getAccountNo());
    }

    @Test
    public void should_create_a_new_account_with_restful_support() {

    }

    @Test
    public void should_find_account_when_given_account_no() {

    }

    @Test
    public void should_post_multi_legged_transaction() {
        //Given
        String cashAccount = "20011";
        String salesIncomeAccount = "20012";
        String taxPayableAccount = "20013";

        Money.Currency currency = Money.Currency.USD;

        PostTransactionModel model = new PostTransactionModel(
                1023000,
                "Redeem from Merchant and get a cashback",
                new TransactionEntryModel(cashAccount, 700 * 100L, currency),
                new TransactionEntryModel(salesIncomeAccount, -600 * 100L, currency),
                new TransactionEntryModel(taxPayableAccount, -100 * 100L, currency)
        );


        IAccountRepository accountRepository = Mockito.mock(IAccountRepository.class);
        ITransactionSupport<AccountingTransaction> transactionSupport = supplier -> (AccountingTransaction) supplier.get();
        IAccountingTransactionRepository accountingTransactionRepository = Mockito.mock(IAccountingTransactionRepository.class);

        Account accountMock = Mockito.mock(Account.class);
        Mockito.when(accountRepository.find(Mockito.any(AccountNumber.class))).thenReturn(accountMock);

        IPostTransactionCommand postTransactionCommand = new PostTransactionCommand(
                accountRepository,
                accountingTransactionRepository,
                transactionSupport);

        //When
        postTransactionCommand.execute(model);
        //Then
        Mockito.verify(accountingTransactionRepository).add(Mockito.any());
    }

}
