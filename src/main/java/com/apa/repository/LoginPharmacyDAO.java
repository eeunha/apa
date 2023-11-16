package com.apa.repository;

import com.apa.DBUtil;
import com.apa.model.LoginPharmacyDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginPharmacyDAO {
    private Connection conn;
    private Statement stat;
    private PreparedStatement pstat;
    private ResultSet rs;

    public LoginPharmacyDAO() {
        this.conn = DBUtil.open();
    }
    public LoginPharmacyDTO login(LoginPharmacyDTO dto) {

        try {
            System.out.println(dto.getPharmacyId());

            String sql = "select * from tblPharmacy where PharmacyId = ? and PharmacyPw = ? and IsPharmacyunregister = 'n'";

            pstat = conn.prepareStatement(sql);
            pstat.setString(1, dto.getPharmacyId());
            pstat.setString(2, dto.getPharmacyPw());

            rs = pstat.executeQuery();

            if (rs.next()) {

                LoginPharmacyDTO result = new LoginPharmacyDTO();

                result.setPharmacyId(rs.getString("pharmacyId"));
                result.setPharmacyPw(rs.getString("pharmacyPw"));
                result.setPharmacyName(rs.getString("pharmacyName"));

                return result;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int PharmacyRegister(LoginPharmacyDTO dto) {

        try {

            String sql = "insert into TBLPHARMACY(pharmacyid, pharmacypw, pharmacyname, pharmacyssn, pharmacyaddress, pharmacyemail , pharmacytel, isdispense, ispharmacy, regdate, ispharmacyunregister) values (?, ?, ?, ?, ?, ?, ?, default, default, default, default)";

            pstat = conn.prepareStatement(sql);
            pstat.setString(1, dto.getPharmacyId());
            pstat.setString(2, dto.getPharmacyPw());
            pstat.setString(3, dto.getPharmacyName());
            pstat.setString(4, dto.getPharmacySsn());
            pstat.setString(5, dto.getPharmacyAddress());
            pstat.setString(6, dto.getPharmacyEmail());
            pstat.setString(7, dto.getPharmacyTel());


            return pstat.executeUpdate();

        } catch (Exception e) {
            System.out.println(" ");
            e.printStackTrace();
        }

        return 0;

    }

    public LoginPharmacyDTO searchId(LoginPharmacyDTO dto) {
        try {

            String sql = "select * from TBLPHARMACY where PHARMACYNAME = ? and PHARMACYTEL = ? and PHARMACYEMAIL = ? and ISPHARMACYUNREGISTER = 'n'";


            pstat = conn.prepareStatement(sql);
            pstat.setString(1, dto.getPharmacyName());
            pstat.setString(2, dto.getPharmacyTel());
            pstat.setString(3, dto.getPharmacyEmail());

            rs = pstat.executeQuery();

            if (rs.next()) {

                LoginPharmacyDTO result = new LoginPharmacyDTO();

                result.setPharmacyName(rs.getString("PharmacyName"));
                result.setPharmacyTel(rs.getString("PharmacyTel"));
                result.setPharmacyId(rs.getString("PharmacyId"));
                result.setPharmacyEmail(rs.getString("PharmacyEmail"));

                return result;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public LoginPharmacyDTO updatePw(LoginPharmacyDTO dto) {
        try {

            String sql = "update TBLPHARMACY set PHARMACYPW = ? where PHARMACYID = ?";


            pstat = conn.prepareStatement(sql);
            pstat.setString(1, dto.getPharmacyPw());
            pstat.setString(2, dto.getPharmacyId());


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

    public LoginPharmacyDTO searchPw(LoginPharmacyDTO dto) {
        try {

            String sql = "select * from TBLPHARMACY where PHARMACYID = ? and PHARMACYNAME = ? and PHARMACYTEL = ? and ISPHARMACYUNREGISTER = 'n'";


            pstat = conn.prepareStatement(sql);
            pstat.setString(1, dto.getPharmacyId());
            pstat.setString(2, dto.getPharmacyName());
            pstat.setString(3, dto.getPharmacyTel());

            rs = pstat.executeQuery();


            if (rs.next()) {
                LoginPharmacyDTO result = new LoginPharmacyDTO();

                result.setPharmacyName(rs.getString("PharmacyName"));
                result.setPharmacyTel(rs.getString("PharmacyTel"));
                result.setPharmacyId(rs.getString("PharmacyId"));

                return result;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    public LoginPharmacyDTO getPharmacy(LoginPharmacyDTO dto) {
        try {

            String sql = "select * from TBLPHARMACY where PHARMACYID = ?";


            pstat = conn.prepareStatement(sql);
            pstat.setString(1, dto.getPharmacyId());

            rs = pstat.executeQuery();


            if (rs.next()) {
                LoginPharmacyDTO result = new LoginPharmacyDTO();

                result.setPharmacyName(rs.getString("pharmacyName"));
                result.setPharmacyTel(rs.getString("pharmacyTel"));
                result.setPharmacyId(rs.getString("pharmacyId"));

                return result;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }
}
