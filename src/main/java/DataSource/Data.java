package DataSource;

import repository.Player;
import repository.RegistrationPlayer;
import repository.SaveGame;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
public class Data {

    //Players
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

    public List<RegistrationPlayer> dataList(){
        Data data = new Data();
        return data.findAll().stream().toList();
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

    public void updateData(Player player, int i){
        if (i == 1){
            try (var st = ApplDataSource.getConnection().prepareStatement("update postgres set win = win + 1 where fio = '" + player.getFio() + "'")){
                int n = st.executeUpdate();  // выполнить UPDATE запрос
            }
            catch(SQLException ex){
                System.out.println("Connection failed...");
                System.out.println(ex);
            }
        }
        else {
            try (var st = ApplDataSource.getConnection().prepareStatement("update postgres set lose = lose + 1 where fio = '" + player.getFio() + "'")){
                int n = st.executeUpdate();  // выполнить UPDATE запрос
            }
            catch(SQLException ex){
                System.out.println("Connection failed...");
                System.out.println(ex);
            }
        }
    }

    //SaveGames
    public Set<SaveGame> findAllGame(){
        try(var st = ApplDataSource.getConnection().
                prepareStatement("select * from saveprogress")) {
            var result = st.executeQuery();
            return mapResultSetToGame(result);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static Set<SaveGame> mapResultSetToGame(ResultSet resultSet) throws SQLException {
        Set<SaveGame> saveGames = new HashSet<>();
        while (resultSet.next()){
            var fio1 = resultSet.getString("fio1");
            var fio2 = resultSet.getString("fio2");
            var sign1 = resultSet.getString("sign1");
            var sign2 = resultSet.getString("sign2");
            var moves = resultSet.getString("moves");

            saveGames.add(new SaveGame(
                    fio1,
                    fio2,
                    sign1,
                    sign2,
                    moves
            ));

        }
        return saveGames;
    }

    public List<SaveGame> dataListGame(){
        Data data = new Data();
        return data.findAllGame().stream().toList();
    }

    public void addGame(String fio1, String fio2, String sign1, String sign2){
        try (var st = ApplDataSource.getConnection().prepareStatement("insert into saveprogress values('" + fio1 + "','" + fio2 + "','" + sign1 + "','" + sign2 + "','')")){
            int n = st.executeUpdate();  // выполнить UPDATE запрос
        }
        catch(SQLException ex){
            System.out.println("Connection failed...");
            System.out.println(ex);
        }
    }

    public void addGameMove(String move, String fio1, String fio2){
        try (var st = ApplDataSource.getConnection().prepareStatement("update saveprogress set moves = moves || '" + move + "' where fio1 = '" + fio1 + "' and fio2 = '" + fio2 + "'")){
            int n = st.executeUpdate();  // выполнить UPDATE запрос
        }
        catch(SQLException ex){
            System.out.println("Connection failed...");
            System.out.println(ex);
        }
    }
    public void deleteOldGame(String fio1, String fio2){
        try (var st = ApplDataSource.getConnection().prepareStatement("delete from saveprogress where fio1 = '" + fio1 + "' and fio2 = '" + fio2 + "'")){
            int n = st.executeUpdate();  // выполнить UPDATE запрос
        }
        catch(SQLException ex){
            System.out.println("Connection failed...");
            System.out.println(ex);
        }
    }
}
