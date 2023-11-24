<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>

<html>
<head>
    <meta charset="UTF-8">
    <title>THROUGHKO</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
          crossorigin="anonymous">
    <style>
      main {
        height: 80%;
      }
    </style>
</head>
<body>
<c:set var="pageDesc" value="회원가입"/>
<%@include file="../component/header.jspf" %>
<%@include file="../component/navigation.jsp" %>
<main>
    <form class="form card" method="post">
        <div class="card-body mb-3">
            <label for="inputUserId" class="form-label">아이디 :</label>
            <input type="text" name="userid" class="form-control" id="inputUserId"
                   aria-describedby="idHelp" required autofocus/>
            <div id="userIdDesc" class="form-text">
                아이디는 6글자 이상이어야 합니다.
            </div>
        </div>

        <div class="card-body mb-3">
            <label for="inputPassword" class="form-label">비밀번호 :</label>
            <input type="password" name="password" class="form-control" id="inputPassword"
                   aria-describedby="passwordHelp" required/>
            <div id="pwDesc" class="form-text">
                비밀번호는 8글자 이상이어야 합니다.
            </div>
        </div>

        <div class="card-body mb-3">
            <label for="inputEmail" class="form-label">이메일 :</label>
            <input type="email" name="email" class="form-control" id="inputEmail"
                   aria-describedby="emailHelp" required />
            <div id="emailDesc" class="form-text" />
            아이디, 비밀번호 분실 시 사용할 이메일입니다.
        </div>
        </div>

        <div class="card-body mb-3">
            <label for="inputNickname" class="form-label">닉네임 :</label>
            <input type="text" name="nickname" class="form-control" id="inputNickname"
                   aria-describedby="nickHelp" required/>
            <div id="nickDesc" class="form-text">
                닉네임은 10글자 이내로 설정해주세요.
            </div>
        </div>

        <button id="submitBtn" type="submit" class="btn btn-primary">Submit</button>
    </form>

</main>
<%@include file="../component/footer.jsp" %>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>

<script>
  document.addEventListener("DOMContentLoaded", () => {
    const submitBtn = document.querySelector("#submitBtn");
    const inputUserId = document.querySelector("#inputUserId");
    const inputPassword = document.querySelector("#inputPassword");
    const inputNickname = document.querySelector("#inputNickname");
    const inputEmail = document.querySelector("#inputEmail");

    submitBtn.disabled = true;

    let isIdInputValid = false;
    let isPasswordInputValid = false;
    let isNicknameInputValid = false;
    let isEmailInputValid = false;

    inputUserId.addEventListener("keyup", () => {
      isIdInputValid = inputUserId.value.length >= 6;
      onKeyUpToCheck();
    })
    inputPassword.addEventListener("keyup", () => {
      isPasswordInputValid = inputPassword.value.length >= 8;
      onKeyUpToCheck();
    })
    inputEmail.addEventListener("keyup", () => {
      isEmailInputValid = inputEmail.value.length >= 5;
      onKeyUpToCheck();
    })

    inputNickname.addEventListener("keyup", e => {
      isNicknameInputValid = inputNickname.value.length >= 3 && inputNickname.value.length <= 10;
      onKeyUpToCheck();
    })

    function onKeyUpToCheck() {
      console.log(isIdInputValid);
      console.log(isPasswordInputValid);
      console.log(isEmailInputValid);
      console.log(isNicknameInputValid);
      if (isIdInputValid && isPasswordInputValid && isEmailInputValid && isNicknameInputValid) {
        submitBtn.disabled = false;
      }
    }
  })
</script>
</body>
</html>
