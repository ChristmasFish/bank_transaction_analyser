package pl.mkarpinski.spendingsapp.domain;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Transaction {

    String source;
    BigDecimal amount;
    String currency;
    boolean isIncome;

}
