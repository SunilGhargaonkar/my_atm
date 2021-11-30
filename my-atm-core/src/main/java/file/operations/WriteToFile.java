package file.operations;

import java.io.IOException;

public sealed interface WriteToFile permits WriteToCsvFile { // Sealed classes with permits - Java 17 feature
    void write(long finalAccountBalance) throws IOException;
}
