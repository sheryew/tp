package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.BirthdayCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.MatchingBirthdayPredicate;
import seedu.address.model.person.Month;

public class BirthdayCommandParserTest {
    private final BirthdayCommandParser parser = new BirthdayCommandParser();

    @Test
    public void parse_validArgs_returnsBirthdayCommand() throws ParseException {
        List<Month> monthList = new ArrayList<>();
        monthList.add(new Month(3));
        BirthdayCommand successfulMatch = new BirthdayCommand(new MatchingBirthdayPredicate(monthList));
        assertEquals(parser.parse(" m/3"), successfulMatch);
    }

    @Test
    public void parse_validMultipleArgs_returnsBirthdayCommand() throws ParseException {
        List<Month> monthList = new ArrayList<>();
        monthList.add(new Month(3));
        monthList.add(new Month(4));
        BirthdayCommand successfulMatch = new BirthdayCommand(new MatchingBirthdayPredicate(monthList));
        assertEquals(parser.parse(" m/3,4"), successfulMatch);
    }

    @Test
    public void parse_invalidMultipleArgs_exceptionThrown() {
        assertThrows(ParseException.class, () -> parser.parse(" m/1,-1"));
    }

    @Test
    public void duplicatePrefix_exceptionThrown() {
        assertThrows(ParseException.class, () -> parser.parse(" m/1 m/2"));
    }

    @Test
    public void parse_noArgs_returnsBirthdayCommand() throws ParseException {
        List<Month> monthList = new ArrayList<>();
        monthList.add(new Month(LocalDate.now().getMonthValue()));
        BirthdayCommand currentMonthMatch = new BirthdayCommand(
                new MatchingBirthdayPredicate(monthList));
        assertEquals(parser.parse(""), currentMonthMatch);
    }

    @Test
    public void parse_invalidArgs_exceptionThrown() throws ParseException {
        assertThrows(ParseException.class, () -> parser.parse(" r/2"));
        assertThrows(ParseException.class, () -> parser.parse(" m/a"));
    }
}
