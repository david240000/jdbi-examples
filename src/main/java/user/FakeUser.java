package user;

import com.github.javafaker.Faker;

import java.time.ZoneId;
import java.util.Locale;

import static org.apache.commons.codec.digest.DigestUtils.md5Hex;

public class FakeUser {

    private final Faker faker;

    public FakeUser(Locale locale){
        this.faker = new Faker(locale);
    }

    public User getFakeUser(){
        User user = User.builder()
                .username(faker.name().username())
                .password(md5Hex(faker.internet().password()))
                .name(faker.name().fullName())
                .email(faker.internet().emailAddress())
                .gender(faker.options().option(User.Gender.MALE, User.Gender.FEMALE))
                .birthDate(faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
                .enabled(faker.bool().bool())
                .build();
        return user;
    }
}
