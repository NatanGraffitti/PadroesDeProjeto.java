package com.example.bancospringboot.service;

import com.example.bancospringboot.model.ContaBancaria;
import com.example.bancospringboot.repository.ContaBancariaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ContaBancariaService {
    @Autowired
    private ContaBancariaRepository contaBancariaRepository;

    public ContaBancaria criarConta(String numeroConta, double saldoInicial) {
        ContaBancaria conta = new ContaBancaria(numeroConta, saldoInicial);
        return contaBancariaRepository.save(conta);
    }

    public Optional<ContaBancaria> encontrarContaPorId(Long id) {
        return contaBancariaRepository.findById(id);
    }

    public void depositar(Long id, double valor) {
        Optional<ContaBancaria> contaOpt = contaBancariaRepository.findById(id);
        if (contaOpt.isPresent()) {
            ContaBancaria conta = contaOpt.get();
            conta.depositar(valor);
            contaBancariaRepository.save(conta);
        }
    }

    public boolean sacar(Long id, double valor) {
        Optional<ContaBancaria> contaOpt = contaBancariaRepository.findById(id);
        if (contaOpt.isPresent()) {
            ContaBancaria conta = contaOpt.get();
            boolean sucesso = conta.sacar(valor);
            if (sucesso) {
                contaBancariaRepository.save(conta);
            }
            return sucesso;
        }
        return false;
    }

    public double obterSaldo(Long id) {
        Optional<ContaBancaria> contaOpt = contaBancariaRepository.findById(id);
        return contaOpt.map(ContaBancaria::getSaldo).orElse(0.0);
    }
}
