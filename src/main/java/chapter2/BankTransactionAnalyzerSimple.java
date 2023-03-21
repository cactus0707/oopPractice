package chapter2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class BankTransactionAnalyzerSimple {

  private static final String RESOURCES = "src/main/resources/";

  public static void main(String... args) throws IOException {
    final Path path = Paths.get(RESOURCES + args[0]);
    final List<String> lines = Files.readAllLines(path);
    double total = 0d;
    for (String line : lines) {
      final String[] columns = line.split(",");
      final double amount = Double.parseDouble(columns[1]);
      total += amount;
    }
    System.out.println("The total for all transaction is  " + total);

  }

  //   TO -DO
//  1. 인텔리제이 빈 프로젝트 그래들 설정 방법
//  -> 세팅 -> 컴파일러 ->  그래이들로 설정 되어 있지만, 컴파일 속도를 위해 빌드 툴이아닌, 인텔리제이 내장으로 바꾸자.
//  2. 파일 경로 읽기 리소스 밑에 있는 파일 읽기 & 3. Paths.get 동작 읽기 및 경로 표시법 & 4. 명령행 인자 전달 방법
//  -> Paths 객체를 이용하여, 명령행 인자로 파일을 경로를 넘겨지는 방법을 사용한다.
//  -> 인텔리제이의 런 옵션에서 파일명을 인자로주고, 프로젝트 소스 루트 디렉토리를 RESOURCES 상수 변수에 지정하자.
//  -> 루트 디렉토리 + 런옵션에서 준 파일명 이 Paths.get()함수의 인자로 들어감

  /* Summary
  * 요구사항은 csv 파일에 저장된 입출금 내역을 보고
  * 1. 은행 입출금 내역의 총 수입과 총 지출은 각 얼마인가? 양수인가? 음수인가?
  * 2. 특정 달엔 몇건의 입출금 내역이 발생했는가?
  * 3. 지출이 가장 높은 상위 10건은 무엇인가?
  * 4. 돈을 가장 많이 소비하는 항목은 무엇인가?
  * 에 대한 대답이 요구 사항이다.
  *
  * 우선 첫 번째 질문에 대한 대답 부터 하자.
  * KISS 원칙(Keep it short simple)을 이용해 응용프로그램 코드를 한 개의 클래스로 구현 했다.
  * */
}
