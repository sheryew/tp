package seedu.address.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.ClaimCommand;

import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.model.person.Claim.ALPHABETS_ERROR;
import static seedu.address.model.person.Claim.NO_SYMBOLS_ERROR;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

public class ClaimCommandParserTest {

    private ClaimCommandParser parser = new ClaimCommandParser();

    @Test
    public void parse_allFieldsPresent_Success() {
        String userInput = INDEX_FIRST_PERSON.getOneBased() + " " +  CLAIM_DELIMITER + CLAIM_ADDITION + CLAIM_AMOUNT_STR;
        ClaimCommand successClaimCommand = new ClaimCommand(INDEX_FIRST_PERSON, false, CLAIM_AMOUNT);
        assertParseSuccess(parser, userInput, successClaimCommand);
    }

    @Test
    public void parse_emptyIndex_Failure() {
        String userInput = CLAIM_DELIMITER + CLAIM_ADDITION + CLAIM_AMOUNT_STR;
        assertParseFailure(parser, userInput, CLAIM_EMPTY_INDEX);
    }

    @Test
    public void parse_emptyAmount_Failure() {
        String userInput = String.valueOf(INDEX_FIRST_PERSON.getOneBased());
        assertParseFailure(parser, userInput, CLAIM_EMPTY_AMOUNT);
    }

    @Test
    public void parse_emptySymbols_Failure() {
        String userInput = INDEX_FIRST_PERSON.getOneBased() + " " + CLAIM_DELIMITER + CLAIM_AMOUNT_STR;
        assertParseFailure(parser, userInput, NO_SYMBOLS_ERROR);
    }

    @Test
    public void parse_claimAmount_NonDigits_Failure() {
        String userInput = INDEX_FIRST_PERSON.getOneBased() + " " + CLAIM_DELIMITER + CLAIM_ADDITION + CLAIM_AMOUNT_STR + "b";
        assertParseFailure(parser, userInput, ALPHABETS_ERROR);
    }
}
