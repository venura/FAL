package hexagon.application;

import lombok.Getter;

@Getter
public class PostTransactionModel {
    private long timestamp;
    private String description;
    private TransactionEntryModel[] entries;

    public PostTransactionModel(long timestamp, String description, TransactionEntryModel... entries) {
        this.timestamp = timestamp;
        this.description = description;
        this.entries = entries;
    }
}
