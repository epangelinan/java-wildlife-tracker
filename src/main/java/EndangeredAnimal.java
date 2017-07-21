import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;

public class EndangeredAnimal extends Animal {
  //No longer need name and id variables because they are available in Animal class
  // public String name;
  // public int id;
  public boolean endangered;
  private String health;
  private String age;
  public static final String HEALTHY = "healthy";
  public static final String ILL = "ill";
  public static final String OKAY = "okay";
  public static final String NEWBORN = "newborn";
  public static final String YOUNG = "young";
  public static final String ADULT = "adult";

  public EndangeredAnimal(String name, String health, String age) {
    this.name = name;
  //  this.id = id;
    this.health = health;
    this.age = age;
  }

  public String getHealth() {
    return health;
  }

  public String getAge() {
    return age;
  }
//No longer need getName and getId because they are present in Animal class
  // public String getName() {
  //   return name;
  // }
  //
  // public int getId() {
  //   return id;
  // }

  // @Override
  // public boolean equals(Object otherEndangeredAnimal) {
  //   if(!(otherEndangeredAnimal instanceof EndangeredAnimal)) {
  //     return false;
  //   } else {
  //     EndangeredAnimal newEndangeredAnimal = (EndangeredAnimal) otherEndangeredAnimal;
  //     return this.getName().equals(newEndangeredAnimal.getName()) && this.getHealth().equals(newEndangeredAnimal.getHealth()) && this.getAge().equals(newEndangeredAnimal.getAge());
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
  public static List<EndangeredAnimal> all() {
    String sql = "SELECT * FROM animals;";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql)
        .executeAndFetch(EndangeredAnimal.class);
    }
  }

//Updated
  public static EndangeredAnimal find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM animals WHERE id=:id;";
      EndangeredAnimal animal = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(EndangeredAnimal.class);
      return animal;
    }
  }

  public void updateHealth(String health) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE endangered_animals SET health=:health WHERE id=:id;";
      con.createQuery(sql)
        .addParameter("id", id)
        .addParameter("health", health)
        .executeUpdate();
    }
  }

  public void updateAge(String age) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE endangered_animals SET age=:age WHERE id=:id;";
      con.createQuery(sql)
        .addParameter("age", age)
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
