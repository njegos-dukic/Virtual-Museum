package org.unibl.etf.virtualmuseumapi.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.unibl.etf.virtualmuseumapi.model.entities.TransactionEntity;
import org.unibl.etf.virtualmuseumapi.repositories.TransactionRepository;

@Service
@AllArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;

    @Transactional
    public TransactionEntity insert(TransactionEntity transactionEntity) {
        return transactionRepository.saveAndFlush(transactionEntity);
    }

    public TransactionEntity getTransactionEntityByTicketNumberAndTourIdAndUserId(String ticketNumber, Integer tourId, Integer userId) {
        return transactionRepository.getTransactionEntityByTicketNumberAndTourIdAndUserId(ticketNumber, tourId, userId);
    }
}