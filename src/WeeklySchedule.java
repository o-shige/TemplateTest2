import java.util.Calendar;

public class WeeklySchedule implements PaymentSchedule {
    public boolean IsPayDate(Calendar payDate) {
        return (payDate.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY);
    }

    public Calendar GetPayPeriodStartDate(Calendar payDate) {
        Calendar payPeriodStartDate = Calendar.getInstance();
        payPeriodStartDate.setTime(payDate.getTime());
        payPeriodStartDate.add(Calendar.DATE, -6);
        return payPeriodStartDate;
    }
}
