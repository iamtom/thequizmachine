package tom.iamtom.thequizmachine.controllers;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import tom.iamtom.thequizmachine.exceptions.QuizNotFoundException;
import tom.iamtom.thequizmachine.models.Quiz;
import tom.iamtom.thequizmachine.repositories.QuizRepository;

@RestController
public class QuizController {
    
    @Autowired
    private QuizRepository quizRepository;
    @Autowired
    private QuizModelAssembler assembler;
    
    @GetMapping("/quizzes")
    public CollectionModel<EntityModel<Quiz>> allQuizzes() {
        List<EntityModel<Quiz>> quizzes = quizRepository.findAll().stream()
                .map(assembler::toModel) //
                .collect(Collectors.toList());

        return CollectionModel.of(quizzes, linkTo(methodOn(QuizController.class).allQuizzes()).withSelfRel());
    }
    
    @GetMapping("/quizzes/{id}")
    public EntityModel<Quiz> oneQuiz(@PathVariable Long id) {
        Quiz quiz = quizRepository.findById(id).orElseThrow(() -> new QuizNotFoundException(id));

        return assembler.toModel(quiz);
    }
    
    //TODO: Post mapping should receive an object containing a set of question ids that is then used to construct a proper quiz object
    
}
