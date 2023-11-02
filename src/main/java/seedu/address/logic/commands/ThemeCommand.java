package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Changes the application theme to the specified theme.
 */
public class ThemeCommand extends Command {

    public static final String COMMAND_WORD = "theme";

    public static final String MESSAGE_THEME_CHANGE_ACKNOWLEDGEMENT = "Theme changed.";

    public final String styleSheet;

    public ThemeCommand(String styleSheet) {
        this.styleSheet = styleSheet;
    }

    @Override
    public CommandResult execute(Model model, String commandText) {
        return new CommandResult(MESSAGE_THEME_CHANGE_ACKNOWLEDGEMENT, false,
                false, true, styleSheet);
    }
}
