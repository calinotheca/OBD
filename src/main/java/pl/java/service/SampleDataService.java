package pl.java.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.java.model.Rate;
import pl.java.model.Student;
import pl.java.model.Subject;
import pl.java.model.Teacher;
import pl.java.repository.*;

import javax.transaction.Transactional;

@Service
@Transactional
public class SampleDataService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired TeacherRepository teacherRepository;

    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    RateRepository rateRepository;

    @Autowired
    RatingRepository ratingRepository;

    public void createSampleData()  {

        // clear if exists
        ratingRepository.deleteAll();
        studentRepository.deleteAll();
        teacherRepository.deleteAll();
        subjectRepository.deleteAll();
        rateRepository.deleteAll();

        // Add some students
        Student student1 = new Student("Jan", "Bałwan");
        studentRepository.save(student1);
        Student student2 = new Student("Mirosław", "Młotek");
        studentRepository.save(student2);
        Student student3 = new Student("Ksawery", "Tuman");

        // Add some teachers
        Teacher teacher1 = new Teacher("Ignacy", "Mościcki");
        teacherRepository.save(teacher1);
        Teacher teacher2 = new Teacher("Mikołaj", "Kopernik");
        teacherRepository.save(teacher2);
        Teacher teacher3 = new Teacher("Jan", "Śniadecki");
        teacherRepository.save(teacher3);

        // add some subjects
        Subject subject1 = new Subject("Chemia organiczna");
        subjectRepository.save(subject1);
        Subject subject2 = new Subject("Astronomia");
        subjectRepository.save(subject2);
        Subject subject3 = new Subject("Matematyka");
        subjectRepository.save(subject3);

        // add some rates
        Rate rate1 = new Rate(2, "Niedostateczny");
        rateRepository.save(rate1);
        Rate rate2 = new Rate(3, "Dostateczny");
        rateRepository.save(rate2);
        Rate rate3 = new Rate(4, "Dobry");
        rateRepository.save(rate3);
        Rate rate4 = new Rate(5, "Bardzo dobry");
        rateRepository.save(rate4);
    }


}
