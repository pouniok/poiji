package com.poiji.deserialize;

import com.poiji.deserialize.model.EmployeeExtended;
import com.poiji.exception.PoijiException;
import com.poiji.internal.Poiji;
import com.poiji.internal.PoijiOptions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

/**
 * Created by hakan on 17/01/2017.
 */
@RunWith(Parameterized.class)
public class DerializersExtendedTest {

    private String path;
    private List<EmployeeExtended> expectedEmployess;
    private Class<?> expectedException;

    public DerializersExtendedTest(String path, List<EmployeeExtended> expectedEmployess, Class<?> expectedException) {
        this.path = path;
        this.expectedEmployess = expectedEmployess;
        this.expectedException = expectedException;
    }

    @Parameterized.Parameters(name = "{index}: ({0})={1}")
    public static Iterable<Object[]> queries() throws Exception {
        return Arrays.asList(new Object[][]{
                {"src/test/resources/employees_extended.xls", unmarshalling(), null},
                {"src/test/resources/fruits.xlsx", unmarshalling(), FileNotFoundException.class},
                {"src/test/resources/cloud.xls", unmarshalling(), PoijiException.class},
        });
    }

    @Test
    public void shouldMapExcelToJava() {

        try {
            PoijiOptions options = PoijiOptions.PoijiOptionsBuilder.settings().setSkip(1).setDatePattern("dd/M/yyyy").build();
            List<EmployeeExtended> actualEmployees = Poiji.fromExcel(new File(path), EmployeeExtended.class, options);

            assertThat(actualEmployees, notNullValue());
            assertThat(actualEmployees.size(), not(0));
            assertThat(actualEmployees.size(), is(expectedEmployess.size()));


            EmployeeExtended actualEmployee1 = actualEmployees.get(0);
            EmployeeExtended actualEmployee2 = actualEmployees.get(1);
            EmployeeExtended actualEmployee3 = actualEmployees.get(2);

            EmployeeExtended expectedEmployee1 = expectedEmployess.get(0);
            EmployeeExtended expectedEmployee2 = expectedEmployess.get(1);
            EmployeeExtended expectedEmployee3 = expectedEmployess.get(2);

            assertThat(actualEmployee1.toString(), is(expectedEmployee1.toString()));
            assertThat(actualEmployee2.toString(), is(expectedEmployee2.toString()));
            assertThat(actualEmployee3.toString(), is(expectedEmployee3.toString()));

        } catch (Exception e) {
            if (expectedException == null) {
                fail(e.getMessage());
            } else {
                assertThat(e, instanceOf(expectedException));
            }
        }
    }

    private static List<EmployeeExtended> unmarshalling() throws ParseException {
        List<EmployeeExtended> employees = new ArrayList<>(3);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");

        EmployeeExtended employee1 = new EmployeeExtended();
        employee1.setEmployeeId(123923L);
        employee1.setName("Joe");
        employee1.setSurname("Doe");
        employee1.setSingle(true);
        employee1.setAge(30);
        employee1.setBirthday("4/9/1987");
        employee1.setRate(4.9);
        employee1.setDate(sdf.parse("05/01/2017"));
        employees.add(employee1);

        EmployeeExtended employee2 = new EmployeeExtended();
        employee2.setEmployeeId(123123L);
        employee2.setName("Sophie");
        employee2.setSurname("Derue");
        employee2.setSingle(false);
        employee2.setAge(20);
        employee2.setBirthday("5/3/1997");
        employee2.setRate(5.3);
        employee2.setDate(sdf.parse("05/01/2017"));
        employees.add(employee2);

        EmployeeExtended employee3 = new EmployeeExtended();
        employee3.setEmployeeId(135923L);
        employee3.setName("Paul");
        employee3.setSurname("Raul");
        employee3.setSingle(false);
        employee3.setAge(31);
        employee3.setBirthday("4/9/1986");
        employee3.setRate(6.6);
        employee3.setDate(sdf.parse("05/01/2017"));
        employees.add(employee3);

        return employees;
    }
}
