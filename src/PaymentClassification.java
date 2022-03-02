import java.util.Calendar;

public interface PaymentClassification {
    abstract double CalculatePay(Paycheck pc);

    public default boolean IsInPayPeriod(Calendar theDate, Paycheck pc) {
        Calendar payPeriodStartDate = pc.GetPayPeriodStartDate();
        Calendar payPeriodEndDate = pc.GetPayPeriodEndDate();
        if (0 <= theDate.compareTo(payPeriodStartDate) && theDate.compareTo(payPeriodEndDate) <= 0) {
            return true;
        }
        return false;
    }
}
