package com.apa.repository;

import com.apa.DBUtil;
import com.apa.model.LoginHospitalDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class LoginHospitalDAO {
    private Connection conn;
    private Statement stat;
    private PreparedStatement pstat;
    private ResultSet rs;

    public LoginHospitalDAO() {
        this.conn = DBUtil.open();
    }
    public int HospitalRegister(LoginHospitalDTO dto) {
        try {

            String sql = "insert into tblHospital(hospitalid, hospitalpw, hospitalname, hospitalssn, hospitaladdress, hospitalemail ,hospitaltel, DEPARTMENTSEQ, ishospital, regdate, ishospitalunregister) values (?, ?, ?, ?, ?, ?, ?, ?, default, default, default)";

            pstat = conn.prepareStatement(sql);
            pstat.setString(1, dto.getHospitalId());
            pstat.setString(2, dto.getHospitalPw());
            pstat.setString(3, dto.getHospitalName());
            pstat.setString(4, dto.getHospitalSsn());
            pstat.setString(5, dto.getHospitalAddress());
            pstat.setString(6, dto.getHospitalEmail());
            pstat.setString(7, dto.getHospitalTel());
            pstat.setString(8, dto.getDepartmentSeq());


            return pstat.executeUpdate();

        } catch (Exception e) {
            System.out.println(" ");
            e.printStackTrace();
        }

        return 0;
    }

    public LoginHospitalDTO login(LoginHospitalDTO dto) {
        try {
            System.out.println(dto.getHospitalId());

            String sql = "select * from TBLHOSPITAL where HospitalId = ? and HospitalPw = ? and ISHOSPITALUNREGISTER = 'n'";

            pstat = conn.prepareStatement(sql);
            pstat.setString(1, dto.getHospitalId());
            pstat.setString(2, dto.getHospitalPw());

            rs = pstat.executeQuery();

            if (rs.next()) {

                LoginHospitalDTO result = new LoginHospitalDTO();

                result.setHospitalId(rs.getString("hospitalId"));
                result.setHospitalPw(rs.getString("hospitalPw"));
                result.setHospitalName(rs.getString("hospitalName"));

                return result;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public LoginHospitalDTO searchId(LoginHospitalDTO dto) {
        try {

            String sql = "select * from TBLHOSPITAL where HOSPITALNAME = ? and HOSPITALTEL = ? and HOSPITALEMAIL = ? and ishospitalunregister = 'n'";


            pstat = conn.prepareStatement(sql);
            pstat.setString(1, dto.getHospitalName());
            pstat.setString(2, dto.getHospitalTel());
            pstat.setString(3, dto.getHospitalEmail());


            rs = pstat.executeQuery();

            if (rs.next()) {

                LoginHospitalDTO result = new LoginHospitalDTO();

                result.setHospitalName(rs.getString("HospitalName"));
                result.setHospitalTel(rs.getString("HospitalTel"));
                result.setHospitalId(rs.getString("HospitalId"));
                result.setHospitalEmail(rs.getString("HospitalEmail"));

                return result;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public LoginHospitalDTO updatePw(LoginHospitalDTO dto) {
        try {

            String sql = "update TBLHOSPITAL set HOSPITALPW = ? where HOSPITALID = ?";


            pstat = conn.prepareStatement(sql);
            pstat.setString(1, dto.getHospitalPw());
            pstat.setString(2, dto.getHospitalId());


            int result = pstat.executeUpdate();

            if (result == 1) {
                return dto;
            }
            //실패할 경우 처리 추가

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    public LoginHospitalDTO searchPw(LoginHospitalDTO dto) {
        try {

            String sql = "select * from tblhospital where HOSPITALID = ? and HOSPITALNAME = ? and HOSPITALTEL = ? and ISHOSPITALUNREGISTER = 'n'";


            pstat = conn.prepareStatement(sql);
            pstat.setString(1, dto.getHospitalId());
            pstat.setString(2, dto.getHospitalName());
            pstat.setString(3, dto.getHospitalTel());

            rs = pstat.executeQuery();


            if (rs.next()) {
                LoginHospitalDTO result = new LoginHospitalDTO();

                result.setHospitalName(rs.getString("hospitalName"));
                result.setHospitalTel(rs.getString("hospitalTel"));
                result.setHospitalId(rs.getString("hospitalId"));

                return result;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    public LoginHospitalDTO getHospital(LoginHospitalDTO dto) {
        try {

            String sql = "select * from TBLHOSPITAL where HOSPITALID = ?";


            pstat = conn.prepareStatement(sql);
            pstat.setString(1, dto.getHospitalId());

            rs = pstat.executeQuery();


            if (rs.next()) {
                LoginHospitalDTO result = new LoginHospitalDTO();

                result.setHospitalName(rs.getString("hospitalName"));
                result.setHospitalTel(rs.getString("hospitalTel"));
                result.setHospitalId(rs.getString("hospitalId"));

                return result;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }
}
