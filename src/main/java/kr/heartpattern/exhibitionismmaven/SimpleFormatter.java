package kr.heartpattern.exhibitionismmaven;

import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class SimpleFormatter extends Formatter {
    @Override
    public String format(LogRecord record) {
        return '[' + record.getLevel().getName() + "] " + record.getMessage() + '\n';
    }
}
