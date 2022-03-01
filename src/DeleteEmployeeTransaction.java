public class DeleteEmployeeTransaction implements Transaction {
    private int itsEmpId;

    public DeleteEmployeeTransaction(int empId) {
        itsEmpId = empId;
    }

    public void Execute() {
        PayrollDatabase.DeleteEmployee(itsEmpId);
    }

}
