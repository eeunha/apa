select r.mediSeq, userSeq, childSeq, mediWay, doctorSeq, treatmentDate, 
    regdate, symptom, dispenseSeq, userName, userSSN, userId, userTel, 
    userAddress, userEmail, userChild, childName, childSSN, childTel, 
    doctorName, doctorImage, departmentName, waitingPatientSeq, waitingStatus
from vwRegisterDetail r 
    left outer join tblWatingPatientList p on r.mediSeq = p.mediSeq 
where r.mediSeq = 153;

select *
from vwRegisterDetail
where mediSeq = 153;

select * from tblRegister
where mediSeq = 153;

update tblRegister set childSeq = 6 where mediSeq = 153;

commit;

select * from tblChild
where tblchild.childname = '이진태'; --childSeq = 6

select * from tblUser
where userName = '예세은'; --userSeq = 23

select * from tblMediHistory;

insert into tblMediHistory (mediHistorySeq, mediSeq, mediName, mediCode, mediContent) values (seqMediHistory.nextVal, 132, 's', 'ss', 'sss');
delete from tblMediHistory where mediHistorySeq = 387;
commit;

--진료대기 상태 수정
--1. 진행상태 -> 진료중
update tblWatingPatientList set waitingStatus = '진료중' where mediSeq = 132;
--2. 진행상태 -> 진료완료
update tblWatingPatientList set waitingStatus = '진료완료' where mediSeq = 117;
commit;


select count(*) as cnt from vwDiagnosisHistory where to_date(treatmentDate, 'yyyy-mm-dd') = to_date((select sysdate from dual), 'yyyy-mm-dd') and hospitalId = 'yonse';


select * from vwDiagnosisHistory where hospitalId = 'yonse';

select * from tblChild where userSeq = 204;

select * from tblRegister where hospitalId = 'yonse' order by treatmentDate desc;

select * 
from (select a.*, rownum as rnum 
    from (select * 
        from vwDiagnosisHistory 
        where hospitalId = 'yonse' 
        order by treatmentDate, mediSeq) a)
where to_date(treatmentDate, 'yyyy-mm-dd') = to_date((select sysdate from dual), 'yyyy-mm-dd');
   
select * 
from (select a.*, rownum as rnum 
    from (select * 
        from vwDiagnosisHistory 
        where to_date(treatmentDate, 'yyyy-mm-dd') = to_date((select sysdate from dual), 'yyyy-mm-dd') 
            and hospitalId = 'yonse'
        order by treatmentDate, mediSeq) a);
        
select * from (select a.*, rownum as rnum from (select * from vwDiagnosisHistory where hospitalId = 'yonse' order by treatmentDate, mediSeq) a) where rnum between 1 and 10;

--찐
select * 
from (select a.*, rownum as rnum 
    from (select * 
        from vwDiagnosisHistory 
        where to_date(treatmentDate, 'yyyy-mm-dd') = to_date((select sysdate from dual), 'yyyy-mm-dd') 
            and hospitalId = '%s'
        order by treatmentDate, mediSeq) a)
where rnum between %s and %s;

--찐(값 존재 버전)
select * 
from (select a.*, rownum as rnum 
    from (select * 
        from vwDiagnosisHistory 
        where to_date(treatmentDate, 'yyyy-mm-dd') = to_date((select sysdate from dual), 'yyyy-mm-dd') 
            and hospitalId = 'yonse'
        order by treatmentDate, mediSeq) a)
where rnum between 1 and 10;

select count(*) as cnt 
from vwDiagnosisHistory 
where to_date(treatmentDate, 'yyyy-mm-dd') = to_date((select sysdate from dual), 'yyyy-mm-dd') and hospitalId = 'yonse';


select *
from(
select a.*, rownum as rnum 
from (select * from vwDiagnosisHistory where to_date(treatmentDate, 'yyyy-mm-dd') = to_date((select sysdate from dual), 'yyyy-mm-dd') and hospitalId = 'yonse') a )
where rnum between 1 and 10;

-- 10개 가져오기
select * 
from (select a.*, rownum as rnum 
    from (select * 
        from vwDiagnosisHistory 
        where to_date(treatmentDate, 'yyyy-mm-dd') = to_date((select sysdate from dual), 'yyyy-mm-dd') 
            and hospitalId = 'yonse' 
            and waitingStatus not like '예약거부'
        order by treatmentDate, mediSeq) a) 
