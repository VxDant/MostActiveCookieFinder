package com.findmostactivecookie.interfaces;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

public interface FileReader {
    Stream<String> readLogFile(String filename) throws IOException;
}
