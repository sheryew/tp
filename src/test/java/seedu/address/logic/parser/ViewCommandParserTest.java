package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.ViewCommandParser.CLAIM_BUDGET;
import static seedu.address.logic.parser.ViewCommandParser.DEPARTMENT;
import static seedu.address.logic.parser.ViewCommandParser.SALARY_IDENTIFIER;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ViewCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class ViewCommandParserTest {
    private ViewCommandParser parser = new ViewCommandParser();

    @Test
    public void parse_withClaim_presentReturnsSuccess() throws ParseException {
        String userInput = " b/1";
        Index index = ParserUtil.parseIndex("1");
        List<Index> indexOne = new ArrayList<>(List.of(index));
        HashMap<String, List<Index>> successHashMap = new HashMap<>();
        successHashMap.put(CLAIM_BUDGET, indexOne);
        ViewCommand successCommand = new ViewCommand(successHashMap);
        assertParseSuccess(parser, userInput, successCommand);
    }

    @Test
    public void parse_withDepartment_presentReturnsSuccess() throws ParseException {
        String userInput = " d/2";
        Index index = ParserUtil.parseIndex("2");
        List<Index> indexTwo = new ArrayList<>(List.of(index));
        HashMap<String, List<Index>> successHashMap = new HashMap<>();
        successHashMap.put(DEPARTMENT, indexTwo);
        ViewCommand successCommand = new ViewCommand(successHashMap);
        assertParseSuccess(parser, userInput, successCommand);
    }

    @Test
    public void parse_withSalary_presentReturnsSuccess() throws ParseException {
        String userInput = " s/4";
        Index index = ParserUtil.parseIndex("4");
        List<Index> indexTwo = new ArrayList<>(List.of(index));
        HashMap<String, List<Index>> successHashMap = new HashMap<>();
        successHashMap.put(SALARY_IDENTIFIER, indexTwo);
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
    public void parse_excessparams_success() throws ParseException {
        String userInput = " n/1,2 q/1,2";
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
