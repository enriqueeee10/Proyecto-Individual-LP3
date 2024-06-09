package com.lp3.sistema.IndividualCanchas.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import com.lp3.sistema.IndividualCanchas.Request.usuarioRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/usuario")
public class usuarioController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostMapping("/nuevousuario")
    public void nuevoRol(@RequestBody usuarioRequest usuario) {
        String sqlUsuario = "INSERT INTO usuario (dni, nombre, apellido, correo, telefono, direccion, pass, estado) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        // Insertar el nuevo usuario y obtener el ID generado
        int usuarioId = jdbcTemplate.execute((Connection conn) -> {
            PreparedStatement ps = conn.prepareStatement(sqlUsuario, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, usuario.getDni());
            ps.setString(2, usuario.getNombre());
            ps.setString(3, usuario.getApellido());
            ps.setString(4, usuario.getCorreo());
            ps.setString(5, usuario.getTelefono());
            ps.setString(6, usuario.getDireccion());
            ps.setString(7, usuario.getPass());
            ps.setInt(8, usuario.getEstado());
            ps.executeUpdate();
            var rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1); // Obtener el ID generado
            } else {
                throw new RuntimeException("No se pudo obtener el ID del nuevo usuario");
            }
        });

        // Asumimos que el rol siempre es 1 en este ejemplo
        String sqlRol = "INSERT INTO usuario_rol (id_usuario, id_rol) VALUES (?, ?)";
        jdbcTemplate.update(sqlRol, usuarioId, usuario.getRoles());
    }

    @GetMapping("/verusuarios")
    public List<Map<String, Object>> getUsuarios() {
        String sql = "SELECT * FROM usuario";
        return jdbcTemplate.queryForList(sql);
    }

    @GetMapping("/verusuario/{idusuario}")
    public Map<String, Object> getUsuarioid(@PathVariable int idusuario) {
        String sql = "SELECT * FROM usuario WHERE idusuario = ?";
        return jdbcTemplate.queryForMap(sql, idusuario);
    }

    @PutMapping("/actualizarusuario/{idusuario}")
    public void actualizarUsuario(@PathVariable int idusuario, @RequestBody usuarioRequest usuario) {
        String sql = "UPDATE usuario SET dni = ?, nombre = ?, apellido = ?, correo = ?, telefono = ?, direccion = ?, pass = ? WHERE idusuario = ?";
        jdbcTemplate.update(sql, 
            usuario.getDni(),
            usuario.getNombre(),
            usuario.getApellido(),
            usuario.getCorreo(),
            usuario.getTelefono(),
            usuario.getDireccion(),
            usuario.getPass(),
            idusuario);
    }

    @DeleteMapping("/eliminarusuario/{idusuario}")
    public void eliminarUsuario(@PathVariable int idusuario) {
        // Primero eliminar los registros en usuario_roles
        String sqlDeleteRoles = "DELETE FROM usuario_rol WHERE id_usuario = ?";
        jdbcTemplate.update(sqlDeleteRoles, idusuario);

        // Luego eliminar el registro en usuario
        String sqlDeleteUsuario = "DELETE FROM usuario WHERE idusuario = ?";
        jdbcTemplate.update(sqlDeleteUsuario, idusuario);
    }

}
