Team7-README.txt

1. 디렉토리 구조
├── csv: 수집한 데이터 및 더미 데이터
├── images: 수집한 이미지, 향후 구현에서 사용 예정
├── script: Python 스크립트
│   └── insert-generator: .csv를 SQL INSERT 문으로 변환하는 스크립트
└── sql: SQL 파일
    ├── insert: 각 테이블의 INSERT 문이 저장된 디렉토리
    ├── ddl.sql: 제약 조건 및 트리거를 포함한 DDL 문
    ├── insert.sql: 하나로 통합한 INSERT 문
    └── query.sql: QUERY 문

2. 동작 환경
다음 환경에서 본 SQL 문을 작성/실행 했습니다.
- Docker 이미지: jaspeen/oracle-de-11g
- Oracle 버젼: Oracle Database 11g Express Edition Release 11.2.0.2.0 - 64bit Production
- SQLPLUS 버젼: PL/SQL Release 11.2.0.2.0 - Production
- Oracle SQL Developer 버젼: 22.2.1.234
- Oracle SQL Developer 빌드: 234.1810

3. 실행 방법
데이터베이스 무결성을 위해 다음 순서를 통해 실행 부탁드립니다.
1) SQL Developer 접속
2) sql/ddl.sql 실행
3) sql/insert.sql 실행
5) sql/query.sql 실행