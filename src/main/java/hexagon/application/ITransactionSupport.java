package hexagon.application;

import java.util.function.Supplier;

public interface ITransactionSupport<T> {
    T commit(Supplier supplier);
}
