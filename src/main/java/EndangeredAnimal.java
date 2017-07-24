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

  // public EndangeredAnimal(String name, String health, String age) {
  //   this.name = name;
  // //  this.id = id;
  //   this.health = health;
  //   this.age = age;
  // }

  public EndangeredAnimal(String name) {
    super(name);
    this.type = "endangered";
    this.id = id;
  }

  public EndangeredAnimal(String name, String health, String age) {
    super(name);
    this.type = "endangered";
    this.health = health;
    this.age = age;
    this.id = id;
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

  @Override
  public boolean equals(Object otherEndangeredAnimal) {
    if(!(otherEndangeredAnimal instanceof EndangeredAnimal)) {
      return false;
    } else {
      EndangeredAnimal newEndangeredAnimal = (EndangeredAnimal) otherEndangeredAnimal;
      return this.getName().equals(newEndangeredAnimal.getName()) && this.getHealth().equals(newEndangeredAnimal.getHealth()) && this.getAge().equals(newEndangeredAnimal.getAge());
    }
  }

@Override
  public void save() {
  try(Connection con = DB.sql2o.open()) {
    String sql = "INSERT INTO animals (name, health, age, type) VALUES (:name, :health, :age, :type);";
    this.id = (int) con.createQuery(sql, true)
      .addParameter("name", this.name)
      .addParameter("health", this.health)
      .addParameter("age", this.age)
      .addParameter("type", this.type)
      .throwOnMappingFailure(false)
      .executeUpdate()
      .getKey();
  }
}


public static List<EndangeredAnimal> endangeredAll() {
  try(Connection con = DB.sql2o.open()) {
    String sql = "SELECT * FROM animals WHERE type = 'endangered';";
    return con.createQuery(sql)
      .throwOnMappingFailure(false)
      .executeAndFetch(EndangeredAnimal.class);
  }
}

public static EndangeredAnimal find(int id) {
  try(Connection con = DB.sql2o.open()) {
    String sql = "SELECT * FROM animals WHERE id=:id;";
    EndangeredAnimal endangeredanimal = con.createQuery(sql)
      .addParameter("id", id)
      .throwOnMappingFailure(false)
      .executeAndFetchFirst(EndangeredAnimal.class);
    return endangeredanimal;
  }
}

  public void updateHealth(String health) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE animals SET health=:health WHERE id=:id;";
      con.createQuery(sql)
        .addParameter("id", id)
        .addParameter("health", health)
        .throwOnMappingFailure(false)
        .executeUpdate();
    }
  }

  public void updateAge(String age) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE animals SET age=:age WHERE id=:id;";
      con.createQuery(sql)
        .addParameter("age", age)
        .addParameter("id", id)
        .throwOnMappingFailure(false)
        .executeUpdate();
    }
  }

//No longer need getSightings() here because it is present in Animal class
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
