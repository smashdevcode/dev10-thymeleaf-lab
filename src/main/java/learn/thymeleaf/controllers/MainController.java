package learn.thymeleaf.controllers;

import learn.thymeleaf.domain.EncouragementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Controller
public class MainController {
    @Autowired
    EncouragementService encouragementService;

    @GetMapping("/")
    public String getHomeView() {
        return "home";
    }

    @GetMapping("/encouragement")
    public String getEncouragementView(Model model) {
        model.addAttribute("encouragement", encouragementService.getRandomEncouragement());
        return "encouragement";
    }

    @GetMapping("/personal/encouragement")
    public String getPersonalEncouragementView() {
        return "personal-encouragement";
    }

    @PostMapping("/personal/encouragement")
    public String getPersonalEncouragementView(String name, Model model) {
        model.addAttribute("name", name);
        model.addAttribute("encouragement", encouragementService.getRandomEncouragement());
        return "personal-encouragement";
    }

    @GetMapping("/tip")
    public String getTipView() {
        return "tip";
    }

    @PostMapping("/tip")
    public String getTipView(BigDecimal check, int percent, Model model) {
        model.addAttribute("check", check);
        model.addAttribute("percent", percent / 100.0);
        BigDecimal percentToMultiply = new BigDecimal(percent)
                .divide(new BigDecimal(100), 2, RoundingMode.HALF_UP);
        double tip = check.multiply(percentToMultiply).doubleValue();
        model.addAttribute("tip", tip);
        return "tip";
    }

    @GetMapping("/color")
    public String getColorView() {
        return "color";
    }

    @PostMapping("/color")
    public String getColorView(String color, Model model) {
        model.addAttribute("color", color);
        return "color";
    }

    @GetMapping("/math")
    public String getMathView(Model model) {
        model.addAttribute("operand1", 0);
        model.addAttribute("operation", "+");
        model.addAttribute("operand2", 0);
        model.addAttribute("result", 0.0);
        return "math";
    }

    @PostMapping("/math")
    public String getMathView(int operand1, String operation, int operand2, Model model) {
        model.addAttribute("operand1", operand1);
        model.addAttribute("operation", operation);
        model.addAttribute("operand2", operand2);

        double result = 0.0;

        switch (operation) {
            case "+":
                result = operand1 + operand2;
                break;
            case "-":
                result = operand1 - operand2;
                break;
            case "*":
                result = operand1 * operand2;
                break;
            case "/":
                result = (double)operand1 / (double)operand2;
                break;
        }

        model.addAttribute("result", result);

        return "math";
    }

    @GetMapping("/hockey")
    public String getHockeyView(Model model) {
        model.addAttribute("row", 8);
        model.addAttribute("col", 1);
        return "hockey";
    }

    @PostMapping("/hockey")
    public String getHockeyView(int row, int col, String move, Model model) {
        switch (move) {
            case "up":
                if ((row == 1 && (col == 2 || col == 3)) || (row > 1)) {
                    row--;
                }
                break;
            case "left":
                if (col > 0) {
                    col--;
                }
                break;
            case "right":
                if (col < 5) {
                    col++;
                }
                break;
            case "down":
                if (row < 8) {
                    row++;
                }
                break;
        }

        model.addAttribute("row", row);
        model.addAttribute("col", col);

        return "hockey";
    }
}
