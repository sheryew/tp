package seedu.address.logic.parser;

import static seedu.address.logic.commands.CommandTestUtil.VALID_DEPARTMENT;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Department;
import seedu.address.model.person.HasLeaveAnyMonthPredicate;
import seedu.address.model.person.HasLeaveThisMonthPredicate;
import seedu.address.model.person.MatchingDepartmentPredicate;
import seedu.address.model.person.Person;

class ViewLeaveCommandParserTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    private ViewLeaveCommandParser parser = new ViewLeaveCommandParser();

    @Test
    void parse_success() throws ParseException {
        Predicate<Person> testPredicateAnyMonth = new HasLeaveAnyMonthPredicate();
        Predicate<Person> testPredicateMonth = new HasLeaveThisMonthPredicate("3");
        Predicate<Person> testPredicateMonths = testPredicateMonth.and(new HasLeaveThisMonthPredicate("1"));
        Predicate<Person> testPredicateMonthAndDept = testPredicateMonth.and(
                new MatchingDepartmentPredicate(new Department(VALID_DEPARTMENT)));

        Model expectedModel1 = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel1.updateFilteredPersonList(testPredicateAnyMonth);
        assertCommandSuccess(parser.parse(""), model, String.format(Messages.MESSAGE_VIEW_LEAVE_SUCCESS,
                expectedModel1.getFilteredPersonList().size()), expectedModel1);

        Model expectedModel2 = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel2.updateFilteredPersonList(testPredicateMonth);
        assertCommandSuccess(parser.parse(" m/3"), model, String.format(Messages.MESSAGE_VIEW_LEAVE_SUCCESS,
                expectedModel2.getFilteredPersonList().size()), expectedModel2);

        Model expectedModel3 = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel3.updateFilteredPersonList(testPredicateMonths);
        assertCommandSuccess(parser.parse(" m/1,3"), model, String.format(Messages.MESSAGE_VIEW_LEAVE_SUCCESS,
                expectedModel3.getFilteredPersonList().size()), expectedModel3);

        Model expectedModel4 = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel4.updateFilteredPersonList(testPredicateMonthAndDept);
        assertCommandSuccess(parser.parse(" m/1 d/Engineering"), model, String.format(Messages.MESSAGE_VIEW_LEAVE_SUCCESS,
                expectedModel4.getFilteredPersonList().size()), expectedModel4);
    }

    @Test
    void parse_failure() {
        assertParseFailure(parser, " d/Engineering d/engineering",
                Messages.getErrorMessageForDuplicatePrefixes(new Prefix("d/")));
        assertParseFailure(parser, " m/1 m/5",
                Messages.getErrorMessageForDuplicatePrefixes(new Prefix("m/")));
        assertParseFailure(parser, " abc", String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                Messages.MESSAGE_VIEW_LIST_COMMAND_FORMAT));
        assertParseFailure(parser, " m/1, 4", Messages.MESSAGE_MONTHS_SPACES_DETECTED
                + Messages.MESSAGE_VIEW_LIST_COMMAND_FORMAT);
        assertParseFailure(parser, " m/15", Messages.MESSAGE_INVALID_MONTH);
    }
}
