package seedu.address.logic.parser;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Department;
import seedu.address.model.person.MatchingDepartmentPredicate;

class ListCommandParserTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    private ListCommandParser parser = new ListCommandParser();

    @Test
    void parse_success() throws ParseException {
        Department department1 = new Department("Engineering");
        MatchingDepartmentPredicate predicate1 = new MatchingDepartmentPredicate(department1);
        Model expectedModel1 = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel1.updateFilteredPersonList(predicate1);
        assertCommandSuccess(parser.parse(" d/engineering"), model,
                String.format(Messages.MESSAGE_FILTER_SUCCESS,
                expectedModel1.getFilteredPersonList().size()), expectedModel1);
    }

    @Test
    void parse_failure() {
        assertParseFailure(parser, " d/Engineering d/engineering",
                Messages.getErrorMessageForDuplicatePrefixes(new Prefix("d/")));
        assertParseFailure(parser, " abc", String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                Messages.MESSAGE_LIST_COMMAND_FORMAT));
        assertParseFailure(parser, " d/", Messages.MESSAGE_EMPTY_DEPARTMENT_FILTER);
    }
}
