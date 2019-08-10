package douchejar.Handlers.DoucheJarIntents;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.impl.IntentRequestHandler;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.Predicates;
import com.amazon.ask.request.RequestHelper;

import java.util.Optional;

public class CreateDoucheJarRequestHandler implements IntentRequestHandler {

    @Override
    public boolean canHandle(HandlerInput handlerInput, IntentRequest intentRequest) {
        // CreateDoucheJarIntent matches -:
        // 1. "Create a new douche jar for {Name}",
        // 2. "Create a douche jar for {Name}"
        // 3. "Create {Name} douche jar"
        return handlerInput.matches(Predicates.intentName("CreateDoucheJarIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput, IntentRequest intentRequest) {
        RequestHelper requestHelper = RequestHelper.forHandlerInput(handlerInput);

        // Use a helper method to get the slot value wrapped in an Optional.
        Optional<String> firstNameValue = requestHelper.getSlotValue("firstName");

        String speechText = firstNameValue.map(firstName -> "Creating a new douche jar for, " + firstName + "!")
                .orElse("Hello World! I'm sorry, I don't yet know your name.");

        if(firstNameValue.isPresent()) {

        }

        return handlerInput.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("DoucheJars", speechText)
                .build();
    }
}
