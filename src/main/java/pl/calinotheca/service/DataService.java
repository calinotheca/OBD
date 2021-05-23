package pl.calinotheca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DataService {

    @Autowired RequestExecutorService requestExecutorService;

    public int createSampleData()   {
        int errors = 0;
        // Create tables
        errors += requestExecutorService.execSqlRequest("DECLARE isEmpty int:=0; BEGIN SELECT count(*) into isEmpty FROM user_tables where table_name = 'TEACHER'; IF isEmpty<=0 THEN EXECUTE IMMEDIATE 'CREATE TABLE teacher (id INTEGER NOT NULL, first_name VARCHAR2(20), last_name VARCHAR2(30))'; ELSE EXECUTE IMMEDIATE 'TRUNCATE TABLE teacher'; END IF; END;");
        errors += requestExecutorService.execSqlRequest("DECLARE isEmpty int:=0; BEGIN SELECT count(*) into isEmpty FROM user_tables where table_name = 'STUDENT'; IF isEmpty<=0 THEN EXECUTE IMMEDIATE 'CREATE TABLE student (id INTEGER NOT NULL, first_name VARCHAR2(20), last_name VARCHAR2(30))'; ELSE EXECUTE IMMEDIATE 'TRUNCATE TABLE student'; END IF; END;");
        errors += requestExecutorService.execSqlRequest("DECLARE isEmpty int:=0; BEGIN SELECT count(*) into isEmpty FROM user_tables where table_name = 'SUBJECT'; IF isEmpty<=0 THEN EXECUTE IMMEDIATE 'CREATE TABLE subject (id INTEGER NOT NULL, name VARCHAR2(20))'; ELSE EXECUTE IMMEDIATE 'TRUNCATE TABLE subject'; END IF; END;");
        errors += requestExecutorService.execSqlRequest("DECLARE isEmpty int:=0; BEGIN SELECT count(*) into isEmpty FROM user_tables where table_name = 'RATE'; IF isEmpty<=0 THEN EXECUTE IMMEDIATE 'CREATE TABLE rate (id INTEGER NOT NULL, rate FLOAT(1), description VARCHAR2(20))'; ELSE EXECUTE IMMEDIATE 'TRUNCATE TABLE rate'; END IF; END;");
        errors += requestExecutorService.execSqlRequest("DECLARE isEmpty int:=0; BEGIN SELECT count(*) into isEmpty FROM user_tables where table_name = 'RATING'; IF isEmpty<=0 THEN EXECUTE IMMEDIATE 'CREATE TABLE rating (id INTEGER NOT NULL, type VARCHAR2(1), teacher_id INTEGER, student_id INTEGER, subject_id INTEGER, rate_id INTEGER)'; ELSE EXECUTE IMMEDIATE 'TRUNCATE TABLE rating'; END IF; END;");

        // insert values
        errors += requestExecutorService.execSqlRequest("INSERT INTO teacher (id,first_name,last_name) values ('1','Ignacy','Mościcki')");
        errors += requestExecutorService.execSqlRequest("INSERT INTO teacher (id,first_name,last_name) values ('2','Mikołaj','Kopernik')");
        errors += requestExecutorService.execSqlRequest("INSERT INTO teacher (id,first_name,last_name) values ('3','Jan','Śniadecki')");
        errors += requestExecutorService.execSqlRequest("INSERT INTO subject (id,name) values ('1','Chemia organiczna')");
        errors += requestExecutorService.execSqlRequest("INSERT INTO subject (id,name) values ('2','Astronomia')");
        errors += requestExecutorService.execSqlRequest("INSERT INTO subject (id,name) values ('3','Matematyka')");
        errors += requestExecutorService.execSqlRequest("INSERT INTO student (id,first_name,last_name) values ('1','Jan','Bałwan')");
        errors += requestExecutorService.execSqlRequest("INSERT INTO student (id,first_name,last_name) values ('2','Mirosław','Młotek')");
        errors += requestExecutorService.execSqlRequest("INSERT INTO student (id,first_name,last_name) values ('3','Ksawery','Tuman')");
        errors += requestExecutorService.execSqlRequest("INSERT INTO rate (id,rate,description) values ('1','2','Niedostateczny')");
        errors += requestExecutorService.execSqlRequest("INSERT INTO rate (id,rate,description) values ('2','3','Dostateczny')");
        errors += requestExecutorService.execSqlRequest("INSERT INTO rate (id,rate,description) values ('3','4','Dobry')");
        errors += requestExecutorService.execSqlRequest("INSERT INTO rate (id,rate,description) values ('4','5','Bardzo dobry')");

        return errors;
    }

    public void addRating(List<Long> parameters) {
        requestExecutorService.execSqlRequest("INSERT INTO rating (id, type, subject_id, teacher_id, student_id, rate_id) VALUES (" + parameters.get(0) + ", '" + (parameters.get(5)==1L?'C':'S') + "', " + parameters.get(1) + ", " + parameters.get(2) + ", " + parameters.get(3) + ", " + Float.parseFloat(parameters.get(4).toString()) + ")");
    }

    public void printTable(String table)    {
        requestExecutorService.execSqlRequest("SELECT * FROM " + table, "R");
    }

    public void printLastRowTable(String table)    {
        requestExecutorService.execSqlRequest("SELECT 'Ostatnio użyte ID oceniania: ' || NVL(MAX(id), 0) FROM " + table, "R");
    }

    public Long validateField(String fieldName, Long value)   {

        Long result;
        
        // check next id
        if (fieldName.equals("id"))
            result = value-requestExecutorService.execSqlRequest("SELECT MAX(id) FROM RATING", "C");
        else if (fieldName.equals(fieldName))
            result = requestExecutorService.execSqlRequest("SELECT COUNT(*) FROM " + fieldName + " WHERE id=" + value, "C");
        else
            result = -1L;

        return result;
    }
}