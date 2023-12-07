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

<main class="my-4">


    <p class="d-inline-flex gap-1">
        <a class="btn btn-primary" data-bs-toggle="collapse" href="#multiCollapseExample1"
           role="button" aria-expanded="false" aria-controls="multiCollapseExample1">아이디 찾기</a>
        <button class="btn btn-primary" type="button" data-bs-toggle="collapse"
                data-bs-target="#multiCollapseExample2" aria-expanded="false"
                aria-controls="multiCollapseExample2">비밀번호 재설정
        </button>
    </p>
    <div class="row card ">
        <div class="form-control card-body">
            <div class="collapse multi-collapse" id="multiCollapseExample1">
                <div>
                    <form>
                        <h4>아이디 찾기</h4>
                        <input id="emailInputForId" class="form-control my-2"
                               placeholder="이메일 입력" name="email"/>
                        <input type="hidden" id="typeForId" value="1"/>
                        <input type="submit" id="findIdBtn" class="form-control my-2 btn-primary"
                               value="아이디 찾기"/>
                    </form>
                </div>
            </div>
        </div>
        <div class="form-control card-body">
            <div class="collapse multi-collapse" id="multiCollapseExample2">
                <div>
                    <form>
                        <h4>비밀번호 찾기</h4>
                        <input id="emailInputForPw" class="form-control my-2" name="email"
                               placeholder="이메일 입력"/>
                        <input id="userIdInputForPw" class="form-control my-2" name="userId"
                               placeholder="아이디 입력"/>
                        <input type="hidden" id="typeForPw" value="2"/>
                        <input type="submit" id="findPwBtn" class="form-control my-2 btn-primary"
                               value="비밀번호 재설정하기"/>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <!-- 댓글 업데이트 모달(다이얼로그) -->
    <div id="commentModal" class="modal" tabindex="-1">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Modal title</h5>
                    <button type="button" class="btn-close"
                            data-bs-dismiss="modal"
                            aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <!-- 수정할 댓글 아이디 -->
                    <input class="d-none" id="modalCommentId"/>
                    <!-- 댓글 입력 -->
                    <textarea class="form-control" id="modalCommnentText"></textarea>
                </div>
                <div class="modal-footer">
                    <button type="button"
                            class="btn btn-secondary"
                            data-bs-dismiss="modal">취소
                    </button>
                    <button type="button"
                            class="btn btn-primary">변경 내용 저장
                    </button>
                </div>
            </div>
        </div>
    </div> <!-- end modal -->

</main>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<script src="../js/find_account.js">

</script>
</body>
</html>
