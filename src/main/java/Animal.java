import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Animal {
  public String name;
  public int id;
  public String type;


  // public Animal(String name) {
  //   this.name = name;
  //   this.id = id;
  // }

  public String getName() {
    return name;
  }

  public int getId() {
    return id;
  }

  @Override
  public boolean equals(Object otherAnimal) {
    if(!(otherAnimal instanceof Animal)) {
      return false;
    } else {
      Animal newAnimal = (Animal) otherAnimal;
      return this.getName().equals(newAnimal.getName());
    }
  }

//Updated
  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO animals (name, type) VALUES (:name, :type);";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .executeUpdate()
        .getKey();
    }
  }
//Remove because this is now an abstract class.
  // public static List<Animal> all() {
  //   try(Connection con = DB.sql2o.open()) {
  //     String sql = "SELECT * FROM animals;";
  //     return con.createQuery(sql)
  //       .executeAndFetch(Animal.class);
  //   }
  // }

//Remove because this is now an abstract class
  // public static Animal find(int id) {
  //   try(Connection con = DB.sql2o.open()) {
  //     String sql = "SELECT * FROM animals WHERE id=:id;";
  //     Animal animal = con.createQuery(sql)
  //       .addParameter("id", id)
  //       .executeAndFetchFirst(Animal.class);
  //     return animal;
  //   }
  // }

  public void updateName(String name) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE animals SET name=:name WHERE id=:id;";
      con.createQuery(sql)
        .addParameter("id", id)
        .addParameter("name", name)
        .executeUpdate();
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM animals WHERE id=:id;";
      con.createQuery(sql)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public List<Sighting> getSightings() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM sightings WHERE animal_id=:id;";
        List<Sighting> sightings = con.createQuery(sql)
          .addParameter("id", id)
          .executeAndFetch(Sighting.class);
      return sightings;
    }
  }

}
