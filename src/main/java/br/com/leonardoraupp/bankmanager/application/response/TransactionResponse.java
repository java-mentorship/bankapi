package br.com.leonardoraupp.bankmanager.application.response;

import br.com.leonardoraupp.bankmanager.domain.Transaction;

public record TransactionResponse(Transaction transaction) { // TODO: Tirar dúvida se retorno a transação toda(a domain) ou crio um objeto para o retorno.
}

