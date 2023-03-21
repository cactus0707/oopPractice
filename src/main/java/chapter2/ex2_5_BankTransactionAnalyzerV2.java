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
    final String fileName = args[0];
    final Path path = Paths.get(RESOURCES + fileName);
    final List<String> lines = Files.readAllLines(path);

    final List<ex2_4_BankTransaction> bankTransactions = bankStatementParser.parseLinesFromCSV(
        lines); //파일을 읽어서(리스트<String> 타입으로 파서로 보냄
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

/**
 * 메서드가 수행하는 일을 바로 이애할 수 있도록 자체 문서화를 제공하는 메서드명을 사용한다(예를들어 cacliateTotalAmount 와 같은 메서드명)
 * 코드의 다른부분이 파라미터의 상태에 의존할 수 있으므로, 파라미터의 상태를 바꾸지 않는다.(final로 예방)
 *
 * 프로그램의 진입점인 BankTransactionAnalyzer는 다양한 응용프로그램으로 연결하는 역할을한다는 것을 알 수 있다.
 * 하지만, 계산 작업하는 로직은 클래스 내에 정적 메소드로 정의되어있음. 즉, 클래스의 응집도가 떨어짐을 알 수 있다.(서로 연관이 없는 것이 모여있음)
 * 문제를 나누자!! == Sub program을 만들자 !
 */