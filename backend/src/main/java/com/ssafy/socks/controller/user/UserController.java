package com.ssafy.socks.controller.user;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.socks.entity.user.User;
import com.ssafy.socks.model.response.CommonResult;
import com.ssafy.socks.model.response.ListResult;
import com.ssafy.socks.model.response.SingleResult;
import com.ssafy.socks.service.ResponseService;
import com.ssafy.socks.service.user.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;

@Api(tags = {"2. User"})
@RequestMapping(value = "/api")
@RequiredArgsConstructor
@RestController
@CrossOrigin
public class UserController {

	private final UserService userService;
	private final ResponseService responseService; // 결과를 처리할 Service

	@ApiImplicitParams({
		@ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
	})
	@ApiOperation(value = "회원 리스트 조회", notes = "모든 회원을 조회한다")
	@GetMapping(value = "/users")
	public ListResult<User> findAllUser() {
		// 결과데이터가 여러건인경우 getListResult를 이용해서 결과를 출력한다.
		return responseService.getListResult(userService.findUsers());
	}

	@ApiImplicitParams({
		@ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = false, dataType = "String", paramType = "header")
	})
	@ApiOperation(value = "회원 단건 조회", notes = "회원번호(pk)로 회원을 조회한다")
	@GetMapping(value = "/user")
	public SingleResult<User> findUserById(@ApiParam(value = "언어", defaultValue = "ko") @RequestParam String lang) {
		// SecurityContext에서 인증받은 회원의 정보를 얻어온다.
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String id = authentication.getName();
		// 결과데이터가 단일건인경우 getSingleResult를 이용해서 결과를 출력한다.
		return responseService.getSingleResult(userService.findByEmail(id));
	}

	@ApiImplicitParams({
		@ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
	})
	@ApiOperation(value = "회원 삭제", notes = "userId로 회원정보를 삭제한다")
	@DeleteMapping(value = "/user/{id}")
	public CommonResult delete(
		@ApiParam(value = "회원번호", required = true) @PathVariable Long id) {
		userService.deleteById(id);
		// 성공 결과 정보만 필요한경우 getSuccessResult()를 이용하여 결과를 출력한다.
		return responseService.getSuccessResult();
	}
}