<%@ page pageEncoding="UTF-8" %>

<c:url var="homePath" value="/"/>
<c:url var="signInPath" value="/user/signin">
    <c:param name="target" value="${targetUrl}"/>
</c:url>
<c:url var="postList" value="/post/list"/>
<c:url var="createPost" value="/post/create"/>
<c:url var="profilePage" value="/user/profile"/>
<c:url var="signUpPath" value="/user/signup"/>
<c:url var="signOutPath" value="/user/signout"/>
<c:url var="postListBest" value="/post/list">
    <c:param name="order" value="best"/>
</c:url>


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
                    <a class="nav-link" href="${postListBest}">실시간 베스트 글</a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="${postList}" role="button"
                       data-bs-toggle="dropdown" aria-expanded="false">
                        게시판
                    </a>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="${postList}">전체글 보기</a></li>
                        <c:forEach var="category" items="${categories}">
                            <c:url var="postListByCategory" value="/post/list">
                                <c:param name="category_id"
                                         value="${category.category_pk}"/>
                            </c:url>
                            <li><a class="dropdown-item" href="${postListByCategory}">
                                    ${category.category_title}
                                게시판 보기</a></li>
                        </c:forEach>

                    </ul>
                </li>

                <c:choose>
                    <c:when test="${empty signedUser}">
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="" role="button"
                               data-bs-toggle="dropdown" aria-expanded="false">
                                회원이세요?
                            </a>
                            <ul class="dropdown-menu">
                                <li><a class="dropdown-item" href="${signInPath}">로그인</a></li>
                                <li><a class="dropdown-item" href="">아이디/비밀번호 찾기</a></li>
                                <li><a class="dropdown-item" href="${signUpPath}">회원가입</a></li>
                            </ul>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="" role="button"
                               data-bs-toggle="dropdown" aria-expanded="false">
                                    ${signedUser.nickname} 님

                            </a>
                            <ul class="dropdown-menu">
                                <li><a class="dropdown-item" href="${profilePage}">프로필</a></li>
                                <li><a id="logoutBtn" class="dropdown-item" href="${signOutPath}">
                                    로그아웃</a></li>
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