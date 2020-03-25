package hexagon;


import hexagon.application.*;
import hexagon.domain.model.account.*;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.function.Supplier;

import static org.junit.Assert.assertEquals;


public class AcceptanceTest {

    @Test
    public void should_create_a_new_account() {
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

        ITransactionSupport<Account> transactionSupport = Mockito.mock(ITransactionSupport.class);
        Mockito.when((transactionSupport.commit(Mockito.any(Supplier.class)))).thenReturn(accountMock);

        //2.Instantiate hexagon
        ICreateAccountCommand accountCreatorCommand = new CreateAccountCommand(accountRepository, transactionSupport);
        CreateAccountModel model = new CreateAccountModel(
                "Card no:1234",
                "CoJ card",
                5000,
                Money.Currency.USD,
                type);
        Account account = accountCreatorCommand.execute(model);
        assertEquals(new AccountNumber("20011"), account.getAccountNo());
    }

    @Test
    public void should_create_a_new_account_with_restful_support() {

    }

    @Test
    public void should_find_account_when_given_account_no() {

    }


}
