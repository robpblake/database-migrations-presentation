package com.example.dbmigration;

import com.example.dbmigration.entities.Person;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/people")
public class PersonResource {

    @PersistenceContext
    EntityManager em;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public List<Person> list() {
        return em.createQuery("select p from Person p", Person.class).getResultList();
    }

    @PUT
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Person update(@PathParam("id") String id, Person person) {

        Person p = em.find(Person.class, id);
        if (p == null) {
            throw new NotFoundException("Unable to locate person with id '" + id + "'");
        }

        p.setAge(person.getAge());
        p.setFirstName(person.getFirstName());
        p.setSurname(person.getSurname());

        /*
            Set the name field to the value of firstName to maintain backwards compatibility
         */
        p.setName(person.getFirstName());
        return p;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Person create(Person person) {
        person.setName(person.getFirstName());
        em.persist(person);
        return person;
    }
}