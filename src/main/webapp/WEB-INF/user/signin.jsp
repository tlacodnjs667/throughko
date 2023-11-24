<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>

<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
          crossorigin="anonymous">

</head>
<body>
<c:set var="pageDesc" value="로그인"/>
<%@include file="../component/header.jspf" %>
<%@include file="../component/navigation.jsp" %>
<main>
<div class="p-3" style="height: 200%">
<form class="form card " method="post">
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

    <input type="hidden" name="target" value="${target}"/>

    <button id="submitBtn" type="submit" class="btn btn-primary mx-3">Submit</button>

    <c:if test="${not empty param.result}">
        <div id="signInException" class="mt-3 mx-3">
            <div class="text-bg-danger mb-3 card" style="max-width: 18rem;">
                <div class="card-header">로그인 실패</div>
                <div class="card-body">
                    <p class="card-text">다시 시도해주세요!</p>
                </div>
            </div>
        </div>
    </c:if>
</form>
</div>

</main>
<%@include file="../component/footer.jsp" %>
<script src="../js/signin.js"></script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>

</body>
</html>
