<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>

<html>
<head>
    <meta charset="UTF-8">
    <title>THROUGHKO-프로필</title>
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
<div>
</div>
<c:set var="pageDesc" value="프로필"/>
<%@include file="../component/header.jspf" %>
<%@include file="../component/navigation.jsp" %>
<main style="margin-bottom: 15%">
    <h2 class="m-2">내 프로필 보기</h2>
    <div class="card m-2 p-3">
        <form class="form" method="post">
            <div class="card-body mb-3">
                <label for="inputUserId" class="form-label">아이디 :</label>
                <input type="text" name="userid" class="form-control" id="inputUserId"
                       aria-describedby="idHelp" value="${userInfo.userId}" readonly/>
            </div>

            <div class="card-body mb-3">
                <label for="inputEmail" class="form-label">이메일 :</label>
                <input type="email" name="email" class="form-control" id="inputEmail"
                       aria-describedby="emailHelp" value="${userInfo.email}" readonly/>

            </div>

            <div class="card-body mb-3">
                <label for="inputNickname" class="form-label">닉네임 :</label>
                <input type="text" name="nickname" class="form-control" id="inputNickname"
                       aria-describedby="nickHelp" value="${userInfo.nickname}" readonly/>

            </div>
            <div class="card-body">
                <button id="submitBtn" type="submit" class="btn btn-primary form-control mb-1">
                    내 정보 수정하기
                </button>
                <a id="btnBack" class="btn btn-warning form-control">
                    돌아가기
                </a>
            </div>
        </form>

    </div>


</main>
<%@include file="../component/footer.jsp" %>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>

<script>
  document.addEventListener("DOMContentLoaded", () => {
    const btnBack = document.getElementById("btnBack");
    btnBack.addEventListener("click", () => {
      location.href = "http://localhost:8080/blog/"
    })
  })
</script>

</body>
</html>
