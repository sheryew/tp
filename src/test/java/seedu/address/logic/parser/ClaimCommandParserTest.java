package seedu.address.logic.parser;

import static seedu.address.logic.commands.CommandTestUtil.CLAIM_ADDITION;
import static seedu.address.logic.commands.CommandTestUtil.CLAIM_AMOUNT;
import static seedu.address.logic.commands.CommandTestUtil.CLAIM_AMOUNT_STR;
import static seedu.address.logic.commands.CommandTestUtil.CLAIM_DELIMITER;
import static seedu.address.logic.commands.CommandTestUtil.CLAIM_EMPTY_AMOUNT;
import static seedu.address.logic.commands.CommandTestUtil.CLAIM_EMPTY_INDEX;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.model.person.Claim.ALPHABETS_ERROR;
import static seedu.address.model.person.Claim.NO_SYMBOLS_ERROR;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.ClaimCommand;

public class ClaimCommandParserTest {

    private ClaimCommandParser parser = new ClaimCommandParser();

    @Test
    public void parse_withAllFields_presentReturnsSuccess() {
        String userInput = String.format("%s %s%s%s", INDEX_FIRST_PERSON.getOneBased(),
                CLAIM_DELIMITER, CLAIM_ADDITION, CLAIM_AMOUNT_STR);
        ClaimCommand successClaimCommand = new ClaimCommand(INDEX_FIRST_PERSON,
                false, CLAIM_AMOUNT);
        assertParseSuccess(parser, userInput, successClaimCommand);
    }

    @Test
    public void parse_withEmptyIndex_returnsFailure() {
        String userInput = CLAIM_DELIMITER + CLAIM_ADDITION + CLAIM_AMOUNT_STR;
        assertParseFailure(parser, userInput, CLAIM_EMPTY_INDEX);
    }

    @Test
    public void parse_claimTooLarge_returnsFailure() {
        String userInput = "1 $/+100000000000000000000000000000000";
        assertParseFailure(parser, userInput, Messages.TOO_LARGE_A_NUMBER);
    }

    @Test
    public void parse_withEmptyAmount_returnsFailure() {
        String userInput = String.valueOf(INDEX_FIRST_PERSON.getOneBased());
        assertParseFailure(parser, userInput, CLAIM_EMPTY_AMOUNT);
    }

    @Test
    public void parse_withEmptySymbols_returnsFailure() {
        String userInput = String.format("%s %s%s", INDEX_FIRST_PERSON.getOneBased(),
                CLAIM_DELIMITER, CLAIM_AMOUNT_STR);
        assertParseFailure(parser, userInput, NO_SYMBOLS_ERROR);
    }

    @Test
    public void parse_withNonDigitClaimAmount_returnsFailure() {
        String userInput = String.format("%s %s%s%s%s", INDEX_FIRST_PERSON.getOneBased(),
                CLAIM_DELIMITER, CLAIM_ADDITION, CLAIM_AMOUNT_STR, "b");
        assertParseFailure(parser, userInput, ALPHABETS_ERROR);
    }
}
