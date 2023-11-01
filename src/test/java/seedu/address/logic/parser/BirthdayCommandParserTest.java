package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.BirthdayCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.MatchingBirthdayPredicate;
import seedu.address.model.person.Month;

public class BirthdayCommandParserTest {
    private final BirthdayCommandParser parser = new BirthdayCommandParser();

    @Test
    public void parse_validArgs_returnsBirthdayCommand() throws ParseException {
        BirthdayCommand successfulMatch = new BirthdayCommand(new MatchingBirthdayPredicate(new Month(3)));
        assertEquals(parser.parse(" m/3"), successfulMatch);
    }

    @Test
    public void parse_noArgs_returnsBirthdayCommand() throws ParseException {
        LocalDate date = LocalDate.now();
        BirthdayCommand currentMonthMatch = new BirthdayCommand(
                new MatchingBirthdayPredicate(new Month(date.getMonthValue())));
        assertEquals(parser.parse(""), currentMonthMatch);
    }

    @Test
    public void parse_invalidArgs_exceptionThrown() throws ParseException {
        assertThrows(ParseException.class, () -> parser.parse(" r/2"));
    }
}
