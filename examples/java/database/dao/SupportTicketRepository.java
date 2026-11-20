package com.revature.database.dao;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.revature.database.models.SupportTicket;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

public class SupportTicketRepository {

    /*
     * The following example uses the built-in POJO support by passing
     * the Model as the Generic type to the MongoCollection object.
     * See the Comments in Main.java and the SupportTicket.java class for more
     * details.
     *
     * A simpler manual alternative is to build the Document yourself from the
     * SupportTicket's data and pass it to a MongoCollection<Document>:
     *
     * public static boolean insertTicket(MongoCollection<Document> collection,
     *                                    SupportTicket ticket) {
     *     Document newTicket = new Document("subject", ticket.getSubject())
     *             .append("description", ticket.getDescription())
     *             .append("labels", ticket.getLabels());
     *     InsertOneResult result = collection.insertOne(newTicket);
     *
     *     return result.wasAcknowledged();
     * }
     *
     * You may favor this more simple approach when building your own projects, since
     * the built-in CodecRegistry requires you to either follow some specific conventions
     * or import and use other annotations and classes.
     */
    private final MongoCollection<SupportTicket> collection;

    public SupportTicketRepository(MongoCollection<SupportTicket> collection) {
        this.collection = collection;
    }

    // The insert method returns the generated Object id
    public ObjectId insertTicket(SupportTicket ticket) {
        // When using the insertOne (or insertMany) methods, each successfully
        // inserted document is populated with it's MongoDB generated ObjectId
        collection.insertOne(ticket);
        return ticket.getId();
    }

    // Equivalent to "db.supportTickets.findOne({ _id: ObjectId("...") })" in mongoshell
    public SupportTicket findById(ObjectId id) {
        return collection.find(Filters.eq("_id", id)).first();  // null if not found
    }

    // Equivalent to "db.supportTickets.find({}).sort({ subject: 1 })" in mongoshell
    public List<SupportTicket> findAll() {
        // The 'into' method populates an Iterable collection with the data from the find() method's results
        // Notice that we can sort this data as well
        return collection.find().sort(Sorts.ascending("subject")).into(new ArrayList<>());
    }

    // Equivalent to: "db.supportTickets.find({ labels: "..." })" - search for all tickets matching a single label
    public List<SupportTicket> findByLabel(String label) {
        return collection.find(Filters.eq("labels", label)).into(new ArrayList<>());
    }

    // Equivalent to: "db.supportTickets.find({ labels: { $all: ["...", "..."] } })" - search for a ticket with ALL labels
    public List<SupportTicket> findByAllLabels(String... labels) {
        return collection.find(Filters.all("labels", labels)).into(new ArrayList<>());
    }

    // Note:  A 'FindByAny...' method should use Filters.in(...)

    /*
     * Note: When using this 'update' method, the typical workflow would be:
     * 1. Search for the Document (findById...)
     * 2. Update fields as appropriate
     * 3. call update() to change the entire document in Mongo
     *
     * i.e.
     *  SupportTicket updated = repo.findById(id);
     *  updated.setDescription("New Description");
     *  updated.getLabels().add("new-label");
     *  repository.update(ticket);
     */
    public boolean update(SupportTicket ticket) {
        if (ticket.getId() == null) {
            throw new IllegalArgumentException("Cannot update a ticket that has no id");
        }

        UpdateResult result = collection.updateOne(
                Filters.eq("_id", ticket.getId()),
                Updates.combine(
                        Updates.set("subject", ticket.getSubject()),
                        Updates.set("description", ticket.getDescription()),
                        Updates.set("labels", ticket.getLabels())));

        // Did the ticket actually exist to be updated?
        return result.getMatchedCount() > 0;
    }

    /*
     * Equivalent to: "db.supportTickets.deleteOne({ _id })"
     *
     * Note: if deleting by a field other than the '_id', it is best
     * practice to first search your database to see how many documents
     * would be impacted by the delete operation, and if the deletion affects
     * more than one (i.e. the search result is greater than one) you do
     * not perform the deletion
     */
    public boolean deleteById(ObjectId id) {
        DeleteResult result = collection.deleteOne(Filters.eq("_id", id));
        return result.getDeletedCount() > 0;
    }
}
