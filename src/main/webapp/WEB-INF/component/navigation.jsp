<%@ page pageEncoding="UTF-8" %>

<c:set var="homePath" value=""/>
<c:set var="signInPath" value=""/>
<c:set var="signUpPath" value=""/>

<nav class="navbar navbar-expand-lg bg-body-tertiary">
    <div class="container-fluid">
        <a class="navbar-brand" href="${homePath}">Home</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                data-bs-target="navbarNavDropdown" aria-controls="navbarNavDropdown"
                aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNavDropdown">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="">실시간 베스트 글</a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="" role="button"
                       data-bs-toggle="dropdown" aria-expanded="false">
                        게시판
                    </a>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="">전체글 보기</a></li>
                        <li><a class="dropdown-item" href="">카테고리A</a></li>
                        <li><a class="dropdown-item" href="">Another Category</a></li>
                        <li><a class="dropdown-item" href="">the other Category</a></li>
                    </ul>
                </li>

                <c:choose>
                    <c:when test="${empty signedUserNick}">
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="" role="button"
                               data-bs-toggle="dropdown" aria-expanded="false">
                                회원이세요?
                            </a>
                            <ul class="dropdown-menu">
                                <li><a class="dropdown-item" href="">로그인</a></li>
                                <li><a class="dropdown-item" href="">아이디/비밀번호 찾기</a></li>
                                <li><a class="dropdown-item" href="">회원가입</a></li>
                            </ul>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="" role="button"
                               data-bs-toggle="dropdown" aria-expanded="false">
                                    <%--                                    ${signedUserNick} 님--%>
                                ㅇㅇ님
                            </a>
                            <ul class="dropdown-menu">
                                <li><a class="dropdown-item" href="">프로필</a></li>
                                <li><a class="dropdown-item" href="">로그아웃</a></li>
                            </ul>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href=""></a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>
    </div>
</nav>