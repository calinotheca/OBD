package pl.java;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import pl.java.repository.RateRepository;
import pl.java.repository.StudentRepository;
import pl.java.repository.SubjectRepository;
import pl.java.repository.TeacherRepository;
import pl.java.service.RatingService;
import pl.java.service.SampleDataService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class ObdApplication implements CommandLineRunner {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private SampleDataService sampleDataService;

    @Autowired SubjectRepository subjectRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    RateRepository rateRepository;

    @Autowired
    RatingService ratingService;

    public static void main(String[] args) {
        SpringApplication.run(ObdApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {

        Scanner scanner = new Scanner(System.in);
        int selection = 0;
        selection = displayMainMenu(scanner, selection);


        if (selection == 1) {
            sampleDataService.createSampleData();
            System.out.println("Dane załadowane!");
            run();
        }

        if (selection == 2) {
            addRating(scanner);
            run();
        }

    }

    private int displayMainMenu(Scanner scanner, int selection)  {
        System.out.println("---------------------------------------");
        System.out.println("---   SYSTEM OCENIANIA STUDENTÓW    ---");
        System.out.println("---------------------------------------");
        System.out.println("1. Załaduj przykładowe dane");
        System.out.println("2. Wystaw ocenę\n");
        System.out.print("Wybierz: ");
        return scanner.nextInt();
    }

    private void addRating(Scanner scanner) {
        List<Long> parameters = new ArrayList<>();

        // choose a subject
        subjectRepository.findAll().forEach(x -> System.out.println(x.getId() + " | " + x.getName()));
        System.out.print("Wprowadź id przedmiotu: ");
        parameters.add(scanner.nextLong());

        // choose a teacher
        teacherRepository.findAll().forEach(x -> System.out.println(x.getId() + " | " + x.getFirstName() + " " + x.getLastName()));
        System.out.print("Wprowadź id nauczyciela: ");
        parameters.add(scanner.nextLong());

        // choose a student
        studentRepository.findAll().forEach(x -> System.out.println(x.getId() + " | " + x.getFirstName() + " " + x.getLastName()));
        System.out.print("Wprowadź id studenta: ");
        parameters.add(scanner.nextLong());

        // chose a rate
        rateRepository.findAll().forEach(x -> System.out.println((int) x.getRate() + " | " + x.getDescription()));
        System.out.print("Wybierz ocenę: ");
        parameters.add(scanner.nextLong());

        // Chose a type
        System.out.println("C | Ocena cząstkowa");
        System.out.println("S | Ocena semestralna");
        System.out.print("Wybierz typ oceny: ");
        String typOceny = scanner.next();
        parameters.add(typOceny.equals("C")?1L:typOceny.equals("S")?2L:null);

        // add rating
        ratingService.addRating(parameters);
        System.out.println("Ocena została dodana!");


    }

}
