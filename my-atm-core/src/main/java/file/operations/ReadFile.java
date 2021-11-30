package file.operations;

import java.io.FileNotFoundException;
import java.util.List;

public sealed interface ReadFile<T> permits ReadCsvFile {// Sealed classes with permits - Java 17 feature

    List<T> read() throws FileNotFoundException;
}
