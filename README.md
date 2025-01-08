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

### -로그인
https://github.com/user-attachments/assets/df991d5c-6630-445a-8abb-da1c0d6860a9
<img src="https://github.com/user-attachments/assets/398a6322-2e3c-4851-baa2-78b30f7a2d4e">
<img src="https://github.com/user-attachments/assets/c9e9ca65-ed46-4926-85a6-07a7ad7e62a5" width="600" />
<img src="https://github.com/user-attachments/assets/b3282ae4-095d-4fbb-a5e2-faa769175a51" width="400" />

- 소셜 로그인 후 추가 정보 입력 페이지로 이동. 소셜 로그인 시 토큰만 발급된 준회원과 같은 개념. 정보 입력 페이지에서 다른 페이지로 이동하거나 취소 버튼 클릭 시 토큰은 삭제되고 비회원인 상태로 변경되어 다시 소셜 로그인을 해야 한다. 


### -마이 페이지
<img src="https://github.com/user-attachments/assets/e3c5e36e-fa1c-4f8f-922f-9856dc9fddf1" width="600" />
<img src="https://github.com/user-attachments/assets/1cd2b212-581a-4d04-9667-8f84fbb0514d" width="400" />

![mypage3](https://github.com/user-attachments/assets/8160aaed-239c-4b77-af15-73ce21d79d8f)

- 추가 정보 입력 후 확인 버튼 클릭 시 회원가입이 완료되고 메인 페이지로 이동. 데이터베이스에 정보들 저장.           
- 이후 마이 페이지에서 프로필 이미지, 기술 스택, 포지션 등 정보 수정 가능.


### -프로젝트 모집글 작성
<img src="https://github.com/user-attachments/assets/56302653-478b-4e62-bf47-8711a30f257d" width="600" />
<img src="https://github.com/user-attachments/assets/6a9e8658-2563-440b-9be3-3fb51cd0c304" width="400" />

- 회원가입 완료한 사용자는 글쓰기 버튼을 클릭하여 프로젝트 모집 게시글 작성 가능.
- 모집하는 프로젝트 사용 기술 스택, 지역, 구하는 포지션별로 인원수 설정, 프로젝트 진행 기간, 상세 내용 등 입력 후 등록 버튼 클릭 시 메인 페이지로 이동하고 최신 순으로 게시글이 보인다. 

### -메인 페이지
<img src="https://github.com/user-attachments/assets/38a3f9db-8aea-4b97-a84f-1dcb67e15cca" width="400" />

- 메인 페이지에서 인원 표시된 곳에 마우스 호버 시에 각 포지션별 모집인원 상세가 표시된다.

### -프로젝트 지원, 승인하기
<img src="https://github.com/user-attachments/assets/cfaf54c3-cb96-46d4-80a6-90b4bbc2e4b0" width="600" />
<img src="https://github.com/user-attachments/assets/5b2991a2-bc37-4939-b336-4667045166f4" width="400" />

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














