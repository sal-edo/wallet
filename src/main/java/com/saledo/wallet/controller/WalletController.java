package com.saledo.wallet.controller;

import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saledo.wallet.dto.WalletDto;
import com.saledo.wallet.entity.Wallet;
import com.saledo.wallet.service.WalletService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "wallet", description = "Operation for manipulating with player wallet")
@RestController
@RequestMapping(value = "/wallet")
public class WalletController {
	@Autowired
	WalletService walletService;

	@Autowired
	ModelMapper modelMapper;

	@Operation(summary = "Get a current balance", description = "Get a current balance for requested player")
	@ApiResponse(responseCode = "200", description = "successful response - player wallet", content = {
			@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = WalletDto.class))) })
	// TODO: type string inside parameter set because required doesnt work when its some other type
	@Parameter(in = ParameterIn.PATH, name = "id", required = true, description = "requested player id", example = "2", schema = @Schema(type = "string"))
	@GetMapping(value = "/balance/{id}")
	public ResponseEntity<WalletDto> getBalanceByPlayerId(@PathVariable Long id) {
		Wallet wallet = walletService.getByPlayerId(id);
		return new ResponseEntity<WalletDto>(modelMapper.map(wallet, WalletDto.class),
				HttpStatus.OK);
	}
}
