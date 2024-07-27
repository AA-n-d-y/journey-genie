package com.genie.journey_genie.controllers;

import com.genie.journey_genie.models.Budget;
import com.genie.journey_genie.models.User;
import com.genie.journey_genie.models.BudgetRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Sort;

import java.util.List;

@Controller
public class BudgetController {

    @Value("${OPENCURRENCY_API_KEY}")
    private String openCurrencyApiKey;

    @Autowired
    private BudgetRepository budgetRepository;

    private boolean isUserLoggedIn(HttpSession session) {
        User user = (User) session.getAttribute("sessionUser");
        return user != null;
    }

    @GetMapping("/track-budget")
    public String showBudgetPage(Model model, HttpSession session) {
        if (!isUserLoggedIn(session)) {
            return "redirect:/login";
        }
        model.addAttribute("OPENCURRENCY_API_KEY", openCurrencyApiKey);
        return "track-budget";
    }

    @RestController
    @RequestMapping("/budgets")
    public class BudgetRestController {

        @GetMapping
        public List<Budget> getAllBudgets() {
            return budgetRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
        }

        @PostMapping
        public Budget createBudget(@RequestBody Budget budget) {
            return budgetRepository.save(budget);
        }

        @PutMapping("/{id}")
        public Budget updateBudget(@PathVariable Long id, @RequestBody Budget updatedBudget) {
            return budgetRepository.findById(id)
                    .map(budget -> {
                        budget.setItem(updatedBudget.getItem());
                        budget.setAmount(updatedBudget.getAmount());
                        budget.setFromCurrency(updatedBudget.getFromCurrency());
                        budget.setToCurrency(updatedBudget.getToCurrency());
                        budget.setConvertedAmount(updatedBudget.getConvertedAmount());
                        return budgetRepository.save(budget);
                    })
                    .orElseGet(() -> {
                        updatedBudget.setId(id);
                        return budgetRepository.save(updatedBudget);
                    });
        }

        @DeleteMapping("/{id}")
        public void deleteBudget(@PathVariable Long id) {
            budgetRepository.deleteById(id);
        }
    }
}
