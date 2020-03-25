package hexagon.domain.model.account;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
/**
 * Immutable value object
 */
public class Money implements Serializable, Comparable<Money> {


    public enum Currency{
        USD,EUR
    }
    private final Long value;
    private final Currency currency;

    private Money(long value, Currency currency) {
        this.value = value;
        this.currency = currency;
    }

    public Money add(Money other) {
        assertSameCurrency(other);
        return new Money(this.value + other.value, currency);
    }

    public Money subtract(Money other) {
        assertSameCurrency(other);
        return new Money(this.value - other.value, currency);
    }

    private void assertSameCurrency(Money other) {
        if (!other.currency.equals(this.currency)) {
            throw new IllegalArgumentException("Money objects must have the same currency");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return value == money.value &&
                currency == money.currency;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, currency);
    }

    @Override
    public int compareTo(Money o) {
        assertSameCurrency(o);
        return this.value == o.value ? 0 : (this.value > o.value ? 1 : -1 );
    }
    public boolean isZero() {
        return this.value.compareTo(0L) == 0;
    }
    public static Money inCents(long value, Currency currency){
        return new Money(value,currency);
    }
}
