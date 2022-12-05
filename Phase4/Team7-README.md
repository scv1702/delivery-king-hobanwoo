# 배달왕 호반우 Phase4

## 개발 환경

### 백엔드

- 언어: TypeScript 4.9.3
- 프레임워크: Express 4.18.2
- 데이터베이스: Oracle Database 11g Express Edition Release 11.2.0

### 프론트

- 프레임워크: React 17.0.2

## 실행 방법

1. `https://docs.docker.com/get-docker/`를 참고해 Docker 설치

2. DockerHub에서 이미지 다운로드

```
docker pull scv1702/delivery_king_hobanwoo
```

3. Docker 컨테이너 실행 및 접속

```
docker run --name ${CONTAINR_NAME} -d -p 9080:8080 -p 1600:1521 -p 15000:3000 -p 15010:3010 -d scv1702/delivery_king_hobanwoo
docker exec –it ${CONTAINR_NAME} /bin/bash
```

4. 리액트 서버 실행

```
cd /delivery-king-hobanwoo/Phase4/front
npm start
```

5. Express 서버 실행

```
cd /delivery-king-hobanwoo/Phase4/server
npm run dev
```

6. `http://localhost:15000` 접속

## 기능 설명

1. `/`: 홈페이지
 - 음식 카테고리 선택시 해당 카테고리별 가게 목록 조회

2. `/signup`: 회원 가입
 - 아이디, 비밀번호, 학과명, 전화 번호로 회원 가입

3. `/login`: 로그인
 - 아이디, 비밀번호로 로그인

4. `/stores`: 가게 목록 조회
 - 전체 가게 목록 조회

5. `/stores/:storeId`: 가게 상세 조회
 - 해당 가게의 판매 메뉴 목록 조회 가능
 - 해당 가게에 대한 리뷰 조회 가능

6. `/orders` 메뉴 주문
 - 선택한 가게에서 원하는 메뉴 주문
 - 주문 메뉴 별 수량 선택
 - 결제 방식 선택(카드 또는 현금)

7. `/mypage` 마이 페이지
 - 내 프로필 조회(회원 가입 시 작성한 아이디, 학과명, 전화 번호)
 - 내 주문 및 주문 상세 내역 조회
 - 리뷰 작성 및 삭제
 - 내 쿠폰 조회
