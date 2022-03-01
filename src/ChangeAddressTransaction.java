public class ChangeAddressTransaction {
    private String itsAddress;

    public ChangeAddressTransaction(int empId, String address) {
        super(empId);
        itsAddress = address;
    }

    public void Change(Employee e) {
        e.SetAddress(itsAddress);
    }
}
