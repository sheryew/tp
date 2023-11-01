package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.ThemeCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class ThemeCommandParser implements Parser<ThemeCommand> {

    /**
     * Parses {@code userInput} into a command and returns it.
     *
     * @param args arguments given
     * @throws ParseException if {@code userInput} does not conform the expected format
     */
    @Override
    public ThemeCommand parse(String args) throws ParseException {
        requireNonNull(args);
        switch (args.trim()) {
        case "dark":
            return new ThemeCommand("DarkTheme.css");
        case "light":
            return new ThemeCommand("LightTheme.css");
        case "red":
            return new ThemeCommand("RedTheme.css");
        case "green":
            return new ThemeCommand("GreenTheme.css");
        case "blue":
            return new ThemeCommand("BlueTheme.css");
        default:
            throw new ParseException(Messages.MESSAGE_INVALID_THEME);
        }
    }
}
