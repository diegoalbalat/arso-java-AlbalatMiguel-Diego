package repositorio;

//import com.mongodb.MongoClientSettings;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;

import java.net.UnknownHostException;

public class MongoContext {
    private static MongoContext ctx = new MongoContext();
    private static MongoClient client;
    private static MongoDatabase database;
    private static CodecRegistry pojoCodecRegistry;
    private MongoContext(){
        try{
            init();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    private void init() throws UnknownHostException {
        MongoClientURI uri = new MongoClientURI("mongodb://arsoalbalat:arsoalbalat@cluster0-shard-00-00-tdlfv.azure.mongodb.net:27017,cluster0-shard-00-01-tdlfv.azure.mongodb.net:27017,cluster0-shard-00-02-tdlfv.azure.mongodb.net:27017/tareas?ssl=true&replicaSet=Cluster0-shard-0&authSource=admin&retryWrites=true&w=majority");
        client = new MongoClient(uri);
        database = client.getDatabase("user");
    }
    public static MongoContext get(){
        return ctx;
    }

    public static MongoClient getClient(){
        return client;
    }

    public static MongoDatabase getDatabase() {
        return database;
    }

    public static MongoCollection<Document> getCollection() {
        return database.getCollection("tareas");
    }

    public static void close(){
        client.close();
    }
}
