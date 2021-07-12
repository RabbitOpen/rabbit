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
public class PracticeController {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    PractiseService practiseService;

    ReentrantLock lock = new ReentrantLock();

    /**
     * practise页面
     *
     * @return
     */
    @RequestMapping("/practise")
    public String practise() {
        return "practise";
    }

    @RequestMapping("/history")
    public String history() {
        return "history";
    }

    @RequestMapping("/generate")
    @ResponseBody
    public List<EquationRecord> generate() {
        // 简单防重一下
        if (lock.tryLock()) {
            try {
                Practise practise = practiseService.createQuery().addFilter("commitStatus", Practise.CommitStatus.INIT).unique();
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
                .page(0, 20).list();
    }
}
