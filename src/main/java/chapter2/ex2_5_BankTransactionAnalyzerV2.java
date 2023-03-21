package chapter2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class ex2_5_BankTransactionAnalyzerV2 {
  private static final String RESOURCES = "src/main/resources/";
  public static void main(String... args) throws IOException {
    final ex2_3_BankStatementCSVParser bankStatementParser = new ex2_3_BankStatementCSVParser();
    final String fimeName = args[0];
    final Path path = Paths.get(RESOURCES + fimeName);
    final List<String> lines = Files.readAllLines(path);

    final List<ex2_4_BankTransaction> bankTransactions = bankStatementParser.parseLinesFromCSV(
        lines);
    System.out.println(
        "The total for all transaction is " + calculateTotalAmount(bankTransactions));
    System.out.println("Transactions in January " + selectInMonth(bankTransactions, Month.JANUARY));
  }

  public static double calculateTotalAmount(final List<ex2_4_BankTransaction> bankTransactions) {
    double total = 0d;
    for (ex2_4_BankTransaction bankTransaction : bankTransactions) {
      total += bankTransaction.getAmount();
    }
    return total;
  }

  public static List<ex2_4_BankTransaction> selectInMonth(final List<ex2_4_BankTransaction> bankTransactions,
                                                           Month month) {
    final List<ex2_4_BankTransaction> bankTransactionsInMonth = new ArrayList<>();
    for (final ex2_4_BankTransaction bankTransaction : bankTransactions) {
      if (bankTransaction
          .getDate()
          .getMonth() == month) {
        bankTransactionsInMonth.add(bankTransaction);
      }
    }
    return bankTransactionsInMonth;
  }

}
