# 2022-database-project

2022년 2학기 KNU CSE 데이타베이스 팀 프로젝트

## 팀 이름

- 우아한 호반우

## 팀원

- 노준혁 2020117830
- 신찬규 2020111854
- 이지안 2020111674

## 디렉토리 구조

```
├── README.md: 프로젝트 README
├── csv: 크롤링을 통해 수집한 데이터 및 더미 데이터
├── docs: 프로젝트 관련 문서
│   ├── Team7-README.txt: SQL 동작 환경 및 실행 방법 등 설명하고자 하는 사항들에 대한 문서
│   ├── Team7-etr_mapping.txt: ER Schema가 어떻게 관계형 Schema로 매핑 되었는지 관계 순으로 설명한 문서
│   ├── diagrams: 각종 다이어그램
│   │   ├── entity_relationship: ER 다이어그램
│   │   │   ├── entity_relationship.drawio
│   │   │   ├── entity_relationship.png
│   │   │   └── entity_relationship.pdf
│   │   └── ert_mapping: ETR Mapping 다이어그램
│   │       ├── ert_mapping.drawio
│   │       └── ert_mapping.png
│   └── ppts: 발표 자료
│       └── 배달왕_호반우_프로젝트_제안서.pptx
├── images: 수집한 이미지, 향후 구현에서 사용 예정
├── script: Python 스크립트
│   └── insert-generator: .csv를 SQL INSERT 문으로 변환하는 스크립트
└── sql
    ├── Team7-Phase2-1.sql: 제약 조건 및 트리거를 포함한 DDL
    ├── Team7-Phase2-2.sql: DDL을 통해 생성된 테이블에 대한 INSERT문들
    ├── Team7-Phase2-3.sql: 20개의 Query문들
    ├── query_result.txt: Team7-Phase2-3.sql을 실행한 결과
    └── trigger_test.sql: 트리거 테스트 용 QUERY문
```
