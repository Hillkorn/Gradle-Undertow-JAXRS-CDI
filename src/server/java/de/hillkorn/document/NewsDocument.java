package de.hillkorn.document;

import org.bson.BsonReader;
import org.bson.BsonType;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;

public class NewsDocument implements Codec<NewsDocument> {

    private String title;

    public NewsDocument(String title) {
        this.title = title;
    }

    public NewsDocument() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public void encode(BsonWriter writer, NewsDocument value, EncoderContext encoderContext) {
        writer.writeStartDocument();
        writer.writeName("title");
        writer.writeString(value.title);
        writer.writeEndDocument();
    }

    @Override
    public Class<NewsDocument> getEncoderClass() {
        return NewsDocument.class;
    }

    @Override
    public NewsDocument decode(BsonReader reader, DecoderContext decoderContext) {
        reader.readStartDocument();
        NewsDocument newsDocument = new NewsDocument();
        while (reader.readBsonType() != BsonType.END_OF_DOCUMENT) {
            String fieldName = reader.readName();
            if (fieldName.equals("title")) {
                newsDocument.title = reader.readString();
            } else {
                reader.skipValue();
            }
        }
        reader.readEndDocument();
        return newsDocument;
    }
}
