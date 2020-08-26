package com.saledo.wallet.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saledo.wallet.dto.TransactionDto;
import com.saledo.wallet.entity.Transaction;
import com.saledo.wallet.service.TransactionService;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@OpenAPIDefinition(info = @Info(title = "WalletApp API Docs", version = "1.0", description = "My API", contact = @Contact(name = "Edin", email = "edin.salaka1@gmail.com")))
@Tag(name = "transaction", description = "Operation for manipulating with transactions")
@RestController
@RequestMapping(value = "/transaction")
public class TransactionController {
	@Autowired
	TransactionService transactionService;

	@Autowired
	ModelMapper modelMapper;

	@Operation(summary = "Get a list of transactions", description = "Get a list of transactions for requested player")
	@ApiResponse(responseCode = "200", description = "successful response - array of transactions", content = {
			@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = TransactionDto.class))) })
	@Parameter(in = ParameterIn.PATH, name = "id", required = true, description = "requested player id", example = "2", schema = @Schema(type = "string"))
	@GetMapping(value = "/history/{id}")
	public ResponseEntity<List<TransactionDto>> getBalanceByPlayerId(
			@PathVariable Long id) {
		List<Transaction> transactions = transactionService.getPlayerHistory(id);
		return new ResponseEntity<>(
				transactions.stream().map(t -> modelMapper.map(t, TransactionDto.class))
						.collect(Collectors.toList()),
				HttpStatus.OK);
	}

	@Operation(summary = "Add new transaction", description = "Add new transaction for requested player and transaction type")
	@ApiResponse(responseCode = "200", description = "successful response - newly added transaction", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TransactionDto.class)))
	@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "New transaction to be created", required = true, content = @Content(mediaType = "application/json", schema = @Schema(implementation = TransactionDto.class)))
	@PostMapping(value = "/add")
	public ResponseEntity<TransactionDto> registerTransaction(
			@Valid @RequestBody TransactionDto transactionDto) throws RuntimeException {
		Transaction transaction = transactionService
				.registerTransaction(modelMapper.map(transactionDto, Transaction.class));
		return new ResponseEntity<>(modelMapper.map(transaction, TransactionDto.class),
				HttpStatus.OK);
	}
}
