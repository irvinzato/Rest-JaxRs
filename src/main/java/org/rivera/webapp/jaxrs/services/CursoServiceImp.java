package org.rivera.webapp.jaxrs.services;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import org.rivera.webapp.jaxrs.models.Curso;
import org.rivera.webapp.jaxrs.repositories.CursoRepository;

import java.util.List;
import java.util.Optional;

@Stateless  //Para poder inyectar el repositorio tiene que ser un componente CDI o JB
public class CursoServiceImp implements CursoService {

  @Inject
  private CursoRepository repository;

  @Override
  public List<Curso> listCourses() {
    return repository.toList();
  }

  @Override
  public Curso saveCourse(Curso course) {
    System.out.println("Curso guardado con éxito: " + course.getName());
    return repository.save(course);
  }

  @Override
  public Optional<Curso> findCourseById(Long id) {
    return Optional.ofNullable(repository.byId(id));
  }

  @Override
  public void deleteCourse(Long id) {
    repository.delete(id);
  }

}
