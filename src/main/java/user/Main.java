package user;


import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import java.util.Locale;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

public class Main {

    public static void main(String[] args) {
        Jdbi jdbi = Jdbi.create("jdbc:h2:mem:test");
        jdbi.installPlugin(new SqlObjectPlugin());
        try (Handle handle = jdbi.open()) {
            UserDao dao = handle.attach(UserDao.class);
            dao.createTable();
            var faker = new FakeUser(new Locale("hu"));
            User user1 = faker.getFakeUser();
            dao.insertUser(user1);
            User user2 = faker.getFakeUser();
            dao.insertUser(user2);
            User user3 = faker.getFakeUser();
            dao.insertUser(user3);
            System.out.println(dao.getUser(2).get());
            System.out.println(dao.getUser(user1.getName()).get());
            dao.listUsers().forEach(System.out::println);
            dao.deleteUser(user2);
            dao.listUsers().forEach(System.out::println);
        }
    }
}
