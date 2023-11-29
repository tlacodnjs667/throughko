<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>

<html>
<head>
    <meta charset="UTF-8">
    <title>throughko-게시글 수정</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
          crossorigin="anonymous">

</head>
<body>
<c:set var="pageDesc" value="게시글 수정"/>
<%@include file="../component/header.jspf" %>
<%@include file="../component/navigation.jsp" %>

<main class="m-3">
    <div>
        <c:url var="postUpdateUrl" value="/post/update"/>
        <form action="${postUpdateUrl}" method="post">
            <div class="mb-3">
                <label for="category-id" class="form-label my-2">카테고리</label>
                <select id="category-id" class="form-select" name="category"
                        aria-label="Default select example">
                    <c:forEach var="category" items="${categories}">
                        <c:choose>
                            <c:when test="${post.category.category_pk eq category.category_pk}">
                                <option value="${category.category_pk}" selected
                                >${category.category_title}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${category.category_pk}" disabled
                                >${category.category_title}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
            </div>

            <%--제목 input--%>
            <div class="mb-3">
                <label for="titleInput" class="form-label">제목</label>
                <input type="text" class="form-control" id="titleInput" name="title"
                       placeholder="수정할 제목 입력" value="${post.post_title}" required/>
            </div>

            <div class="mb-3 d-none">
                <input type="hidden" class="form-control" id="idInput" name="postId"
                       value="${post.post_pk}" required/>
            </div>

            <%--내용 input--%>
            <div class="mb-3">
                <label for="contentTextArea" class="form-label">내용</label>
                <textarea class="form-control" id="contentTextArea" rows="10" name="content"
                          placeholder="내용을 입력해주세요."
                          required>${post.post_content}</textarea>
            </div>

            <%--사진 input--%>
            <div class="input-group mb-3">
                <label class="input-group-text" for="photoInput">Upload</label>
                <input type="file" class="form-control" id="photoInput" name="photo"/>
            </div>

            <%--제출 버튼--%>
            <button type="submit" id="btnUpdate" class="btn btn-primary form-control">수정하기</button>
        </form>
        <div class="my-2"><a id="btnBack" class="btn btn-warning form-control">게시글로 돌아가기</a></div>


    </div>
</main>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
<script>
  document.addEventListener("DOMContentLoaded", () => {
    const url = location.href;
    console.log(url)
    if (url.includes("fail")) {
      alert("게시글 수정에 실패하였습니다! 다시 작성해주세요.");
    }

    const btnUpdate = document.getElementById("btnUpdate");

    btnUpdate.addEventListener("click", (e) => {
      const check = confirm("해당 글을 수정하시겠습니까?");
      if (!check) {
        alert("수정을 취소합니다.");
        e.preventDefault();
      }
    })

    const btnBack = document.getElementById("btnBack");

    btnBack.addEventListener("click", () => {
      history.back();
    })
  })
</script>
</body>
</html>
