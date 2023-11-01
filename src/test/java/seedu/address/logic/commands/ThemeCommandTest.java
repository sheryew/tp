package seedu.address.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

class ThemeCommandTest {

    private Model model = new ModelManager();

    @Test
    public void execute_success() {
        assertCommandSuccess(new ThemeCommand("LightTheme.css"), model,
                ThemeCommand.MESSAGE_THEME_CHANGE_ACKNOWLEDGEMENT, model);
    }
}