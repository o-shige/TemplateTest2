public class PaydayTransaction {

    public void Execute() {
        List<Integer> empIds = PayrollDatabase.GetAllEmployeeIds();
        for (int empId : empIds) {
            Employee e = PayrollDatabase.GetEmployee(empId);
            if (e.IsPayDate(itsPayDate)) {
                Paycheck pc = new Paycheck(e.GetPayPeriodStartDate(itsPayDate), itsPayDate);
                itsPaychecks.put(empId, pc);
                e.Payday(pc);
            }
        }
    }
}
