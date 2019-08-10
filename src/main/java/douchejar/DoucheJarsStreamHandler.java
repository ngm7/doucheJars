package douchejar;

import douchejar.Handlers.DoucheJarIntents.CreateDoucheJarRequestHandler;
import douchejar.Handlers.DoucheJarIntents.DeleteDoucheJarRequestHandler;
import douchejar.Handlers.DoucheJarLuncherHandler;
import douchejar.Handlers.DoucheJarIntents.ShowDoucheJarsRequestHandler;
import douchejar.Handlers.BuiltInIntents.CancelAndStopIntentHandler;
import douchejar.Handlers.BuiltInIntents.HelpIntentHandler;
import douchejar.Handlers.BuiltInIntents.SessionEndedRequestHandler;
import com.amazon.ask.Skill;
import com.amazon.ask.Skills;
import com.amazon.ask.SkillStreamHandler;


public class DoucheJarsStreamHandler extends SkillStreamHandler {

    private static Skill getSkill() {

        return Skills.standard()
                .addRequestHandlers(
                        new CancelAndStopIntentHandler(),
                        new ShowDoucheJarsRequestHandler(),
                        new HelpIntentHandler(),
                        new DoucheJarLuncherHandler(),
                        new SessionEndedRequestHandler(),
                        new CreateDoucheJarRequestHandler(),
                        new DeleteDoucheJarRequestHandler())
                .build();
    }

    public DoucheJarsStreamHandler() {
        super(getSkill());
    }
}