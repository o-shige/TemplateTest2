public class ChangeMemberTransaction extends ChangeAffiliationTransaction {
    private int itsMemberId;
    private double itsDues;

    public ChangeMemberTransaction(int empId, int memberId, double dues) {
        super(empId);
        itsMemberId = memberId;
        itsDues = dues;
    }

    public Affiliation GetAffiliation() {
        return new UnionAffiliation(itsMemberId, itsDues);
    }

    public void RecordMembership(Employee e) {
        PayrollDatabase.AddUnionMember(itsMemberId, e);
    }
}
