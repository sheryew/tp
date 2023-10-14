package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.LeaveCommand;


public class LeaveCommandParserTest {

    private LeaveCommandParser parser = new LeaveCommandParser();

    @Test
    public void parse_validArgs_returnsLeaveCommand() {
        assertParseSuccess(parser, " 1 m/2", new LeaveCommand(INDEX_FIRST_PERSON, "0+0000000000"));
        assertParseSuccess(parser, " 1 m/-2", new LeaveCommand(INDEX_FIRST_PERSON, "0-0000000000"));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT, LeaveCommand.MESSAGE_USAGE));

        assertParseFailure(parser, " 1 m/2, 3", LeaveCommand.MESSAGE_SPACES_DETECTED + LeaveCommand.MESSAGE_USAGE);

        assertParseFailure(parser, " 1 ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, LeaveCommand.MESSAGE_USAGE));

        assertParseFailure(parser, " 1 m/", LeaveCommand.MESSAGE_EMPTY + LeaveCommand.MESSAGE_USAGE);

        assertParseFailure(parser, " 1 m/asdf", LeaveCommand.MESSAGE_INVALID_MONTH + LeaveCommand.MESSAGE_USAGE);

        assertParseFailure(parser, " 1 m/3,-3", LeaveCommand.MESSAGE_AMBIGUOUS + LeaveCommand.MESSAGE_USAGE);

        assertParseFailure(parser, " 1 m/-3,3", LeaveCommand.MESSAGE_AMBIGUOUS + LeaveCommand.MESSAGE_USAGE);

        assertParseFailure(parser, " 1 m/z", LeaveCommand.MESSAGE_NOT_A_NUMBER + LeaveCommand.MESSAGE_USAGE);
    }
}
