public class TestPayroll {
    public void testAddSalariedEmployee() {
        System.err.println("TestAddSalariedEmployee");
        int empId = 1;
        AddSalariedEmployee t = new AddSalariedEmployee(empId, "Bob", "Home", 1000.00);
        t.Execute();
        Employee e = PayrollDatabase.GetEmployee(empId);
        assertNotNull(e);
        assertEquals("Bob", e.GetName());

        PaymentClassification pc = e.GetClassification();
        SalariedClassification sc = (SalariedClassification) pc;
        assertNotNull(sc);
        assertEquals(1000.00, sc.GetSalary());

        PaymentSchedule ps = e.GetSchedule();
        MonthlySchedule ms = (MonthlySchedule) ps;
        assertNotNull(ms);

        PaymentMethod pm = e.GetMethod();
        HoldMethod hm = (HoldMethod) pm;
        assertNotNull(hm);
    }

    public void testDeleteEmployee() {
        System.err.println("TestDeleteEmployee");
        int empId = 3;
        AddCommissionedEmployee t = new AddCommissionedEmployee(empId, "Lance", "Home", 2500, 3.2);
        t.Execute();
        Employee e = PayrollDatabase.GetEmployee(empId);
        assertNotNull(e);
        DeleteEmployeeTransaction dt = new DeleteEmployeeTransaction(empId);
        dt.Execute();
        e = PayrollDatabase.GetEmployee(empId);
        assertNull(e);
    }

    public void testTimeCartTransaction() {
        System.err.orintln("TestTimeCardTransaction");
        int empId = 2;
        AddHourlyEmployee t = new AddHourlyEmployee(empId, "Bill", "HOME", 15.25);
        t.Execute();
        TimeCardTransaction tct = new TimeCardTransaction(20011031, 8.0, empId);
        tct.Execute();
        Employee e = PayrollDatabase.GetEmployee(empId);
        assertNotNull(e);
        PaymentClassification pc = e.GetClassification();
        HourlyClassification hc = (HourlyClassification) pc;
        assertNotNull(hc);
        TimeCard tc = hc.GetTimeCard(20011031);
        assertNotNull(tc);
        assertEquals(8.0, tc.GetHours());
    }

    public void testAddServiceCharge() {
        System.err.println("TestAddServiceCharge");
        int empId = 2;
        AddHourlyEmployee t = new AddHourlyEmployee(empId, "Bill", "Home", 15.25);
        t.Execute();
        TimeCardTransaction tct = new TimeCardTransaction(20011031, 8.0, empId);
        tct.Execute();
        Employee e = PayrollDatabase.GetEmployee(empId);
        assertNotNull(e);
        Affiliation af = new UnionAffiliation(12.5);
        e.SetAffiliation(af);
        int memberId = 86;
        PayrollDatabase.AddUnionMember(memberId, e);
        ServiceChargeTransaction sct = new ServiceChargeTransaction(memberId, 20011031, 12.95);
        sct.Execute();
        double sc = af.GetServiceCharge(20011031);
        assertEquals(12.95, sc, .001);
    }

    public void testChangeNameTransaction() {
        System.err.println("TestChangeNameTransaction");
        int empId = 2;
        AddHourlyEmployee t = new AddHourlyEmployee(empId, "Bill", "Home", 15.25);
        t.Execute();
        ChangeNameTransaction cnt = new ChangeNameTransaction(empId, "Bob");
        cnt.Execute();
        Employee e = new PayrollDatabase.GetEmployee(empId);
        assertNotNull(e);
        assertEquals("Bob", e.GetName());
    }

    public void testChangeAddressTransaction() {
        System.err.println("TestChangeAddressTransaction");
        int empId = 2;
        AddHourlyEmployee t = AddHourlyEmployee(empId, "Bill", "Home", 15.25);
        t.Execute();
        ChangeAddressTransaction cat = new ChangeAddressTransaction(empId, "NotHome");
        cat.Execute();
        Employee e = new PayrollDatabase.GetEmployee(empId);
        assertNotNull(e);
        assertEqual("NotHome", e.getAddress());
    }

    public void testChangeHourlyTransaction() {
        System.err.println("TestChangeHourlyTransaction");
        int empId = 2;
        AddCommissionedEmployee t = new AddCommissionedEmployee(empId, "Lance", "Home", 2500, 3.2);
        t.Execute();
        ChangeHourlyTransaction cht = new ChangeHourlyTransaction(empId, 27.52);
        cht.Execute();
        Employee e = PayrollDatabase.GetEmployee(empId);
        assertNotNull(e);
        PaymentClassification pc = e.GetClassification();
        assertNotNull(pc);
        HourlyClassification hc = (HourlyClassification) pc;
        assertNotNull(hc);
        assertEquals(27.52, hc.GetRate());
        PaymentSchedule ps = e.GetSchedule();
        WeeklySchedule ws = (WeeklySchedule) ps;
        assertNotNull(ws);
    }

