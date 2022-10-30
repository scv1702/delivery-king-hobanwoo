1. 동작 환경
    다음 환경에서 본 SQL문을 작성/실행 했습니다.
    1) Docker 이미지: jaspeen/oracle-de-11g
    2) Oracle 버젼: Oracle Database 11g Express Edition Release 11.2.0.2.0 - 64bit Production
    3) SQLPLUS 버젼: PL/SQL Release 11.2.0.2.0 - Production
    4) Oracle SQL Developer 버젼: 22.2.1.234
    5) Oracle SQL Developer 빌드: 234.1810

2. 실행 방법
    데이터베이스 무결성을 위해 다음 순서를 통해 실행 부탁드립니다.
    1) SQL Developer 접속
    2) sql/Team7-Phase2-1.sql을 실행하여 제약 조건 및 트리거 선언
    3) sql/Team7-Phase2-2.sql을 실행하여 테이블에 데이터 삽입
    5) sql/Team7-Phase2-3.sql을 실행하여 Query문 실행
    6) sql/trigger_test.sql을 실행하여 트리거 테스트 실행