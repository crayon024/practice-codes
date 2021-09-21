package self.practice.codes.sql.milion;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    long id;
    String name;
    String password;
    String phoneNumber;
}
