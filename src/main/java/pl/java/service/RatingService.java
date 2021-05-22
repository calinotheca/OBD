package pl.java.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.java.model.Rating;
import pl.java.model.Subject;
import pl.java.model.Teacher;
import pl.java.repository.*;

import java.util.List;

@Service
public class RatingService {

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private RateRepository rateRepository;

    public void addRating(List<Long> parameters) {
        Rating rating = new Rating(
                parameters.get(4)==1L?'C':'S',
                subjectRepository.findById(parameters.get(0)).get(),
                teacherRepository.findById(parameters.get(1)).get(),
                studentRepository.findById(parameters.get(2)).get(),
                rateRepository.findByRate(Float.parseFloat(parameters.get(3).toString()))
        );

        ratingRepository.save(rating);

    }


}
