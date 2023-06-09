package chapter3;

import chapter2.ex2_4_BankTransaction;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class BankStatementCSVParser {
  private static final DateTimeFormatter DATE_PATTERN = DateTimeFormatter.ofPattern("dd-MM-yyyy");

  public List<BankTransaction> parseLinesFromCSV(final List<String> lines) {
    final List<BankTransaction> bankTransactions = new ArrayList<>();
    for (String line : lines) {
      bankTransactions.add(parseFromCSV(line));
    }
    return bankTransactions;
  }

  private BankTransaction parseFromCSV(String line) {
    final String[] columns = line.split(",");
    final LocalDate date = LocalDate.parse(columns[0], DATE_PATTERN);
    final double amount = Double.parseDouble(columns[1]);
    final String description = columns[2];

    return new BankTransaction(date, amount, description);
  }

}