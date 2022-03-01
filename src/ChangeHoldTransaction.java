public class ChangeHoldTransaction extends ChangeMethodTransaction {
    private String itsAddress;

    public ChangeHoldTransaction(int empId, String address) {
        super(empId);
        itsAddress = address;
    }

    public void Change(Employee e) {
        e.SetMethod(GetMethod());
    }

    PaymentMethod GetMethod() {
        return new HoldMethod(itsAddress);
    }

}
