public class ChangeHoldTransaction extends ChangeMethodTransaction {
    private String itsAddress;

    public ChangeHoldTransaction(int empId, String address) {
        super(empId);
        itsAddress = address;
    }

    public void Change(Employee e) {
        e.SetMethod(HoldMethod());
    }

    PaymentMethod GetMethod() {
        return new HoldMethod(itsAddress);
    }

}
