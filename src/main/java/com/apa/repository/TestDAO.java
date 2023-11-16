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

    public TestDAO() {
        this.conn = DBUtil.open();
    }


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