where rnum between 1 and 10;

--글 총 개수
select count(*) as cnt 
from vwDiagnosisHistory 
where to_date(treatmentDate, 'yyyy-mm-dd') = to_date((select sysdate from dual), 'yyyy-mm-dd') 
    and hospitalId = 'yonse'
    and waitingStatus not like '예약거부';


내가 뽑아낼 것

번호 병원아이디 사용자번호(일반/자녀) 이름(일반/자녀) 
주민등록번호(일반/자녀) 전화번호(일반/자녀) 주소(일반만) 
이메일(일반만) 기타메모 버튼들

vwRegisterDetail + tblPatientMemo

select * from tblRegister;

--내원환자 부모 번호
select distinct userSeq
from tblRegister
where hospitalId = 'yonse' and childSeq is null
order by 1;

--내원환자 자녀 번호
select distinct childSeq
from tblRegister
where hospitalId = 'yonse' and childSeq is not null
order by 1;


-- 내원환자(부모인 경우) 정보
-- 사용자번호(일반/자녀) 이름(일반/자녀) 
-- 주민등록번호(일반/자녀) 전화번호(일반/자녀) 주소(일반만) 
-- 이메일(일반만) 
--select userSeq as patientSeq, userName as patientName, userSSN as patientSSN, userTel as patientTel, userAddress as patientAddress, 
--    userEmail as patientEmail, userChild as patientStatus
--from tblUser
--where userSeq in (select distinct userSeq
--    from tblRegister
--    where hospitalId = 'yonse' and childSeq is null);

-- 메모 추가
select u.userSeq as patientSeq, u.userName as patientName, u.userSSN as patientSSN, u.userTel as patientTel, u.userAddress as patientAddress, 
    u.userEmail as patientEmail, u.userChild as patientStatus, m.memo as memo
from tblUser u
    left outer join tblPatientMemo m
    on u.userSeq = m.userSeq and m.childSeq is null
where u.userSeq in (select distinct userSeq
    from tblRegister
    where hospitalId = 'yonse' and childSeq is null)
order by patientSeq;


-- 내원환자(자녀인 경우) 정보
-- 사용자번호(일반/자녀) 이름(일반/자녀) 
-- 주민등록번호(일반/자녀) 전화번호(일반/자녀) 주소(일반만) 
-- 이메일(일반만) 
--select childSeq as patientSeq, childName as patientName, childSSN as patientSSN, childTel as patientTel, 
--null as patientAddress, null as patientEmail, '자녀' as patientStatus
--from tblChild
--where childSeq in (select distinct childSeq
--    from tblRegister
--    where hospitalId = 'yonse' and childSeq is not null);

--위의 값에서 부모의 주소와 이메일 불러오기
--select c.childSeq as patientSeq, c.childName as patientName, c.childSSN as patientSSN, c.childTel as patientTel, 
--u.userAddress as patientAddress, u.userEmail as patientEmail, '자녀' as patientStatus
--from tblChild c
--    inner join tblUser u
--    on c.userSeq = u.userSeq
--where childSeq in (select distinct childSeq
--    from tblRegister
--    where hospitalId = 'yonse' and childSeq is not null);
    
--위에서 메모 추가
select c.childSeq as patientSeq, c.childName as patientName, c.childSSN as patientSSN, c.childTel as patientTel, 
u.userAddress as patientAddress, u.userEmail as patientEmail, '자녀' as patientStatus, m.memo as memo
from tblChild c
    inner join tblUser u
    on c.userSeq = u.userSeq
        left outer join tblPatientMemo m
        on c.childSeq = m.childSeq and m.userSeq is null
where c.childSeq in (select distinct childSeq
    from tblRegister
    where hospitalId = 'yonse' and childSeq is not null);


    
--내원환자 테이블
create or replace view vwPatientDetail
as
(select userSeq as patientSeq, userName as patientName, userSSN as patientSSN, userTel as patientTel, userAddress as patientAddress, 
    userEmail as patientEmail, userChild as patientStatus
from tblUser
where userSeq in (select distinct userSeq
    from tblRegister
    where hospitalId = 'yonse' and childSeq is null))
