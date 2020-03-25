package hexagon.domain.model.account;

import java.io.Serializable;

/**
 * Immutable value objects
 */
public class AccountId implements Serializable {
    private final Long id;

    public AccountId(Long id) {
        this.id = id;
    }
}
