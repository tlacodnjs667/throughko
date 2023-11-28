<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>

<div id="float-box"
     class="card-body" style="background: white; width: 15%; height: 30%; float:right; align-items:
center;
position:fixed; right: 0px; top: 400px">
    <c:choose>
        <c:when test="${not empty signedUser}">
            <div style="align-items: center"> <!--회원 정보-->
                <div class="btn form-control"
                     style="align-items: center;border: none;">${signedUser.nickname}
                    님
                    <br/>
                    id :
                    <span style="color: rgb(128,128,128)">${signedUser.userId}</span>
                </div>

                <!-- TODO : 프로필 보기 버튼 추가 -->
                <a href="${profilePage}" class="btn form-control">프로필 보기</a></li>
                <a id="logoutBtn" class="btn form-control" style="color:
                rgb(128,128,128)"
                   href="${signOutPath}">
                    로그아웃</a>
            </div>
            <hr/>
            <!-- TODO : 게시글 관련 -->
            <a href="${createPost}" class="btn form-control">게시글 작성하기</a></li>
            <a href="${postList}" class="btn form-control">게시글 전체 목록</a></li>
            <a href="${postListBest}" class="btn form-control">베스트 게시글</a></li>
        </c:when>
        <c:otherwise>
            <div>
                <h5 class="m-1">
                    WELCOME!
                </h5>
            </div>
            <div>
                <a class="dropdown-item" href="${signInPath}">로그인</a>
            </div>
            <div>
                <a class="dropdown-item" href="${signUpPath}">회원가입</a>
            </div>
        </c:otherwise>
    </c:choose>

</div>