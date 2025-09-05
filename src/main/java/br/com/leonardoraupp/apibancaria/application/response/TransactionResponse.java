package br.com.leonardoraupp.apibancaria.application.response;

import br.com.leonardoraupp.apibancaria.domain.Transaction;

public record TransactionResponse(Transaction transaction) { // TODO: Tirar dúvida se retorno a transação toda(a domain) ou crio um objeto para o retorno.
}

