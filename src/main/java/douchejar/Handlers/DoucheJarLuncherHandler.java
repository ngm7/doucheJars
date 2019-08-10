package douchejar.Handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.LaunchRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.Predicates;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.model.ListTablesRequest;
import com.amazonaws.services.dynamodbv2.model.ListTablesResult;
import com.amazonaws.services.dynamodbv2.model.ResourceNotFoundException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class DoucheJarLuncherHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(Predicates.requestType(LaunchRequest.class));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        String speechText = "Welcome to the Douche Jar, you can say, show me my shelf";

        // List Tables, make sure "DoucheShelves" exists as a table.
        if (!doesTableExist("DoucheShelves")) {
            speechText = "Welcome to Douche Jar, DoucheShelves table not present in DynamoDb";
        }

        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("Douche Jars!", speechText)
                .withReprompt(speechText)
                .build();
    }

    private boolean doesTableExist(String tableName) {

        final AmazonDynamoDB ddb = AmazonDynamoDBClientBuilder.defaultClient();

        ListTablesRequest request;

        boolean more_tables = true;
        String last_name = null;

        List<String> table_names = Collections.EMPTY_LIST;
        while(more_tables) {
            try {
                if (last_name == null) {
                    request = new ListTablesRequest().withLimit(10);
                } else {
                    request = new ListTablesRequest()
                            .withLimit(10)
                            .withExclusiveStartTableName(last_name);
                }

                ListTablesResult table_list = ddb.listTables(request);
                table_names = table_list.getTableNames();

                if (table_names.size() > 0) {
                    for (String cur_name : table_names) {
                        System.out.format("* %s\n", cur_name);
                    }
                } else {
                    System.out.println("No tables found!");
                    System.exit(0);
                }

                last_name = table_list.getLastEvaluatedTableName();
                if (last_name == null) {
                    more_tables = false;
                }

            } catch (ResourceNotFoundException resourceEx) {
                System.out.println(String.format("Caught exception: {}", resourceEx));
            }
        }
        return table_names.contains(tableName);
    }
}