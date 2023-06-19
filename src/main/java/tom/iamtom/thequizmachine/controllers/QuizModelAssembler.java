package tom.iamtom.thequizmachine.controllers;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Component;
import tom.iamtom.thequizmachine.models.Quiz;

@Component
public class QuizModelAssembler implements RepresentationModelAssembler<Quiz, EntityModel<Quiz>> {
    
    @Override
    public EntityModel<Quiz> toModel(Quiz quiz) {
        
        return EntityModel.of(quiz, //
                linkTo(methodOn(QuizController.class).oneQuiz(quiz.getId())).withSelfRel(),
                linkTo(methodOn(QuizController.class).allQuizzes()).withRel("quizzes"));
    }
}
