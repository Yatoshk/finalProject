package DataSource;

import repository.Player;
import repository.RegistrationPlayer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
public class Data {
    public Set<RegistrationPlayer> findAll(){
        try(var st = ApplDataSource.getConnection().
                prepareStatement("select * from postgres")) {
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

    public void addPlayer(String log, String pas, String fio){
        try (var st = ApplDataSource.getConnection().prepareStatement("insert into postgres values( '" + log + "', '" + pas + "', '" + fio + "' ) ")){
            int n = st.executeUpdate();  // выполнить UPDATE запрос
        }
        catch(SQLException ex){
            System.out.println("Connection failed...");
            System.out.println(ex);
        }
    }

    public List<RegistrationPlayer> dataList(){
        Data data = new Data();
        List<RegistrationPlayer> players = data.findAll().stream().toList();
        return players;
    }
}
