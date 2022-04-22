package pl.mkarpinski.spendingsapp.adapter.api;

import java.io.InputStreamReader;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import pl.mkarpinski.spendingsapp.domain.Transaction;
import pl.mkarpinski.spendingsapp.domain.port.CsvConverter;

@RestController
@RequiredArgsConstructor
public class ImportPkoBpTransactionsCSV {

    private final static String SUPPORTED_MEDIA_TYPE = "text/csv";

    @Qualifier(value = "pkoBpCsvConverter")
    private final CsvConverter csvConverter;

    @SneakyThrows
    @PostMapping(path = "/api/transactions/import/pko-bp", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity importPkoBpTransactions(@RequestPart(name = "file") MultipartFile multipartFile) {
        if(!SUPPORTED_MEDIA_TYPE.equals(multipartFile.getContentType())) {
            return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).build();
        }

        List<ImportTransactionResponse> result = csvConverter.convert(new InputStreamReader(multipartFile.getInputStream()))
                .stream()
                .map(ImportTransactionResponse::of)
                .collect(Collectors.toList());

        return ResponseEntity.ok(result);
    }

}
