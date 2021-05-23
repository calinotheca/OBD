package pl.calinotheca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import pl.calinotheca.service.DataService;


@SpringBootApplication
public class ObdApplication implements CommandLineRunner {

    @Autowired
    private DataService dataService;

    public static void main(String[] args) {
        SpringApplication.run(ObdApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String selection = "";
        selection = displayMainMenu(scanner, selection);

        if (selection.equals("1")) {
            System.out.println("Poczekaj chwilę...");
            System.out.println(dataService.createSampleData()>0?"Błąd w trakcie ładowania danych!":"Dane załadowane poprawnie!");
            run();
        } else if (selection.equals("2")) {
            addRating(scanner, "id", new ArrayList<>());
            run();
        } else {
            System.out.println("Nieprawidłowy wybór!");
            run();
        }
    }

    private String displayMainMenu(Scanner scanner, String selection)  {
        System.out.println("---------------------------------------");
        System.out.println("---   SYSTEM OCENIANIA STUDENTÓW    ---");
        System.out.println("---------------------------------------");
        System.out.println("1. Załaduj przykładowe dane");
        System.out.println("2. Wystaw ocenę\n");
        System.out.print("Wybierz: ");
        return scanner.next();
    }

    private void addRating(Scanner scanner, String switcher, List<Long> parameters) throws Exception {
        Long value = 0L;
        switcher = switcher.isEmpty()?"id":switcher;

        // insert an ID
        if (switcher.equals("id")) {
            dataService.printLastRowTable("rating");
            System.out.print("Wprowadź ID oceniania: ");
            value = scanner.nextLong();

            if (dataService.validateField("id", value) == 1) {
                parameters.add(value);
                addRating(scanner, "subject", parameters);
            } else {
                System.out.println("## ERROR ## Wprowadzony ID narusza integralność bazy danych.");
                addRating(scanner, "id", parameters);
            }
        }

        // insert a subject
        if (switcher.equals("subject")) {
            dataService.printTable("subject");
            System.out.print("Wprowadź ID przedmiotu: ");
            value = scanner.nextLong();

            if (dataService.validateField("subject", value) == 1) {
                parameters.add(value);
                addRating(scanner, "teacher", parameters);
            } else {
                System.out.println("## ERROR ## Wprowadzony ID przedmiotu narusza integralność bazy danych.");
                addRating(scanner, "subject", parameters);
            }
        }

        // choose a teacher
        if (switcher.equals("teacher")) {
            dataService.printTable("teacher");
            System.out.print("Wprowadź ID nauczyciela: ");
            value = scanner.nextLong();

            if (dataService.validateField("teacher", value) == 1) {
                parameters.add(value);
                addRating(scanner, "student", parameters);
            } else {
                System.out.println("## ERROR ## Wprowadzony ID nauczyciela narusza integralność bazy danych.");
                addRating(scanner, "teacher", parameters);
            }
        }

        // choose a student
        if (switcher.equals("student")) {
            dataService.printTable("student");
            System.out.print("Wprowadź ID studenta: ");
            value = scanner.nextLong();
            if (dataService.validateField("student", value) == 1) {
                parameters.add(value);
                addRating(scanner, "rate", parameters);
            } else {
                System.out.println("## ERROR ## Wprowadzony ID studenta narusza integralność bazy danych.");
                addRating(scanner, "student", parameters);
            }
        }
        
        // chose a rate
        if (switcher.equals("rate")) {
            dataService.printTable("rate");
            System.out.print("Wybierz ocenę: ");
            value = scanner.nextLong();

            if (dataService.validateField("rate", value) == 1) {
                parameters.add(value);
                addRating(scanner, "type", parameters);
            } else {
                System.out.println("## ERROR ## Wprowadzony ID oceny narusza integralność bazy danych.");
                addRating(scanner, "rate", parameters);
            }
        }
        
        // Chose a type
        if (switcher.equals("type")) {
            System.out.println("C | Ocena cząstkowa");
            System.out.println("S | Ocena semestralna");
            System.out.print("Wybierz typ oceny: ");
            String typOceny = scanner.next();

            if (!typOceny.equals("C") || typOceny.equals("S"))  {
                System.out.println("## ERROR ## Możesz wybrać wartości S lub C.");
                addRating(scanner, "type", parameters);
            } else {
                parameters.add(typOceny.equals("C") ? 1L : typOceny.equals("S") ? 2L : null);
            }
        }
        
        // add rating
        dataService.addRating(parameters);
        System.out.println("Ocena została dodana!");
        run();
    }
}