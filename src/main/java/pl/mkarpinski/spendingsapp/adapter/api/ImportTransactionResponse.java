package pl.mkarpinski.spendingsapp.adapter.api;

import lombok.Builder;
import lombok.Value;

import pl.mkarpinski.spendingsapp.domain.Transaction;

@Value
@Builder
public class ImportTransactionResponse {

    String source;
    String amount;
    String currency;
    String isIncome;

    public static ImportTransactionResponse of(Transaction transaction) {
        return ImportTransactionResponse.builder()
                .source(transaction.getSource())
                .amount(transaction.getAmount().toString())
                .currency(transaction.getCurrency())
                .isIncome(String.valueOf(transaction.isIncome()))
                .build();
    }

}
