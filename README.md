# java와 servlet을 이용한 게시판 작업입니다.

## 개발 환경
#### · JDK-11
#### · IDE : Eclipse IDE
#### · Framework : Java Maven Project
#### · Database : MySQL(8.0.33)
#### · Server : Apache tomcat 9.0.46

## 페이지 구성은 다음과 같습니다.
### 기본 페이지 (index)
### 메인 페이지 (main)
### 로그인 페이지 (login)
### 아이디 / 비밀번호 찾기 (member/findIdPw)
### 회원가입 · 회원수정 페이지 (member/register · edtregister)
### 게시판 리스트 페이지 (boards/...)
#### > 공지사항 (notice)
#### > 정보게시판 (information)
#### > 자유계시판 (free)
#### > Q&A (qanda)
### 게시판 작성 · 수정 페이지 (boards/bwrite · bmodify)
### 게시판 상세 페이지 (boards/bwatch)
### 회원관리 (관리자) 페이지 (memberlist)


## 페이지 별 작업 내용입니다.

### 메인 페이지
#### > 게시판 최신 글을 5개씩 출력
#### > 인기글 상위 6개 출력

### 로그인 페이지
#### > 쿠키를 이용하여 아이디 저장

### 아이디 / 비밀번호 찾기
#### > 정보에 맞는 아이디 · 비밀번호 출력

### 회원가입 · 회원수정 페이지
#### > fetch 를 통한 비동기 처리로 아이디 중복처리
#### > 카카오 우편번호 검색 api를 이용해 주소찾기 처리
#### > 사진 파일 선택

### 게시판 리스트 페이지
#### > 카테고리 별 작성한 글 출력
#### > 공지사항의 경우 관리자만 작성 가능하도록 처리
#### > Q&A 비밀글의 경우 관리자와 해당 글 작성자만 볼 수 있도록 처리
#### > 게시판 페이징 처리

### 게시판 작성 · 수정 페이지
#### > 공지사항 카테고리는 관리자만 선택 가능하도록 처리
#### > Q&A 카테고리의 경우 나만보기(비밀 글) 선택 가능하도록 처리

### 게시판 상세 페이지
#### > 상세페이지 진입 시 조회 여부를 확인하여 조회 수 증가
#### > 게시판 별 내용 출력
#### > 댓글쓰기 · 수정 · 삭제 (로그인 시)
#### > 댓글 리스트 출력
#### > 댓글 작성자의 경우 댓글 수정 · 삭제 출력
#### > 댓글 페이징 처리

### 회원관리 페이지
#### > 회원 목록 전체 출력
#### > 회원 목록 페이징 처리
#### > 회원등급 설정 가능
