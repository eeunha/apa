package com.apa.repository;

import com.apa.DBUtil;
import com.apa.model.LoginPharmacyDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * `LoginPharmacyDAO` 클래스는 약국과 관련된 데이터 액세스 메서드를 제공합니다.
 */
public class LoginPharmacyDAO {
    private Connection conn;
    private Statement stat;
    private PreparedStatement pstat;
    private ResultSet rs;

    /**
     * 디폴트 생성자는 데이터베이스 연결을 초기화합니다.
     */
    public LoginPharmacyDAO() {
        this.conn = DBUtil.open();
    }

    /**
     * 약국 사용자의 로그인 자격 증명을 유효성 검사합니다.
     *
     * @param dto 로그인 정보를 포함하는 `LoginPharmacyDTO` 객체
     * @return 로그인이 성공하면 `LoginPharmacyDTO` 객체를 반환하고, 그렇지 않으면 null을 반환합니다.
     */
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

                return result;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 새로운 약국 사용자를 등록합니다.
     *
     * @param dto 등록 정보를 포함하는 `LoginPharmacyDTO` 객체
     * @return 등록 프로세스에 영향을 받는 행 수
     */
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

    /**
     * 약국 정보를 검색하여 ID, 이름, 전화번호 및 이메일이 일치하는 경우 `LoginPharmacyDTO` 객체를 반환합니다.
     *
     * @param dto 검색 정보를 포함하는 `LoginPharmacyDTO` 객체
     * @return 검색 결과를 나타내는 `LoginPharmacyDTO` 객체
     */
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

    /**
     * 약국 사용자의 비밀번호를 업데이트합니다.
     *
     * @param dto 업데이트할 정보를 포함하는 `LoginPharmacyDTO` 객체
     * @return 업데이트 프로세스에 영향을 받는 행 수
     */
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

    /**
     * 약국 사용자의 비밀번호를 검색하여 일치하는 경우 `LoginPharmacyDTO` 객체를 반환합니다.
     *
     * @param dto 검색 정보를 포함하는 `LoginPharmacyDTO` 객체
     * @return 검색 결과를 나타내는 `LoginPharmacyDTO` 객체
     */
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

    /**
     * 약국 사용자의 정보를 가져옵니다.
     *
     * @param dto 사용자 정보를 포함하는 `LoginPharmacyDTO` 객체
     * @return 약국 사용자 정보를 나타내는 `LoginPharmacyDTO` 객체
     */
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
