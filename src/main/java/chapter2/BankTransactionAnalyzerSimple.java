package chapter2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class BankTransactionAnalyzerSimple {

  private static final String RESOURCES = "src/main/resources/";

//   TO -DO
//  1. 인텔리제이 빈 프로젝트 그래들 설정 방법
//  2. 파일 경로 읽기 리소스 밑에 있는 파일 읽기
//  3. Paths.get 동작 읽기 및 경로 표시법,
//  4. 명령행 인자 전달 방법

  public static void main(String... args) throws IOException {
    final Path path = Paths.get(RESOURCES + args[0]);
    System.out.println(path);
    final List<String> lines = Files.readAllLines(path);
    double total = 0d;
    for (String line : lines) {
      final String[] columns = line.split(",");
      final double amount = Double.parseDouble(columns[1]);
      total += amount;
    }
    System.out.println("The total for all transaction is  " + total);

  }


}
