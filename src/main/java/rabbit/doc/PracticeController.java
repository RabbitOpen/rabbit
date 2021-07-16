package rabbit.doc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import rabbit.doc.entity.EquationRecord;
import rabbit.doc.entity.Practise;
import rabbit.doc.service.EquationRecordService;
import rabbit.doc.service.PractiseService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

@Controller
@RequestMapping("/practise")
public class PracticeController {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    PractiseService practiseService;

    @Autowired
    EquationRecordService equationRecordService;

    ReentrantLock lock = new ReentrantLock();

    /**
     * practise页面
     *
     * @return
     */
    @RequestMapping("/index")
    public String practise() {
        return "practise";
    }

    @RequestMapping("/history")
    public String history() {
        return "history";
    }

    @RequestMapping("/practiseDetail")
    public String practiseDetail() {
        return "practise-detail";
    }

    @RequestMapping("/generate")
    @ResponseBody
    public List<EquationRecord> generate() {
        // 简单防重一下
        if (lock.tryLock()) {
            try {
                Practise practise = practiseService.createQuery().addFilter("commitStatus", Practise.CommitStatus.INIT)
                        .addFilter("username", RequestUtil.getLoginUser())
                        .unique();
                if (null != practise) {
                    // 返回当前题目
                    return practiseService.getRecordsByPractiseNo(practise.getId());
                } else {
                    return practiseService.generatePractise();
                }
            } finally {
                lock.unlock();
            }
        } else {
            return new ArrayList<>();
        }
    }

    @RequestMapping("/commit")
    @ResponseBody
    public String commit(@RequestParam("result")String result, @RequestParam("practiseId")Long practiseId) {
        if (lock.tryLock()) {
            try {
                Practise practise = practiseService.getByID(practiseId);
                if (practise.getCommitStatus() == Practise.CommitStatus.COMMITTED) {
                    return "success";
                }
                practiseService.batchUpdate(result, practise);
                return "success";
            } finally {
                lock.unlock();
            }
        } else {
            return "failed";
        }
    }

    @RequestMapping("/practises")
    @ResponseBody
    public List<Practise> practises() {
        return practiseService.createQuery().desc("id")
                .addFilter("commitStatus", Practise.CommitStatus.COMMITTED)
                .addFilter("username", RequestUtil.getLoginUser())
                .page(0, 20).list();
    }

    @RequestMapping("/load")
    @ResponseBody
    public List<EquationRecord> load(@RequestParam("practiseId")Long practiseId) {
        return equationRecordService.createQuery().addFilter("practiseId", practiseId)
            .addFilter("result", EquationRecord.Result.FAILED).list();
    }

    @RequestMapping("/details")
    @ResponseBody
    public List<String[]> details(@RequestParam("equationId")Long equationId) {
        EquationRecord record = equationRecordService.getByID(equationId);
        String deriveText = record.getDeriveText();
        List<String[]> list = new ArrayList<>();
        for (String line : deriveText.split(",")) {
            String[] split = line.split("=");
            list.add(new String[]{split[0], " = ", split[1]});
        }
        return list;
    }

    @RequestMapping("/setTotal")
    @ResponseBody
    public int setTotal(@RequestParam("total") int total) {
        if (!"xiaokunqin".equals(RequestUtil.getLoginUser())) {
            return -1;
        }
        if (total > 30 || total < 1) {
            total = 30;
        }
        practiseService.setTotal(total);
        return total;
    }
}
