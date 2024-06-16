package com.react.controller;

import com.react.exception.ResourceNotFoundException;
import com.react.model.Cliente;
import com.react.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class ClienteController {
    @Autowired
    private ClientRepository clienteRepository;

    @GetMapping("/clientes")
    public List<Cliente> listClients() {
        return clienteRepository.findAll();
    }

    @PostMapping("/clientes")
    public Cliente guardarCliente(@RequestBody Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @GetMapping("/clientes/{id}")
    public ResponseEntity<Cliente> listClientId(@PathVariable Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client with ID does not exists: " + id));
        return ResponseEntity.ok(cliente);
    }

    @PutMapping("/clientes/{id}")
    public ResponseEntity<Cliente> setClient(@PathVariable Long id, @RequestBody Cliente clientRequest) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client with ID does not exists: " + id));

        cliente.setNombre(clientRequest.getNombre());
        cliente.setApellido(clientRequest.getApellido());
        cliente.setEmail(clientRequest.getEmail());

        Cliente clientSetted = clienteRepository.save(cliente);
        return ResponseEntity.ok(clientSetted);
    }

    @DeleteMapping("/clientes/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteClient(@PathVariable Long id){
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client with ID does not exists: " + id));
        clienteRepository.delete(cliente);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

}