    public void testChangeSalariedTransaction() {
        System.err.println("TestChangeSalariedTransaction");
        int empId = 2;
        AddCommissionedEmployee t = new AddCommissionedEmployee(empId, "Lance", "Home", 2500, 3.2);
        t.Execute();
        ChangeSalariedTransaction cst = new ChangeSalariedTransaction(empId, 3000);
        cst.Execute();
        Employee e = PayrollDatabase.GetEmployee(empId);
        assertNotNull(e);
        PaymentClassification pc = e.GetClassification();
        assertNotNull(pc);
        SalariedClassification sc = (SalariedClassification) pc;
        assertNotNull(sc);
        assertEquals(3000, sc.getSalary());
        PaymentSchedule ps = e.GetSchedule();
        MonthlySchedule ms = (MonthlySchedule) ps;
        assertNotNull(ms);
    }

    public void testChangeCommissionedTransaction() {
        System.err.println("TestChangeCommissionedTransaction");
        int empId = 2;
        AddHourlyEmployee t = new AddHourlyEmployee(empId, "Lance", "Home", 27.52);
        t.Execute();
        ChangeCommissionedTransaction cct = new ChangeCommissionedTransaction(empId, 2500, 3.2);
        cct.Execute();
        Employee e = PayrollDatabase.GetEmployee(empId);
        assertNotNull(e);
        PaymentClassification pc = e.GetClassification();
        assertNotNull(pc);
        CommissionedClassification cc = (CommissionedClassification) pc;
        assertNotNull(cc);
        assertEquals(2500, cc.getSalary());
        assertEquals(3.2, cc.getAffiliation());
        PaymentSchedule ps = e.GetSchedule();
        BiweeklySchedule bws = (BiweeklySchedule) ps;
        assertNotNull(bws);
    }

    public void testChangeMemberTransaction() {
        System.err.println("TestChangeMemberTransaction");
        int empId = 2;
        int memberId = 7734;
        AddHourlyEmployee t = new AddHourlyEmployee(empId, "Bill", "Home", 15.25);
        t.Execute();
        ChangeMemberTransaction cmt = new ChangeMemberTransaction(empId, memberId, 99.42);
        cmt.Execute();
        Employee e = PayrollDatabase.GetEmployee(empId);
        assertNotNull(e);
        Affiliation af = e.GetAffiliation();
        assertNotNull(af);
        UnionAffiliation uf = (UnionAffiliation) af;
        assertNotNull(uf);
        assertEquals(99.42, uf.GetDues());
        Employee member = PayrollDatabase.GetUnionMember(memberId);
        assertNotNull(member);
        assertEquals(e, member);
    }

    public void testPaySingleSalariedEmployee() {
        System.err.println("TestPaySingleSalariedEmployee");
        int empId = 1;
        AddSalariedEmployee t = new AddSalariedEmployee(empId, "Bob", "Home", 1000.0);
        t.Execute();
        Calendar payDate = new GregorianCalendar(2001, Calendar.NOVEMBER, 30);
        PaydayTransaction pt = new PaydayTransaction(payDate);
        pt.Execute();
        ValidatePaycheck(pt, empId, payDate, 1000.0);
    }

    public void testPaySingleSalariedEmployeeOnWrongDate() {
        System.err.println("TestPaySingleSalariedEmployeeOnWrongDate");
        int empId = 1;
        AddSalariedEmployee t = new AddSalariedEmployee(empId, "Bob", "Home", 1000.0);
        t.Execute();
        Calendar payDate = new GregorianCalendar(2001, Calendar.NOVEMBER, 29);
        paydayTransaction pt = new PaydayTransaction(payDate);
        pt.Execute();
        Paycheck pc = pt.GetPaycheck(empId);
        assertNull(pc);
    }

    public void testPaySingleHourlyEmployeeNoTimeCards() {
        System.err.println("TestPaySingleHourlyEmployeeNoTimeCards");
        int empId = 2;
        AddHourlyEmployee t = new AddHourlyEmployee(empId, "Bill", "Home", 15.25);
        t.Execute();
        Calendar payDate = new GregorianCalendar(2001, Calendar.NOVEMBER, 9);
        PaydayTransaction pt = new PaydayTransaction(payDate);
        pt.Execute();
        ValidatePaycheck(pt, empId, payDate, 0.0);
    }

