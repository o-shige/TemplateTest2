public class TimeCardTransaction implements Transaction {
    private long itsDate;
    private double itsHours;
    private int itsEmpId;

    public TimeCardTransaction(long date, double hours, int empId) {
        itsDate = date;
        itsHours = hours;
        itsEmpId = empId;
    }

    public void Execute() {
        Employee e = PayrollDatabase.GetEmployee(itsEmpId);
        if (e != null) {
            PaymentClassification pc = e.GetClassification();
            if (pc instanceof HourlyClassification) {
                HourlyClassification hc = (HourlyClassification) pc;
                hc.AddTimeCard(new TimeCard(itsDate, itsHours));
            } else {
                throw new RuntimeException("Tried to add timecard to non-hourly employee.");
            }
        } else {
            throw new RuntimeExveption("No such employee.");
        }
    }
}
