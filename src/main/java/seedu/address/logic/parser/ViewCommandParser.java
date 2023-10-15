package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLAIM_BUDGET;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEPARTMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DOB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALARY;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ViewCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ViewCommand object.
 */
public class ViewCommandParser implements Parser<ViewCommand> {

    public static final String NAME_IDENTIFIER = "Name";
    public static final String PHONE_IDENTIFIER = "Phone";
    public static final String EMAIL_IDENTIFIER = "Email";
    public static final String ADDRESS_IDENTIFIER = "Address";
    public static final String SALARY_IDENTIFIER = "Salary";
    public static final String CLAIM_BUDGET = "Claim Budget";
    public static final String DEPARTMENT = "Department";
    public static final String BIRTHDAY = "Birthday";

    public static final List<Prefix> VALIDPREFIXES = new ArrayList<>(List.of(PREFIX_NAME,
        PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_SALARY, PREFIX_CLAIM_BUDGET,
                PREFIX_DEPARTMENT, PREFIX_DOB));

    /**
     * Returns ViewCommand Object based on user's input.
     * HashMap is generated which contains actions and indexes.
     *
     * @param args User's input to the system.
     * @return ViewCommand Object.
     * @throws ParseException if wrong prefix or no-prefix provided.
     */
    public ViewCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.viewTokenize(args);
        HashMap<String, List<Index>> map = new HashMap<>();
        validatePrefixes(argMultimap);
        if (argMultimap.getAllValues(PREFIX_NAME).size() != 0) {
            List<Index> resultIndex = relevantIndexes(argMultimap, PREFIX_NAME);
            map.put(NAME_IDENTIFIER, new ArrayList<>(resultIndex));
        }
        if (argMultimap.getAllValues(PREFIX_PHONE).size() != 0) {
            List<Index> resultIndex = relevantIndexes(argMultimap, PREFIX_PHONE);
            map.put(PHONE_IDENTIFIER, new ArrayList<>(resultIndex));
        }
        if (argMultimap.getAllValues(PREFIX_EMAIL).size() != 0) {
            List<Index> resultIndex = relevantIndexes(argMultimap, PREFIX_EMAIL);
            map.put(EMAIL_IDENTIFIER, new ArrayList<>(resultIndex));
        }
        if (argMultimap.getAllValues(PREFIX_ADDRESS).size() != 0) {
            List<Index> resultIndex = relevantIndexes(argMultimap, PREFIX_ADDRESS);
            map.put(ADDRESS_IDENTIFIER, new ArrayList<>(resultIndex));
        }
        if (argMultimap.getAllValues(PREFIX_SALARY).size() != 0) {
            List<Index> resultIndex = relevantIndexes(argMultimap, PREFIX_SALARY);
            map.put(SALARY_IDENTIFIER, new ArrayList<>(resultIndex));
        }
        if (argMultimap.getAllValues(PREFIX_CLAIM_BUDGET).size() != 0) {
            List<Index> resultIndex = relevantIndexes(argMultimap, PREFIX_CLAIM_BUDGET);
            map.put(CLAIM_BUDGET, new ArrayList<>(resultIndex));
        }
        if (argMultimap.getAllValues(PREFIX_DEPARTMENT).size() != 0) {
            List<Index> resultIndex = relevantIndexes(argMultimap, PREFIX_DEPARTMENT);
            map.put(DEPARTMENT, new ArrayList<>(resultIndex));
        }
        if (argMultimap.getAllValues(PREFIX_DOB).size() != 0) {
            List<Index> resultIndex = relevantIndexes(argMultimap, PREFIX_DOB);
            map.put(BIRTHDAY, new ArrayList<>(resultIndex));
        }
        return new ViewCommand(map);
    }

    /**
     * Returns List of all  prefixes split by ",".
     *
     * @param arg List returned by getAllValues function.
     * @return List containing all valid prefixes.
     */
    public List<String> parseView(List<String> arg) {
        List<String> splitList = new ArrayList<>();
        for (String item : arg) {
            String[] parts = item.split(",");
            splitList.addAll(Arrays.asList(parts));
        }
        return splitList;
    }

    /**
     * Returns a List informing us of the various indexes view operations have to be performed on.
     *
     * @param argumentMultimap  Object containing the prefixes user typed.
     * @param prefix Prefix Object that user wants to view.
     * @return List containing all indexes the particular prefix operation has to be performed on.
     * @throws ParseException If index is not of the correct format [Non-integer].
     */
    public List<Index> relevantIndexes(ArgumentMultimap argumentMultimap, Prefix prefix) throws ParseException {
        List<Index> finalIndex = new ArrayList<>();
        List<String> listStringIndex = argumentMultimap.getAllValues(prefix);
        List<String> indexList = parseView(listStringIndex);
        for (String stringIndex : indexList) {
            finalIndex.add(ParserUtil.parseIndex(stringIndex));
        }
        return finalIndex;
    }

    /**
     * Validates user's input in ensuring prefixes are given and are valid.
     *
     * @param argMultimap Object containing the prefixes user typed.
     * @throws ParseException In the event user input wrong prefix or didn't input any prefix.
     */
    public void validatePrefixes(ArgumentMultimap argMultimap) throws ParseException {
        List<Prefix> userGivenPrefixes = argMultimap.userPrefixes();
        if (userGivenPrefixes.size() == 0) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.WRONG_PREFIX));
        }
        for (Prefix prefix: userGivenPrefixes) {
            if (!VALIDPREFIXES.contains(prefix)) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.WRONG_PREFIX));
            }
        }
    }
}
