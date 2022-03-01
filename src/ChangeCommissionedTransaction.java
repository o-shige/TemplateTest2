public class ChangeCommissionedTransaction extends ChangeClassificationTransaction {
    private double itsSalary;
    private double itsRate;

    public ChangeCommissionedTransaction(int empId, double salary, double rate) {
        super(empId);
        itsSalary = salary;
        itsRate = rate;
    }

    PaymentSchedule GetSchedule() {
        return new BiweeklySchedule();
    }

    PaymentClassification GetClassification() {
        return new CommissionedClassification(itsSalary, itsRate);
    }

}
