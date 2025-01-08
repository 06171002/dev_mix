# ⛓️ Devmix
- 개요: IT 사이드 프로젝트를 간편하게 모집할 수 있는 플랫폼을 구축하는 것을 목표로, 대학생/직장인 등 다양한 연령층을 고려하여 원하는 팀원들을 모집하거나 원하는 프로젝트에 지원할 수 있다.

## 주요 기능
- 프로젝트 게시글 등록하여 지원자 모집
  - 모집 지역, 기술 스택, 모집 포지션 등을 선택하여 모집 게시글 등록
- 프로젝트 게시글 댓글 기능
- 각 프로젝트 모집 페이지에서 지원하고자 하는 포지션에 지원하기
- 소셜 로그인 (`Google`, `Kakao`, `Naver`) 으로 서비스 회원 가입
- 알림 기능
  - 프로젝트 모집자에게 해당 프로젝트에 대한 지원 알림
  - 프로젝트 모집자에게 해당 프로젝트 게시글에 대한 댓글 알림
  - 해당 프로젝트에 지원한 지원자에게 참여 확정 여부 알림 (승인, 거절)

## 사용 기술 스택
<img alt="Spring" src ="https://img.shields.io/badge/Spring-6DB33F.svg?&style=for-the-badge&logo=Python&logoColor=white"/> <img alt="Spring Boot" src ="https://img.shields.io/badge/Spring Boot-6DB33F?style=for-the-badge&logo=Spring Boot&logoColor=white"/> <img alt="Spring Security" src ="https://img.shields.io/badge/Spring Security-6DB33F?style=for-the-badge&logo=Spring Security&logoColor=white"/> <img alt="MySQL" src ="https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=MySQL&logoColor=white"/> <img alt="Grafana" src ="https://img.shields.io/badge/Grafana-F46800?style=for-the-badge&logo=Grafana&logoColor=white"/> <img alt="Prometheus" src ="https://img.shields.io/badge/Prometheus-E6522C?style=for-the-badge&logo=Prometheus&logoColor=white"/> <img alt="Gradle" src ="https://img.shields.io/badge/Gradle-02303A?style=for-the-badge&logo=Gradle&logoColor=white"/> <img alt="HTML5" src ="https://img.shields.io/badge/HTML5-E34F26?style=for-the-badge&logo=HTML5&logoColor=white"/> <img alt="CSS3" src ="https://img.shields.io/badge/CSS3-1572B6?style=for-the-badge&logo=CSS3&logoColor=white"/> <img alt="Tailwind CSS" src ="https://img.shields.io/badge/Tailwind CSS-86B6D4?style=for-the-badge&logo=Tailwind CSS&logoColor=white"/> <img alt="JavaScript" src ="https://img.shields.io/badge/JavaScript-F7DF1E?style=for-the-badge&logo=JavaScript&logoColor=white"/> <img alt="Vue.js" src ="https://img.shields.io/badge/Vue.js-4FC08D?style=for-the-badge&logo=Vue.js&logoColor=white"/> <img alt="Swagger" src ="https://img.shields.io/badge/Swagger-85EA2D?style=for-the-badge&logo=Swagger&logoColor=white"/> <img alt="Docker" src ="https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=Docker&logoColor=white"/> <img alt="Amazon S3" src ="https://img.shields.io/badge/Amazon S3-569A31?style=for-the-badge&logo=Amazon S3&logoColor=white"/> <img alt="GitHub" src ="https://img.shields.io/badge/GitHub-181717?style=for-the-badge&logo=GitHub&logoColor=white"/> <img alt="Figma" src ="https://img.shields.io/badge/Figma-F24E1E?style=for-the-badge&logo=Figma&logoColor=white"/>

- **Language**: Java 17
- **DB**: MySQL 8.3.0
- **IDE**: IntelliJ, Visual Studio Code
- **Back-End**
  - Spring Boot 3.3.4
  - Spring Boot Actuator
  - Spring Data JPA 3.3.4
  - Spring Security 6.3.3
  - Spring OAuth2 Client
- **Front-End**
  - HTML/CSS
  - Javascript
  - Vue.js
  - Tailwind CSS
- Swagger 2.2.15
- **Deployment**
  - Docker
  - AWS
- **Monitoring**
  - Prometheus
  - Grafana

