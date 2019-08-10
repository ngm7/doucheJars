package douchejar.Handlers.BuiltInIntents;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import static com.amazon.ask.request.Predicates.intentName;

import java.util.Optional;

public class HelpIntentHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("AMAZON.HelpIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        String speechText = "You can say show me my douche jars";
        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("Douche Jars!", speechText)
                .withReprompt(speechText)
                .build();
    }
}