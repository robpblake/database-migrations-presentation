package com.example.dbmigration;

import com.example.dbmigration.entities.Person;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/people")
public class PersonResource {

    @PersistenceContext
    EntityManager em;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public List<Person> frank() {
        return em.createQuery("select p from Person p", Person.class).getResultList();
    }
}