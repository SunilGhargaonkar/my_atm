package file.operations;

import com.opencsv.bean.CsvToBeanBuilder;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public final class ReadCsvFile implements ReadFile<Account> {
    @Override
    public List<Account> read() {
        final ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("accounts.csv");

        final List<Account> accounts = new CsvToBeanBuilder(new InputStreamReader(inputStream))
                .withType(Account.class)
                .build()
                .parse();

        return accounts;
    }
}
