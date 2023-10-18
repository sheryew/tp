package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MONTH;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.LeaveCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Leave;

/**
 * Parses input arguments and creates a new LeaveCommand object
 */
public class LeaveCommandParser implements Parser<LeaveCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the LeaveCommand
     * and returns an LeaveCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public LeaveCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_MONTH);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, LeaveCommand.MESSAGE_USAGE), pe);
        }
        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_MONTH);
        if (argMultimap.getValue(PREFIX_MONTH).isPresent()) {
            return new LeaveCommand(index, parseLeave(argMultimap.getValue(PREFIX_MONTH).get()));
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, LeaveCommand.MESSAGE_USAGE));
        }
    }

    private String parseLeave(String arg) throws ParseException {
        arg = arg.trim();
        if (arg.length() == 0) {
            throw new ParseException(LeaveCommand.MESSAGE_EMPTY + LeaveCommand.MESSAGE_USAGE);
        }
        int len = arg.split("\\s+").length;
        if (len > 1) {
            throw new ParseException(LeaveCommand.MESSAGE_SPACES_DETECTED + LeaveCommand.MESSAGE_USAGE);
        }
        StringBuilder months = new StringBuilder(Leave.NO_LEAVE);
        String[] args = arg.split(",");
        for (int i = 0; i < args.length; i++) {
            if (args[i].length() > 3) {
                throw new ParseException(LeaveCommand.MESSAGE_INVALID_MONTH + LeaveCommand.MESSAGE_USAGE);
            }
            try {
                if (args[i].charAt(0) == '-') {
                    Integer month = Integer.valueOf(args[i].substring(1));
                    if (month < 1 || month > 12) {
                        throw new ParseException(LeaveCommand.MESSAGE_INVALID_MONTH + LeaveCommand.MESSAGE_USAGE);
                    }
                    if (months.charAt(month - 1) == '+') {
                        throw new ParseException(LeaveCommand.MESSAGE_AMBIGUOUS + LeaveCommand.MESSAGE_USAGE);
                    }
                    months.setCharAt(month - 1, '-');
                } else {
                    Integer month = Integer.valueOf(args[i]);
                    if (month < 1 || month > 12) {
                        throw new ParseException(LeaveCommand.MESSAGE_INVALID_MONTH + LeaveCommand.MESSAGE_USAGE);
                    }
                    if (months.charAt(month - 1) == '-') {
                        throw new ParseException(LeaveCommand.MESSAGE_AMBIGUOUS + LeaveCommand.MESSAGE_USAGE);
                    }
                    months.setCharAt(month - 1, '+');
                }
            } catch (NumberFormatException e) {
                throw new ParseException(LeaveCommand.MESSAGE_NOT_A_NUMBER + LeaveCommand.MESSAGE_USAGE);
            }
        }
        return months.toString();
    }

}
