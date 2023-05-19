<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

	<%@ include file="../include/header.jsp" %>
	
    <section>
        <div class="container">
            <div class="row">
                <div class="col-lg-6 col-md-7 col-xs-10 login-form">
                    <div class="titlebox">
                        로그인
                    </div>
                    <form name="loginForm" method="post">
                        <div class="form-group"><!--사용자클래스선언-->
                            <label for="id">아이디</label>
                            <input type="text" class="form-control" name="userId" id="id" placeholder="아이디">
                         </div>
                         <div class="form-group"><!--사용자클래스선언-->
                            <label for="id">비밀번호</label>
                            <input type="password" class="form-control" name="userPw" id="pw" placeholder="비밀번호">
                         </div>
                         <div class="form-group">
                            <button type="button" id="loginBtn" class="btn btn-info btn-block">로그인</button>
                            <button type="button" id="joinBtn" class="btn btn-primary btn-block">회원가입</button>
                         </div>
                    </form>                
                </div>
            </div>
        </div>
    </section>

    <%@ include file="../include/footer.jsp" %>
    
    
    
    <script>
    
	    //회원 가입 완료 후 addFlashAttribute로 msg 데이터가 전달 되는지 확인
	    const msg = '${msg}'	
	    if(msg === 'joinSuccess'){
	    	alert('회원 가입이 정상 처리되었습니다.')
	    } else if(msg === 'loginFail'){
	    	alert('로그인에 실패했습니다. 아이디와 비밀번호를 확인하세요.')	
	    }
	    
	    
	    //id, pw 입력란이 공백인지 아닌지 확인한 후, 공백이 아니라면 submit을 진행하세요.
	    //요청 url은 /user/userLogin -> post로 간다. (비동기X)
	    
        document.getElementById('loginBtn').onclick = function() {
            if(document.getElementById('id').value === '' || document.getElementById('pw').value === ''){
                alert('아이디와 비밀번호를 입력해주세요.')
                return;
            } else{
                document.loginForm.submit();
            }
        }
	    
        document.getElementById('joinBtn').onclick = () =>{
            location.href='${pageContext.request.contextPath}/user/join';
        }
	    
	    
    
    </script>