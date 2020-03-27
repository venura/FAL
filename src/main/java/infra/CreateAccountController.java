package infra;

import hexagon.application.CreateAccountCommand;
import hexagon.application.CreateAccountModel;
import infra.repository.AccountRepositoryImpl;
import infra.service.TransactionSupportImpl;

@Controller
public class CreateAccountController {

    @Autowired
    AccountRepositoryImpl accountRepository;

    @Autowired
    TransactionSupportImpl transactionSupport;

    @RequestMapping(value = "/account")
    public AccountResponse post(HttpRequest request){
        CreateAccountModel model = toCreateAcccountModel(request);
        CreateAccountCommand command = new CreateAccountCommand(
                accountRepository,
                transactionSupport);
        Account account = command.execute(model);// should return simple dto instead of domain entity here
        return toAccountResponse(account);
    }
}
