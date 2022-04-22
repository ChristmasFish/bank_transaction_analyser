package pl.mkarpinski.spendingsapp.domain.model;

import java.math.BigDecimal;

import lombok.Data;

import pl.mkarpinski.spendingsapp.domain.Transaction;

@Data
public class PkoBpTransaction {

    private String transactionDate;
    private String currencyDate;
    private String transactionType;
    private String amount;
    private String currency;
    private String balance;
    private String description;
    private String unknownOne;
    private String unknownTwo;
    private String unknownThree;
    private String unknownFour;

    public PkoBpTransaction(String[] csvLine) {
        this.transactionDate = csvLine[0];
        this.currencyDate = csvLine[1];
        this.transactionType = csvLine[2];
        this.amount = csvLine[3];
        this.currency = csvLine[4];
        this.balance = csvLine[5];
        this.description = csvLine[6];
        this.unknownOne = csvLine[7];
        this.unknownTwo = csvLine[8];
        this.unknownThree = csvLine[9];
        this.unknownFour = csvLine[10];
    }

    public Transaction toTransaction() {
        BigDecimal amount = new BigDecimal(this.amount);
        return Transaction.builder()
                .source("PKO_BP")
                .amount(amount.abs())
                .currency(this.currency)
                .isIncome(amount.compareTo(BigDecimal.ZERO) > 0)
                .build();
    }

}
