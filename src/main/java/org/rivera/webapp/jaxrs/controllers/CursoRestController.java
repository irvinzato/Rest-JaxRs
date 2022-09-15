package org.rivera.webapp.jaxrs.controllers;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.rivera.webapp.jaxrs.models.Curso;
import org.rivera.webapp.jaxrs.services.CursoService;

import java.util.List;
import java.util.Optional;

@RequestScoped
@Path("/cursos")
@Produces(MediaType.APPLICATION_XML)  //Indico que todos los métodos van a producir el mismo contenido(XML,JSON), otra forma es indicarlo en método "Response.ok()" de cada petición
public class CursoRestController {

  @Inject
  private CursoService service;

  @GET
  public List<Curso> listCourses() {
    return service.listCourses();
  }

  @GET
  @Path("/{id}")
  public Response courseById(@PathParam("id") Long id) { //Regreso un "Response" para trabajar con Status en caso correcto e incorrecto
    Optional<Curso> optCourse = service.findCourseById(id);
    if( optCourse.isPresent() ) {
      return Response.ok(optCourse.get()).build();
    }
    return Response.status(Response.Status.NOT_FOUND).build();
  }

  @DELETE
  @Path("/{id}")
  public void deleteCourse(@PathParam("id") Long id) {
    service.deleteCourse(id);
  }



}
