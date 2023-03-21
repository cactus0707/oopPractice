package chapter2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ex2_2_BankTransactionAnalyzerSimpleMonthly {

  private static final String RESOURCES = "src/main/resources/";
  public static void main(String... args) throws IOException {
    final Path path = Paths.get(RESOURCES + args[0]);
    final List<String> lines = Files.readAllLines(path);
    double total = 0d;
    final DateTimeFormatter DATE_PATTERN = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    for (String line : lines) {
      String[] columns = line.split(",");
      final LocalDate date = LocalDate.parse(columns[0], DATE_PATTERN);
      if (date.getMonth() == Month.JANUARY) {
        final double amount = Double.parseDouble(columns[1]);
        total += amount;
      }
    }
    System.out.println("The total for all transactions in January is " + total);

  }
}

/*
* final every where?
* final 이 계속 붙어 있는 모습을 보면서, 전문가를 위한 C++ - 마크그레고리 저 에서 읽었던 CONST EVERY WHERE 규칙이 생각 났다.
* 무엇이 변할 수 있고, 무엇이 변할 수 없는지 명확하게 구분할 수 있는 장점이 있다. 하지만 c++에서와 마찬가지로 자바에서도 final의 의미는
* 붙는 위치에 따라 의미가 조금 달라진다.
* 나의 해석은 "해당 데이터 혹은 주소를 변경할 수 없음을 나타낸다. 즉, 메모리에서 유일성이 보장되어야 한다." 이다.
* 1. final 변수
*    원시 타입
*    객체 타입 : 객체의 주소는 변경할 수 없음. 즉, 다른 객체로 변경 할 수 없음을 의미.(객체의 프로퍼티는 변경 가능)
*    클래스 필드(멤버 변수) : 값을 변경할 수 없음. 단, 생성 시점에 차이는 있을 수 있음 (static final, instance final)
*    -> 구분 방법은 static 이 붙으면 클래스 로더 호출시 한번만 초기화 되고 바꿀 수 없음을 의미하고,
*    -> static이 아니라면, 객체를 생성할 때 마다 초기화를 해(부모 먼저, 자신의 생성자 이전)
*    메서드 인자 : 메서드 안에서 변수 값을 변경할 수 없음. 여기서 조심해야할 점은 객체로 넘겼을 경우임.
* 2. final 메서드
*   -> 상속받은 객체에서 오버라이드 불가능을 의미. 기본원리 "해당 데이터 혹은 주소를 변경할 수 없음을 나타낸다."를 지킴
*   -> 해당 메소드에 final이 붙어 있다면, 이것은 동적 바인딩 시 다른 함수의 주소로 이동할 수 없음을 나타내고, 즉, 오버라이딩
*   -> 불가능을 의미함.(해당 함수는 그 주소에 맡는 동작을 수행함, 즉 동작이 변경 불가능함을 나타냄)
*   -> 앨런튜링은 처음으로 데어터와 연산이 같다는걸 이해한 사람이지 않을까?
*   -> 동적 바인딩: 런타임에 무엇이 결정 되는 것을 의미하는데, 오버라이딩 함수들은 자바의 경우 vtable을 보고 결정 되어진다.
*   -> 즉, vtable의 주소에서 다른 주소로 이동 불가능을 의미함
* 3. final 클래스
*   -> 상속 불가능을 의미함.
*   -> 상속이 동작하기 위해선 부모의 생성자를 호출하여 부모의 객체를 생성해야한다. 즉 final의 기본원리인 유일성 보장에 위배 된다.
*   -> 따라서, 다른 객체를 생성할 수 없음을 보장해야하므로 상속 불가능. 활용 방법은 constant 클래스나 util 클래스,
*   -> 즉 해당 클래스의 멤버 변수가 클래스 상태에 관여하지않는 클래스에 쓸 수 있다. (여기서 상태란 FSM 관점에서본 기계 해석)
*
 */

/* TO -DO
* 1. SRP를 적용해보자
* 2. SRP 적용 테스트 방법
*   2-1 한클래스는 한 기능만 책임진다.
*   2-2 클래스가 바뀌어야하는 이유는 오직 하나여야 한다.
*
* 왜 SOLID를 적용하는가? 나의 생각
* SOLID를 적용하는 이유는 시스템은 "변화" 하기 떄문이라고 생각함. 괴델의 불확정성 원리에 따르면, 기계는 새로운 진리를 창출해 낼 수 없다.
* 다만, 여러가지 공리들을 이용하여, 해당 머신이 기계적 완결성을 갖추게 하는 일이 엔지니어의 일이라고 생각함.
* 환경이 변하고 상황이 변하기 때문에, 항상 기계는 "변화"할 준비를 해야한다고 생각함.
*
* Art Of Scalability에 따르면, 확장성에 관한 이야기가 나오는데, 책을 관통하는 생각은 "추상화와 구조화" 문제에 대한 생각
* 을 정리해 놓은 것이라 생각함. 즉, 변화에 잘 대응 하려면, 추상화와 구조화를 잘 나누어야하고(문제나누기), SOLID는 문제를 한정시키는
* 하나의 도구라고 생각함. Art Of Scalability 에서 이야기하는 것과 코드레벨에서 이야기하는 것에 층위가 다르긴하지만, 증명된 괴델의 생각을
* 지지하고 있고, 코드가 아키텍처이고 아키텍처가 코드란 밥 아저씨의 의견을 지지 하므로, 확장성을 갖추기 위한 하나의 도구로써 SOLID를 적용하자.
* 물론 예외상황은 항상 존재하고 trade off는 존재함.
*
*
* */
