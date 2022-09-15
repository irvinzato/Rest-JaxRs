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
@Produces(MediaType.APPLICATION_JSON)  //Indico que todos los métodos van a responder el mismo contenido(XML,JSON), otra forma es indicarlo en método "Response.ok()" de cada petición
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

  @POST
  @Consumes(MediaType.APPLICATION_JSON)  //El formato que viene en la request "body", debe estar acorde a lo que produce cada método "Produces"
  public Response createCourse(Curso course) {
    try {
      Curso newCourse = service.saveCourse(course);
      return Response.ok(newCourse).build();
    }catch (Exception e){
      e.printStackTrace();
      return Response.serverError().build();
    }
  }

  @PUT
  @Path("/{id}")
  @Consumes(MediaType.APPLICATION_JSON)
  public Response editCourse(@PathParam("id") Long id, Curso course) {
    Optional<Curso> optCourse = service.findCourseById(id);
    if( optCourse.isPresent() ) {
      Curso editCourse = optCourse.get();
      editCourse.setName(course.getName());
      editCourse.setTeacher(course.getTeacher());
      editCourse.setDescription(course.getDescription());
      editCourse.setDuration(course.getDuration());
      try{
        service.saveCourse(editCourse);
        return Response.ok(editCourse).build();
      }catch (Exception e){
        e.printStackTrace();
        return Response.serverError().build();
      }
    } else {
      return Response.status(Response.Status.NOT_FOUND).build();
    }
  }

  @DELETE
  @Path("/{id}")
  public Response deleteCourse(@PathParam("id") Long id) {
    Optional<Curso> optCourse = service.findCourseById(id);
    if( optCourse.isPresent() ) {
      try{
        service.deleteCourse(optCourse.get().getId());
        return Response.noContent().build();
      }catch (Exception e) {
        e.printStackTrace();
        return Response.serverError().build();
      }
    } else {
      return Response.status(Response.Status.NOT_FOUND).build();
    }
  }



}
