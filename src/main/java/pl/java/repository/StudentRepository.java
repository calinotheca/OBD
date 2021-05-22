package pl.java.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.java.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Student findByFirstNameAndLastName(String firstName, String lastName);
}
