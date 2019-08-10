package douchejar.Handlers.DoucheJarIntents;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.impl.IntentRequestHandler;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.Predicates;

import java.util.Optional;

public class DeleteDoucheJarRequestHandler implements IntentRequestHandler {

    @Override
    public boolean canHandle(HandlerInput handlerInput, IntentRequest intentRequest) {
        // DeleteDoucheJarIntent matches -:
        // 1. "delete douche jar for {Name}",
        // 2. "delete {Name} douche jar"
        // 3. "delete {Name} douche jar from my shelf"
        return handlerInput.matches(Predicates.intentName("DeleteDoucheJarIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput, IntentRequest intentRequest) {
        String speechText = "Deleting a douche jar for";
        return handlerInput.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("DoucheJars", speechText)
                .build();
    }
}
