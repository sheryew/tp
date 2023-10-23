package seedu.address.logic.parser;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.ExportCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.parser.exceptions.ParseException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Objects.requireNonNull;

public class ExportCommandParser implements Parser<ExportCommand>  {


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
