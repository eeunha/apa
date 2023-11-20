package com.apa.repository;

import com.apa.DBUtil;
import com.apa.model.LoginChildDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class ChildDAO {

    private Connection conn;
    private Statement stat;
    private PreparedStatement pstat;
    private ResultSet rs;

    public ChildDAO() {
        this.conn = DBUtil.open();
    }

    public int childRegister(LoginChildDTO childdto) {
        try{
        String sql = "insert into tblUser(userseq, username, userssn, userid, userpw, usertel ,useraddress, useremail, userchild, usercautioncount, registerdate, isuserunregister) values (SEQUSER.nextval,?, ? , ?, ?, ?, ? , ?, ?, default, default, default)";

        pstat = conn.prepareStatement(sql);
        pstat.setString(1, childdto.getChildName());
        pstat.setString(2, childdto.getChildSsn());
        pstat.setString(3, childdto.getChildTel());

        return pstat.executeUpdate();

    } catch (Exception e) {
        System.out.println(" ");
        e.printStackTrace();
    }
        return 0;
    }
}
