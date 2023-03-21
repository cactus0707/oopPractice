package chapter2;

import java.time.LocalDate;
import java.util.Objects;

public class ex2_4_BankTransaction {
  private final LocalDate date;
  private final double amount;
  private final String description;


  public ex2_4_BankTransaction(final LocalDate date,
                               final double amount,
                               final String description) {
    this.date = date;
    this.amount = amount;
    this.description = description;
  }

  public LocalDate getDate() {
    return date;
  }

  public double getAmount() {
    return amount;
  }

  public String getDescription() {
    return description;
  }

  @Override
  public String toString() {
    return "ex2_4_BankTransaction{" +
        "date=" + date +
        ", amount=" + amount +
        ", description='" + description + '\'' +
        '}';
  }
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ex2_4_BankTransaction that = (ex2_4_BankTransaction) o;
    return Double.compare(that.amount, amount) == 0 && Objects.equals(date,
        that.date) && Objects.equals(description, that.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(date, amount, description);
  }
}
