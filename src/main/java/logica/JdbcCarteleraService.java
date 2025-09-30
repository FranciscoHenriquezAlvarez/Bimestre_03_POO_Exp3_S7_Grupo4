package logica;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import persistencia.DatabaseConnection;
import persistencia.InicializacionTabla;

public class JdbcCarteleraService implements ICarteleraService {  // Actualización

    private static volatile boolean tablaLista = false;  // Actualización

    private static synchronized void ensureSchemaOnce() throws SQLException {  // Actualización
        if (!tablaLista) {
            InicializacionTabla.realizar();
            tablaLista = true;
        }
    }

    @Override
    public void guardar(Cartelera c) throws Exception {  // Actualización
        if (c == null) {
            throw new IllegalArgumentException("Cartelera nula");
        }

        ensureSchemaOnce();  // Actualización

        final String sql = "INSERT INTO cartelera (titulo, director, anio, duracion, genero) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, c.getTitulo());
            ps.setString(2, c.getDirector());
            ps.setInt(3, c.getAnio());
            ps.setInt(4, c.getDuracion());
            ps.setString(5, c.getGenero().name());
            ps.executeUpdate();
            
        } catch (SQLException e) {
            String detalle = "SQLState=" + e.getSQLState() + ", code=" + e.getErrorCode() + ", msg=" + e.getMessage(); // Actualización
            throw new Exception("Error al guardar cartelera vía JDBC. " + detalle, e);
        }
    }
@Override
    public void actualizar(Cartelera c) throws Exception {
        if (c == null) throw new IllegalArgumentException("Cartelera nula");
        if (c.getId() <= 0) throw new IllegalArgumentException("Id inválido");
        if (c.getGenero() == null) throw new IllegalArgumentException("Género obligatorio");
        ensureSchemaOnce();

        final String sql = "UPDATE cartelera SET titulo=?, director=?, anio=?, duracion=?, genero=? WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, c.getTitulo());
            ps.setString(2, c.getDirector());
            ps.setInt(3, c.getAnio());
            ps.setInt(4, c.getDuracion());
            ps.setString(5, c.getGenero().name());
            ps.setInt(6, c.getId());

            int rows = ps.executeUpdate();
            if (rows == 0) {
                throw new Exception("No se encontró película id=" + c.getId());
            }
        } catch (SQLException e) {
            String detalle = "SQLState=" + e.getSQLState() + ", code=" + e.getErrorCode() + ", msg=" + e.getMessage();
            throw new Exception("Error al actualizar cartelera vía JDBC. " + detalle, e);
        }
    }

    @Override
    public void eliminar(int id) throws Exception {
        if (id <= 0) throw new IllegalArgumentException("Id inválido");
        ensureSchemaOnce();

        final String sql = "DELETE FROM cartelera WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            if (rows == 0) {
                throw new Exception("No se encontró película id=" + id);
            }
        } catch (SQLException e) {
            String detalle = "SQLState=" + e.getSQLState() + ", code=" + e.getErrorCode() + ", msg=" + e.getMessage();
            throw new Exception("Error al eliminar cartelera vía JDBC. " + detalle, e);
        }
    }

    @Override
    public Cartelera buscarPorId(int id) throws Exception {
        if (id <= 0) throw new IllegalArgumentException("Id inválido");
        ensureSchemaOnce();

        final String sql = "SELECT id, titulo, director, anio, duracion, genero FROM cartelera WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) return null;

                Cartelera c = new Cartelera();
                c.setId(rs.getInt("id"));
                c.setTitulo(rs.getString("titulo"));
                c.setDirector(rs.getString("director"));
                c.setAnio(rs.getInt("anio"));
                c.setDuracion(rs.getInt("duracion"));
                String g = rs.getString("genero");
                c.setGenero(g != null ? Cartelera.Genero.valueOf(g) : null);
                return c;
            }
        } catch (SQLException e) {
            String detalle = "SQLState=" + e.getSQLState() + ", code=" + e.getErrorCode() + ", msg=" + e.getMessage();
            throw new Exception("Error al buscar cartelera vía JDBC. " + detalle, e);
        }
    }

    @Override
    public List<Cartelera> listar() throws Exception {
        ensureSchemaOnce();

        final String sql = "SELECT id, titulo, director, anio, duracion, genero FROM cartelera ORDER BY id";
        List<Cartelera> out = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Cartelera c = new Cartelera();
                c.setId(rs.getInt("id"));
                c.setTitulo(rs.getString("titulo"));
                c.setDirector(rs.getString("director"));
                c.setAnio(rs.getInt("anio"));
                c.setDuracion(rs.getInt("duracion"));
                String g = rs.getString("genero");
                c.setGenero(g != null ? Cartelera.Genero.valueOf(g) : null);
                out.add(c);
            }
            return out;
        } catch (SQLException e) {
            String detalle = "SQLState=" + e.getSQLState() + ", code=" + e.getErrorCode() + ", msg=" + e.getMessage();
            throw new Exception("Error al listar carteleras vía JDBC. " + detalle, e);
        }
    }
}
