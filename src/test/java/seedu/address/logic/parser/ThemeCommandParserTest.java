package seedu.address.logic.parser;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.ThemeCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;


class ThemeCommandParserTest {

    private Model model = new ModelManager();

    private ThemeCommandParser parser = new ThemeCommandParser();

    @Test
    public void execute_success() throws ParseException {
        assertCommandSuccess(parser.parse("red"), model,
                ThemeCommand.MESSAGE_THEME_CHANGE_ACKNOWLEDGEMENT, model);
        assertCommandSuccess(parser.parse("green"), model,
                ThemeCommand.MESSAGE_THEME_CHANGE_ACKNOWLEDGEMENT, model);
        assertCommandSuccess(parser.parse("blue"), model,
                ThemeCommand.MESSAGE_THEME_CHANGE_ACKNOWLEDGEMENT, model);
        assertCommandSuccess(parser.parse("light"), model,
                ThemeCommand.MESSAGE_THEME_CHANGE_ACKNOWLEDGEMENT, model);
        assertCommandSuccess(parser.parse("dark"), model,
                ThemeCommand.MESSAGE_THEME_CHANGE_ACKNOWLEDGEMENT, model);
    }

    @Test
    public void execute_failure() throws ParseException {
        assertParseFailure(parser, " abc", Messages.MESSAGE_INVALID_THEME);
        assertParseFailure(parser, "", Messages.MESSAGE_INVALID_THEME);
        assertParseFailure(parser, "  ", Messages.MESSAGE_INVALID_THEME);
    }
}
