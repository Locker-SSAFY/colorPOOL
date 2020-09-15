package com.ssafy.socks.advice;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ssafy.socks.advice.exception.CAccessDeniedException;
import com.ssafy.socks.advice.exception.CAuthenticationEntryPointException;
import com.ssafy.socks.advice.exception.CCommunicationException;
import com.ssafy.socks.advice.exception.CEmailSigninFailedException;
import com.ssafy.socks.advice.exception.CUserDuplicatedException;
import com.ssafy.socks.advice.exception.CUserNotFoundException;
import com.ssafy.socks.model.response.CommonResult;
import com.ssafy.socks.service.ResponseService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor @RestControllerAdvice
public class ExceptionAdvice {

	private final ResponseService responseService;
	private final MessageSource messageSource;

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	protected CommonResult defaultException(HttpServletRequest request, Exception e) {
		return responseService.getFailResult(Integer.parseInt(getMessage("unKnown.code")), getMessage("unKnown.msg"));
	}

	@ExceptionHandler(CUserNotFoundException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	protected CommonResult userNotFoundException(HttpServletRequest request, CUserNotFoundException e) {
		return responseService.getFailResult(Integer.parseInt(getMessage("userNotFound.code")), getMessage("userNotFound.msg"));
	}

	@ExceptionHandler(CEmailSigninFailedException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	protected CommonResult emailFailedException(HttpServletRequest request, CEmailSigninFailedException e) {
		return responseService.getFailResult(Integer.parseInt(getMessage("emailSigninFailed.code")), getMessage("emailSigninFailed.msg"));
	}

	@ExceptionHandler(CAuthenticationEntryPointException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	protected CommonResult entryPointException(HttpServletRequest request, CAuthenticationEntryPointException e) {
		return responseService.getFailResult(Integer.parseInt(getMessage("entryPointException.code")), getMessage("entryPointException.msg"));
	}

	@ExceptionHandler(CAccessDeniedException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	protected CommonResult accessDeniedException(HttpServletRequest request, CAccessDeniedException e) {
		return responseService.getFailResult(Integer.parseInt(getMessage("accessDenied.code")), getMessage("accessDenied.msg"));
	}

	@ExceptionHandler(CCommunicationException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	protected CommonResult accessDeniedException(HttpServletRequest request, CCommunicationException e) {
		return responseService.getFailResult(Integer.parseInt(getMessage("communicationError.code")), getMessage("communicationError.msg"));
	}

	@ExceptionHandler(CUserDuplicatedException.class)
	@ResponseStatus(HttpStatus.CONFLICT) // 409
	protected CommonResult userDuplicatedException(HttpServletRequest request, CUserDuplicatedException e) {
		return responseService.getFailResult(Integer.parseInt(getMessage("userDuplicated.code")), getMessage("userDuplicated.msg"));
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	@ResponseStatus(HttpStatus.CONFLICT) // 409
	protected CommonResult userEmailConstraintException(HttpServletRequest request, DataIntegrityViolationException e) {
		return responseService.getFailResult(Integer.parseInt(getMessage("userEmailConstraint.code")), getMessage("userEmailConstraint.msg"));
	}

	// code정보에 해당하는 메시지를 조회합니다.
	private String getMessage(String code) {
		return getMessage(code, null);
	}
	// code정보, 추가 argument로 현재 locale에 맞는 메시지를 조회합니다.
	private String getMessage(String code, Object[] args) {
		return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
	}
}
