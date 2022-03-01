public class ServiceChargeTransaction implements Transaction {
    private int itsMemberId;
    private long itsDate;
    private double itsAmount:

    public ServiceChargeTransaction(int memberId, long date, double amount) {
        itsMemberId = memberId;
        itsDate = date;
        itsAmount = amount;
    }

    public void Execute() {
        Employee e = PayrollDatabase.GetUnionMember(itsMEmberId);
        Affiliation af = e.GetAffiliation();
        if (af instanceof UnionAffiliation) {
            UnionAffiliation uaf = (UnionAffiliation) af;
            uaf.AddServiceCharge(itsDate, itsAmount);
        }
    }

}
