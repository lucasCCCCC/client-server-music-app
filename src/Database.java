import java.sql.*;

public class Database {

    public String getArtistInfo(String artist){

        StringBuilder sb = new StringBuilder();

        try {

            Class.forName("org.postgresql.Driver");

            Connection con = DriverManager.getConnection(Credentials.URL, Credentials.USERNAME, Credentials.PASSWORD);

            String sql = "SELECT * FROM artist INNER JOIN album on album.artistID = artist.artistID AND artist.name = ?";

            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, artist);

            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String countryOfOrigin = resultSet.getString("countryOfOrigin");
                String title = resultSet.getString("title");
                String label = resultSet.getString("label");
                String year = resultSet.getString("year");
                String genre = resultSet.getString("genre");
                String price = resultSet.getString("price");

                sb.append(name).append("|").append(countryOfOrigin).append("|").append(title).append("|").append(label)
                        .append("|").append(year).append("|").append(genre).append("|").append(price);
            }

            resultSet.close();
            pstmt.close();
            con.close();

        } catch (Exception e){
            e.printStackTrace();
        }

        return sb.toString();
    }

    public boolean establishDBConnection() {

        try{

            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(Credentials.URL, Credentials.USERNAME, Credentials.PASSWORD);
            return con.isValid(5);

        } catch (SQLException | ClassNotFoundException e){

            e.printStackTrace();
        }
        return false;
    }
}