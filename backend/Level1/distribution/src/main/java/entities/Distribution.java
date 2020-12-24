package entities;

import java.util.Date;

public class Distribution {
    private Long id;
    private float amount;
    private Date start_date;
    private Date end_date;
    private Long company_id;
    private Long user_id;

    public Distribution(Long id, float amount, Date start_date, Date end_date, Long company_id, Long user_id) {
        this.id = id;
        this.amount = amount;
        this.start_date = start_date;
        this.end_date = end_date;
        this.company_id = company_id;
        this.user_id = user_id;
    }

    public Long getId() {
        return id;
    }

    public float getAmount() {
        return amount;
    }

    public Date getStart_date() {
        return start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public Long getCompany_id() {
        return company_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public void setCompany_id(Long company_id) {
        this.company_id = company_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }
}
