package chapter3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class BankStatementAnalyzer {

  public static final String RESOURCES ="src/main/resources/";
  private static final BankStatementCSVParser bankStatementCSVParser = new BankStatementCSVParser();

  public static void main(String[] args) throws IOException {
    Path path = Paths.get(RESOURCES + args[0]);
    List<String> lines = Files.readAllLines(path);
    List<BankTransaction> bankTransactions = bankStatementCSVParser.parseLinesFromCSV(lines);
    for (BankTransaction bankTransaction : bankTransactions) {
      System.out.println(bankTransaction);
    }
  }

}
