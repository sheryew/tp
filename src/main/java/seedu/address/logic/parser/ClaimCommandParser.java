package seedu.address.logic.parser;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.ClaimCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Claim;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;
public class ClaimCommandParser implements Parser<ClaimCommand> {

    /**
     * Returns ClaimCommand which contains the employee-of-interest's index, boolean to represent subtraction/addition as well as the claim amount.
     * Parser to parse out the index as well as claim amount (containing both symbol [+/-] and amount) based on delimiter of "$/".
     * Checks are in place to ensure the index is inputted and claim amount consists of only digits.
     *
     * @param args String object which represents the user's input.
     * @return ClaimCommand which consists of employee's index, subtraction/addition boolean indicator and claim amount.
     * @throws ParseException Exception thrown either when index is not inputted or claim amount contains non-digits.
     */
    public ClaimCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_CLAIM_AMOUNT);

        Index index;
        Claim claim;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ClaimCommand.MESSAGE_EMPTY), pe);
        }

        if (argMultimap.getValue(PREFIX_CLAIM_AMOUNT).isPresent()) {
            claim = ParserUtil.parseClaim(argMultimap.getValue(PREFIX_CLAIM_AMOUNT).get());
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ClaimCommand.AMOUNT_EMPTY));
        }

        return new ClaimCommand(index, claim.isSubtract, claim.amount);
    }
}
