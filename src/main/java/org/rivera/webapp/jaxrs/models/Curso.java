package org.rivera.webapp.jaxrs.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlRootElement;

//@XmlRootElement //Configuración SOLO para cuando genero formatos XML como respuesta de mis controladores
@Entity
@Table(name = "cursos")
public class Curso {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "nombre")
  private String name;

  @Column(name = "descripcion")
  private String description;

  //@XmlTransient Para cuando hay relaciones bi direccionales(Que no se haga cíclico)
  //@JsonbTransient Para omitir este JSON en la respuesta(No incluye el instructor en las consultas)
  //@JsonIgnore Utiliza dependencia en pom "resteasy-jackson2-provider", parecida a anotación de arriba pero más robusta
  @JsonIgnoreProperties({"courses", "handler", "hibernateLazyInitializer"}) //Ignoro de "Instructor" el atributo "courses". Se evita ciclo infinito. Utiliza dependencia "resteasy-jackson2-provider"
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "instructor_id") //Asigno de forma manual el nombre de la llave foránea
  private Instructor teacher;

  @Column(name = "duracion")
  private Double duration;

  public Curso() {
  }

  public Curso(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Instructor getTeacher() {
    return teacher;
  }

  public void setTeacher(Instructor teacher) {
    this.teacher = teacher;
  }

  public Double getDuration() {
    return duration;
  }

  public void setDuration(Double duration) {
    this.duration = duration;
  }
}
