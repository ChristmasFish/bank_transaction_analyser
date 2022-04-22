package pl.mariuszkarpinski.spendingsapp.domain.port;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import pl.mkarpinski.spendingsapp.domain.Transaction;
import pl.mkarpinski.spendingsapp.domain.port.PkoBpCsvConverter;

import static org.assertj.core.api.Assertions.assertThat;

public class PkoBpCsvConverterTest {

    private final PkoBpCsvConverter converter = new PkoBpCsvConverter();

    @Test
    public void testIsTransactionsNumberEqualToFileLinesNumber() throws IOException {
        Resource resource = new ClassPathResource("history_csv_pko_bp.csv");
        Reader reader = new InputStreamReader(resource.getInputStream());

        List<Transaction> result = converter.convert(reader);

        assertThat(result.size()).isEqualTo(2);
    }

    @Test
    public void testIsTransactionLineAnIncome() throws IOException {
        Resource resource = new ClassPathResource("history_csv_pko_bp.csv");
        Reader reader = new InputStreamReader(resource.getInputStream());

        List<Transaction> result = converter.convert(reader);

        assertThat(result.get(1).getAmount()).isEqualByComparingTo(new BigDecimal("1000.00"));
        assertThat(result.get(1).isIncome()).isTrue();
    }

    @Test
    public void testIsTransactionLineAnOutcome() throws IOException {
        Resource resource = new ClassPathResource("history_csv_pko_bp.csv");
        Reader reader = new InputStreamReader(resource.getInputStream());

        List<Transaction> result = converter.convert(reader);

        assertThat(result.get(0).getAmount()).isEqualByComparingTo(new BigDecimal("5.40"));
        assertThat(result.get(0).isIncome()).isFalse();
    }

    @Test
    public void testIsTransactionSourcePkoBp() throws IOException {
        Resource resource = new ClassPathResource("history_csv_pko_bp.csv");
        Reader reader = new InputStreamReader(resource.getInputStream());

        List<Transaction> result = converter.convert(reader);

        for(Transaction transaction : result) {
            assertThat(transaction.getSource()).isEqualTo("PKO_BP");
        }
    }

}
