package de.otto.flummi;

 import org.elasticsearch.client.RestClient;
import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;

public class AdminClient {

    public static final Logger LOG = getLogger(AdminClient.class);
    private RestClient httpClient;

    public AdminClient(RestClient httpClient) {
        this.httpClient = httpClient;
    }

    public IndicesAdminClient indices() {
        return new IndicesAdminClient(httpClient);
    }

    public ClusterAdminClient cluster() {
        return new ClusterAdminClient(httpClient);
    }
}
