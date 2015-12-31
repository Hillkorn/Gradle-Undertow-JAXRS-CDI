package de.hillkorn.rest;

import com.mongodb.async.client.MongoClient;
import com.mongodb.async.client.MongoCollection;
import com.mongodb.async.client.MongoDatabase;
import de.hillkorn.api.NewsResource;
import de.hillkorn.document.NewsDocument;
import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Response;

@Path("/news")
public class NewsResourceImpl implements NewsResource {

    @Inject
    private MongoClient mongoClient;

    @Override
    public void getNews(@Suspended final AsyncResponse asyncResponse) throws InterruptedException {
        MongoDatabase database = mongoClient.getDatabase("myDB");
        database.getCollection("news", NewsDocument.class).find(NewsDocument.class)
            .first((NewsDocument result, Throwable t) -> {
                if (t != null) {
                    asyncResponse.resume(t);
                } else {
                    asyncResponse.resume(Response.status(Response.Status.OK).entity(result).build());
                }
            });
    }

    @Override
    public void createNews(@Suspended AsyncResponse asyncResponse) {
        MongoDatabase database = mongoClient.getDatabase("myDB");
        MongoCollection<NewsDocument> collection = database.getCollection("news", NewsDocument.class);
        NewsDocument newsDocument = new NewsDocument("Some Title");
        collection.insertOne(newsDocument, (Void result, Throwable t) -> {
            if (t != null) {
                asyncResponse.resume(t);
            } else {
                asyncResponse.resume(Response.status(Response.Status.CREATED).build());
            }
        });

    }
}