## 프로젝트 협업 관리 기술
- Github
- Notion

## 주요 기능 세부

### -로그인 & 회원가입

<img src="https://github.com/user-attachments/assets/ee829773-7cdd-4130-88a9-61997b2bbb5b">

- 소셜 로그인 후 추가 정보 입력 페이지로 이동. 소셜 로그인 시 토큰만 발급된 준회원과 같은 개념. 정보 입력 페이지에서 다른 페이지로 이동하거나 취소 버튼 클릭 시 토큰은 삭제되고 비회원인 상태로 변경되어 다시 소셜 로그인을 해야 한다.
- 닉네임은 한글,영어,숫자로 2~8글자로 입력해야한다. 특수문자나 공백 입력 후 중복확인시 형식 오류라고 안내. 올바른 형식으로 중복확인 후 다시 닉네임을 바꾸고 회원가입 완료 버튼 클릭시 중복확인 요구.
- 추가 정보 입력 후 확인 버튼 클릭 시 회원가입이 완료되고 메인 페이지로 이동. 데이터베이스에 정보들 저장. 


### -프로필 수정
<img src="https://github.com/user-attachments/assets/41e08a23-1a4d-4b58-999c-ba61e8452445">
           
- 마이 페이지에서 프로필 이미지, 기술 스택, 포지션 등 정보 수정 가능.
- 프로필 수정 페이지 이동시 기존 프로필 정보 불러오기.
- 닉네임 변경시 중복확인 요구.
- 완료버튼 클릭시 변경사항 저장 후 마이페이지에서 변경된 사항 확인. 


### -프로젝트 지원
<img src="https://github.com/user-attachments/assets/85f61c22-cc51-4899-a273-ec8f3d966e0b">

- 로그인, 회원가입 완료한 유저는 원하는 프로젝트, 원하는 포지션에 지원가능.
- 하나의 프로젝트에 하나의 지원만 가능하고, 지원한 이력이 있는 프로젝트일 경우 이미 지원하였다는 안내와 함께 지원하지 못하도록 막아놓음.
- 이후 마이페이지 신청관리에서 본인이 신청한 내역 확인가능. 

### -프로젝트 승인
<img src="https://github.com/user-attachments/assets/6fcd7ce5-422e-4a0f-88d1-f2a98b6a9e66">

- 프로젝트 모집 게시글 작성자는 해당 게시글에 신청이 들어오면 알림으로 확인.
- 이후 지원자 확인페이지에서 신청한 유저의 프로필 확인.
- 승인 버튼 클릭시 서버에서는 해당 게시글의 작성자가 맞는지, 해당 게시글에 포지션이 존재하고 모집인원이 꽉 차지 않았는지 등의 검증과정을 거친 후 이상이 없다면 신청 보낸 유저의 모집상태 승인으로 변경 후 해당 게시글의 해당 포지션 현재인원 증가.

### -프로젝트 상세페이지
<img src="https://github.com/user-attachments/assets/838ebee1-b0d1-4c40-af9a-b646d714be79">

- 원하는 포지션 지원 버튼 클릭 시 지원 한마디 내용을 작성하는 모달창이 나오고 예 버튼 클릭 시 지원 완료.
- 이후 마이 페이지에서 나의 지원 현황 확인가능.

<img src="https://github.com/user-attachments/assets/972c3fec-a1f1-4b32-afe3-8a6a330c53dd" width="600" />
<img src="https://github.com/user-attachments/assets/ba639010-9dba-435b-a9d7-965c194a10d1" width="400" />
<img src="https://github.com/user-attachments/assets/da6d9196-b507-4f95-9b33-71dddb5b30ad" width="800" />

- 프로젝트 모집 게시글 작성자는 지원자 확인 페이지에서 지원자들 현황 확인 가능.
- 내용 클릭 시 승인/거절 모달창이 나오고, 승인 시에 해당 게시글 해당 포지션 현재 인원 증가.
- 지원한 유저 마이 페이지에도 지원 상태 승인으로 변경됨.


## ERD
![image](https://github.com/user-attachments/assets/66f82698-c20f-43f1-b7fa-8f5f0e0123b1)

## 프로젝트 담당 기능 구현 및 문제 해결 과정 세부 내용
https://jagged-sodalite-d43.notion.site/Devmix-1586b964de0380e1be09d58e7241fd64














