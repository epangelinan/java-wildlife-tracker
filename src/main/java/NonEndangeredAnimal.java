import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;

public class NonEndangeredAnimal extends Animal {
  //No longer need name and id variables because they are available in Animal class
  // public String name;
  // public int id;
  //public boolean endangered;
  //private String health;
  //private String age;
  public static final String DATABASE_TYPE = "nonendangered";
  // public static final String HEALTHY = "healthy";
  // public static final String ILL = "ill";
  // public static final String OKAY = "okay";
  // public static final String NEWBORN = "newborn";
  // public static final String YOUNG = "young";
  // public static final String ADULT = "adult";

  public NonEndangeredAnimal(String name) {
    this.name = name;
  //  this.id = id;
    // this.health = health;
    // this.age = age;
    type = DATABASE_TYPE;
  }

  // public String getHealth() {
  //   return health;
  // }

  // public String getAge() {
  //   return age;
  // }
//No longer need getName and getId because they are present in Animal class
  // public String getName() {
  //   return name;
  // }
  //
  // public int getId() {
  //   return id;
  // }

  // @Override
  // public boolean equals(Object otherNonEndangeredAnimal) {
  //   if(!(otherNonEndangeredAnimal instanceof NonEndangeredAnimal)) {
  //     return false;
  //   } else {
  //     NonEndangeredAnimal newNonEndangeredAnimal = (NonEndangeredAnimal) otherNonEndangeredAnimal;
  //     return this.getName().equals(newNonEndangeredAnimal.getName()) //&& this.getHealth().equals(newNonEndangeredAnimal.getHealth()) && this.getAge().equals(newNonEndangeredAnimal.getAge());
  //   }
  // }

  // public void save() {
  //   try(Connection con = DB.sql2o.open()) {
  //     String sql = "INSERT INTO endangered_animals (name, health, age) VALUES (:name, :health, :age);";
  //     this.id = (int) con.createQuery(sql, true)
  //       .addParameter("name", this.name)
  //       .addParameter("health", this.health)
  //       .addParameter("age", this.age)
  //       .executeUpdate()
  //       .getKey();
  //   }
  // }

//Updated
  public static List<NonEndangeredAnimal> all() {
    String sql = "SELECT * FROM animals WHERE type= 'nonendangered';";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql)
        .executeAndFetch(NonEndangeredAnimal.class);
    }
  }

//Updated
  public static NonEndangeredAnimal find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM animals WHERE id=:id;";
      NonEndangeredAnimal animal = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(NonEndangeredAnimal.class);
      return animal;
    }
  }

  // public void updateHealth(String health) {
  //   try(Connection con = DB.sql2o.open()) {
  //     String sql = "UPDATE animals SET health=:health WHERE id=:id;";
  //     con.createQuery(sql)
  //       .addParameter("id", id)
  //       .addParameter("health", health)
  //       .executeUpdate();
  //   }
  // }
  //
  // public void updateAge(String age) {
  //   try(Connection con = DB.sql2o.open()) {
  //     String sql = "UPDATE animals SET age=:age WHERE id=:id;";
  //     con.createQuery(sql)
  //       .addParameter("age", age)
  //       .addParameter("id", id)
  //       .executeUpdate();
  //   }
  // }

  // public List<Sighting> getSightings() {
  //   try(Connection con = DB.sql2o.open()) {
  //     String sql = "SELECT * FROM sightings WHERE animal_id=:id;";
  //       List<Sighting> sightings = con.createQuery(sql)
  //         .addParameter("id", id)
  //         .executeAndFetch(Sighting.class);
  //     return sightings;
  //   }
  // }


}
