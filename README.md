# COMMIT
![main](https://github.com/JsolLee/commit/assets/28701071/e3f2c91a-b737-404b-8a1c-cbecb1a7262f)
---
## 1. 프로젝트 소개
### 목적 
- IT 관련 뉴스 및 정보 공유 및 댓글을 통한 소통 서비스 구성
- IT관련 채용정보를 제공하는 형태의 서비스를 구축
- 정보를 모으기 위한 사용자들간의 정보 공유 및 댓글을 통한 소통으로 커뮤니티 구성
### 서비스 대상
- 취업준비생, IT 업계 신입 및 이직을 염두하는 경력자, IT관련 지식을 다른 사람들과 나누고자 하는 사람
### 프로젝트 기간
- 2023.09.12-.2024.01.10
### 프로젝트 멤버
|Name|GitHub Address|
|------|---|
|이진솔|https://github.com/JsolLee|
|김경륜|https://github.com/KKRYOUN|
|김사라|https://github.com/kong980|
|이성길|https://github.com/istjprogrammer|
|이현재|https://github.com/dlguswo1|


## 2. 프로젝트 기능
프로젝트의 주요 기능은 다음과 같습니다.
- 사용자 - 회원가입 및 로그인 및 마이페이지
- 뉴스 - 조회수, 좋아요, 신고, 페이징 및 검색 처리
- 채용 - 조회수, 좋아요, 신고, 페이징 및 검색 처리
- 커뮤니티 - CRUD 기능, 조회수, 좋아요, 신고 페이징 및 검색 처리
- 댓글 - CRUD 기능

## 3. 개발 환경
### FRONTEND

### BACKEND
#### 주요 프레임워크/라이브러리
- Java 17
- SpringBoot 3.2.0
- JPA(Spring Data JPA)
  
#### Build Tool
Gradle 7.2

#### DataBase
MariaDB

## 4. 실행 화면
<details>
  <summary>사용자/마이페이지</summary>
  1. 사용자 로그인 및 회원 가입
  2. 마이페이지
</details>
<details>
  <summary>뉴스</summary>
  1. 뉴스 메인
  2. 뉴스 리스트
  3. 뉴스 상세
</details>
<details>
  <summary>채용</summary>
  1. 채용 메인
  2. 채용 상세
</details>
<details>
  <summary>커뮤니티</summary>
  1. 채용 메인
  2. 채용 상세
</details>
<details>
  <summary>댓글</summary>
  1. 댓글 기능
</details>

## 5. 구조 및 설계
### DB 설계
<details>
  <summary>ERD</summary>
  
  ![erd](https://github.com/JsolLee/commit/assets/28701071/b5ca98ee-fa0b-410c-9790-c3eab40c7f9a)
</details>
<details>
  <summary>테이블</summary>
  
  - Members
    ![members](https://github.com/JsolLee/commit/assets/28701071/ca48bf86-523d-4f38-95e5-1ef48d81246b)
  - LoginHistory
    ![LoginHistory](https://github.com/JsolLee/commit/assets/28701071/29a60a9b-8c6b-4ea5-8a69-df7ad72535b8)
  - News
    ![News](https://github.com/JsolLee/commit/assets/28701071/7899d137-f4f3-4f7e-ba3c-fad880bfe51d)
  - Job
    ![Job](https://github.com/JsolLee/commit/assets/28701071/79724d7b-f8b2-425e-be5e-631c92275df6)
  - Board
    ![Board](https://github.com/JsolLee/commit/assets/28701071/ef80c34d-959c-49dc-af58-51ce375e822f)
  - BoardFile
    ![BoardFile](https://github.com/JsolLee/commit/assets/28701071/097bafba-fbd7-4c91-b52d-2a0e4709084b)
  - Comment
    ![Comment](https://github.com/JsolLee/commit/assets/28701071/ff17dc80-ddae-4290-a2bc-56b5549bc4aa)
  - Like
    ![Like](https://github.com/JsolLee/commit/assets/28701071/ee0a7d87-8d25-479d-9269-663ebb2d70fe)
  - Scarp
    ![scrap](https://github.com/JsolLee/commit/assets/28701071/e7e1c29b-e765-4a7d-a0df-e6c41d08a976)
  - Report
    ![Report1](https://github.com/JsolLee/commit/assets/28701071/8f7e09f3-1ee9-4d27-ba95-4b2dcbd05de2)
   ![Report2](https://github.com/JsolLee/commit/assets/28701071/16f342c4-3bcf-47d1-bd3e-0ffa94f3f53b) 
  
</details>


## 6. 프로젝트를 마치며



