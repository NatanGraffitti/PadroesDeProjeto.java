package com.example.bancospringboot.controller;

import com.example.bancospringboot.model.ContaBancaria;
import com.example.bancospringboot.service.ContaBancariaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contas")
public class ContaBancariaController {
    @Autowired
    private ContaBancariaService contaBancariaService;

    @PostMapping("/criar")
    public ContaBancaria criarConta(@RequestParam String numeroConta, @RequestParam double saldoInicial) {
        return contaBancariaService.criarConta(numeroConta, saldoInicial);
    }

    @PostMapping("/{id}/depositar")
    public void depositar(@PathVariable Long id, @RequestParam double valor) {
        contaBancariaService.depositar(id, valor);
    }

    @PostMapping("/{id}/sacar")
    public boolean sacar(@PathVariable Long id, @RequestParam double valor) {
        return contaBancariaService.sacar(id, valor);
    }

    @GetMapping("/{id}/saldo")
    public double obterSaldo(@PathVariable Long id) {
        return contaBancariaService.obterSaldo(id);
    }
}
