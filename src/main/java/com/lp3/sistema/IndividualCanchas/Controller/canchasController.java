package com.lp3.sistema.IndividualCanchas.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import com.lp3.sistema.IndividualCanchas.Request.canchasRequest;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cancha")
public class canchasController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostMapping("/nuevacancha")
    public ResponseEntity<String> registrarCancha(@RequestBody canchasRequest cancha) {
        try {
            String sql = "INSERT INTO canchas_futbol (codigo, nombre, direccion, precio) VALUES (?, ?, ?, ?)";
            jdbcTemplate.update(sql, 
                cancha.getCodigo(), 
                cancha.getNombre(), 
                cancha.getDireccion(), 
                cancha.getPrecio());
            return ResponseEntity.ok("Cancha registrada con éxito");
        } catch (Exception e) {
            e.printStackTrace(); // Registrar el error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al registrar la cancha: " + e.getMessage());
        }
    }

    @GetMapping("/vercanchas")
    public List<Map<String, Object>> obtenerCanchas() {
        String sql = "SELECT * FROM canchas_futbol";
        return jdbcTemplate.queryForList(sql);
    }

    @GetMapping("/vercancha/{idcanchafutbol}")
    public ResponseEntity<Map<String, Object>> obtenerCanchaPorId(@PathVariable int idcanchafutbol) {
        try {
            String sql = "SELECT * FROM canchas_futbol WHERE idcanchafutbol = ?";
            Map<String, Object> cancha = jdbcTemplate.queryForMap(sql, idcanchafutbol);
            return ResponseEntity.ok(cancha);
        } catch (Exception e) {
            e.printStackTrace(); // Registrar el error
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping("/actualizarcancha/{idcanchafutbol}")
    public ResponseEntity<String> actualizarCancha(@PathVariable int idcanchafutbol, @RequestBody canchasRequest cancha) {
        try {
            String sql = "UPDATE canchas_futbol SET codigo = ?, nombre = ?, direccion = ?, precio = ? WHERE idcanchafutbol = ?";
            int rows = jdbcTemplate.update(sql, 
                cancha.getCodigo(), 
                cancha.getNombre(), 
                cancha.getDireccion(), 
                cancha.getPrecio(), 
                idcanchafutbol);
            if (rows > 0) {
                return ResponseEntity.ok("Cancha actualizada con éxito");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cancha no encontrada");
            }
        } catch (Exception e) {
            e.printStackTrace(); // Registrar el error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar la cancha: " + e.getMessage());
        }
    }

    @DeleteMapping("/eliminarcancha/{idcanchafutbol}")
    public ResponseEntity<String> eliminarCancha(@PathVariable int idcanchafutbol) {
        try {
            String sql = "DELETE FROM canchas_futbol WHERE idcanchafutbol = ?";
            int rows = jdbcTemplate.update(sql, idcanchafutbol);
            if (rows > 0) {
                return ResponseEntity.ok("Cancha eliminada con éxito");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cancha no encontrada");
            }
        } catch (Exception e) {
            e.printStackTrace(); // Registrar el error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar la cancha: " + e.getMessage());
        }
    }
}
