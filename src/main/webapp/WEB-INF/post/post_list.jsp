<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>

<html>
<head>
    <meta charset="UTF-8">
    <title>게시글 목록</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
          crossorigin="anonymous">

    <style>
      .wrapper-div {
        height: auto;
        align-items: center;
        padding-bottom: 153px;
        padding-right: 15%;
      }
    </style>
</head>
<body>

<c:set var="pageDesc" value="게시글 목록"/>
<%@include file="../component/header.jspf" %>
<%@include file="../component/navigation.jsp" %>
<main style="display: flex">
    <div style="width: 100%">
        <div>
            <h2 class="m-2">${listDescription}</h2>
        </div>

        <div class="wrapper-div">
            <table class="mx-4 table" style="width: 100%">
                <thead>
                <tr>
                    <th>카테고리</th>
                    <th>글 제목</th>
                    <th>작성자</th>
                    <th>글 작성 시간</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="post" items="${postListItems}">
                    <c:url var="postDetailUrl" value="/post/detail">
                        <c:param name="postId" value="${post.postPk}"/>
                    </c:url>
                    <tr>
                        <td>${post.category}</td>
                        <td><a href="${postDetailUrl}" type="button"
                               class="link-secondary link-offset-2 link-offset-3-hover link-underline link-underline-opacity-0 link-underline-opacity-75-hover">${post.postTitle}</a>
                        </td>
                        <td>${post.author}</td>
                        <td>${post.createdTime}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>


    </div>
    <%@include file="../component/float-test.jsp" %>
</main>

<%@include file="../component/footer.jsp" %>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
<script>
    document.addEventListener("DOMContentLoaded", () => {
      if(location.href.includes("delete=success")) {
        alert("게시글 삭제가 완료되었습니다.")
      }
    })
</script>
</body>
</html>
