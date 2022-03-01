public abstract class ChangeClassificationTransaction extends ChangeEmployeeTransaction {
    public ChangeClassificationTransaction(int empId) {
        super(empId);
    }

    public void Change(Employee e) {
        e.SetSchedule(GetSchedule());
        e.SetClassification(GetClassification());
    }

    abstract PaymentSchedulle GetSchedule();

    abstract PaymentClassification GetClassification();

}
