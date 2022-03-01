import java.util.Calendar;

public class MonthlySchedule {

    private boolean IsLastDayOfMonth(Calendar date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date.getTime());
        return (cal.getActualMaximum(Calendar.DATE) == cal.get(Calendar.DATE));
    }

    public boolean IsPayDate(Calendar payDate) {
        return IsLastDayOfMonth(payDate);
    }
}
