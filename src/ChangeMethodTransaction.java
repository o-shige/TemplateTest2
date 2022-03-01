public abstract class ChangeMethodTransaction extends ChangeEmployeeTransaction {
    public ChangeMethodTransaction(int empId) {
        super(empId);
    }

    public void Change(Employee e) {
        e.SetMethod(GetMethod());
    }

    abstract PaymentMethod GetMethod();

}
