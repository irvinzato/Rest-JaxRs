package org.rivera.webapp.jaxrs.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "instructores")
public class Instructor {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  //"handler", "hibernateLazyInitializer", son para evitar problemas de cache
  @JsonIgnoreProperties({"teacher", "handler", "hibernateLazyInitializer"})   //Ignoro de "Curso" el atributo "teacher". Se evita ciclo infinito cuando es bi-direccional
  @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL) //Establecer relación inversa con el atributo de la contra parte y cascada para eliminar cursos si se elimina profesor.
  private List<Curso> courses;

  @Column(name = "nombre")
  private String name;

  @Column(name = "apellido")
  private String lastName;

  public Instructor() {
    this.courses = new ArrayList<>();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public List<Curso> getCourses() {
    return courses;
  }

  public void setCourses(List<Curso> courses) {
    this.courses = courses;
  }
}
