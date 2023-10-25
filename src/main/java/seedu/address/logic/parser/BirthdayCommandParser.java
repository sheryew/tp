package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MONTH;

import java.time.LocalDate;
import java.util.stream.Stream;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.BirthdayCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.MatchingBirthdayPredicate;
import seedu.address.model.person.Month;

/**
 * Parses input argument and returns a new BirthdayCommand object.
 */
public class BirthdayCommandParser implements Parser<BirthdayCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the BirthdayCommand
     * and returns a BirthdayCommand object for execution.
     * @param args the given month
     * @return a BirthdayCommand object
     * @throws ParseException if the user input does not conform the expected format
     */
    public BirthdayCommand parse(String args) throws ParseException {
        requireNonNull(args);
        if (args.trim().isEmpty()) {
            LocalDate date = LocalDate.now();
            MatchingBirthdayPredicate predicate = new MatchingBirthdayPredicate(new Month(date.getMonthValue()));
            return new BirthdayCommand(predicate);
        }
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_MONTH);
        if (!arePrefixesPresent(argMultimap, PREFIX_MONTH)) {
            throw new ParseException(Messages.MESSAGE_INVALID_MONTH_PREFIX);
        } else {
            int monthValue = ParserUtil.parseMonth(argMultimap.getValue(PREFIX_MONTH).get());
            MatchingBirthdayPredicate predicate = new MatchingBirthdayPredicate(new Month(monthValue));
            return new BirthdayCommand(predicate);
        }
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
