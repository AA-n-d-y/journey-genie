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

    private User getLoggedInUser(HttpSession session) {
        return (User) session.getAttribute("sessionUser");
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
        public List<Budget> getAllBudgets(HttpSession session) {
            User user = getLoggedInUser(session);
            return budgetRepository.findByUser(user, Sort.by(Sort.Direction.ASC, "id"));
        }

        @PostMapping
        public Budget createBudget(@RequestBody Budget budget, HttpSession session) {
            User user = getLoggedInUser(session);
            budget.setUser(user);
            return budgetRepository.save(budget);
        }

        @PutMapping("/{id}")
        public Budget updateBudget(@PathVariable Long id, @RequestBody Budget updatedBudget, HttpSession session) {
            User user = getLoggedInUser(session);
            return budgetRepository.findById(id)
                    .map(budget -> {
                        // if (!budget.getUser().equals(user)) {
                        //     throw new SecurityException("You are not authorized to update this budget.");
                        // }
                        budget.setItem(updatedBudget.getItem());
                        budget.setAmount(updatedBudget.getAmount());
                        budget.setFromCurrency(updatedBudget.getFromCurrency());
                        budget.setToCurrency(updatedBudget.getToCurrency());
                        budget.setConvertedAmount(updatedBudget.getConvertedAmount());
                        return budgetRepository.save(budget);
                    })
                    .orElseGet(() -> {
                        updatedBudget.setId(id);
                        updatedBudget.setUser(user);
                        return budgetRepository.save(updatedBudget);
                    });
        }

        @DeleteMapping("/{id}")
        public void deleteBudget(@PathVariable Long id, HttpSession session) {
            User user = getLoggedInUser(session);
            Budget budget = budgetRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid budget ID"));
            // if (!budget.getUser().equals(user)) {
            //     throw new SecurityException("You are not authorized to delete this budget.");
            // }
            budgetRepository.deleteById(id);
        }
    }
}
