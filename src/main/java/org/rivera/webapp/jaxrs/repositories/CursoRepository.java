package org.rivera.webapp.jaxrs.repositories;

import org.rivera.webapp.jaxrs.models.Curso;

import java.util.List;

public interface CursoRepository {
  List<Curso> toList();
  Curso save(Curso course);
  Curso byId(Long id);
  void delete(Long id);
}
