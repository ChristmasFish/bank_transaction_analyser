package pl.mkarpinski.spendingsapp.domain.port;

import java.io.Reader;
import java.util.List;
import java.util.stream.Collectors;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import pl.mkarpinski.spendingsapp.domain.Transaction;
import pl.mkarpinski.spendingsapp.domain.model.PkoBpTransaction;

@Component("pkoBpCsvConverter")
public class PkoBpCsvConverter implements CsvConverter {

    @SneakyThrows
    @Override
    public List<Transaction> convert(Reader reader) {
        CSVReader csvReader = new CSVReaderBuilder(reader)
                .withSkipLines(1)
                .build();
        List<PkoBpTransaction> pkoBpTransactions = csvReader.readAll()
                .stream()
                .map(PkoBpTransaction::new)
                .collect(Collectors.toList());

        return pkoBpTransactions.stream()
                .map(PkoBpTransaction::toTransaction)
                .collect(Collectors.toList());
    }

}
