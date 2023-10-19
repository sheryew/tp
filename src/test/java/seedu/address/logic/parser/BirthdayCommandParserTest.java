package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.BirthdayCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class BirthdayCommandParserTest {
    private BirthdayCommandParser parser = new BirthdayCommandParser();

    @Test
    public void parse_validArgs_returnsBirthdayCommand() throws ParseException {
        assertTrue(parser.parse("m/2") instanceof BirthdayCommand);
    }

    @Test
    public void parse_noArgs_returnsBirthdayCommand() throws ParseException {
        assertTrue(parser.parse("") instanceof BirthdayCommand);
    }
}
