package com.revature.database;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.revature.database.dao.EmployeeDAO;
import com.revature.database.dao.EmployeeDAOImpl_pgsql;
import com.revature.database.dao.SupportTicketRepository;
import com.revature.database.models.Employee;
import com.revature.database.models.SupportTicket;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.types.ObjectId;

import java.sql.SQLException;
import java.util.List;

import static com.revature.database.util.ConnectionUtil.*;
import static com.revature.database.util.MongoConnectionManager.getMongoClient;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class Main {
    public static void main(String[] args) {
//        postgresConnectionSanityTest();
//        jdbcDaoImpl();
//        getMongoClient();
        mongoRepositoryExample();
    }

    // Make sure to test your connection before you get started!
    public static void postgresConnectionSanityTest() {
        // This acts as a sanity test to make sure our connection is working
        try {
            System.out.println("Properties");
            getConnection_prop();
            System.out.println("Env Var");
            getConnection_env();
            System.out.println("Connection Strings - unsafe method");
            getConnection();

            System.out.println("Connection was successful");
        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Connection was NOT sucessful");
        }
    }

    public static void jdbcDaoImpl() {
        EmployeeDAO eDao = new EmployeeDAOImpl_pgsql();

//        System.out.println("SELECT BY NAME");
//        System.out.println( eDao.selectEmployeeByName("Joseph") );

        System.out.println("INSERTION");
        Employee insert = new Employee(null, "From Java", "Developer", 150000.00);
        System.out.println( eDao.insertIntoEmployees(insert) );

        System.out.println("SELECT ALL");
        for (Employee e : eDao.selectAllEmployees() ) {
            System.out.println(e);
        }

        // Remember that my Update/Delete methods search by Name (not a unique value or a PK)...
        // It is better practice to use the PK, to ensure we only manipulate a single value
        // so searching for a record first to obtain the correct PK is best practice!
//        System.out.println("UPDATION");
//        // Typically when we update a record - we query the database first for the ID of the record (and to check it exists)
//        System.out.println("SELECT BY NAME");
//        Employee old = eDao.selectEmployeeByName("From Java");
//
//        Employee update = new Employee(null, "New Name", "New Title", 125000.00);
//        System.out.println( eDao.updateEmployeeByName(old.getEmp_name(), update) );
//
//        System.out.println("DELETION");
//        System.out.println( eDao.deleteEmployeeByName("New Name") );
    }


    public static  void mongoRepositoryExample() {
        MongoClient client = getMongoClient();

        /*
         * By Default 'getCollection' returns MongoCollection<Document>
         * It is acceptable to use Documents and build each document
         * in your Repository Code (see comment in SupportTicketRepository.java)
         *
         * However, to leverage our model class more directly, we must
         * perform 2 steps:
         *  1. Inform the 'CodecRegistry' that we want to build codecs
         *      at runtime. A 'Codec' acts as a look-up table for Mongo
         *      to understand how to build Documents from Java Types
         *      we can inform the registry to build our potential Pojos
         *      on the fly with the code below
         *  2. Pass 'SupportTicket.class' to the 'getCollection' method call
         *      which tells the registry which Pojo to use
         */

        // Create a provider (fromProviders(PojoCodecProvider.builder().automatic(true).build())
        // this provider will generate our POJO codec at runtime.
        CodecRegistry pojoCodecRegistry = fromRegistries(
                MongoClientSettings.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build())
        );

        // Create our database using the registry provider above
        MongoDatabase database = client.getDatabase("trng-2499")
                .withCodecRegistry(pojoCodecRegistry);

        // Reference our collection - pass the Model class (our Pojo) so that
        // our CodecRegistry can tell Mongo how to create this Document during runtime
        // Note: in our example, we only have a single collection (table)
        // In your own projects, you will need multiple collections - you
        // can create multiple collections from a single MongoClient
        MongoCollection<SupportTicket> ticketsMongoCollection =
                database.getCollection("supportTickets", SupportTicket.class);

        // Create the Repository Instance and pass in this Mongo Collection
        SupportTicketRepository repo = new SupportTicketRepository(ticketsMongoCollection);

        System.out.println("CREATE TICKETS");
        SupportTicket ticket = new SupportTicket(
                "Password reset email never arrives",
                "Users who request a reset link report no email after 15 minutes. Spam folders are already checked",
                List.of("bug", "complaint", "urgent"));

        // Remember that when inserting we get the ObjectId back
        ObjectId id = repo.insertTicket(ticket);

        System.out.println("Inserted Ticket. New _id is: " + id);

        System.out.println("SELECT A SINGLE TICKET");
        System.out.println( repo.findById(id) );

        repo.insertTicket(
                new SupportTicket("Mobile menu overlaps page content", "On screens under 400px wide, the navigation menu covers the first section of the page.",
                        List.of("bug", "ui", "mobile"))
        );
        repo.insertTicket(
                new SupportTicket("Search is case sensitive", "Searching for 'invoice' does not return records titled 'Invoice'.",
                        List.of("bug", "ux"))
        );

        System.out.println("SELECT ALL TICKETS");
        for (SupportTicket s : repo.findAll() ) {
            System.out.println(s);
        }

        System.out.println("SELECT BY LABEL [bug]");
        for (SupportTicket s : repo.findByLabel("bug") ) {
            System.out.println(s);
        }

//        System.out.println("DELETE");
//        repo.deleteById(id);
    }
}
