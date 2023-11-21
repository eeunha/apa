package com.apa.repository;

import com.apa.DBUtil;
import com.apa.model.LoginUserDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginUserDAO {

    private Connection conn;
    private Statement stat;
    private PreparedStatement pstat;
    private ResultSet rs;

    /**
     * LoginUserDAO 클래스의 생성자입니다. DB 연결을 수행합니다.
     */
    public LoginUserDAO() {
        this.conn = DBUtil.open();
    }

    /**
     * 사용자 회원가입을 처리합니다.
     *
     * @param dto 사용자 정보를 담은 `LoginUserDTO` 객체
     * @return 데이터베이스에 저장된 행의 수
     */
    public int userRegister(LoginUserDTO dto) {
        try {
            String sql = "insert into tblUser(userseq, username, userssn, userid, userpw, usertel ,useraddress, useremail, userchild, usercautioncount, registerdate, isuserunregister) values (SEQUSER.nextval,?, ? , ?, ?, ?, ? , ?, default, default, default, default)";



            pstat = conn.prepareStatement(sql);
            pstat.setString(1, dto.getUserName());
            pstat.setString(2, dto.getUserSsn());
            pstat.setString(3, dto.getUserId());
            pstat.setString(4, dto.getUserPw());
            pstat.setString(5, dto.getUserTel());
            pstat.setString(6, dto.getUserAddress());
            pstat.setString(7, dto.getUserEmail());

            return pstat.executeUpdate();

        } catch (Exception e) {
            System.out.println(" ");
            e.printStackTrace();
        }

        return 0;
    }


    /**
     * 사용자 로그인을 처리합니다.
     *
     * @param dto 사용자 로그인 정보를 담은 `LoginUserDTO` 객체
     * @return 로그인 성공 시 해당 사용자 정보를 담은 `LoginUserDTO` 객체, 실패 시 null
     */
    public LoginUserDTO login(LoginUserDTO dto) {
            try {

                String sql = "select * from tblUser where UserId = ? and USerPw = ? and ISUSERUNREGISTER = 'n'";


                pstat = conn.prepareStatement(sql);
                pstat.setString(1, dto.getUserId());
                pstat.setString(2, dto.getUserPw());

                rs = pstat.executeQuery();

                if (rs.next()) {

                    LoginUserDTO result = new LoginUserDTO();

                    result.setUserId(rs.getString("userId"));
                    result.setUserName(rs.getString("userName"));
                    result.setUserSeq(rs.getInt("userSeq"));

                    return result;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;

        }


    /**
     * 사용자 아이디 찾기를 처리합니다.
     *
     * @param dto 사용자 아이디 찾기 정보를 담은 `LoginUserDTO` 객체
     * @return 찾은 사용자 정보를 담은 `LoginUserDTO` 객체, 실패 시 null
     */
    public LoginUserDTO searchId(LoginUserDTO dto) {
        try {

            String sql = "select * from tblUser where USERNAME = ? and USERTEL = ? and USEREMAIL = ? and ISUSERUNREGISTER = 'n'";


            pstat = conn.prepareStatement(sql);
            pstat.setString(1, dto.getUserName());
            pstat.setString(2, dto.getUserTel());
            pstat.setString(3, dto.getUserEmail());

            rs = pstat.executeQuery();


            if (rs.next()) {
                LoginUserDTO result = new LoginUserDTO();

                result.setUserName(rs.getString("userName"));
                result.setUserTel(rs.getString("userTel"));
                result.setUserSeq(rs.getInt("userSeq"));
                result.setUserId(rs.getString("userId"));
                result.setUserEmail(rs.getString("userEmail"));

                return result;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    /**
     * 사용자 정보를 가져옵니다.
     *
     * @param dto 사용자 정보를 담은 `LoginUserDTO` 객체
     * @return 사용자 정보를 담은 `LoginUserDTO` 객체, 실패 시 null
     */
    public LoginUserDTO getUser(LoginUserDTO dto) {
        try {

            String sql = "select * from tblUser where USERId = ?";


            pstat = conn.prepareStatement(sql);
            pstat.setString(1, dto.getUserId());

            rs = pstat.executeQuery();


            if (rs.next()) {
                LoginUserDTO result = new LoginUserDTO();

                result.setUserName(rs.getString("userName"));
                result.setUserTel(rs.getString("userTel"));
                result.setUserSeq(rs.getInt("userSeq"));
                result.setUserId(rs.getString("userId"));

                return result;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    /**
     * 사용자 비밀번호 찾기를 처리합니다.
     *
     * @param dto 사용자 비밀번호 찾기 정보를 담은 `LoginUserDTO` 객체
     * @return 찾은 사용자 정보를 담은 `LoginUserDTO` 객체, 실패 시 null
     */
    public LoginUserDTO searchPw(LoginUserDTO dto) {
        try {

            String sql = "select * from tblUser where USERID = ? and USERNAME = ? and USERTEL = ? and ISUSERUNREGISTER = 'n'";


            pstat = conn.prepareStatement(sql);
            pstat.setString(1, dto.getUserId());
            pstat.setString(2, dto.getUserName());
            pstat.setString(3, dto.getUserTel());

            rs = pstat.executeQuery();


            if (rs.next()) {
                LoginUserDTO result = new LoginUserDTO();

                result.setUserName(rs.getString("userName"));
                result.setUserTel(rs.getString("userTel"));
                result.setUserSeq(rs.getInt("userSeq"));
                result.setUserId(rs.getString("userId"));

                return result;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    /**
     * 사용자 비밀번호를 업데이트합니다.
     *
     * @param dto 사용자 정보를 담은 `LoginUserDTO` 객체
     * @return 업데이트 성공 시 해당 사용자 정보를 담은 `LoginUserDTO` 객체, 실패 시 null
     */
    public LoginUserDTO updatePw(LoginUserDTO dto) {
        try {

            String sql = "update tblUser set USERPW = ? where USERID = ?";


            pstat = conn.prepareStatement(sql);
            pstat.setString(1, dto.getUserPw());
            pstat.setString(2, dto.getUserId());


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
     * 사용자 비밀번호로 사용자 정보를 가져옵니다.
     *
     * @param dto 사용자 정보를 담은 `LoginUserDTO` 객체
     * @return 사용자 정보를 담은 `LoginUserDTO` 객체, 실패 시 null
     */
    public LoginUserDTO getUserPw(LoginUserDTO dto) {
        try {

            String sql = "select * from tblUser where USERPW = ?";


            pstat = conn.prepareStatement(sql);
            pstat.setString(1, dto.getUserPw());

            rs = pstat.executeQuery();


            if (rs.next()) {
                LoginUserDTO result = new LoginUserDTO();

                result.setUserName(rs.getString("userName"));
                result.setUserPw(rs.getString("userPw"));
                result.setUserTel(rs.getString("userTel"));
                result.setUserSeq(rs.getInt("userSeq"));
                result.setUserId(rs.getString("userId"));

                return result;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

}