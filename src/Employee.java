public class Employee {

    public void Payday(Paycheck pc) {
        double grossPay = itsClassification.CalculatePay(pc);
        double deductions = itsAffiliation.CalculateDeductions(pc);
        double netPay = grossPay - deductions;
        pc.SetGrossPay(grosspay);
        pc.SetDeductions(deductions);
        pc.SetNetPay(netPay);
        itsPaymentMethod.Pay(pc);
    }
}
