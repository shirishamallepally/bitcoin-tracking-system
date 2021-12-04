package com.db.bts.controller;

import com.db.bts.entity.Transaction;
import com.db.bts.model.TransactionModel;
import com.db.bts.service.impl.TransactionServiceImpl;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/bts/transactions")
public class TransactionsController {

    @Autowired
    private TransactionServiceImpl transactionService;
    
    @GetMapping("")
    public ModelAndView buyOrSell() throws Exception {
        
        return new ModelAndView("userBuySell");
    }


    @GetMapping("/{id}")
    public ModelAndView getTransactionByUserId(@PathVariable(value = "id") int userId) throws Exception {
        List<Transaction> transactionList = transactionService.getTransactionByUserId(userId);
        return new ModelAndView("userTransactionHistory", "transactionList", transactionList);
    }

    @PostMapping()
    public ResponseEntity<Transaction> addTransaction(@RequestBody @NonNull TransactionModel transactionDTO) throws Exception {
        Transaction transaction1 = transactionService.addTransaction(transactionDTO);
        return ResponseEntity.ok().body(transaction1);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateStatus(@PathVariable(value = "id") int transactionId) throws Exception {
        transactionService.updateStatus(transactionId, 2);
        return ResponseEntity.ok().body("Updated Successfully");
    }

}
