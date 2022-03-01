public abstract class ChangeEmployeeTransaction implements Transaction {
    private int itsEmpId;

    public void ChangeEmployeeTransaction(int empId) {
        itsEmpId = empId;
    }

    public void Execute() {
        // ユーザデータを取得して名前を変更する
        Employee e = new PayrollDatabase.GetEmployee(itsEmpId);
        if (e != null) {
            Change(e);
        }
    }

    abstract void Change(Employee e);
}
