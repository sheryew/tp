package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.ExportCommand;

public class ExportCommandParserTest {

    private ExportCommandParser parser = new ExportCommandParser();

    @Test
    public void parse_validFilename_success() {
        String userInput = "engineering_dept";
        ExportCommand successExportCommand = new ExportCommand(userInput);
        assertParseSuccess(parser, userInput, successExportCommand);
    }

    @Test
    public void parse_excessFilenames_failure() {
        String invalidUserInput = "engineering_dept birthday_jan";
        assertParseFailure(parser, invalidUserInput, Messages.WRONG_EXPORT_FILE_NAME_FAILURE);
    }

    @Test
    public void parse_nullField_failure() {
        assertThrows(NullPointerException.class, () -> {
            parser.parse(null);
        });
    }

    @Test
    public void nameChecker_noArgs_failure() {
        String noFileNames = "";
        boolean isValidFilename = parser.nameChecker(noFileNames);
        assertFalse(isValidFilename);
    }

    @Test
    public void nameChecker_multipleArgs_failure() {
        String noFileNames = "engineering_dept superman";
        boolean isValidFilename = parser.nameChecker(noFileNames);
        assertFalse(isValidFilename);
    }

    @Test
    public void nameChecker_singleArgs_success() {
        String noFileNames = "hr_department";
        boolean isValidFilename = parser.nameChecker(noFileNames);
        assertTrue(isValidFilename);
    }
}
