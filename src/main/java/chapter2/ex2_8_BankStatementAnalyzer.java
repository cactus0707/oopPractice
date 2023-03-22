package chapter2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Month;
import java.util.List;

public class ex2_8_BankStatementAnalyzer {

  private static final String RESOURCES = "src/main/resources/";
  private static final ex2_3_BankStatementCSVParser bankStatementParser = new ex2_3_BankStatementCSVParser();

  public static void main(final String... args) throws IOException {
    final String fileName = args[0];
    final Path path = Paths.get(RESOURCES + fileName);
    final List<String> lines = Files.readAllLines(path);
    List<ex2_4_BankTransaction> bankTransactions = bankStatementParser.parseLinesFromCSV(lines);
    ex2_7_BankStatementProcessor bankStatementProcessor = new ex2_7_BankStatementProcessor(
        bankTransactions);

    collectSummary(bankStatementProcessor);


  }

  private static void collectSummary(ex2_7_BankStatementProcessor bankStatementProcessor) {
    System.out.println(
        "The total for all transactions is " + bankStatementProcessor.calculateTotalAmount());
    System.out.println(
        "The total for transaction in January is " + bankStatementProcessor.calculateTotalInMonth(
            Month.JANUARY));
    System.out.println(
        "The total for transactions in Febuary is " + bankStatementProcessor.calculateTotalInMonth(
            Month.FEBRUARY));
    System.out.println(
        "The total salary received is " + bankStatementProcessor.calculateTotalForCategory(
            "Salary"));
  }
}
/*
* 클래스 수준의 응집도
* 1. 기능
* 2. 정보
* 3. 유틸리티
* 4. 논리
* 5. 순차
* 6. 시간
* 의 기준으로 그룹화를 시킴
* 각각의 그룹화 하는 방법
* 1. 기능
*  - 비슷한 메서드를 그룹화 했다 (CSVParser). 함께 사용하는 메서드를 그룹화 하면 찾기도 쉽고 이해하기도 쉬우므로 응집도를 높인다.
* 2. 정보
*  - 같은 데이터나 도메인 객체를 처리하는 메서드를 그룹화 하는 방법도 있다. 예제 처럼 BankTransaction 객체를 만들고 읽고
* 갱신하고 삭제하는 기능이 필요해 이런 기능만 제공하는 클래스를 만들어야 한다. 도메인 객체에 CRUD 메서드 함께 적는 것.
* (DAO 객체). 정보 응집은 여러 기능을 그룹화 하면서, 필요한 일부 기능을 포함하는 클래스 전체를 디펜던시로 추가한다는 약점이 있다.
* 3. 유틸리티
* - 때로는 관련성 없이 없는 메서드를 한 클래스에 포함시켜야 한다. 특히 메서드가 어디에 속해야 할지 결정하기 어려울 떄는 유틸리티
* 클래스에 추가히기도 한다.
* 4. 논리
* CSV,JSON,XML 자료를 파싱하는 코드를 생각해본다면, "파싱" 이라는 논리로 그룹화 되었다. 하지만, 네 메서드는 서로 관련이 없다.
* SRP위배 되므로 결국 이 방법은 권장되지 않는다.
* 5. 순차
* 파일을 읽고, 파싱하고, 처리하고, 정보를 저장하는 메서드 들을 한 클래스(ex2_8_BankStatementAnalyzer)로 그룹화 한다.
* 입출력이 순차적으로 흐르는 것을 순차 응집이라고 부른다. 마찬가지로, 순차 응집은 SRP에 위배 된다. 따라서, 각 책임을 개별적으로 응집된
* 클래스로 분리하는 것이 더 좋은 방법이다.
* 6. 시간
* 시간 응집 클래스는 여러 연산 중 시간과 관련된 연산을 그룹화 한다. 어떤 처리 작업을 시작하기 전과 뒤에 초기화, 뒷 정리 작업(데이터베이스 연결과 종료)을
* 담당하는 메서드를 포함하는 클래스가 그 예다.초기화 작업은 다른작업과 관련이 없지만, 다른 작업보다 먼저 실행 되어야한다.
* */
