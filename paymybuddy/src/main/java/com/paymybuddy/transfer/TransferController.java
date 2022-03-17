package com.paymybuddy.transfer;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.paymybuddy.transfer.service.TransferService;
import com.paymybuddy.transfer.transferDTO.TransferRequest;
import com.paymybuddy.transfer.transferDTO.TransferResponseDTO;

@RestController
public class TransferController {

	@Autowired
	private TransferService transferService;

	@GetMapping("/myTransfer")
	@ResponseStatus(code = HttpStatus.OK)
	public Optional<TransferResponseDTO> getTransfer(@RequestBody Integer ID) {
		return transferService.getTransfer(ID);
	}

	@PostMapping("/createTransfer")
	@ResponseStatus(code = HttpStatus.CREATED)
	public void create(@ModelAttribute("newTransfer") @Valid TransferRequest transferRequest,
			HttpServletRequest request, HttpServletResponse response, Model model, Errors errors) throws IOException {
		transferService.createTransfer(transferRequest);
		response.sendRedirect("/transfer");
	}

}
