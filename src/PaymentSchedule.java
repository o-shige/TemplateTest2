import java.util.Calendar;

public interface PaymentSchedule {
    boolean IsPayDate(Calendar payDate);

    Calendar GetPayPeriodStartDate(Calendar payDate);
}
