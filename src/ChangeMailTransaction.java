public class ChangeMailTransaction extends ChangeMethodTransaction {
    private String itsAddress;

    public ChangeMailTransaction(int empId, String address) {
        super(empId);
        itsAddress = address;
    }

    public void Change(Employee e) {
        e.SetMethod(MailMethod());
    }

    PaymentMethod GetMethod() {
        return new MailMethod(itsAddress);
    }

}
