package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.ExportCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * .Performs exporting of employee's data into csv format.
 */
public class ExportCommandParser implements Parser<ExportCommand> {

    /**
     * Returns ExportCommand containing the name in which user wants to name the file.
     *
     * @param args CLI arguments that user typed.
     * @return ExportCommand Object containing the file name user provided.
     * @throws ParseException If user provides 2 or more filenames.
     */
    @Override
    public ExportCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.viewTokenize(args);
        String nameArgs = argMultimap.getPreamble();
        System.out.println(nameArgs);
        boolean validFileName = nameChecker(nameArgs);
        if (!validFileName) {
            throw new ParseException(Messages.WRONG_EXPORT_FILE_NAME_FAILURE);
        }
        return new ExportCommand(nameArgs);
    }

    /**
     * Returns boolean to check if user Args only contains exactly one filename.
     *
     * @param nameArgs String after the export word.
     * @return boolean true if nameArgs is valid else false.
     */
    public boolean nameChecker(String nameArgs) {
        String[] variousNames = nameArgs.split(" ");
        int strlen = variousNames.length;
        if (strlen == 1 && nameArgs.equals("")) {
            return false;
        } else if (strlen > 1) {
            return false;
        } else {
            return true;
        }
    }
}
