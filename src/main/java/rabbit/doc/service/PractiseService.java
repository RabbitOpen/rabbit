package rabbit.doc.service;

import com.sun.org.apache.xpath.internal.operations.Div;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rabbit.doc.dao.BasicService;
import rabbit.doc.entity.EquationRecord;
import rabbit.doc.entity.Practise;
import rabbit.open.math.practise.service.Equation;
import rabbit.open.math.practise.service.impl.Addition;
import rabbit.open.math.practise.service.impl.Division;
import rabbit.open.math.practise.service.impl.Minus;
import rabbit.open.math.practise.service.impl.Multi;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

@Service
public class PractiseService extends BasicService<Practise> {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    EquationRecordService equationRecordService;

    @Transactional
    public List<EquationRecord> generatePractise() {
        Practise practise = new Practise();
        practise.setBegin(new Date());
        add(practise);
        List<EquationRecord> equations = new ArrayList<>();
        equations.addAll(generateEquation(practise.getId(), 15, simpleAddOrMinusEquationSupplier()));
        equations.addAll(generateEquation(practise.getId(), 15, simpleMultiAndDivEquationSupplier()));
        practise.setTotal((long) equations.size());
        updateByID(practise);
        return equations;
    }

    public List<EquationRecord> getRecordsByPractiseNo(Long practiseId) {
        return equationRecordService.createQuery().asc("id").addFilter("practiseId", practiseId).list();
    }

    @Transactional
    public void batchUpdate(String result, Practise p) {
        String[] results = result.split(",");
        long successCount = 0;
        for (String r : results) {
            String[] arr = r.split(":");
            EquationRecord record = new EquationRecord();
            record.setId(Long.parseLong(arr[0]));
            record.setCommittedResult(arr[1]);
            boolean success = arr[1].equals(arr[2]);
            if (success) {
                successCount++;
            }
            record.setResult(success ? EquationRecord.Result.SUCCESS : EquationRecord.Result.FAILED);
            record.setPractiseId(p.getId());
            equationRecordService.updateByID(record);
        }
        Practise practise = new Practise();
        practise.setCommitStatus(Practise.CommitStatus.COMMITTED);
        practise.setId(p.getId());
        practise.setEnd(new Date());
        practise.setCorrect(successCount);
        practise.setError(p.getTotal() - successCount);
        practise.setCostTime((practise.getEnd().getTime() - p.getBegin().getTime()) / 1000);
        updateByID(practise);
    }

    /**
     * 简单加减法生成器
     * @return
     */
    private Supplier<Equation> simpleAddOrMinusEquationSupplier() {
        return () -> {
            Equation[] equations = new Equation[]{
                new Addition(),
                new Minus(),
                new Minus(new Minus(), new Addition()),
                new Addition(new Minus(), new Addition())
            };
            return equations[new Random().nextInt(equations.length)];
        };
    }

    /**
     * 简单加乘除法生成器
     * @return
     */
    private Supplier<Equation> simpleMultiAndDivEquationSupplier() {
        return () -> {
            Equation[] equations = new Equation[]{
                    new Multi(),
                    new Division(),
                    new Multi(new Minus(), new Addition()),
                    new Division(new Minus(), new Addition())
            };
            return equations[new Random().nextInt(equations.length)];
        };
    }

    private List<EquationRecord> generateEquation(Long batchId, int count, Supplier<Equation> generator) {
        List<EquationRecord> equations = new ArrayList<>();
        for (int i = 0; i< count; i++) {
            Equation equation = generator.get();
            equation.randomMask();
            String text = equation.writeAsText();
            EquationRecord record = new EquationRecord();
            record.setPractiseId(batchId);
            record.setText(text);
            record.setFinalResult(equation.getMaskedValue());
            List<String> deriveTextList = equation.derive().getDeriveEquationTextList();
            deriveTextList.add(0, text);
            record.setDeriveText(String.join(",", deriveTextList));
            equationRecordService.add(record);
            equations.add(record);
        }
        return equations;
    }


}
