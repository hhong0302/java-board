<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko-kr">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap" rel="stylesheet">
<link rel="stylesheet" href="../resource/css/bootstrap.css" />
<style>
body
{
	padding-top:30px;
	padding-bottom:20px;
}
.tab-content
{
padding:25px 20px;
}
</style>
<title>ID/PW 찾기</title>
</head>
<body>
 <ul class="nav nav-tabs" id="myTab" role="tablist">
     <li class="nav-item" role="presentation">
         <button class="nav-link active" id="id-tab"
                 data-bs-toggle="tab" data-bs-target="#id" type="button" role="tab"
                 aria-controls="id" aria-selected="true">
            아이디찾기
         </button>
     </li>
     <li class="nav-item" role="presentation">
         <button class="nav-link" id="pwd-tab"
                 data-bs-toggle="tab" data-bs-target="#pwd" type="button" role="tab"
                 aria-controls="pwd" aria-selected="true">
            비밀번호찾기
         </button>
     </li>
 </ul>

<div class="tab-content" id="myTabContent">
      <div class="tab-pane fade show active" id="id" role="tabpanel" aria-labelledby="id-tab">
          <form class="findIdform" action="/board/FindIdPw" method="post">
              <div class="mb-2 px-5">
                   <input type="text" name="username" placeholder="이름" class="form-control" />
              </div>
              <div class="mb-2 px-5">
                   <input type="email" name="useremail" placeholder="이메일" class="form-control" />
              </div>
              <div class="text-center">
                  <button type="submit" class="btn btn-success"> 찾 기 </button>
              </div>
              <input type="hidden" name="opt" value="1" />
          </form>
      </div>
      
      <div class="tab-pane fade show" id="pwd" role="tabpanel" aria-labelledby="pwd-tab">
          <form class="findIdform" action="/board/FindIdPw" method="post">
              <div class="mb-2 px-5">
                   <input type="text" name="userid" placeholder="아이디" class="form-control" />
              </div>
              <div class="mb-2 px-5">
                   <input type="email" name="useremail" placeholder="이메일" class="form-control" />
              </div>
              <div class="text-center">
                  <button type="submit" class="btn btn-success"> 찾 기 </button>
              </div>
              <input type="hidden" name="opt" value="2" />
          </form>
      </div>
 </div>


<footer>
	<a href="index.jsp">Copyright &copy; hong all right reserved.</a>
</footer>
    
<script src="../resource/js/popper.min.js"></script>
<script src="../resource/js/bootstrap.js"></script>
<script src="../resource/js/script.js"></script>
</body>
</html>