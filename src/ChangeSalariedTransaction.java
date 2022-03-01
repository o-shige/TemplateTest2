public class ChangeSalariedTransaction extends ChangeClassificationTransaction {
    private double itsSalary;

    public ChangeSalariedTransaction(int empId, double salary) {
        super(empId);
        itsSalary = salary;
    }

    PaymentSchedule GetSchedule() {
        return new MonthlySchedule();
    }

    PaymentClassification GetClassification() {
        return new MonthlyClassification(itsSalary);
    }

}
