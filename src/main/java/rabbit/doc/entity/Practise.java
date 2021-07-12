package rabbit.doc.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import rabbit.open.orm.common.dml.Policy;
import rabbit.open.orm.core.annotation.Column;
import rabbit.open.orm.core.annotation.Entity;
import rabbit.open.orm.core.annotation.PrimaryKey;

import java.util.Date;

@Entity("practise")
public class Practise {

    @PrimaryKey(policy = Policy.AUTOINCREMENT)
    @Column("ID")
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @Column("begin_time")
    private Date begin;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @Column("end_time")
    private Date end;

    @Column("total_count")
    private Long total;

    @Column("error_count")
    private Long error;

    @Column("correct_count")
    private Long correct;

    @Column("cost_time")
    private Long costTime;

    @Column("commit_status")
    private CommitStatus commitStatus = CommitStatus.INIT;

    public enum CommitStatus {
        INIT,
        COMMITTED
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getBegin() {
        return begin;
    }

    public void setBegin(Date begin) {
        this.begin = begin;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Long getError() {
        return error;
    }

    public void setError(Long error) {
        this.error = error;
    }

    public Long getCorrect() {
        return correct;
    }

    public void setCorrect(Long correct) {
        this.correct = correct;
    }

    public Long getCostTime() {
        return costTime;
    }

    public void setCostTime(Long costTime) {
        this.costTime = costTime;
    }

    public CommitStatus getCommitStatus() {
        return commitStatus;
    }

    public void setCommitStatus(CommitStatus commitStatus) {
        this.commitStatus = commitStatus;
    }
}
