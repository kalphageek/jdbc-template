## 요건
SQL과 필터조건을 실행해서 추출 또는 조회하거나, 테이블의 샘플데이터를 추출 또는 조회할 수 있다.
<br>
Oracle, Hive, PostgreSQL 등 다양한 DB에 적용할 수 있다.

## Project 구성
1. JPA nativeQuery 적용
2. Unit Test - Junit 5
3. API Documents - Rest Docs (http://localhost:8080/docs/index.html)    
   * AsciiDocs skin - adoc-colony.css

## AsciiDocs Skin 적용
1. Luby 설치
2. gem install asciidoctor
3. adoc-colony.css를 asciidoc 폴더로 copy 
4. index.adoc파일에 내용 추가
```
:stylesheet: adoc-colony.css
```
5. index.adoc에 skin 적용
```shell
asciidoctor index.adoc
```
6. Maven package
```shell
mvn package
```

## Multi DataSource 설정 : createNativeQuery를 사용하는 경우
1. EntityManager에 이름을 등록후 직접 호출한다
   - DataSourceConfig에서 EntityManager Bean 등록시 builder.persistenceUnit(Constants.BATCH_UNIT_NAME)를 통해 Bean 이름 등록
   - Repository에서 EntityManager Injection 시에 @PersistenceContext(unitName = Constants.BATCH_UNIT_NAME)을 통해 해당 Bean 호출
   - basePackages는 무시 됨.
2. @PersistenceContext를 이용해서 주입받은 EntityManager는 JEE가 관리하는 트랜잭션에 참여한다.
   - 애플리케이션 코드에서 트랜잭션을 직접 관리하지 않는다.   

## Multi DataSource 설정 : Jpa Interface를 사용하는 경우
1. CrudRepository/JpaRepository Interface 위치를 등록한다.
   - basePackages 를 통해 Repository 위치지정
   - builder.packages("me.kalpha.jdbctemplate.batch")를 통해 Entity 위치지정


## 전체 과정 (systemId별 DB 접속해서 createNativeQuery 실행 과정)
1. DB별 DataSourceConfig 설정 (BatchDataSourceConfig.java)
2. Named Bean으로 EntityManager Injection (EntityManagerConfig.java)
3. systemId별로 EntityManager 선택 (QueryServiceImpl.java)
4. systemId별로 QueryRepositoryOthersImpl 생성하면서 EntityManager 전달 (QueryServiceImpl.java)
5. 전달받은 EntityManager를 통해 createNativeQuery 실행  (QueryRepositoryOthersImpl.java)
