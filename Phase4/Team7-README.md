# 배달왕 호반우 Phase4

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
