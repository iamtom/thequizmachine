package tom.iamtom.thequizmachine.controllers;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Component;
import tom.iamtom.thequizmachine.models.Question;

@Component
public class QuestionModelAssembler implements RepresentationModelAssembler<Question, EntityModel<Question>> {
    
    @Override
    public EntityModel<Question> toModel(Question question) {
        
        return EntityModel.of(question, //
                linkTo(methodOn(QuestionController.class).oneQuestion(question.getId())).withSelfRel(),
                linkTo(methodOn(QuestionController.class).allQuestions()).withRel("questions"));
    }
}
