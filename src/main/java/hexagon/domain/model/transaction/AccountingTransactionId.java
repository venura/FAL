package hexagon.domain.model.transaction;

import java.io.Serializable;

public class AccountingTransactionId implements Serializable {
    private final Long id;

    public AccountingTransactionId(Long id) {
        this.id = id;
    }
}
