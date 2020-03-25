package hexagon.application;

import hexagon.domain.model.account.Account;
import hexagon.domain.model.account.Money;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CreateAccountModel {


    private String title;
    private String description;
    private long initialBalanceInCents;
    private Money.Currency currency;
    private Account.Type type;


}
