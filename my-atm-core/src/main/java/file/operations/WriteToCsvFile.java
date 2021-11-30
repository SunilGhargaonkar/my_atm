package file.operations;

import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.List;

public record WriteToCsvFile(long accountNumber) implements WriteToFile {// Record Type - Java 15 feature
    private static final String FILE_PATH = "accounts.csv";

    @Override
    public void write(final long finalAccountBalance) throws FileNotFoundException {
        final ReadFile<Account> readFile = new ReadCsvFile();
        final List<Account> accounts = readFile.read();

        accounts.stream()
                .filter(x -> x.getAccountNumber() == accountNumber)
                .forEach(x -> x.setAccountBalance(finalAccountBalance));

        try {
            final ClassLoader classLoader = getClass().getClassLoader();

            final File file = Paths.get(classLoader.getResource(FILE_PATH).toURI())
                    .toFile();

            final Writer writer = new FileWriter(file);

            final StatefulBeanToCsv<Account> beanToCsv = new StatefulBeanToCsvBuilder<Account>(writer).build();
            beanToCsv.write(accounts);
            writer.close();


        } catch (CsvRequiredFieldEmptyException | CsvDataTypeMismatchException | IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

}
