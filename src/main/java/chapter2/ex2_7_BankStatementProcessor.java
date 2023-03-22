package chapter2;

import java.time.Month;
import java.util.List;

//ex2_6은 2_5의 calcualteTotalAmount와 selectInMonth 함수 부분임
public class ex2_7_BankStatementProcessor {

  private final List<ex2_4_BankTransaction> bankTransactions;

  public ex2_7_BankStatementProcessor(final List<ex2_4_BankTransaction> bankTransactions) {
    this.bankTransactions = bankTransactions;
  }

  public double calculateTotalAmount(){
    double total = 0d;
    for (ex2_4_BankTransaction bankTransaction : bankTransactions) {
      total += bankTransaction.getAmount();
    }
    return total;
  }

  public double calculateTotalInMonth(final Month month) {
    double total = 0d;
    for (final ex2_4_BankTransaction bankTransaction : bankTransactions) {
      if (bankTransaction
          .getDate()
          .getMonth() == month) {
        total += bankTransaction.getAmount();
      }
    }
    return total;
  }

  public double calculateTotalForCategory(final String category) {
    double total = 0d;
    for (final ex2_4_BankTransaction bankTransaction : bankTransactions) {
      if (bankTransaction
          .getDescription()
          .equals(category)) {
        total += bankTransaction.getAmount();
      }
    }
    return total;
  }
}