union
(select c.childSeq as patientSeq, c.childName as patientName, c.childSSN as patientSSN, c.childTel as patientTel, 
u.userAddress as patientAddress, u.userEmail as patientEmail, '자녀' as patientStatus
from tblChild c
    inner join tblUser u
    on c.userSeq = u.userSeq
where childSeq in (select distinct childSeq
    from tblRegister
    where hospitalId = 'yonse' and childSeq is not null));
    
select * from vwPatientDetail;


-- 내원환자 목록 (병원 아이디 필요)
--select *
--from (select userSeq as patientSeq, userName as patientName, userSSN as patientSSN, userTel as patientTel, userAddress as patientAddress, 
--    userEmail as patientEmail, userChild as patientStatus
--from tblUser
--where userSeq in (select distinct userSeq
--    from tblRegister
--    where hospitalId = ? and childSeq is null))
--union
--(select c.childSeq as patientSeq, c.childName as patientName, c.childSSN as patientSSN, c.childTel as patientTel, 
--u.userAddress as patientAddress, u.userEmail as patientEmail, '자녀' as patientStatus
--from tblChild c
--    inner join tblUser u
--    on c.userSeq = u.userSeq
--where childSeq in (select distinct childSeq
--    from tblRegister
--    where hospitalId = ? and childSeq is not null));
    
--내원환자 + 메모 추가 (병원 아이디 필요)
select *
from (select u.userSeq as patientSeq, u.userName as patientName, u.userSSN as patientSSN, u.userTel as patientTel, u.userAddress as patientAddress, 
    u.userEmail as patientEmail, u.userChild as patientStatus, m.memo as memo
from tblUser u
    left outer join tblPatientMemo m
    on u.userSeq = m.userSeq and m.childSeq is null
where u.userSeq in (select distinct userSeq
    from tblRegister
    where hospitalId = 'yonse' and childSeq is null)
order by patientSeq)
    union
    (select c.childSeq as patientSeq, c.childName as patientName, c.childSSN as patientSSN, c.childTel as patientTel, 
u.userAddress as patientAddress, u.userEmail as patientEmail, '자녀' as patientStatus, m.memo as memo
from tblChild c
    inner join tblUser u
    on c.userSeq = u.userSeq
        left outer join tblPatientMemo m
        on c.childSeq = m.childSeq and m.userSeq is null
where c.childSeq in (select distinct childSeq
    from tblRegister
    where hospitalId = 'yonse' and childSeq is not null));
    

create or replace view vwPatientDetail2
as
(select userSeq as patientSeq, userName as patientName, userSSN as patientSSN, userTel as patientTel, userAddress as patientAddress, 
    userEmail as patientEmail, userChild as patientStatus, hospitalId
from tblUser
where userSeq in (select distinct userSeq
    from tblRegister
    where childSeq is null))
union
(select c.childSeq as patientSeq, c.childName as patientName, c.childSSN as patientSSN, c.childTel as patientTel, 
u.userAddress as patientAddress, u.userEmail as patientEmail, '자녀' as patientStatus
from tblChild c
    inner join tblUser u
    on c.userSeq = u.userSeq
where childSeq in (select distinct childSeq
    from tblRegister
    where childSeq is not null));
    


--내원환자관리 기본 테이블
select r.hospitalId, r.userSeq, r.childSeq,
    r.userName, r.childName, r.userSSN, r.childSSN, r.userTel,
    r.childTel, r.userAddress, r.userEmail, m.memo
from (select * from vwRegisterDetail where userSeq in (select userSeq from tblRegister where hospitalId = 'yonse')) r
    left outer join tblPatientMemo m
    on r.childSeq = m.childSeq
order by userSeq;

select *
from vwUserChild
where userSeq in (select userSeq from tblRegister where hospitalId = 'yonse');

    
--부모회원만
select r.userSeq, r.childSeq,
    r.userName, r.childName, r.userSSN, r.childSSN, r.userTel,
    r.childTel, r.userAddress, r.userEmail, m.memo
from vwRegisterDetail r
    left outer join tblPatientMemo m
    on r.childSeq = m.childSeq;



