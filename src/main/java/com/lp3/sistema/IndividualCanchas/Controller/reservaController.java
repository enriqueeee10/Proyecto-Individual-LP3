package com.lp3.sistema.IndividualCanchas.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import com.lp3.sistema.IndividualCanchas.Request.ReservaRequest;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/reserva")
public class reservaController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostMapping("/nueva")
    public ResponseEntity<String> registrarReserva(@RequestBody ReservaRequest reserva) {
        try {
            String sql = "INSERT INTO reserva (fechareseva, horainicio, horafinal, idcanchafutbol, idusuario, idcliente, observaciones, pago, totaldepositado) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            jdbcTemplate.update(sql, 
                reserva.getFechaReserva(), 
                reserva.getHoraInicio(), 
                reserva.getHoraFinal(), 
                reserva.getIdCanchaFutbol(), 
                reserva.getIdUsuario(), 
                reserva.getIdCliente(),
                reserva.getObservaciones(), 
                reserva.getPago(), 
                reserva.getTotalDepositado());
            return ResponseEntity.ok("Reserva registrada con éxito");
        } catch (Exception e) {
            e.printStackTrace(); // Registrar el error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al registrar la reserva: " + e.getMessage());
        }
    }

    @GetMapping("/verreservas")
    public List<Map<String, Object>> obtenerReservas() {
        String sql = "SELECT * FROM reserva";
        return jdbcTemplate.queryForList(sql);
    }

    @GetMapping("/verclientes")
    public List<Map<String, Object>> obtenerClientes() {
        String sql = "SELECT * FROM clientes";
        return jdbcTemplate.queryForList(sql);
    }
}
