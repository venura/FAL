package hexagon.domain.model.account;

import java.io.Serializable;
import java.util.Objects;

/**
 * Immutable value object
 */
public class AccountNumber implements Serializable {
    private String value;

    public AccountNumber(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountNumber that = (AccountNumber) o;
        return that.value.equals(this.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    public static AccountNumber generateNumber(Account.Type category, Long accountId) {
        String template;
        switch (category) {
            case Merchant:
                template = "100%d";
                break;
            case Card:
                template = "200%d";
                break;
            default:
                throw new IllegalArgumentException("Category not supported by account number generator");
        }
        return new AccountNumber(String.format(template, accountId.longValue()));
    }

}
