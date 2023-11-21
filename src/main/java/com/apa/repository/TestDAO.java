package com.apa.repository;

import com.apa.DBUtil;
import com.apa.model.TestDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class TestDAO {
    private Connection conn;
    private Statement stat;
    private PreparedStatement pstat;
    private ResultSet rs;
    /**
     * 
     * TestDAO 클래스의 생성자입니다. DB 연결을 수행합니다.
     */

    public TestDAO() {
        this.conn = DBUtil.open();
    }


    /**
     * 특정 의학 테스트에 대한 문제 목록을 가져옵니다.
     *
     * @param seq 의학 테스트 시퀀스
     * @return 의학 테스트 문제 목록을 담은 `ArrayList<TestDTO>` 객체
     */
    public ArrayList<TestDTO> questionList(String seq) {

        try {
            String sql = "select * from TBLMEDITESTQUESTION where MEDITESTSEQ = ?";

            pstat = conn.prepareStatement(sql);
            pstat.setString(1, seq);

            rs = pstat.executeQuery();

            ArrayList<TestDTO> list = new ArrayList<TestDTO>();


            while (rs.next()) {
                TestDTO dto = new TestDTO();

                dto.setMediTestQuestionContent(rs.getString("meditestquestioncontent"));
                dto.setMediTestQuestionNo(rs.getString("meditestquestionno"));

                list.add(dto);
            }
            return list;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 의학 테스트 결과를 저장합니다.
     *
     * @param dto 의학 테스트 결과 정보를 담은 `TestDTO` 객체
     * @return 데이터베이스에 저장된 행의 수
     */
    public int testResult(TestDTO dto) {
        try {
            String sql = "insert into tblMediTestSave(meditestsaveseq, meditestseq, userseq, meditesttotalscore, testdate) values (seqMediTestSave.nextval,?, ? , ?, default)";

            pstat = conn.prepareStatement(sql);
            pstat.setString(1, dto.getMediTestSeq());
            pstat.setString(2, dto.getUserSeq());
            pstat.setString(3, dto.getMediTestTotalScore());


            return pstat.executeUpdate();

        } catch (Exception e) {
            System.out.println(" ");
            e.printStackTrace();
        }

        return 0;
    }
}