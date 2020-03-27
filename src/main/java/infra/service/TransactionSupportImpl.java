package infra.service;

import hexagon.application.ITransactionSupport;

import java.util.function.Supplier;

@Service
public class TransactionSupportImpl implements ITransactionSupport {
    @Transactional
    public Object commit(Supplier supplier) {
        return supplier.get();
    }
}
