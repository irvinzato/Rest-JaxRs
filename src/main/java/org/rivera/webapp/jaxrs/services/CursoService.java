package org.rivera.webapp.jaxrs.services;

import jakarta.ejb.Local;
import org.rivera.webapp.jaxrs.models.Curso;

import java.util.List;
import java.util.Optional;

@Local
public interface CursoService {
  List<Curso> listCourses();
  Curso saveCourse(Curso course);

  Optional<Curso> findCourseById(Long id);

  void deleteCourse(Long id);
}