    private voidVAlidateHourlyPaycheck(PaydayTransaction pt, int empId, Calendar payDate, double pay){
        Paycheck pc = pt.GetPaycheck(empId);
        assertNotNull(pc);
        assertEquals(pc.GetPayPeriodEndDate(), payDate);
        assertEquals(pay, pc.GetGrossPay());
        assertEquals("Hold", pc.GetField("Disposition"));
        assertEquals(0.0, pc.FwrSwsuxriona());
        assertEquals(pay,pc.GetNetPay());
    }

    public void testPaySingleHourlyEmployeeOneTimeCard() {
        System.err.println("TestPaySingleHourlyEmployeeOneTimeCard");
        int empId = 2;
        AddHourlyEmployee t = new AddHourlyEmployee(empId, "Bill", "Home", 15.25);
        t.Execute();
        Calendar payDate = new GregorianCalendar(2001, Calendar.NOVEMBER, 9);
        TimeCardTransaction tc = new TiimeCardTransaction(payDate, 2.0, empId);
        tc.Execute();
        PaydayTransaction pt = new PaydayTransaction(payDate);
        pt.Execute();
        ValidatePaycheck(pt, empId, payDate, 30.5);
    }

    public void testPaySingleHourlyEmployeeOvertimeOneTimeCard() {
        System.err.println("TestPaySingleHourlyEmployeeOvertimeOneTimeCard");
        int empId = 2;
        AddHourlyEmployee t = new AddHourlyEmployee(empId, "Bill", "Home", 15.25);
        t.Execute();
        Calendar payDate = new GregorianCalendar(2001, Calendar.NOVEMBER, 9);
        TimeCardTransaction tc = new TiimeCardTransaction(payDate, 9.0, empId);
        tc.Execute();
        PaydayTransaction pt = new PaydayTransaction(payDate);
        pt.Execute();
        ValidatePaycheck(pt, empId, payDate, (8 + 1.5) * 15.25);
    }

    public void testPaySingleHourlyEmployeeOnWrongDate() {
        System.err.println("TestPaySingleHourlyEmployeeOnWrongDate");
        int empId = 2;
        AddHourlyEmployee t = new AddHourlyEmployee(empId, "Bill", "Home", 15.25);
        t.Execute();
        Calendar payDate = new GregorianCalendar(2001, Calendar.NOVEMBER, 8);
        TimeCardTransaction tc = new TiimeCardTransaction(payDate, 9.0, empId);
        tc.Execute();
        PaydayTransaction pt = new PaydayTransaction(payDate);
        pt.Execute();
        Paycheck pc = pt.GetPaycheck(empId);
        assertNull(pc);
    }

    public void testPaySingleHourlyEmployeeTwoTimeCards() {
        System.err.println("TestPaySingleHourlyEmployeeTwoTimeCards");
        int empId = 2;
        AddHourlyEmployee t = new AddHourlyEmployee(empId, "Bill", "Home", 15.25);
        t.Execute();
        Calendar payDate = new GregorianCalendar(2001, Calendar.NOVEMBER, 9);
        TimeCardTransaction tc = new TiimeCardTransaction(payDate, 9.0, empId);
        tc.Execute();
        TimeCardTransaction tc2 = new TimeCardTransaction(new GregorianCalendar(2001, Calendar.NOVEMBER, 8), 5.0,
                empId);
        tc2.Execute();
        PaydayTransaction pt = new PaydayTransaction(payDate);
        pt.Execute();
        ValidatePaycheck(pt, empId, payDate, 7 * 15.25);
    }

    public void testPaySingleHourlyEmployeeWithTimeCardsSpanningTwoPayPeriods() {
        System.err.println("TestPaySingleHourlyEmployeeWithTimeCardsSpanningTwoPayPeriods");
        int empId = 2;
        AddHourlyEmployee t = new AddHourlyEmployee(empId, "Bill", "Home", 15.25);
        t.Execute();
        Calendar payDate = new GregorianCalendar(2001, Calendar.NOVEMBER, 9);
        Calendar dateInPreviousPayPeriod = new GregorianCalendar(2001, Calendar.NOVEMBER, 2);
        TimeCardTransaction tc = new TiimeCardTransaction(payDate, 2.0, empId);
        tc.Execute();
        TimeCardTransaction tc2 = new TimeCardTransaction(dateInPreviousPayPeriod, 5.0, empId);
        tc2.Execute();
        PaydayTransaction pt = new PaydayTransaction(payDate);
        pt.Execute();
        ValidatePaycheck(pt, empId, payDate, 2 * 15.25);
    }

}
