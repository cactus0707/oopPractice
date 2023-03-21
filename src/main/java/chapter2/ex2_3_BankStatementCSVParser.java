package chapter2;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/*TO - DO
* 이전 갓 클래스 프로그램의 책임들
* 1. 입력 읽기
* 2. 주어진 형식의 입력 파싱
* 3. 결과 처리
* 4. 결과 요약 리포트
*
* -> 2번 책임을 나누자.
* */
public class ex2_3_BankStatementCSVParser {

  private static final DateTimeFormatter DATE_PATTERN = DateTimeFormatter.ofPattern("dd-MM-yyyy");

  private ex2_4_BankTransaction parseFromCSV(final String line) {
    final String[] columns = line.split(",");
    final LocalDate date = LocalDate.parse(columns[0], DATE_PATTERN);
    final double amount = Double.parseDouble(columns[1]);
    final String description = columns[2];

    return new ex2_4_BankTransaction(date, amount, description);
  }

  public List<ex2_4_BankTransaction> parseLinesFromCSV(final List<String> lines) {
    final List<ex2_4_BankTransaction> bankTransactions = new ArrayList<>();
    for (String line : lines) {
      bankTransactions.add(parseFromCSV(line));
    }
    return bankTransactions;
  }
}
