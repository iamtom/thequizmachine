package tom.iamtom.thequizmachine.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tom.iamtom.thequizmachine.exceptions.QuestionNotFoundException;
import tom.iamtom.thequizmachine.models.Question;
import tom.iamtom.thequizmachine.repositories.QuestionRepository;

@RestController
public class QuestionController {

    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private QuestionModelAssembler assembler;

    @GetMapping("/questions")
    public CollectionModel<EntityModel<Question>> allQuestions() {
        List<EntityModel<Question>> questions = questionRepository.findAll().stream()
                .map(assembler::toModel) //
                .collect(Collectors.toList());

        return CollectionModel.of(questions, linkTo(methodOn(QuestionController.class).allQuestions()).withSelfRel());
    }

    @GetMapping("/questions/{id}")
    public EntityModel<Question> oneQuestion(@PathVariable Long id) {
        Question question = questionRepository.findById(id).orElseThrow(() -> new QuestionNotFoundException(id));

        return assembler.toModel(question);
    }

    @PostMapping("/questions")
    public ResponseEntity<?> newQuestion(@RequestBody Question newQuestion) {
        questionRepository.save(newQuestion);
        EntityModel<Question> entityModel = assembler.toModel(newQuestion);

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @PutMapping("/questions/{id}")
    public ResponseEntity<?> replaceQuestion(@RequestBody Question newQuestion, @PathVariable Long id) {
        Optional<Question> questionOptional = questionRepository.findById(id);
        Question question = new Question();

        if (questionOptional.isPresent()) {
            question.setId(id);
            question.setCategory(newQuestion.getCategory());
            question.setCorrectAnswer(newQuestion.getCorrectAnswer());
            question.setText(newQuestion.getText());
            question.setWrongAnswers(newQuestion.getWrongAnswers());
        } else {
            question = newQuestion;
            question.setId(id);
        }

        questionRepository.save(question);

        EntityModel<Question> entityModel = assembler.toModel(question);

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }
    
    //TODO: PATCH?
    
    @DeleteMapping("/questions/{id}")
    public ResponseEntity<?> deleteQuestion(@PathVariable Long id) {
        questionRepository.deleteById(id);
        
        //This returns an HTTP 204 No Content response.
        return ResponseEntity.noContent().build();
    }
}
