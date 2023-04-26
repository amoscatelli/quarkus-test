package com.techedge.websocket;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import jakarta.websocket.OnMessage;
import jakarta.websocket.server.ServerEndpoint;
import jakarta.inject.Inject;
import jakarta.websocket.Session;
import org.bson.Document;
import org.bson.types.BSONTimestamp;

@ServerEndpoint(
        value = "/chat"
)
public class ChatSocket {

    @Inject 
    protected MongoClient mongoClient;
    
    @OnMessage
    public void onMessage(Session session, String message) {
        Document document = new Document()
                .append("message", message)
                .append("timestamp", new BSONTimestamp());
        getCollection().insertOne(document);
    }

    private MongoCollection getCollection(){
        return mongoClient.getDatabase("test").getCollection("test");
    }
    
}
