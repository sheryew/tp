package seedu.address.logic.parser;

import static seedu.address.logic.commands.CommandTestUtil.VALID_DEPARTMENT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.model.person.Department;
import seedu.address.model.person.HasLeaveAnyMonthPredicate;
import seedu.address.model.person.HasLeaveThisMonthPredicate;
import seedu.address.model.person.MatchingDepartmentPredicate;
import seedu.address.model.person.Person;

class ViewLeaveCommandParserTest {

    private ViewLeaveCommandParser parser = new ViewLeaveCommandParser();

    @Test
    void parse_success() {
        Predicate<Person> testPredicateAnyMonth = new HasLeaveAnyMonthPredicate();
        Predicate<Person> testPredicateMonth = new HasLeaveThisMonthPredicate("3");
        Predicate<Person> testPredicateMonths = testPredicateMonth.and(new HasLeaveThisMonthPredicate("1"));
        Predicate<Person> testPredicateMonthAndDept = testPredicateMonth.and(
                new MatchingDepartmentPredicate(new Department(VALID_DEPARTMENT)));

        //assertParseSuccess(parser, "", new ViewLeaveCommand(testPredicateAnyMonth));
        //assertParseSuccess(parser, " m/3", new ViewLeaveCommand(testPredicateMonth));
        //assertParseSuccess(parser, " m/1,3", new ViewLeaveCommand(testPredicateMonths));
        //assertParseSuccess(parser, " m/1 d/Engineering", new ViewLeaveCommand(testPredicateMonthAndDept));
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
