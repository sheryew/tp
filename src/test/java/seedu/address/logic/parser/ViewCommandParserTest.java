package seedu.address.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ViewCommand;
import seedu.address.logic.parser.exceptions.ParseException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.ViewCommandParser.BIRTHDAY;
import static seedu.address.logic.parser.ViewCommandParser.NAME_IDENTIFIER;
import static seedu.address.logic.parser.ViewCommandParser.PHONE_IDENTIFIER;
import static seedu.address.logic.parser.ViewCommandParser.EMAIL_IDENTIFIER;
import static seedu.address.logic.parser.ViewCommandParser.ADDRESS_IDENTIFIER;
import static seedu.address.logic.parser.ViewCommandParser.SALARY_IDENTIFIER;
import static seedu.address.logic.parser.ViewCommandParser.CLAIM_BUDGET;
import static seedu.address.logic.parser.ViewCommandParser.DEPARTMENT;

public class ViewCommandParserTest {
    private ViewCommandParser parser = new ViewCommandParser();

    @Test
    public void parse_withAllFields_presentreturnssuccess() throws ParseException {
        String userInput = " n/1 p/1 e/1 a/1 s/1 b/1 d/1 dob/1";
        Index index = ParserUtil.parseIndex("1");
        List<Index> indexOne = new ArrayList<>(List.of(index));
        HashMap<String, List<Index>> successHashMap = new HashMap<>();
        successHashMap.put(NAME_IDENTIFIER, indexOne);
        successHashMap.put(PHONE_IDENTIFIER, indexOne);
        successHashMap.put(EMAIL_IDENTIFIER, indexOne);
        successHashMap.put(ADDRESS_IDENTIFIER, indexOne);
        successHashMap.put(SALARY_IDENTIFIER, indexOne);
        successHashMap.put(CLAIM_BUDGET, indexOne);
        successHashMap.put(DEPARTMENT, indexOne);
        successHashMap.put(BIRTHDAY, indexOne);
        ViewCommand successCommand = new ViewCommand(successHashMap);
        assertParseSuccess(parser, userInput, successCommand);
    }

    @Test
    public void parse_delimiter_success() {
        List<String> testInputArr = new ArrayList<>(List.of("A,B,C"));
        List<String> expectedParseArr = new ArrayList<>(List.of("A", "B", "C"));
        List<String> producedArr = parser.parseView(testInputArr);
        assertEquals(expectedParseArr, producedArr);
    }

    @Test
    public void parse_delimiter_failure() {
        List<String> testInputArr = new ArrayList<String>(List.of("A,B,C"));
        List<String> expectedParseArr = new ArrayList<String>(List.of("A", "BC"));
        List<String> producedArr = parser.parseView(testInputArr);
        assertNotEquals(expectedParseArr, producedArr);
    }

    @Test
    public void parse_relevantIndex_success() throws ParseException {
        String userInput = " n/1,2";
        ArgumentMultimap argMultimap = ArgumentTokenizer.viewTokenize(userInput);
        Index indexOne = ParserUtil.parseIndex("1");
        Index indexTwo = ParserUtil.parseIndex("2");
        List<Index> expectedIndexArr = new ArrayList<>(List.of(indexOne, indexTwo));
        List<Index> producedIndexArr = parser.relevantIndexes(argMultimap, PREFIX_NAME);
        assertEquals(expectedIndexArr, producedIndexArr);
    }

    @Test
    public void parse_relevantIndex_failure() throws ParseException {
        String userInput = " n/1,2";
        ArgumentMultimap argMultimap = ArgumentTokenizer.viewTokenize(userInput);
        Index indexOne = ParserUtil.parseIndex("1");
        List<Index> expectedIndexArr = new ArrayList<>(List.of(indexOne));
        List<Index> producedIndexArr = parser.relevantIndexes(argMultimap, PREFIX_NAME);
        assertNotEquals(expectedIndexArr, producedIndexArr);
    }

    @Test
    public void parse_emptyparams_success() throws ParseException {
        String userInput = " ";
        ArgumentMultimap argMultimap = ArgumentTokenizer.viewTokenize(userInput);
        assertThrows(ParseException.class, () -> {
            parser.validatePrefixes(argMultimap);
        });
    }

    @Test
    public void parse_wrongparams_success() throws ParseException {
        String userInput = " q/1";
        ArgumentMultimap argMultimap = ArgumentTokenizer.viewTokenize(userInput);
        assertThrows(ParseException.class, () -> {
            parser.validatePrefixes(argMultimap);
        });
    }
}
