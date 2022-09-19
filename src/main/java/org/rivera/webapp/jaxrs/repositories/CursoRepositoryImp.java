package org.rivera.webapp.jaxrs.repositories;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.rivera.webapp.jaxrs.models.Curso;

import java.util.List;

@RequestScoped
public class CursoRepositoryImp implements CursoRepository{

  @Inject
  private EntityManager em;

  @Override
  public List<Curso> toList() {
    return em.createQuery("SELECT c FROM Curso c LEFT OUTER JOIN FETCH c.teacher", Curso.class)
            .getResultList();
  }

  @Override
  public Curso save(Curso course) {
    if( course.getId() != null && course.getId() > 0 ) {
      em.merge(course);
    } else {
      em.persist(course);
    }
    return course;
  }

  @Override
  public Curso byId(Long id) {
    //return em.find(Curso.class, id);
    return em.createQuery("SELECT c FROM Curso c LEFT OUTER JOIN FETCH c.teacher WHERE c.id=:idParam", Curso.class)
            .setParameter("idParam", id)
            .getSingleResult();
  }

  @Override
  public void delete(Long id) {
    Curso c = byId(id);
    em.remove(c);
  }
}
