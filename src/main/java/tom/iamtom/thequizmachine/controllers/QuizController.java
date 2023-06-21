package tom.iamtom.thequizmachine.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tom.iamtom.thequizmachine.exceptions.QuizNotFoundException;
import tom.iamtom.thequizmachine.helpers.AnswerChecker;
import tom.iamtom.thequizmachine.models.NewQuizDTO;
import tom.iamtom.thequizmachine.models.Quiz;
import tom.iamtom.thequizmachine.repositories.QuizRepository;
import tom.iamtom.thequizmachine.services.QuizService;

@RestController
public class QuizController {
    
    @Autowired
    private QuizRepository quizRepository;
    @Autowired
    private QuizModelAssembler assembler;
    @Autowired
    private QuizService quizService;
    @Autowired
    private AnswerChecker checker;
    
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
    
    @PostMapping("/quizzes")
    public ResponseEntity<?> newQuiz(@RequestBody NewQuizDTO dto) {
        Quiz newQuiz = quizService.mapNewQuizDTOtoQuiz(dto);
        quizRepository.save(newQuiz);
        EntityModel<Quiz> entityModel = assembler.toModel(newQuiz);

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }
    
    @PostMapping("/quizzes/check")
    public HashMap<Long, Boolean> assessQuiz(@RequestBody HashMap<Long, String> answers) {
        //answers hashmap should be: key - question id, value - user answer
        HashMap<Long, Boolean> results = checker.checkAnswers(answers);
        
        return results;
    }
        
}
