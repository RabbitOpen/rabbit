package rabbit.doc.entity;

import rabbit.open.orm.common.dml.Policy;
import rabbit.open.orm.core.annotation.Column;
import rabbit.open.orm.core.annotation.Entity;
import rabbit.open.orm.core.annotation.PrimaryKey;

@Entity("equation_record")
public class EquationRecord {

    @PrimaryKey(policy = Policy.AUTOINCREMENT)
    @Column("ID")
    private Long id;

    @Column("practise_id")
    private Long practiseId;

    @Column("equation_text")
    private String text;

    /**
     * 正确答案
     */
    @Column("final_result")
    private String finalResult;

    @Column("committed_result")
    private String committedResult;

    /**
     * 对还是错
     */
    @Column("result")
    private Result result;

    public enum Result {
        SUCCESS,
        FAILED;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPractiseId() {
        return practiseId;
    }

    public void setPractiseId(Long practiseId) {
        this.practiseId = practiseId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getFinalResult() {
        return finalResult;
    }

    public void setFinalResult(String finalResult) {
        this.finalResult = finalResult;
    }

    public String getCommittedResult() {
        return committedResult;
    }

    public void setCommittedResult(String committedResult) {
        this.committedResult = committedResult;
    }
}
