package com.florianpaul.epn;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.florianpaul.epn.mdl.Attendee;
import com.florianpaul.epn.mdl.Event;
import com.florianpaul.epn.mdl.EventType;
import com.florianpaul.epn.mdl.Plan;
import com.florianpaul.epn.mdl.ResourceItem;
import com.florianpaul.epn.mdl.ResourceType;
import com.florianpaul.epn.mdl.ResourceType.DataType;
import com.florianpaul.epn.srv.EMF;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

@SuppressWarnings("serial")
public class EventplanServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		persist(req, resp);
	}

	@SuppressWarnings("unchecked")
	public void persist(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		// create a new Plan for the user
		Plan plan = modelExample(req, resp);

		EntityManager em = EMF.get().createEntityManager();
		try {
			em.persist(plan);
			resp.setContentType("text/plain");
			PrintWriter w = resp.getWriter();
			
			for(Plan p : (List<Plan>) em.createQuery("select p from Plan as p").getResultList()) {
				w.println(p.getKey() + ": " + p.getOwner());
				for(EventType et : p.getEventTypes()) {
					w.println(et.getName());
				}
			}
		} finally {
			em.close();
		}
		
	}

	public Plan modelExample(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		UserService us = UserServiceFactory.getUserService();
		User user = us.getCurrentUser();

		// create a new Plan for the user
		Plan plan = new Plan();
		plan.setOwner(user != null ? user.getUserId() : null);

		// One resource for all plans should be the ability to choose a drink
		ResourceType drinks = new ResourceType();
		plan.addResourceType(drinks);

		drinks.setName("Drinks");
		drinks.setDescription("This resource represents Drinks that the attendee can choose.");
		drinks.setDatatype(DataType.MultipleSelection);

		// Add some drinks to the collection of drinks
		ResourceItem coke = new ResourceItem();
		coke.setText("Coke");
		drinks.addResourceItem(coke);

		ResourceItem water = new ResourceItem();
		water.setText("Water");
		drinks.addResourceItem(water);

		ResourceItem beer = new ResourceItem();
		beer.setText("Beer");
		drinks.addResourceItem(beer);

		// The second basic ResourceType should be a name of the attendee. We
		// want to know always, who will be at our events.
		ResourceType name = new ResourceType();
		plan.addResourceType(name);

		name.setName("Name");
		name
				.setDescription("This is the field, where the name of the attendee can be stored.");
		name.setDatatype(DataType.Text);

		// Additionally we can generally ask for an email
		ResourceType email = new ResourceType();
		plan.addResourceType(email);

		email.setName("E-Mail");
		email
				.setDescription("This is the field, where the email of the attendee can be stored.");
		email.setDatatype(DataType.Text);

		// Now a special type of event is designed as a template for concrete
		// events
		EventType bbq = new EventType();
		plan.addEventType(bbq);
		
		bbq.setName("Planning of any BBQs.");
		// We will use all the types of resources, that we created generally at
		// the plan's level above.
		bbq.addResourceTypes(plan.getResourceTypes());

		// But, we won't ask for the email, that shouldn't be relevant for our
		// little festival
		bbq.removeResourceType(email);

		// After we have created the template, it time to create a concrete
		// festival
		Event birthday = new Event();
		birthday.setTitle("My Birthday Party with BBQ.");

		// Add this event to the drawer, so we can list it categorized by
		// event-type.
		bbq.addEvent(birthday);

		// Now the attendee can visit my software and answer the relevant
		// information so that i can plan my birthday party.
		// First person is peter
		Attendee peter = new Attendee();

		// Peter can choose any drinks of a list (because it's of type
		// DataType.MultipleSelection
		peter.addResourceItem(water);
		peter.addResourceItem(beer);

		// We removed the email from the List of ResourceTypes, so peter is not
		// asked for his email by the software.
		// Peter must set his name. The software creates a new ResourceItem that
		// is associated with peter.
		ResourceItem petersName = new ResourceItem();
		petersName.setText("Peter");
		peter.addResourceItem(petersName);

		// After peter has given his data to the software, he is added to my
		// concrete birthday event.
		birthday.addAttendee(peter);

		resp.setContentType("text/plain");
		PrintWriter w = resp.getWriter();

		// I can list all the information that is relevant for my birthday party
		w.println("All information of my guests at '" + birthday.getTitle()
				+ "'");
		for (Attendee guest : birthday.getAttendees()) {
			w.println("");
			for (Key itemKey : guest.getResourceItemKeys()) {
				w.println(itemKey);
			}
		}
		
		return plan;
	}
}
