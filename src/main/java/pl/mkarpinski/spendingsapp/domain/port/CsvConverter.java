package pl.mkarpinski.spendingsapp.domain.port;

import java.io.Reader;
import java.util.List;

import pl.mkarpinski.spendingsapp.domain.Transaction;

public interface CsvConverter {

    List<Transaction> convert(Reader inputStreamReader);
}
