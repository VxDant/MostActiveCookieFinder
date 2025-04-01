package com.findmostactivecookie.interfaces;

import java.io.IOException;
import java.util.List;

public interface FileReader {
    List<String> readLogFile(String filename) throws IOException;
}
