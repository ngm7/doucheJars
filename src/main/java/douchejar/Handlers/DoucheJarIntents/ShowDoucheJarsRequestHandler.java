package douchejar.Handlers.DoucheJarIntents;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.impl.IntentRequestHandler;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.Predicates;

import java.util.Optional;

public class ShowDoucheJarsRequestHandler implements IntentRequestHandler {

    @Override
    public boolean canHandle(HandlerInput handlerInput, IntentRequest intentRequest) {
        // ShowDoucheJarIntent matches -:
        // 1. "Show my Douche Jars",
        // 2. "Show my Douche Shelf.java" etc.
        return handlerInput.matches(Predicates.intentName("ShowDoucheJarIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput, IntentRequest intentRequest) {
        String speechText = "Here are your Douche Jars";
        return handlerInput.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("DoucheJars", speechText)
                .build();
    }
}