INSERT INTO tblRegister (mediSeq, hospitalId, userSeq, childSeq, mediWay, doctorSeq, treatmentDate, regdate, symptom, dispenseSeq) VALUES (seqRegister.nextVal, 'yonse', 196, null, '대면', 127, TO_DATE('2023-11-16 11:10:00', 'YYYY-MM-DD:HH24:MI:SS'), TO_DATE('2023-11-15 10:10:00', 'YYYY-MM-DD:HH24:MI:SS'), '목에 이물감이 느껴져요', null);
INSERT INTO tblRegister (mediSeq, hospitalId, userSeq, childSeq, mediWay, doctorSeq, treatmentDate, regdate, symptom, dispenseSeq) VALUES (seqRegister.nextVal, 'yonse', 203, null, '대면', 41, TO_DATE('2023-11-16 11:10:00', 'YYYY-MM-DD:HH24:MI:SS'), TO_DATE('2023-11-15 10:10:00', 'YYYY-MM-DD:HH24:MI:SS'), '열이 나요', null);
INSERT INTO tblRegister (mediSeq, hospitalId, userSeq, childSeq, mediWay, doctorSeq, treatmentDate, regdate, symptom, dispenseSeq) VALUES (seqRegister.nextVal, 'yonse', 33, 8, '대면', 50, TO_DATE('2023-11-15 17:40:00', 'YYYY-MM-DD:HH24:MI:SS'), TO_DATE('2023-11-15 16:40:00', 'YYYY-MM-DD:HH24:MI:SS'), '눈이 충혈됐어요', null);
INSERT INTO tblRegister (mediSeq, hospitalId, userSeq, childSeq, mediWay, doctorSeq, treatmentDate, regdate, symptom, dispenseSeq) VALUES (seqRegister.nextVal, 'yonse', 33, null, '대면', 64, TO_DATE('2023-11-16 9:20:00', 'YYYY-MM-DD:HH24:MI:SS'), TO_DATE('2023-11-15 10:20:00', 'YYYY-MM-DD:HH24:MI:SS'), '허리가 아파요', 3);
INSERT INTO tblRegister (mediSeq, hospitalId, userSeq, childSeq, mediWay, doctorSeq, treatmentDate, regdate, symptom, dispenseSeq) VALUES (seqRegister.nextVal, 'yonse', 204, null, '대면', 125, TO_DATE('2023-11-16 12:30:00', 'YYYY-MM-DD:HH24:MI:SS'), TO_DATE('2023-11-15 11:30:00', 'YYYY-MM-DD:HH24:MI:SS'), null, 4);

commit;

select r.mediSeq, userSeq, childSeq, mediWay, doctorSeq, treatmentDate, regdate, symptom, dispenseSeq, userName, userSSN, userId, userTel, userAddress, userEmail, userChild, childName, childSSN, childTel, doctorName, doctorImage, departmentName, waitingPatientSeq, waitingStatus from vwRegisterDetail r left outer join tblWatingPatientList p on r.mediSeq = p.mediSeq where r.mediSeq = 410; 

select count(*) as cnt from vwDiagnosisHistory where hospitalId = 'yonse' and waitingStatus not like '예약거부';
select * from (select a.*, rownum as rnum from (select * from vwDiagnosisHistory where hospitalId = 'yonse' and waitingStatus not like '예약거부' order by treatmentDate desc, mediSeq desc) a);


select * from (select a.*, rownum as rnum from (select * from vwDiagnosisHistory where to_date(treatmentDate, 'yyyy-mm-dd') = to_date((select sysdate from dual), 'yyyy-mm-dd') and hospitalId = 'yonse' order by treatmentDate, mediSeq) a) where rnum between 1 and 10;


select * from vwRegisterList where to_date(regdate, 'yyyy-mm-dd') = to_date((select sysdate from dual), 'yyyy-mm-dd') and mediSeq not in (select mediSeq from tblWatingPatientList) and hospitalId = 'yonse' order by regdate;


select * from (select a.*, rownum as rnum from (select * from vwDiagnosisHistory where to_date(treatmentDate, 'yyyy-mm-dd') = to_date((select sysdate from dual), 'yyyy-mm-dd') and hospitalId = 'yonse' order by treatmentDate, mediSeq) a) where rnum between 1 and 10;

전체 글 개수는
날짜가 오늘이고 병원 아이디가 같은 행 개수다.
select count(*) as cnt from where to_date() = to_date() and hospitalId='yonse'


