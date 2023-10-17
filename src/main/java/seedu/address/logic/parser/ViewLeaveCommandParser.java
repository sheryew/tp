package seedu.address.logic.parser;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.ViewLeaveCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.*;

import java.util.function.Predicate;
import java.util.stream.Stream;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;

public class ViewLeaveCommandParser implements Parser<ViewLeaveCommand> {

    /**
     * Parses {@code userInput} into a command and returns it.
     *
     * @param args
     * @throws ParseException if {@code userInput} does not conform the expected format
     */
    @Override
    public ViewLeaveCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_MONTH, PREFIX_DEPARTMENT);
        Predicate<Person> combinedPredicate = new HasLeaveAnyMonthPredicate();
        if (argMultimap.getValue(PREFIX_MONTH).isPresent()) {
            argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_MONTH);
            String month = argMultimap.getValue(PREFIX_MONTH).get();
            combinedPredicate = combinedPredicate.and(new HasLeaveThisMonthPredicate(month));
        }
        if (argMultimap.getValue(PREFIX_DEPARTMENT).isPresent()) {
            argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_DEPARTMENT);
            Department filteringDepartment = new Department(argMultimap.getValue(PREFIX_DEPARTMENT).get());
            combinedPredicate = combinedPredicate.and(new MatchingDepartmentPredicate(filteringDepartment));
        }
        return new ViewLeaveCommand(combinedPredicate);
    }
}
