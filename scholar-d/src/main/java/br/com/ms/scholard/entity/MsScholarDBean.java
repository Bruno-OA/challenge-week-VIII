package br.com.ms.scholard.entity;


public class MsScholarDBean {
    private Long userId;
    private Long msId;


    public MsScholarDBean(Long userId, Long msId) {
        super();
        this.userId = userId;
        this.msId = msId;
    }

    public MsScholarDBean() {
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setMsId(Long msId) {
        this.msId = msId;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getMsId() {
        return msId;
    }
}