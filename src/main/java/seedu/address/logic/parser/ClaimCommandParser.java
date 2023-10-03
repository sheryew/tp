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
