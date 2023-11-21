package com.apa.model;

/**
 * 테스트 정보를 담는 데이터 모델 클래스입니다.
 */
public class TestDTO {
    private String mediTestSeq;             // 테스트 일련번호
    private String mediTestSaveSeq;         // 테스트 저장 일련번호
    private String userSeq;                 // 사용자 일련번호
    private String mediTestTotalScore;      // 테스트 총 점수
    private String testDate;                // 테스트 응시 일자
    private String mediTestName;            // 테스트 이름
    private String minScore;                // 최소 점수
    private String maxScore;                // 최대 점수
    private String mediTestResultContent;   // 테스트 결과 내용
    private String mediTestQuestionContent; // 테스트 문항 내용
    private String mediTestQuestionNo;      // 테스트 문항 번호

    /**
     * 테스트 일련번호를 반환합니다.
     */
    public String getMediTestSeq() {
        return mediTestSeq;
    }

    /**
     * 테스트 일련번호를 설정합니다.
     * @param mediTestSeq 테스트 일련번호
     */
    public void setMediTestSeq(String mediTestSeq) {
        this.mediTestSeq = mediTestSeq;
    }

    /**
     * 테스트 저장 일련번호를 반환합니다.
     */
    public String getMediTestSaveSeq() {
        return mediTestSaveSeq;
    }

    /**
     * 테스트 저장 일련번호를 설정합니다.
     * @param mediTestSaveSeq 테스트 저장 일련번호
     */
    public void setMediTestSaveSeq(String mediTestSaveSeq) {
        this.mediTestSaveSeq = mediTestSaveSeq;
    }

    /**
     * 사용자 일련번호를 반환합니다.
     */
    public String getUserSeq() {
        return userSeq;
    }

    /**
     * 사용자 일련번호를 설정합니다.
     * @param userSeq 사용자 일련번호
     */
    public void setUserSeq(String userSeq) {
        this.userSeq = userSeq;
    }

    /**
     * 테스트 총 점수를 반환합니다.
     */
    public String getMediTestTotalScore() {
        return mediTestTotalScore;
    }

    /**
     * 테스트 총 점수를 설정합니다.
     * @param mediTestTotalScore 테스트 총 점수
     */
    public void setMediTestTotalScore(String mediTestTotalScore) {
        this.mediTestTotalScore = mediTestTotalScore;
    }

    /**
     * 테스트 응시 일자를 반환합니다.
     */
    public String getTestDate() {
        return testDate;
    }

    /**
     * 테스트 응시 일자를 설정합니다.
     * @param testDate 테스트 응시 일자
     */
    public void setTestDate(String testDate) {
        this.testDate = testDate;
    }

    /**
     * 테스트 이름을 반환합니다.
     */
    public String getMediTestName() {
        return mediTestName;
    }

    /**
     * 테스트 이름을 설정합니다.
     * @param mediTestName 테스트 이름
     */
    public void setMediTestName(String mediTestName) {
        this.mediTestName = mediTestName;
    }

    /**
     * 최소 점수를 반환합니다.
     */
    public String getMinScore() {
        return minScore;
    }

    /**
     * 최소 점수를 설정합니다.
     * @param minScore 최소 점수
     */
    public void setMinScore(String minScore) {
        this.minScore = minScore;
    }

    /**
     * 최대 점수를 반환합니다.
     */
    public String getMaxScore() {
        return maxScore;
    }

    /**
     * 최대 점수를 설정합니다.
     * @param maxScore 최대 점수
     */
    public void setMaxScore(String maxScore) {
        this.maxScore = maxScore;
    }

    /**
     * 테스트 결과 내용을 반환합니다.
     */
    public String getMediTestResultContent() {
        return mediTestResultContent;
    }

    /**
     * 테스트 결과 내용을 설정합니다.
     * @param mediTestResultContent 테스트 결과 내용
     */
    public void setMediTestResultContent(String mediTestResultContent) {
        this.mediTestResultContent = mediTestResultContent;
    }

    /**
     * 테스트 문항 내용을 반환합니다.
     */
    public String getMediTestQuestionContent() {
        return mediTestQuestionContent;
    }

    /**
     * 테스트 문항 내용을 설정합니다.
     * @param mediTestQuestionContent 테스트 문항 내용
     */
    public void setMediTestQuestionContent(String mediTestQuestionContent) {
        this.mediTestQuestionContent = mediTestQuestionContent;
    }

    /**
     * 테스트 문항 번호를 반환합니다.
     */
    public String getMediTestQuestionNo() {
        return mediTestQuestionNo;
    }

    /**
     * 테스트 문항 번호를 설정
     * @param mediTestQuestionNo
     */
    public void setMediTestQuestionNo(String mediTestQuestionNo) {
        this.mediTestQuestionNo = mediTestQuestionNo;
    }
}
