package de.otto.flummi.request;

import org.elasticsearch.client.Response;
 import org.elasticsearch.client.RestClient;
import org.slf4j.Logger;

import java.util.concurrent.ExecutionException;

import static de.otto.flummi.RequestBuilderUtil.toHttpServerErrorException;
import static org.slf4j.LoggerFactory.getLogger;

public class ForceMergeRequestBuilder {
    private RestClient httpClient;
    private final String indexName;

    public static final Logger LOG = getLogger(ForceMergeRequestBuilder.class);

    public ForceMergeRequestBuilder(RestClient httpClient, String indexName) {
        this.httpClient = httpClient;
        this.indexName = indexName;
    }

    public void execute() {
        try {
            Response response = httpClient.preparePost("/" + indexName + "/_forcemerge").execute().get();
            if (response.getStatusCode() >= 300) {
                throw toHttpServerErrorException(response);
            }
            return;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
