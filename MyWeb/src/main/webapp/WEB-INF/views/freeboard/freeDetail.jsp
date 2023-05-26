﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    
    <%@ include file = "../include/header.jsp" %>

    <section>
        <div class="container">
            <div class="row">
                <div class="col-xs-12 col-md-9 write-wrap">
                        <div class="titlebox">
                            <p>상세보기</p>
                        </div>
                        
                        <form action="<c:url value='/freeboard/modify' />" method="post"/>
                            <div>
                                <label>DATE</label>
                                <c:if test="${article.updateDate == null}">
                                	<p>
                                		<fmt:parseDate value="${article.regDate}" pattern="yyyy-MM-dd'T'HH:mm:ss" var="parsedDateTime" type="both"/>
	                                	<fmt:formatDate value="${parsedDateTime}" pattern="yyyy년 MM월 dd일  HH시 mm분"/>
                                	</p>
                                </c:if>
                                <c:if test="${article.updateDate != null}">
                                	<p>
                                		<fmt:parseDate value="${article.updateDate}" pattern="yyyy-MM-dd'T'HH:mm:ss" var="parsedDateTime" type="both"/>
	                                	<fmt:formatDate value="${parsedDateTime}" pattern="yyyy년 MM월 dd일  HH시 mm분"/>
                                	</p>
                                </c:if>
                            </div>   
                            <div class="form-group">
                                <label>번호</label>
                                <input class="form-control" name="bno" value="${article.bno}" readonly>
                            </div>
                            <div class="form-group">
                                <label>작성자</label>
                                <input class="form-control" name="writer" value="${article.writer}" readonly>
                            </div>    
                            <div class="form-group">
                                <label>제목</label>
                                <input class="form-control" name="title" value="${article.title}" readonly>
                            </div>

                            <div class="form-group">
                                <label>내용</label>
                                <textarea class="form-control" rows="10" name='content' readonly>${article.content}</textarea>
                            </div>

                            <button type="submit" class="btn btn-primary" onclick="return confirm('변경 페이지로 이동합니다.')">변경</button>
                            <button type="button" class="btn btn-dark" onclick=
                                "location.href='${pageContext.request.contextPath}/freeboard/freeList?pageNum=${p.pageNum}&cpp=${p.cpp}&keyword=${p.keyword}&condition=${p.condition}'">목록</button>
                        </form>
                </div>
            </div>
        </div>
        </section>
        
        
        <!-- 댓글 영역 시작 부분 -->
        <section style="margin-top: 80px;">
            <div class="container">
                <div class="row">
                    <div class="col-xs-12 col-md-9 write-wrap">
                        <form class="reply-wrap">
                            <div class="reply-image">
                                <img src="${pageContext.request.contextPath}/img/profile.png">
                            </div>
                            <!--form-control은 부트스트랩의 클래스입니다-->
	                    <div class="reply-content">
	                        <textarea class="form-control" rows="3" id="reply"></textarea>
	                        <div class="reply-group">
	                              <div class="reply-input">
	                              <input type="text" class="form-control" id="replyId" placeholder="이름">
	                              <input type="password" class="form-control" id="replyPw" placeholder="비밀번호">
	                              </div>
	                              
	                              <button type="button" id="replyRegist" class="right btn btn-info">등록하기</button>
	                        </div>
	
	                    </div>
                        </form>

                        <!--여기에 접근 반복-->
                        <div id="replyList">

                            <!-- 자바스크립트 단에서 반복문을 이용해서 댓글의 개수만큼 반복 표현 -->
                            <!-- <div class='reply-wrap'>
                                <div class='reply-image'>
                                    <img src='${pageContext.request.contextPath}/img/profile.png'>
                                </div>
                                <div class='reply-content'>
                                    <div class='reply-group'>
                                        <strong class='left'>honggildong</strong> 
                                        <small class='left'>2019/12/10</small>
                                        <a href='#' class='right'><span class='glyphicon glyphicon-pencil'></span>수정</a>
                                        <a href='#' class='right'><span class='glyphicon glyphicon-remove'></span>삭제</a>
                                    </div>
                                    <p class='clearfix'>여기는 댓글영역</p>
                                </div>
                            </div> -->

                        </div>
                        <button type="button" class="form-control" id="moreList" style="display: none;" >더보기(페이징)</button>
                    </div>
                </div>
            </div>
        </section>
        
	<!-- 모달 -->
	<div class="modal fade" id="replyModal" role="dialog">
		<div class="modal-dialog modal-md">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="btn btn-default pull-right" data-dismiss="modal">닫기</button>
					<h4 class="modal-title">댓글수정</h4>
				</div>
				<div class="modal-body">
					<!-- 수정폼 id값을 확인하세요-->
					<div class="reply-content">
					<textarea class="form-control" rows="4" id="modalReply" placeholder="내용입력"></textarea>
					<div class="reply-group">
						<div class="reply-input">
						    <input type="hidden" id="modalRno">
							<input type="password" class="form-control" placeholder="비밀번호" id="modalPw">
						</div>
						<button class="right btn btn-info" id="modalModBtn">수정하기</button>
						<button class="right btn btn-info" id="modalDelBtn">삭제하기</button>
                        
					</div>
					</div>
					<!-- 수정폼 끝 -->
				</div>
			</div>
		</div>
	</div>
	
	<%@ include file = "../include/footer.jsp" %>

    <script>

        window.onload = () => {
            
            document.getElementById('replyRegist').onclick = function() {

                console.log('댓글 등록 이벤트 발생!');

                const bno = '${article.bno}'; //현재 게시글 번호
                const reply = document.getElementById('reply').value;
                const replyId = document.getElementById('replyId').value;
                const replyPw = document.getElementById('replyPw').value;

                if(reply === '' || replyId === '' || replyPw ===''){
                    alert('이름과 비밀번호, 내용은 빈 칸일 수 없습니다.')
                    return;
                }

                
                //요청에 관련된 정보 객체
                const reqObj = {
                    method: 'post',
                    headers: { 'Content-type':'application/json' },

                    body: JSON.stringify({
                        'bno' : bno,
                        'reply' : reply,
                        'replyId' : replyId,
                        'replyPw' : replyPw
                    })
                };

                fetch('${pageContext.request.contextPath}/reply/regist', reqObj)
                    .then(res => res.text())
                    .then(data => {
                        console.log('통신 성공!: '+data);
                        document.getElementById('reply').value = ''; //텍스트 에어리어를 비워주기
                        document.getElementById('replyId').value = '';
                        document.getElementById('replyPw').value = '' ;
                        //등록 완료 후 댓글 목록 함수를 호출해서 비동기식으로 목록 표현.
                        getList(1, true); //댓글이 달린 
                    });
                
            }; //댓글 등록 이벤트 끝
                

            //더보기 버튼 처리(클릭 시 전역 변수 page 값을 +1해서 서버로 요청)
            document.getElementById('moreList').onclick = () => {
                /*
                Q. false를 주는 이유
                A. 더보기는 댓글을 누적해서 보여줘야 한다.
                1페이지의 댓글 내용 밑에 2페이지를 누적해서 깔아야 한다. 
                (1페이지 내용을 없애면 안된다.)
                */
                getList(++page, false);

            }


            let page = 1; //전역 의미로 사용할 페이지 번호
            let strAdd = ''; //화면에 그려넣을 태그를 문자열의 형태로 추가할 변수
            getList(1, true); // 게시글 상세보기 화면에 처음 진입했을 시 댓글 리스트를 한 번 불러온다.
            const $replyList = document.getElementById('replyList');


            //댓글 목록 가져올 함수
            //getList의 매개값으로 무엇을 줄 것인지?
            //요청된 페이지 번호와, 화면을 리셋할 것인지의 여부를 boolean 타입의 reset으로 받는다.
            //(페이지가 그대로 머물면서 댓글이 밑에 계속 쌓이기 때문에
            //상황에 따라 페이지를 리셋할 것인지, 누적해서 쌓을 것인지의 여부를 판단한다.)
            function getList(pageNum, reset){ 
                strAdd = '';
                const bno = '${article.bno}'; //게시글 번호

                //get방식으로 댓글 목록을 요청(비동기)
                fetch('${pageContext.request.contextPath}/reply/getList/' + bno + '/' + pageNum)
                    .then(res => res.json())
                    .then(data => {
                        console.log(data);
                            
                        let total = data.total; //총 댓글 수
                        let replyList = data.list; //댓글 리스트

                        //insert, update, delete 작업 후에는
                        //댓글 내용 태그를 누적하고 있는 strAdd 변수를 초기화해서
                        //마치 화면이 리셋된 것처럼 보여줘야 한다.
                        if(reset) {
                            while($replyList.firstChild){ //자식이 있느냐 없으냐
                                $replyList.firstChild.remove();
                            }
                            page = 1;
                        }

                        console.log('getList 테스트');
                        //응답 데이터의 길이(댓글 수)가 0과 같거나 더 작으면 함수를 종료. 
                        if(replyList.length <= 0 ) return;
                        
                        //더보기 버튼 삭제
                        //페이지번호x이번 요청으로 받은 댓글 수보다 전체 댓글 개수가 작다면 더보기 버튼을 없앤다.
                        console.log('현재 페이지: '+page);
                        if(total <= page*5){
                            document.getElementById('moreList').style.display = 'none';
                        } else { document.getElementById('moreList').style.display = 'block'; }


                        //replyList의 개수만큼 태그를 문자열 형태로 직접 작성
                        //중간에 들어갈 글쓴이, 날짜, 댓글 내용은 목록에서 꺼내서 표현.
                        console.log(replyList[0].replyDate);
                        // for(let i = 0; i < replyList.length; i++) {
                        for (let i = 0; i < replyList.length; i++) {
                            strAdd +=
                            `<div class='reply-wrap'>
                                <div class='reply-image'>
                                    <img src='${pageContext.request.contextPath}/img/profile.png'>
                                </div>
                                <div class='reply-content'>
                                    <div class='reply-group'>
                                        <strong class='left'>` + replyList[i].replyId + `</strong>
                                        <small class='left'>` + (replyList[i].updateDate !== null ? parseTime(replyList[i].updateDate) + ' (수정됨)' : parseTime(replyList[i].replyDate)) + `</small>
                                        <a href='` + replyList[i].rno + `' class='right replyDelete'><span class='glyphicon glyphicon-remove'></span> 삭제</a>
                                        <a href='` + replyList[i].rno + `' class='right replyModify'><span class='glyphicon glyphicon-pencil'></span> 수정&nbsp;&nbsp;</a>
                                    </div>
                                    <p class='clearfix'>` + replyList[i].reply + `</p>
                                </div>
                            </div>`;
                        }

                        //id가 replyList라는 div 영역에 문자열 형식으로 모든 댓글을 추가.
                        if(!reset){
                            document.getElementById('replyList').insertAdjacentHTML('beforeend', strAdd);
                        }else{
                            document.getElementById('replyList').insertAdjacentHTML('afterbegin', strAdd);
                        }
                            
                    });
            }

            
            //수정, 삭제
            /*
            document.querySelector('.replyModify').addEventListener('click', function(e) {
                e.preventDefault();
                console.log('수정 버튼 클릭 이벤트 발생!');
            }) (이거 동작 안함!!!) 

            .replyModify 요소는 실제 존재하는 요소가 아니라
            비동기 통신을 통해 생성되는 요소이다. (위에서 반복문을 통해 생산)
            그러다 보니 이벤트가 등록되는 시점보다 fetch 함수의 실행이 먼저 끝날 것이라는
            보장이 없기 때문에 해당 방식으로 이벤트 등록이 불가능하다!
            (이벤트 등록 이후 document 생성하기 때문에 null이 뜸.)
            이 때는 이미 실제로 존재하는 #replyList에 등록하고, 이벤트를 자식에게 위임하여
            사용하는 addEventListener을 통해 처리해야한다.
            */

            document.getElementById('replyList').onclick = (e) => {
                e.preventDefault(); //태그의 고유 기능을 중지. (a 이동 막기)

                //1. 이벤트가 발생한 target이 a태그가 아니라면 이벤트 종료.
                    if(!e.target.matches('a')) return;  

                //2. a태그가 두 개(수정, 삭제)이므로 어떤 링크인지를 확인
                //댓글이 여러 개 -> 수정, 삭제가 발생하는 댓글이 몇 번인지도 확인이 필요함.
                    const rno = e.target.getAttribute('href');
                    console.log('댓글 번호: '+rno);
                    //모달 내부에 숨겨진 input 태그에 댓글 번호를 달아준다.
                    document.getElementById('modalRno').value = rno;
                    
                    const content = e.target.parentNode.nextElementSibling.textContent;
                    console.log('댓글 내용: '+content);


				
                //3. 모달 창 하나를 이용해서 상황에 따라 수정 / 삭제 모달을 구분하기 위해
                //조건문을 작성. (※ 모달 하나로 수정, 삭제를 같이 처리. -> 디자인 조정 필요)
                    if(e.target.classList.contains('replyModify')){ 
                        //수정 버튼을 눌렀으므로 수정 모달 형식을 꾸며주겠다 
                        document.querySelector('.modal-title').textContent = '댓글 수정';
                        document.getElementById('modalReply').style.display = 'inline'; //댓글창
                        document.getElementById('modalReply').value = content; //댓글 내용
                        document.getElementById('modalModBtn').style.display = 'inline';
                        document.getElementById('modalDelBtn').style.display = 'none';

                        //제이쿼리를 이용해서 bootstrap 모달을 여는 방법
                        $('#replyModal').modal('show'); 

                    } else{
                        //삭제 버튼을 눌렀으므로 삭제 모달 형식을 꾸며주겠다.
                        document.querySelector('.modal-title').textContent = '댓글 삭제';
                        document.getElementById('modalReply').style.display = 'none'; //댓글창
                        document.getElementById('modalModBtn').style.display = 'none';
                        document.getElementById('modalDelBtn').style.display = 'inline';

                        
                        $('#replyModal').modal('show');

                    } //수정&삭제 
                
                    //수정 처리 함수 (수정 모달을 열어서 수정 내용을 작성 후 수정 버튼을 클릭했을 때)
                    document.getElementById('modalModBtn').onclick = () => {
                        const reply = document.getElementById('modalReply').value;
                        const rno = document.getElementById('modalRno').value;
                        const replyPw = document.getElementById('modalPw').value;

                        if(reply === '' || replyPw === ''){
                            alert('내용과 비밀번호를 확인하세요!');
                            return;
                        }


                        //요청에 관련된 정보 객체
                        const reqObj = {
                            method: 'put',
                            headers: {
                                'Content-Type': 'application/json'
                            },
                            body: JSON.stringify({
                                'reply' : reply,
                                'replyPw' : replyPw
                            })
                        };

                        fetch('${pageContext.request.contextPath}/reply/'+rno, reqObj)
                            .then(res => res.text())
                            .then(data => {
                                console.log('업데이트 결과 data: '+data);
                                if(data === 'pwFail'){
                                    alert('비밀번호를 확인해주세요.');
                                    document.getElementById('modalPw').value = ''; //입력한 비번 삭제
                                    document.getElementById('modalPw').focus();
                                } else {
                                    alert('정상 수정 되었습니다.');
                                    document.getElementById('modalReply').value = ''; //지워주기
                                    document.getElementById('modalPw').value = '';
                                    //제이쿼리 문법으로 bootstrap 모달 닫기 
                                    $('#replyModal').modal('hide'); // hide로 닫기

                                    getList(1, true); //page 1으로 재 렌더링(reset 값을 참)을 해준다.
                                }
                            })

                    } //end update event


                    //삭제 이벤트
                    document.getElementById('modalDelBtn').onclick = () => {
                        /*
                        1. 모달창에 rno값, replyPw 값을 얻습니다.
                        2. fetch 함수를 이용해서 DELETE 방식으로 reply/{rno} 요청
                        3. 서버에서는 요청을 받아서 비밀번호를 확인하고, 비밀번호가
                        맞으면 삭제를 진행하면 됩니다.
                        4. 만약 비밀번호가 틀렸다면, 문자열을 반환해서
                         '비밀번호가 틀렸습니다.' 경고창을 띄우세요.

                         삭제 완료되면 모달 닫고 목록 요청 다시 보내세요. (reset의 여부를 잘 판단하기.)

                        */
                        const rno = document.getElementById('modalRno').value;
                        const replyPw = document.getElementById('modalPw').value;

                        if(replyPw === ''){
                            alert('비밀번호를 입력해주세요.')
                            return;
                        }

                        const Obj = {
                            method : 'delete',
                            headers : {
                                'Content-Type' : 'application/json'
                            },
                            body : JSON.stringify({'replyPw' : replyPw})
                        }

                        fetch('${pageContext.request.contextPath}/reply/'+rno, Obj)
                            .then(res => res.text())
                            .then(data => {
                                if(data === 'deleteFail'){
                                    alert('비밀번호를 다시 확인해주세요.');
                                    document.getElementById('modalPw').value = '';
                                    document.getElementById('modalPw').focus();
                                    return;
                                } else{
                                    alert('정상적으로 삭제되었습니다.')
                                    document.getElementById('modalPw').value = '';
                                    $('#replyModal').modal('hide');

                                    getList(1, true);
                                }

                            })// fetch, then 종료

                    } //삭제 이벤트 종료 

                    
                } //이벤트종료
                
                //댓글 날짜 변환 함수 
                function parseTime(regDateTime) {
                    console.log('parseTime 들어옴!!');

                    let year, month, day, hour, minute, second;

                    if(regDateTime.length === 5 ){
                        [year, month, day, hour, minute] = regDateTime;
                        second = 0;
                    } else {
                        [year, month, day, hour, minute, second] = regDateTime;
                    }

                    //원하는 날짜로 객체를 생성
                    const regTime = new Date(year, month-1, day, hour, minute, second);
                                                    //자바스크립트는 월을 0부터 센다.
                    console.log(regTime);
                    const date = new Date; //지금 현재 시간
                    console.log(date);
                    const gap = date.getTime() - regTime.getTime(); //밀리초로 빼줌.

                    let time;
                        if (gap < 60 * 60 * 24 * 1000) { // 하루
                            if (gap < 60 * 60 * 1000) { // 1시간
                                time = '방금 전';
                            } else {
                                time = parseInt(gap / (1000 * 60 * 60)) + '시간 전';
                            }
                        } else if (gap < 60 * 60 * 24 * 30 * 1000) { // 한 달
                            time = parseInt(gap / (1000 * 60 * 60 * 24)) + '일 전';
                        } else {
                            time = `${regTime.getFullYear()}년 ${regTime.getMonth()}월 ${regTime.getDate()}일`;
                        }

                    return time;
                } //날짜변환 함수 종료



        
        } // window.onload






    </script>
