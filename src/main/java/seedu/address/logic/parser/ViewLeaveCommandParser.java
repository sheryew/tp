package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEPARTMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MONTH;

import java.util.Optional;
import java.util.function.Predicate;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.ViewLeaveCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Department;
import seedu.address.model.person.HasLeaveAnyMonthPredicate;
import seedu.address.model.person.HasLeaveThisMonthPredicate;
import seedu.address.model.person.MatchingDepartmentPredicate;
import seedu.address.model.person.Person;

/**
 * Parses input arguments and creates a new ViewLeaveCommand object
 */
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
        if (argMultimap.getValue(PREFIX_MONTH).isEmpty()
                && argMultimap.getValue(PREFIX_DEPARTMENT).isEmpty()) {
            String[] secondArg = args.split(" ");
            if (secondArg.length != 1) {
                throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                        Messages.MESSAGE_VIEW_LIST_COMMAND_FORMAT));
            }
        }
        if (argMultimap.getValue(PREFIX_MONTH).isPresent()) {
            argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_MONTH);
            try {
                String monthArgs = argMultimap.getValue(PREFIX_MONTH).get();
                if (monthArgs.isEmpty()) {
                    throw new ParseException(Messages.MESSAGE_EMPTY_MONTH_LEAVE_FILTER);
                }
                String[] months = monthArgs.split(",");
                int len = monthArgs.split(" ").length;
                if (len > 1) {
                    throw new ParseException(Messages.MESSAGE_MONTHS_SPACES_DETECTED
                            + Messages.MESSAGE_VIEW_LIST_COMMAND_FORMAT);
                }
                Predicate<Person> combinedMonthsPredicate = new HasLeaveAnyMonthPredicate().negate();
                for (String month: months) {
                    try {
                        if (Integer.parseInt(month) < 1 || Integer.parseInt(month) > 12) {
                            throw new ParseException(Messages.MESSAGE_INVALID_MONTH);
                        }
                    } catch (NumberFormatException e) {
                        throw new ParseException(Messages.MESSAGE_INVALID_MONTH);
                    }

                    combinedMonthsPredicate = combinedMonthsPredicate.or(new HasLeaveThisMonthPredicate(month));
                }
                combinedPredicate = combinedPredicate.and(combinedMonthsPredicate);
            } catch (IllegalArgumentException e) {
                throw new ParseException(Messages.MESSAGE_EMPTY_MONTH_LEAVE_FILTER);
            }

        }
        if (argMultimap.getValue(PREFIX_DEPARTMENT).isPresent()) {
            try {
                argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_DEPARTMENT);
                Department filteringDepartment = new Department(argMultimap.getValue(PREFIX_DEPARTMENT).get());
                combinedPredicate = combinedPredicate.and(new MatchingDepartmentPredicate(filteringDepartment));
            } catch (IllegalArgumentException e) {
                throw new ParseException(Messages.MESSAGE_EMPTY_DEPARTMENT_FILTER);
            }
        }
        return new ViewLeaveCommand(combinedPredicate);
    }
}
