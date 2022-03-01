public class ChangeDirectTransaction extends ChangeMethodTransaction {
    private String itsBank;
    private String itsAccount;

    public ChangeDirectTransaction(int empId, String bank, String account) {
        super(empId);
        itsBank = bank;
        itsAccount = account;
    }

    public void Change(Employee e) {
        e.SetMethod(GetMethod());
    }

    PaymentMethod GetMethod() {
        return new DirectMethod(itsBank, itsAccount);
    }
}
