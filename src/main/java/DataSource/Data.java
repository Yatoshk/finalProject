package DataSource;

import repository.Player;
import repository.RegistrationPlayer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
public class Data {
    public Set<RegistrationPlayer> findAll(){
        try(var st = ApplDataSource.getConnection().
                prepareStatement("select * from players")) {
            var result = st.executeQuery();
            return mapResultSetToPlayer(result);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static Set<RegistrationPlayer> mapResultSetToPlayer(ResultSet resultSet) throws SQLException {
        Set<RegistrationPlayer> players = new HashSet<>();
        while (resultSet.next()){
            var login = resultSet.getString("login");
            var password = resultSet.getString("password");
            var fio = resultSet.getString("fio");
            var win = resultSet.getInt("win");
            var lose = resultSet.getInt("lose");

            players.add(new RegistrationPlayer(
                                           login,
                                           password,
                                           fio,
                                           win,
                                           lose
            ));

        }
        return players;
    }
}
