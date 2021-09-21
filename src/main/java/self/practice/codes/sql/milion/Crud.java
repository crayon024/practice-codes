package self.practice.codes.sql.milion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Controller
@RequestMapping("jdbc")
@ResponseBody
public class Crud {

    @Autowired
    private DataSource dataSource;

    private Connection conn = null;
    private final String ALL = "SELECT * FROM user";
    private final String INSERT = "INSERT INTO user(name, password, phoneNumber) values (?,?,?)";
    private final String SELECT_ID = "SELECT * FROM user WHERE id = ?";

    @RequestMapping("/hikari/milion/{time1}")
    public String milion(@PathVariable String time1) {
        final Class<? extends DataSource> aClass = dataSource.getClass();
        System.out.println("DataSource Type: " + aClass.toString());
        Connection connection = null;
        String time = "1";

        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);

            try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT);
                 //final PreparedStatement delete_from_user = connection.prepareStatement("delete from user")
            ) {
                int one_milion = 1_000_000;
                int Count = Integer.parseInt(time1);
                int i = 0;
                //delete_from_user.execute();
                final long l = System.currentTimeMillis();
                while (i < Count) {
                    preparedStatement.setObject(1, "na_" + i);
                    preparedStatement.setObject(2, "ps_" + i);
                    preparedStatement.setObject(3, "ph" + i);
                    preparedStatement.execute();
                    i++;
                }
                connection.commit();
                final long l2 = System.currentTimeMillis();
                time = (l2 - l) / 1000 / 60 + " min, " + (l2 - l) / 1000 + " s .";
            }

        } catch (SQLException throwable) {
            throwable.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            JDBCUtil.releaseConn(connection);
        }
        return time;
    }


    @RequestMapping("/hikari/all")
    public List<User> students2() {
        final Class<? extends DataSource> aClass = dataSource.getClass();
        System.out.println("DataSource Type: " + aClass.toString());

        List<User> s = null;
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(ALL);
                 ResultSet resultSet = preparedStatement.executeQuery();) {
                s = results(resultSet);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (connection != null) connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return s;
    }

    @RequestMapping("/all")
    public List<User> students() {
        List<User> students = null;
        try {
            conn = JDBCUtil.getConnection();
            // try-with-resource -> 自动释放资源 AutoClosed
            try (Statement statement = conn.createStatement();
                 ResultSet resultSet = statement.executeQuery(ALL)) {
                students = results(resultSet);
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        } finally {
            JDBCUtil.releaseConn(conn);
        }
        return students;
    }

    @RequestMapping("/InsertAndPrepareStatementAndTransaction/{id}")
    public List<User> insert(@PathVariable Long id) {
        List<User> students = null;
        try {
            conn = JDBCUtil.getConnection();
            // try-with-resource -> 自动释放资源 AutoClosed

            conn.setAutoCommit(false);
            try (PreparedStatement preStatement = conn.prepareStatement(INSERT);
                 PreparedStatement preStatement2 = conn.prepareStatement(SELECT_ID)) {
                preStatement.setObject(1, id);
                preStatement.setObject(2, "random" + id);
                preStatement.setObject(3, 30);

                preStatement2.setObject(1, id);

                preStatement.executeUpdate();
                final ResultSet resultSet = preStatement2.executeQuery();
                students = results(resultSet);
                resultSet.close();
            }

            conn.commit();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            JDBCUtil.releaseConn(conn);
        }
        return students;
    }

    private List<User> results(ResultSet resultSet) throws SQLException {
        List<User> students = new ArrayList<>();
        while (resultSet.next()) {
            students.add(new User(
                    resultSet.getLong(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)));
        }
        return students;
    }

}
