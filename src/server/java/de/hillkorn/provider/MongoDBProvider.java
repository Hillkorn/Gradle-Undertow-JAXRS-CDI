package de.hillkorn.provider;

import com.mongodb.ServerAddress;
import com.mongodb.async.client.MongoClient;
import com.mongodb.async.client.MongoClientSettings;
import com.mongodb.async.client.MongoClients;
import com.mongodb.connection.ClusterSettings;
import de.hillkorn.document.NewsDocument;
import java.util.ArrayList;
import javax.enterprise.inject.Produces;
import javax.inject.Singleton;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;

@Singleton
public class MongoDBProvider {

    private MongoClient client;

    @Produces
    public MongoClient createMongoClient() {
        if (client != null) {
            return client;
        }
        NewsDocument newsDocument = new NewsDocument();
        CodecRegistry codecRegistry = CodecRegistries.fromRegistries(
            MongoClients.getDefaultCodecRegistry(),
            CodecRegistries.fromCodecs(newsDocument));
        ServerAddress serverAddress = new ServerAddress("localhost:27017");
        ArrayList<ServerAddress> hosts = new ArrayList<ServerAddress>();
        hosts.add(serverAddress);

        MongoClientSettings settings = MongoClientSettings.builder()
            .codecRegistry(codecRegistry)
            .clusterSettings(ClusterSettings.builder()
                .hosts(hosts)
                .build())
            .build();

        client = MongoClients.create(settings);
        return client;
    }
}
