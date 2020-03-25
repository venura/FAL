package hexagon.application;

import hexagon.domain.model.account.Account;

public interface ICreateAccountCommand {
    Account execute(CreateAccountModel model);
}
