package pl.java.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.java.model.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

}
