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
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">

    <style>
      .wrapper-div {
        height: auto;
        align-items: center;
        padding-bottom: 153px;
        padding-right: 15%;
      }

      .btn-wrapper {
        height: auto;
        width: auto;
        align-items: center;
        margin-right: 40%;
      }

      .heart-btn {
        width: 50px;
        height: 50px;
        margin-right: 20px;
      }
    </style>
</head>
<body>
<c:set var="pageDesc" value="게시글 읽기"/>
<%--TODO : 게시글의 모든 정보 가져오는 컨트롤러 구현--%>
<%@include file="../component/header.jspf" %>
<%@include file="../component/navigation.jsp" %>
<main>
    <div class="wrapper-div">
        <table class="table m-2 text-center">
            <thead>
            <tr>
                <th scope="col"><h4>글 제목</h4></th>
                <td scope="col"><h4>${post.post_title}</h4></td>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th scope="row">카테고리</th>
                <td>${post.category.category_title}</td>
            </tr>
            <tr>
                <th scope="row">작성자</th>
                <td>${post.author.nickname}</td>
            </tr>
            <tr>
                <th scope="row">내용</th>
                <td colspan="2">${post.post_content}</td>
            </tr>
            <tr>
                <th scope="row">생성 일시</th>
                <td colspan="2">${post.created_time_string}</td>
            </tr>
            <tr>
                <th scope="row">수정 일시</th>
                <td colspan="2">${post.modified_time_string}</td>
            </tr>
            </tbody>
        </table>
        <div class="btn-wrapper position-relative m-4 text-end">
            <c:choose>
                <c:when test="${signedUser.userPk eq post.author.id}">
                    <c:url var="postLike" value="/post/like">
                        <c:param name="postId" value="${post.post_pk}"/>
                    </c:url>
                    <a class="heart-btn" href="/post/like">
                        <i class="fas fa-heart">${post.likes}</i>
                    </a>
                    <a class="btn btn-primary">
                        수정하기</a>
                    <a class="btn btn-warning">
                        삭제하기</a>
                </c:when>
            </c:choose>
            <a id="btn-before" class="btn btn-primary">목록가기</a>
            <span class="heart-btn">
                ${post.hits} 명 조회
            </span>
        </div>
    </div>

    <%@include file="../component/float-test.jsp" %>
    <%@include file="../component/footer.jsp" %>
</main>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
<script>
  document.addEventListener("DOMContentLoaded", () => {
    const btnBefore = document.getElementById("btn-before");

    btnBefore.addEventListener("click", () => {
      history.back();
    })
  })
</script>
</body>
</html>
