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




select r.mediSeq, userSeq, childSeq, mediWay, doctorSeq, treatmentDate, regdate, symptom, dispenseSeq, userName, userSSN, userId, userTel, userAddress, userEmail, userChild, childName, childSSN, childTel, doctorName, doctorImage, departmentName, waitingPatientSeq, waitingStatus from vwRegisterDetail r left outer join tblWatingPatientList p on r.mediSeq = p.mediSeq where r.mediSeq = 410; 

select count(*) as cnt from vwDiagnosisHistory where hospitalId = 'yonse' and waitingStatus not like '예약거부';
select * from (select a.*, rownum as rnum from (select * from vwDiagnosisHistory where hospitalId = 'yonse' and waitingStatus not like '예약거부' order by treatmentDate desc, mediSeq desc) a);


select * from (select a.*, rownum as rnum from (select * from vwDiagnosisHistory where to_date(treatmentDate, 'yyyy-mm-dd') = to_date((select sysdate from dual), 'yyyy-mm-dd') and hospitalId = 'yonse' order by treatmentDate, mediSeq) a) where rnum between 1 and 10;


select * from vwRegisterList where to_date(regdate, 'yyyy-mm-dd') = to_date((select sysdate from dual), 'yyyy-mm-dd') and mediSeq not in (select mediSeq from tblWatingPatientList) and hospitalId = 'yonse' order by regdate;


select * from (select a.*, rownum as rnum from (select * from vwDiagnosisHistory where to_date(treatmentDate, 'yyyy-mm-dd') = to_date((select sysdate from dual), 'yyyy-mm-dd') and hospitalId = 'yonse' order by treatmentDate, mediSeq) a) where rnum between 1 and 10;

전체 글 개수는
날짜가 오늘이고 병원 아이디가 같은 행 개수다.
select count(*) as cnt from where to_date() = to_date() and hospitalId='yonse'


