public class ChangeMailTransaction extends ChangeMethodTransaction {
    private String itsAddress;

    public ChangeMailTransaction(int empId, String address) {
        super(empId);
        itsAddress = address;
    }

    public void Change(Employee e) {
        e.SetMethod(GetMethod());
    }

    PaymentMethod GetMethod() {
        return new MailMethod(itsAddress);
    }

}
