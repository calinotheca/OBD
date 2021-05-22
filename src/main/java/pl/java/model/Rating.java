package pl.java.model;

import javax.persistence.*;

@Entity
public class Rating {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private char type;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @ManyToOne
    @JoinColumn(name = "rate_id")
    private Rate rate;

    public Rating() {
    }

    public Rating(char type, Subject subject, Teacher teacher, Student student, Rate rate) {
        this.id = id;
        this.type = type;
        this.teacher = teacher;
        this.student = student;
        this.subject = subject;
        this.rate = rate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public char getType() {
        return type;
    }

    public void setType(char type) {
        this.type = type;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Rate getRate() {
        return rate;
    }

    public void setRate(Rate rate) {
        this.rate = rate;
    }
}
